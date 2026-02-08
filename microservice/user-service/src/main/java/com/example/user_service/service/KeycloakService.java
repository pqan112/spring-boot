package com.example.user_service.service;

import com.example.user_service.dto.CreateUserDto;

public interface KeycloakService {
    String createUserAndAssignRole(CreateUserDto dto);
}

