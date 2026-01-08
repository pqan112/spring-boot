package com.example.demo.service.impl;

import com.example.demo.dto.req.student.CreateStudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(
            StudentRepository studentRepository,
            StudentMapper studentMapper
    ) {

        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student create(CreateStudentDTO dto) {
        Student student  = studentMapper.toEntity(dto);
        return studentRepository.save(student);
    }

//    private Student toEntity(CreateStudentDTO dto) {
//        Student student = new Student();
//        student.setEmail(dto.getEmail());
//        student.setFirstName(dto.getFirstName());
//        student.setLastName(dto.getLastName());
//
//        School school = new School();
//        school.setId(dto.getSchoolId());
//        student.setSchool(school);
//        return student;
//    }

    @Override
    public Student getStudentById(UUID studentId) {
        return findById(studentId);
    }

    @Override
    public List<Student> findStudentsByName(String name) {
        return studentRepository.findAllByFirstNameContaining(name);
    }


    Student findById(UUID studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }


}
