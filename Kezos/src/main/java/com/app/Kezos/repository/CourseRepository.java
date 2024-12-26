package com.app.Kezos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Kezos.model.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity,Integer> {
    boolean existsByCourseId(String id);
    CourseEntity findByCourseId(String id);
    void deleteByCourseId(String id);
}
