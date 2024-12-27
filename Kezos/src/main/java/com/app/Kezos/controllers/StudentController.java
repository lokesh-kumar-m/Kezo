package com.app.Kezos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.app.Kezos.service.Impl.StudentServiceImpl;

@RestController
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;
}
