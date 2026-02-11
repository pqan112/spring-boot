package com.example.organization_service.controller;


import com.example.organization_service.client.UserClient;
import com.example.organization_service.dto.request.CreateDepartmentDto;
import com.example.organization_service.dto.request.CreateEmployeeDto;
import com.example.organization_service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateEmployeeDto dto, JwtAuthenticationToken jwt) {
        employeeService.create(dto, jwt);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(null);
    }

}
