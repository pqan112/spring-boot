package com.example.demo.controller;

import com.example.demo.dto.req.UserReqDTO;
import com.example.demo.dto.req.UserUpdateReqDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @PostMapping
    User createUser(@RequestBody UserReqDTO req) {
        return userService.create(req);
    }

    @GetMapping
    List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") UUID userId){
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    User updateUser(@PathVariable UUID userId, @RequestBody UserUpdateReqDTO req){
        return userService.updateUser(userId, req);
    }
}
