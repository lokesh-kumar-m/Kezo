package com.app.Kezos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.app.Kezos.service.StudentService;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
}
