package com.example.organization_service.dto.response;


import com.example.organization_service.entity.Employee;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponse {
    private Long id;
    private String departmentName;
    private List<Employee> employees;
}
