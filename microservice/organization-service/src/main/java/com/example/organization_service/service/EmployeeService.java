package com.example.organization_service.service;

import com.example.organization_service.dto.request.CreateEmployeeDto;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public interface EmployeeService {
    void create (CreateEmployeeDto dto, JwtAuthenticationToken jwt);
}
