package com.example.demo.controller;

import com.example.demo.dto.req.auth.AuthenticationReqDTO;
import com.example.demo.dto.res.ApiResponse;
import com.example.demo.dto.res.auth.AuthenticationResponseDTO;
import com.example.demo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<AuthenticationResponseDTO> register(@RequestBody AuthenticationReqDTO req) {
        return ApiResponse.<AuthenticationResponseDTO>builder()
                .message("message.register_success")
                .data(authService.register(req))
                .status(HttpStatus.OK.value())
                .build() ;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<AuthenticationResponseDTO> login(@RequestBody AuthenticationReqDTO req) {
        return ApiResponse.<AuthenticationResponseDTO>builder()
                .message("message.login_success")
                .data(authService.signin(req))
                .status(HttpStatus.OK.value())
                .build();
    }
}
