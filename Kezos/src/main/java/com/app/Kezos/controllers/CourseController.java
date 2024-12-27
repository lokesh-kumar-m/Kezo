package com.app.Kezos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.Kezos.Dto.AssignmentDto;
import com.app.Kezos.Dto.CourseDto;
import com.app.Kezos.model.Assignments;
import com.app.Kezos.model.CourseEntity;
import com.app.Kezos.service.Impl.CourseServiceImpl;

@RestController
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;
    
    @GetMapping("kezo/v1/courses")
    public ResponseEntity<List<CourseEntity>> getMyCourses(){
        return new ResponseEntity<List<CourseEntity>>(courseService.fetchAllCourses(),HttpStatus.OK);
    }
    @GetMapping("kezo/v1/courses/{courseId}")
    public ResponseEntity<CourseEntity> fetchCourse(@PathVariable("courseId")String courseId){
        return new ResponseEntity<CourseEntity>(courseService.fetchCourse(courseId),HttpStatus.OK);
    }

    @PostMapping("kezo/v1/courses/add")
    public ResponseEntity<String> newCourse(@RequestBody CourseDto course){
        return new ResponseEntity<>(courseService.createCourse(course),HttpStatus.OK);
    }
    @DeleteMapping("kezo/v1/courses/{courseId}")
    public ResponseEntity<String> removeCourse(@PathVariable("courseId") String courseId){
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @GetMapping("kezo/v1/courses/{courseId}/assignments")
    public ResponseEntity<List<Assignments>> courseAssignments(@PathVariable("courseId")String courseId ){
        return new ResponseEntity<>(courseService.fetchCourseAssignments(courseId),HttpStatus.OK);
    }

    @PostMapping("kezo/v1/courses/{courseId}/assignments")
    public ResponseEntity<String> newAssignment(@PathVariable("courseId") String courseId, @RequestBody List<AssignmentDto> assignmentDto ){
        String result=courseService.createAssignment(courseId, assignmentDto);
        HttpStatus returnStatus= result.contains("Error")?HttpStatus.BAD_REQUEST:HttpStatus.OK;
        return new ResponseEntity<>( result, returnStatus);
    }
}
