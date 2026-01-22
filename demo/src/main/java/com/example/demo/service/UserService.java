package com.example.demo.service;

import com.example.demo.dto.req.UserUpdateReqDTO;
import com.example.demo.dto.req.user;
import com.example.demo.dto.res.user.UserResponse;
import com.example.demo.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse create(user.UserReqDTO req);
    List<UserResponse> getUsers();
    UserResponse getUser(UUID userId);

    UserResponse updateUser(UUID userId, UserUpdateReqDTO req);
    void deleteUser(UUID userId);
}
