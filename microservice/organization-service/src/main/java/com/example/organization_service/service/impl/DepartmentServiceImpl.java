package com.example.organization_service.service.impl;

import com.example.organization_service.dto.request.CreateDepartmentDto;
import com.example.organization_service.dto.response.DepartmentResponse;
import com.example.organization_service.entity.Department;
import com.example.organization_service.repository.DepartmentRepo;
import com.example.organization_service.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepo departmentRepo;


    @Override
    public void createDepartment(CreateDepartmentDto dto) {
        Department dept = new Department();
        dept.setDepartmentName(dto.getDepartmentName());
        departmentRepo.save(dept);
    }

    @Override
    public List<DepartmentResponse> getDepartments() {
        return departmentRepo
                .findAll()
                .stream()
                .map(this::toDepartmentResponse)
                .toList();
    }

    private DepartmentResponse toDepartmentResponse(Department dept) {
        DepartmentResponse deptRes = new DepartmentResponse();
        deptRes.setDepartmentName(dept.getDepartmentName());
        deptRes.setId(dept.getId());
        return deptRes;
    }
}
