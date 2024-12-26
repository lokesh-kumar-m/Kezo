package com.app.Kezos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Assignments {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private String deadLine;
    private int points;
    @ManyToOne
    @JsonBackReference
    private CourseEntity course;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String date) {
        this.deadLine = date;
    }

        
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}