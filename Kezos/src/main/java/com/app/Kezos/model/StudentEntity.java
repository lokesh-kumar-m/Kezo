package com.app.Kezos.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    private String eMail;
    private String enrollmentNumber; 
    private String contactNumber;
    private int score; 
    @ManyToMany
    @JoinTable(
        name = "student_courses",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonManagedReference
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
    public String geteMail() {
        return eMail;
    }
    public void seteMail(String email) {
        this.eMail = email;
    }
    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }
    public void setEnrollmentNumber(String studentId) {
        this.enrollmentNumber = studentId;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String phoneNumber) {
        this.contactNumber = phoneNumber;
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
