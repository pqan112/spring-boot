package com.example.demo.service.impl;

import com.example.demo.entity.School;
import com.example.demo.repository.SchoolRepository;
import com.example.demo.service.SchoolService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    private SchoolRepository schoolRepository;

    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public School create(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public List<School> findAll() {
        return schoolRepository.findAll();
    }
}
