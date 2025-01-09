package com.app.Kezos.service;

import java.util.List;

import com.app.Kezos.Dto.CourseDto;
import com.app.Kezos.model.CourseEntity;

public interface ICourseService {
    boolean existingCourse(String id);
    List<CourseEntity> fetchAllCourses();
    CourseEntity fetchCourse(String courseId);
    String createCourse(CourseDto courseDto);
    String removeCourse(String courseId);
    void checkpoint(CourseEntity course);
    // String createAssignment(String courseId, List<AssignmentDto> assignmentsDtos);
    // List<Assignments> fetchCourseAssignments(String courseId);
}
