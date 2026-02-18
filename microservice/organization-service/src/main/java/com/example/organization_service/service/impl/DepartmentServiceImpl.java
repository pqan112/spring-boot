package com.example.organization_service.service.impl;

import com.example.organization_service.client.UserClient;
import com.example.organization_service.dto.request.CreateDepartmentDto;
import com.example.organization_service.dto.response.DepartmentResponse;
import com.example.organization_service.dto.response.EmployeeResponse;
import com.example.organization_service.dto.response.UserResponse;
import com.example.organization_service.entity.Department;
import com.example.organization_service.entity.Employee;
import com.example.organization_service.repository.DepartmentRepo;
import com.example.organization_service.repository.EmployeeRepo;
import com.example.organization_service.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepo departmentRepo;
    private final EmployeeRepo employeeRepo;
    private final UserClient userClient;

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

    @Override
    public List<EmployeeResponse> getEmployeesByDepartmentId(Long departmentId) {
        departmentRepo.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));

        List<Employee> employees = employeeRepo.findByDepartmentId(departmentId);

        return employees.stream()
                .map(this::toEmployeeResponse)
                .toList();
    }

    private DepartmentResponse toDepartmentResponse(Department dept) {
        return DepartmentResponse.builder()
                .id(dept.getId())
                .departmentName(dept.getDepartmentName())
                .build();
    }

    private EmployeeResponse toEmployeeResponse(Employee employee) {
        UserResponse user = userClient.getUserById(employee.getUserId());
        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .user(user)
                .build();
    }
}
