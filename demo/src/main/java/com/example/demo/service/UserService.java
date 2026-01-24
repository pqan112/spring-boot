package com.example.demo.service;

import com.example.demo.dto.req.user.UserReqDTO;
import com.example.demo.dto.req.user.UserUpdateReqDTO;
import com.example.demo.dto.res.user.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse create(UserReqDTO req);
    List<UserResponse> getUsers();
    UserResponse getUser(UUID userId);
    UserResponse getMe();

    UserResponse updateUser(UUID userId, UserUpdateReqDTO req);
    void deleteUser(UUID userId);
}
