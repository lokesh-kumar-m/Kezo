package com.app.Kezos.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.Kezos.Dto.CourseDto;
import com.app.Kezos.model.CourseEntity;
import com.app.Kezos.repository.service.CourseService;

@RestController
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService=courseService;
    }
    
    @GetMapping("kezo/v1/courses")
    public List<CourseEntity> getMyCourses(){
        return courseService.fetchAllCourses();
    }
    @PostMapping("kezo/v1/course/add")
    public String newCourse(@RequestBody CourseDto course){
        return courseService.addCourse(course);
    }

}
