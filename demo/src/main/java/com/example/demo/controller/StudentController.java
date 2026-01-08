package com.example.demo.controller;

import com.example.demo.dto.req.student.CreateStudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(
            StudentService studentService
    ) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student post(@Valid @RequestBody CreateStudentDTO dto) {
        return studentService.create(dto);
    }

    @GetMapping
    public List<Student> findAll() {
        return  studentService.findAll();
    }

    @GetMapping("/{studentId}")
    public Student findById(@PathVariable UUID studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping("/search/{studentName}")
    public List<Student> findStudentsByName(@PathVariable String studentName) {
        return studentService.findStudentsByName(studentName);
    }
}
