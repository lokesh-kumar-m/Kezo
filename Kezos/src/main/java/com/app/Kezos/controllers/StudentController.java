package com.app.Kezos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.Kezos.Dto.StudentDto;
import com.app.Kezos.model.StudentEntity;
import com.app.Kezos.service.Impl.StudentServiceImpl;
import com.app.Kezos.service.proxy.ProxyStudentServiceImpl;

@RestController
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private ProxyStudentServiceImpl proxyStudentServiceImpl;
    private final String endpointV1 = "/v1/students";


    @GetMapping(endpointV1)
    public ResponseEntity<List<StudentEntity>> getStudentsInfo(
        @RequestParam(value = "sId", required = false) String sId) {
        return new ResponseEntity<>(proxyStudentServiceImpl.fetchOneOrMany(sId), HttpStatus.OK);
    }
    @PostMapping(endpointV1)
    public ResponseEntity<String> createStudent(@RequestBody StudentDto studentDto){
        return new ResponseEntity<>(studentService.registerStudent(studentDto),HttpStatus.OK);
    }

    @PostMapping(endpointV1+"/register")
    public ResponseEntity<String> registerForCourse(@RequestParam(value = "sId",required = true)String enrollmentId,@RequestParam(value = "courseId",required = true)String courseId){
        return new ResponseEntity<>(studentService.registerCourse(courseId, enrollmentId),HttpStatus.OK);
    }

    @PostMapping(endpointV1+"/submission")
    public ResponseEntity<String> assignmentSubmission(@RequestParam(value = "aId", required = true) int aId,
            @RequestParam(value = "sId", required = true) String enrollmentId,
            @RequestParam(value = "Answer", required = true) String submission) {
                
        String responseString=studentService.submitAssignment(enrollmentId, aId, submission);
        return new ResponseEntity<>(responseString,HttpStatus.OK);
    }

    @GetMapping(endpointV1+"/score")
    public ResponseEntity<String> getScore(@RequestParam(value="sId")String enrollmentId){
        return new ResponseEntity<>(studentService.fetchStudentScore(enrollmentId),HttpStatus.OK);
    }


}
