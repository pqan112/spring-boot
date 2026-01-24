package com.example.demo.controller;

import com.example.demo.dto.req.user.UserReqDTO;
import com.example.demo.dto.req.user.UserUpdateReqDTO;
import com.example.demo.dto.res.ApiResponse;
import com.example.demo.dto.res.user.UserResponse;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserReqDTO req) {
        return ApiResponse.<UserResponse>builder()
                .status(HttpStatus.CREATED.value())
                .message("message.user_create_success")
                .data(userService.create(req)).build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("username: {}", authentication.getName());
        authentication.getAuthorities().forEach(authority ->
                log.info("authority: {}", authority.getAuthority()));
        return ApiResponse.<List<UserResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(userService.getUsers())
                .message("message.get_users_success")
                .build();
    }

    @GetMapping("/me")
    ApiResponse<UserResponse> getMe(){
        return ApiResponse.<UserResponse>builder()
                .status(HttpStatus.OK.value())
                .data(userService.getMe())
                .message("message.get_me_success")
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") UUID userId){
        return ApiResponse.<UserResponse>builder()
                .status(HttpStatus.OK.value())
                .data(userService.getUser(userId))
                .message("message.get_users_success")
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable UUID userId, @RequestBody UserUpdateReqDTO req){
        return ApiResponse.<UserResponse>builder()
                .status(HttpStatus.OK.value())
                .data(userService.updateUser(userId, req))
                .message("message.get_users_success")
                .build();
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable UUID userId){
        userService.deleteUser(userId);
        return "User has been deleted";
    }
}
