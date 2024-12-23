package com.app.Kezos.Dto;

import java.util.List;

public class CourseDto {
    private String courseName;
    private String courseId;
    private int credits;
    private String instructor;
    private List<AssignmentDto> assignments;
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    public int getCredits() {
        return credits;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }
    public String getInstructor() {
        return instructor;
    }
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    public List<AssignmentDto> getAssignments() {
        return assignments;
    }
    public void setAssignments(List<AssignmentDto> assignments) {
        this.assignments = assignments;
    }
}
