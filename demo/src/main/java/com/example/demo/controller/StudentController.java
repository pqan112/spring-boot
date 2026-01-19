package com.example.demo.controller;

import com.example.demo.dto.req.UserReqDTO;
import com.example.demo.dto.req.student.CreateStudentDTO;
import com.example.demo.dto.res.ApiResponse;
import com.example.demo.dto.res.student.CreateStudentResponseDTO;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
     public ApiResponse<CreateStudentResponseDTO> create(@RequestBody @Valid CreateStudentDTO dto) {
        return ApiResponse.<CreateStudentResponseDTO>builder()
                .status(HttpStatus.CREATED.value())
                .message("message.student_create_success")
                .data(studentService.create(dto)).build();
    }

    @GetMapping
    public List<Student> findAll() {
        return  studentService.findAll();
    }

    @GetMapping("/{studentId}")
    public ApiResponse<Student> findById(@PathVariable UUID studentId) {
        return ApiResponse.<Student>builder()
                .status(HttpStatus.OK.value())
                .message("message.student_find_success")
                .data(studentService.getStudentById(studentId))
                .build();
    }

    @GetMapping("/search/{studentName}")
    public ApiResponse<List<Student>> findStudentsByName(@PathVariable String studentName) {
        return ApiResponse.<List<Student>>builder()
                .status(HttpStatus.OK.value())
                .message("message.student_deleted_success")
                .data(studentService.findStudentsByName(studentName)).build();
    }

    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> deleteById(@PathVariable UUID studentId) {
        studentService.deleteStudentById(studentId);
        return ApiResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .message("message.student_deleted_success")
                .data(null).build();
    }
}
