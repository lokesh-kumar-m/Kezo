package com.app.Kezos.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Kezos.Dto.AssignmentDto;
import com.app.Kezos.Dto.CourseDto;
import com.app.Kezos.model.Assignments;
import com.app.Kezos.model.CourseEntity;
import com.app.Kezos.repository.AssignmentRepository;
import com.app.Kezos.repository.CourseRepository;
import com.app.Kezos.service.ICourseService;

import jakarta.transaction.Transactional;

@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    @Transactional
    public List<CourseEntity> fetchAllCourses() {
        List<CourseEntity> courses=courseRepository.findAll();
        courses.forEach(course -> Hibernate.initialize(course.getAssignments()));
        return courses;
    }

    @Override
    public CourseEntity fetchCourse(String courseId){
        return courseRepository.findByCourseId(courseId);
    }

    @Override
    public String createCourse(CourseDto courseDto) {
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

        courseEntity.setStudents(new ArrayList<>());
        courseRepository.save(courseEntity);
        return "Course added successfully!";
    }

    @Override
    @Transactional
    public String removeCourse(String courseId) {
        String result="";
        if(existingCourse(courseId)){
            courseRepository.deleteByCourseId(courseId);
            result="Course removed sucessfully!";
        }else{
            result="Error!\nCourse with the id: "+courseId+" does not exist!";
        }
        return result;
    }

    @Override
    public boolean existingCourse(String id) {
        return courseRepository.existsByCourseId(id);
    }

}
