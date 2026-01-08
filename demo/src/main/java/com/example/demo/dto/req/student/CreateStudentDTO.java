package com.example.demo.dto.req.student;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateStudentDTO {
    private String firstName;
    private String lastName;
    private String email;
    private UUID schoolId;
}
