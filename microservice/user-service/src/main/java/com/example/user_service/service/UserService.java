package com.example.user_service.service;

import com.example.user_service.dto.CreateUserDto;
import com.example.user_service.entity.User;

import java.util.List;

public interface UserService {
    User createUser(CreateUserDto dto);
    List<User> getAllUsers();

}
