package com.example.demo.service;

import com.example.demo.entity.Student;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    List<Student> findAll();
    Student create(Student student);
    Student getStudentById(UUID studentId);
    List<Student> findStudentsByName(String name);
}
