package com.example.demo.dto.req.auth;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationReqDTO {
    private String username;
    private String password;
}
