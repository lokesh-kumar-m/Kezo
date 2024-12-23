package com.app.Kezos.repository.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.Kezos.Dto.CourseDto;
import com.app.Kezos.model.Assignments;
import com.app.Kezos.model.CourseEntity;
import com.app.Kezos.repository.CourseRepository;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseEntity> fetchAllCourses() {
        return courseRepository.findAll();
    }

    public void removeCourse(int id) {
        courseRepository.deleteById(id);
    }

    public String addCourse(CourseDto courseDto) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseName(courseDto.getCourseName());
        courseEntity.setCourseId(courseDto.getCourseId());
        courseEntity.setCredits(courseDto.getCredits());
        courseEntity.setInstructor(courseDto.getInstructor());
        if (courseDto.getAssignments() != null) {
            List<Assignments> assignmentsList = courseDto.getAssignments().stream().map(assignmentDto -> {
                Assignments assignment = new Assignments();
                assignment.setName(assignmentDto.getTitle());
                assignment.setPoints(assignmentDto.getPoints());
                assignment.setDeadLine(assignmentDto.getDeadline());
                assignment.setCourse(courseEntity);
                return assignment;
            }).collect(Collectors.toList());
            courseEntity.setAssignments(assignmentsList);
        }

        // Initially set students to an empty list
        courseEntity.setStudents(new ArrayList<>());

        // Save courseEntity to the database
        courseRepository.save(courseEntity);

        return "Course added successfully!";
    }
}
