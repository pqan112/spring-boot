package com.example.user_service.service;

import com.example.user_service.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();

}
