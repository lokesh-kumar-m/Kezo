package com.app.Kezos.service;

import java.util.List;

import com.app.Kezos.model.StudentEntity;

public interface IStudentService {
    List<StudentEntity> fetchOneOrMany(String sId);
    String submitAssignment(String studentId, int aId, String submission);
    String fetchStudentScore(String id);
}
