package com.example.organization_service.controller;

import com.example.organization_service.dto.request.CreateDepartmentDto;
import com.example.organization_service.dto.response.DepartmentResponse;
import com.example.organization_service.dto.response.EmployeeResponse;
import com.example.organization_service.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateDepartmentDto dto) {
        departmentService.createDepartment(dto);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(null);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments() {
        return ResponseEntity.ok().body(departmentService.getDepartments());
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<List<EmployeeResponse>> getEmployeesByDepartmentId(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getEmployeesByDepartmentId(id));
    }
}
