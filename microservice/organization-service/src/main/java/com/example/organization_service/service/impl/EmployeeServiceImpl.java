package com.example.organization_service.service.impl;

import com.example.organization_service.client.UserClient;
import com.example.organization_service.dto.request.CreateEmployeeDto;
import com.example.organization_service.dto.response.UserResponse;
import com.example.organization_service.entity.Department;
import com.example.organization_service.entity.Employee;
import com.example.organization_service.repository.DepartmentRepo;
import com.example.organization_service.repository.EmployeeRepo;
import com.example.organization_service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final UserClient userClient;
    private final EmployeeRepo employeeRepo;
    private final DepartmentRepo departmentRepo;
    @Override
    public void create(CreateEmployeeDto dto, JwtAuthenticationToken jwt) {

        UserResponse user = userClient.getUserByKeycloakId(jwt.getToken().getSubject());
        Department dept = departmentRepo.findById(dto.getDepartmentId()).orElseThrow(() -> new RuntimeException("Department not found"));

        Employee employee = new Employee();
        employee.setUserId(user.getId());
        employee.setName(dto.getName());
        employee.setDepartment(dept);

        employeeRepo.save(employee);

    }
}
