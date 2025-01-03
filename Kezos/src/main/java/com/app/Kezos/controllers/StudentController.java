package com.app.Kezos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.Kezos.model.StudentEntity;
import com.app.Kezos.service.Impl.StudentServiceImpl;

@RestController
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;
    private final String endpointV1="/v1/students";

    @GetMapping(endpointV1)
    public ResponseEntity<List<StudentEntity>> getAllStudents(@RequestParam(value="sId",required = false)String sId){
        return new ResponseEntity<>(studentService.,HttpStatus.OK);
    }

    @GetMapping(endpointV1+"/{studentId}")
    public ResponseEntity<StudentEntity> getCurrentStudent(@PathVariable("studentId") String studentId){
        return new ResponseEntity<>(studentService.fetchStudentDetails(studentId),HttpStatus.OK);
    }

    // @GetMapping("kezo/v1/student/")
}
