package com.app.Kezos.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Kezos.repository.StudentRepository;

@Service
public class StudentServiceImpl {
    @Autowired
    private StudentRepository studentRepository;

    
}
