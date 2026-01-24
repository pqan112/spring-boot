package com.example.demo.service.impl;

import com.example.demo.dto.req.user.UserReqDTO;
import com.example.demo.dto.req.user.UserUpdateReqDTO;
import com.example.demo.dto.res.user.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.enums.Role;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserResponse create(UserReqDTO req) {
        if(userRepository.existsByUsername(req.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(req);

        user.setPassword(passwordEncoder.encode(req.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);

        User savedUser = userRepository.save(user);
        return userMapper.toResponseDTO(savedUser);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream().map(userMapper::toResponseDTO).toList();
    }

    @Override
    public UserResponse getUser(UUID userId) {
        return userMapper.toResponseDTO(getUserById(userId));
    }

    @Override
    public UserResponse getMe() {
        var context = SecurityContextHolder.getContext();
        String name =  context.getAuthentication().getName();
        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toResponseDTO(user);
    }

    @Override
    public UserResponse updateUser(UUID userId, UserUpdateReqDTO req) {
        User user = getUserById(userId);
        user.setDob(req.getDob());
        return userMapper.toResponseDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    public User getUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }


}
