package com.example.demo.service;

import com.example.demo.dto.req.student.CreateStudentDTO;
import com.example.demo.dto.res.student.CreateStudentResponseDTO;
import com.example.demo.entity.Student;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    List<Student> findAll();
    CreateStudentResponseDTO create(CreateStudentDTO dto);
    Student getStudentById(UUID studentId);
    List<Student> findStudentsByName(String name);
    void deleteStudentById(UUID studentId);
}
