package com.example.demo.mapper;

import com.example.demo.dto.req.student.CreateStudentDTO;
import com.example.demo.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "school.id", source = "schoolId")
    Student toEntity(CreateStudentDTO dto);
}
