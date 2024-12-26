package com.app.Kezos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.Kezos.Dto.AssignmentDto;
import com.app.Kezos.service.CourseService;

@RestController
public class AssignmentController {
    @Autowired
    private CourseService courseService;

    @PostMapping("kezo/v1/course/{courseId}/addAssignment")
    public String newAssignment(@PathVariable("courseId") String courseId, @RequestBody List<AssignmentDto> assignmentDto ){
        return courseService.addAssignments(courseId, assignmentDto);
    }

    @DeleteMapping("kezo/v1/course/{courseId}/assignments/remove/{aId}")
    public String dropAssignemnt(@PathVariable("courseId") String courseId, @PathVariable("aId") int id){
        return courseService.removeAssignment(id);
    }
}
