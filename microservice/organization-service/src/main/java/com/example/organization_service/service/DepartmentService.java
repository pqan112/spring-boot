package com.example.organization_service.service;

import com.example.organization_service.dto.request.CreateDepartmentDto;
import com.example.organization_service.dto.response.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    void createDepartment(CreateDepartmentDto dto);
    List<DepartmentResponse>  getDepartments();
}
