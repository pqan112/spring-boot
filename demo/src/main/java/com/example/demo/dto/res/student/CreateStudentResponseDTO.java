package com.example.demo.dto.res.student;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateStudentResponseDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
}
