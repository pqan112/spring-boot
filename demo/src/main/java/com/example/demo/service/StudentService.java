package com.example.demo.service;

import com.example.demo.dto.req.student.CreateStudentDTO;
import com.example.demo.entity.Student;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    List<Student> findAll();
    Student create(CreateStudentDTO dto);
    Student getStudentById(UUID studentId);
    List<Student> findStudentsByName(String name);
}
