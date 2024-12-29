package com.app.Kezos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Kezos.model.StudentEntity;
import com.app.Kezos.service.Impl.StudentServiceImpl;

@RestController
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("kezo/v1/students")
    public ResponseEntity<List<StudentEntity>> getAllStudents(){
        return new ResponseEntity<>(studentService.fetchAllStudents(),HttpStatus.OK);
    }
}
