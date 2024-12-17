package com.app.Kezos.model;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class StudentEntity {
    @Id
    @GeneratedValue
    private int id;

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String studentId; // Unique student identifier

    private String phoneNumber;

    private String major; // Field of study
    private int year; // Academic year (e.g., 1st year, 2nd year, etc.)

    


}
