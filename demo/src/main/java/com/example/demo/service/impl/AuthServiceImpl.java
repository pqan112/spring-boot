package com.example.demo.service.impl;

import com.example.demo.dto.req.auth.AuthenticationReqDTO;
import com.example.demo.dto.res.auth.AuthenticationResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.enums.Role;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.AuthenticationMapper;
import com.example.demo.repository.AuthRepository;
import com.example.demo.service.AuthService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.StringJoiner;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    @Value("${jwt.signerKey}")
    private String signerKey;
    private final AuthRepository authRepository;
    private final AuthenticationMapper authenticationMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthRepository authRepository, AuthenticationMapper authenticationMapper, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.authenticationMapper = authenticationMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public AuthenticationResponseDTO register(AuthenticationReqDTO req) {
        if (authRepository.existsByUsername(req.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = authenticationMapper.toUser(req);
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
//        user.setRoles(roles);
        User savedUser = authRepository.save(user);
        var token = generateAccessToken(savedUser);
        return AuthenticationResponseDTO.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponseDTO signin(AuthenticationReqDTO req) {
        var user = authRepository.findByUsername(req.getUsername()).orElseThrow(() ->

                new AppException(ErrorCode.USER_NOT_FOUND));
        boolean matched = passwordEncoder.matches(req.getPassword(), user.getPassword());
        if(!matched) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }
        var token = generateAccessToken(user);
        return AuthenticationResponseDTO.builder()
                .token(token)
                .build();
    }

    private String generateAccessToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("pqan.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("userId", user.getId())
                .claim("scope", buildScope(user))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);
        log.info("signerKey: {}", signerKey.getBytes());
        try {
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
            return jwsObject.serialize();
        } catch(JOSEException e) {
            log.info("JOSE Exception", e);
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
//        if(!CollectionUtils.isEmpty(user.getRoles())) {
//            user.getRoles().forEach(stringJoiner::add);
//        }

        return stringJoiner.toString();
    }
}
