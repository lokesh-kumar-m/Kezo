package com.app.Kezos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Kezos.model.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity,Integer>{
    StudentEntity findByStudentId(String id);
}
