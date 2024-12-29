package com.app.Kezos.model;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class StudentEntity {
    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String studentMail;
    private String studentId; 
    private String phoneNumber;
    private int score; 
    @ManyToMany
    @JoinTable(
        name = "student_courses",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<CourseEntity> courses;
    
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getStudentMail() {
        return studentMail;
    }
    public void setStudentMail(String email) {
        this.studentMail = email;
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int year) {
        this.score = year;
    }
    public List<CourseEntity> getCourses() {
        return courses;
    }
    public void setCourses(List<CourseEntity> courses) {
        this.courses = courses;
    }

}
