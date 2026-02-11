package com.example.organization_service.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDepartmentDto {
    private String departmentName;
}
