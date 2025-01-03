package com.app.Kezos.service;

import java.util.List;

import com.app.Kezos.model.CourseEntity;
import com.app.Kezos.model.StudentEntity;

public interface IStudentService {
    List<StudentEntity> fetchAllStudents();
    List<CourseEntity> fetchMyCourses(String id);
    StudentEntity fetchStudentDetails(String id);
    String submitAssignment(String studentId, int aId, String submission);
    String fetchStudentScore(String id);
}
