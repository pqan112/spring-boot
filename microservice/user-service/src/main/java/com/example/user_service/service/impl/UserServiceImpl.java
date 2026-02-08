package com.example.user_service.service.impl;

import com.example.user_service.dto.CreateUserDto;
import com.example.user_service.entity.User;
import com.example.user_service.repository.UserRepo;
import com.example.user_service.service.KeycloakService;
import com.example.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final KeycloakService keycloakService;

    @Override
    public User createUser(CreateUserDto dto) {
        String keycloakId = keycloakService.createUserAndAssignRole(dto);

        User user = User.builder()
                .keycloakId(keycloakId)
                .username(dto.getUsername())
                .email(dto.getEmail())
                .userType(dto.getUserType())
                .build();

        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }




}
