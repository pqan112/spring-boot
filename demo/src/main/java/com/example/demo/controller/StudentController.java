package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
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
    public Student post(@RequestBody Student student) {
        return studentService.create(student);
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
