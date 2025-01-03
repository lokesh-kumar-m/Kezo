package com.app.Kezos.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.Kezos.Dto.CourseDto;
import com.app.Kezos.model.CourseEntity;
import com.app.Kezos.service.proxy.ProxyCourseService;

@RestController
public class CourseController {

    @Autowired
    private ProxyCourseService proxyService;
    // private Logger logger=LoggerFactory.getLogger(getClass());
    private final String endpointV1="/v1/courses";

    @GetMapping(endpointV1)
    public ResponseEntity<?> fetchCourse(@RequestParam(value="id",required=false) String courseId){
        ResponseEntity<?> response;
        List<CourseEntity> courses=proxyService.fetchOneOrAll(courseId);
        response=new ResponseEntity<>(courses,HttpStatus.OK);
        return response;
    }

    @PostMapping(endpointV1)
    public ResponseEntity<String> newCourse(@RequestBody CourseDto course){
        return new ResponseEntity<>(proxyService.createCourse(course),HttpStatus.OK);
    }
    
    @DeleteMapping(endpointV1+"/{courseId}")
    public ResponseEntity<String> removeCourse(@PathVariable("courseId") String courseId){
        String result=proxyService.removeCourse(courseId);
        HttpStatus status=result.contains("Error")?HttpStatus.BAD_REQUEST:HttpStatus.OK;
        return new ResponseEntity<>(result,status);
    }

    // @GetMapping("kezo/v1/courses/test/{courseId}")
    // public ResponseEntity<?> testingCourse(@PathVariable("courseId")String courseId){
    //     return new ResponseEntity<>(proxyService.courseTest(courseId),HttpStatus.OK);
    // }
}
