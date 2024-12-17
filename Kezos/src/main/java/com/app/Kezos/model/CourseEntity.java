package com.app.Kezos.model;

import java.util.HashMap;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;

@Entity
public class CourseEntity {
    @Id
    @GeneratedValue
    private int id;
    private String courseName;
    @ElementCollection
    @CollectionTable(name = "course_assignments", joinColumns = @JoinColumn(name = "course_id"))
    @MapKeyColumn(name = "assignment_id")
    @Column(name = "assignment_name")
    private HashMap<Integer,String> assignment;
    private int credits;
    private String instructor;
    private HashMap<Integer,String> deadlines;

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public HashMap<Integer, String> getAssignment() {
        return assignment;
    }
    public void setAssignment(HashMap<Integer, String> assignment) {
        this.assignment = assignment;
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
    public HashMap<Integer, String> getDeadlines() {
        return deadlines;
    }
    public void setDeadlines(HashMap<Integer, String> deadlines) {
        this.deadlines = deadlines;
    }
}
