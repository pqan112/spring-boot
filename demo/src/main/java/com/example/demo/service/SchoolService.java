package com.example.demo.service;

import com.example.demo.entity.School;

import java.util.List;

public interface SchoolService {
    School create(School school);
    List<School> findAll();
}
