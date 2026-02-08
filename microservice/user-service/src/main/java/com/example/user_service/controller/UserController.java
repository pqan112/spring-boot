package com.example.user_service.controller;


import com.example.user_service.client.OrderClient;
import com.example.user_service.dto.*;
import com.example.user_service.entity.User;
import com.example.user_service.repository.UserRepo;
import com.example.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserRepo userRepo;
    private final OrderClient orderClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<User> createUser(@RequestBody CreateUserDto dto) {
        log.info("createUser");
        return ApiResponse.<User>builder()
                .success(true)
                .message("message.create_user_success")
                .status(HttpStatus.CREATED.value())
                .data(userService.createUser(dto))
                .build();
    }

    @GetMapping
    public ApiResponse<List<User>> getAllUsers() {
        log.info("getAllUsers");
        return ApiResponse.<List<User>>builder()
                .success(true)
                .message("message.get_users_success")
                .status(HttpStatus.OK.value())
                .data(userService.getAllUsers())
                .build();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("user not found"));
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    @GetMapping("/{id}/with-orders")
    public UserResponseWithOrders getUserWithOrders(@PathVariable("id") Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found"));

        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();

        List<OrderDto> orders = orderClient.getOrdersByUserId(id);

        return new UserResponseWithOrders(userDto.getId(), userDto.getUsername(), userDto.getEmail(), orders);
    }
}
