package com.app.Kezos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.Kezos.model.Assignments;

import jakarta.transaction.Transactional;

public interface AssignmentRepository extends JpaRepository<Assignments,Integer>{
    boolean existsById(int id);
    @Modifying
    @Transactional
    @Query("update Assignments a set a.deadLine=:updateddeadline where id=:id")
    String updateDeadLine(@Param(value="id")int id, @Param(value="updateddeadline") String updateddeadline);
}
