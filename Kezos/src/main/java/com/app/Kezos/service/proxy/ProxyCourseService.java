package com.app.Kezos.service.proxy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.Kezos.Dto.AssignmentDto;
import com.app.Kezos.Dto.CourseDto;
import com.app.Kezos.model.Assignments;
import com.app.Kezos.model.CourseEntity;
import com.app.Kezos.repository.AssignmentRepository;
import com.app.Kezos.repository.CourseRepository;
import com.app.Kezos.service.CourseService;

public class ProxyCourseService implements CourseService{

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public List<CourseEntity> fetchAllCourses() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fetchAllCourses'");
    }

    @Override
    public CourseEntity fetchCourse(String courseId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fetchCourse'");
    }

    @Override
    public String createCourse(CourseDto courseDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCourse'");
    }

    @Override
    public String removeCourse(String courseId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeCourse'");
    }

    @Override
    public String createAssignment(String courseId, List<AssignmentDto> assignmentsDtos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAssignment'");
    }

    @Override
    public List<Assignments> fetchCourseAssignments(String courseId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fetchCourseAssignments'");
    }
    
}
