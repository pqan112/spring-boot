package com.example.demo.controller;

import com.example.demo.entity.School;
import com.example.demo.service.SchoolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schools")
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService)
    {
        this.schoolService = schoolService;
    }

    @PostMapping()
    public School create(@RequestBody School school) {
        return schoolService.create(school);
    }

    @GetMapping()
    public List<School> findAll() {
        return schoolService.findAll();
    }




}
