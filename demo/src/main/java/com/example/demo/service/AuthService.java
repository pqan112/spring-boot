package com.example.demo.service;

import com.example.demo.dto.req.auth.AuthenticationReqDTO;
import com.example.demo.dto.res.auth.AuthenticationResponseDTO;

public interface AuthService {
    AuthenticationResponseDTO register(AuthenticationReqDTO req);
    AuthenticationResponseDTO signin(AuthenticationReqDTO req);

}
