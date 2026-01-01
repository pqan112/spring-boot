package com.example.demo.service;

import com.example.demo.dto.req.UserReqDTO;
import com.example.demo.dto.req.UserUpdateReqDTO;
import com.example.demo.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User create(UserReqDTO req);
    List<User> getUsers();
    User getUser(UUID userId);

    User updateUser(UUID userId, UserUpdateReqDTO req);
}
