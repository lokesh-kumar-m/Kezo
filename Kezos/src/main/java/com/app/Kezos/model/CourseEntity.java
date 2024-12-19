package com.app.Kezos.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class CourseEntity {
    @Id
    @GeneratedValue
    private int id;
    private String courseName;
    private int credits;
    private String instructor;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Assignments> assignments;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<DeadLine> deadlines;

    @ManyToMany(mappedBy = "courses")
    private List<StudentEntity> students;

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public List<Assignments> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignments> assignments) {
        this.assignments = assignments;
    }

    public List<DeadLine> getDeadlines() {
        return deadlines;
    }

    public void setDeadlines(List<DeadLine> deadlines) {
        this.deadlines = deadlines;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StudentEntity> students) {
        this.students = students;
    }

    
}
