package com.app.Kezos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Kezos.model.Assignments;

public interface AssignmentRepository extends JpaRepository<Assignments,Integer>{
    
}
