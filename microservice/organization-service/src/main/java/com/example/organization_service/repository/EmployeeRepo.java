package com.example.organization_service.repository;

import com.example.organization_service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    List<Employee> findByDepartmentId(Long departmentId);
}
