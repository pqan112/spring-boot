package com.example.organization_service.service;

import com.example.organization_service.dto.request.CreateDepartmentDto;
import com.example.organization_service.dto.response.DepartmentResponse;
import com.example.organization_service.dto.response.EmployeeResponse;

import java.util.List;

public interface DepartmentService {
    void createDepartment(CreateDepartmentDto dto);
    List<DepartmentResponse> getDepartments();
    List<EmployeeResponse> getEmployeesByDepartmentId(Long departmentId);
}
