package com.app.Kezos.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Kezos.Dto.AssignmentDto;
import com.app.Kezos.Dto.CourseDto;
import com.app.Kezos.model.Assignments;
import com.app.Kezos.model.CourseEntity;
import com.app.Kezos.repository.AssignmentRepository;
import com.app.Kezos.repository.CourseRepository;
import com.app.Kezos.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public List<CourseEntity> fetchAllCourses() {
        return courseRepository.findAll();
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
    public String removeCourse(String courseId) {
        String result="";
        if(courseRepository.existsByCourseId(courseId)){
            courseRepository.deleteByCourseId(courseId);
            result="Course removed sucessfully!";
        }else{
            result="Error!\nCourse with the id: "+courseId+" does not exist!";
        }
        return result;
    }

    @Override
    public String createAssignment(String courseId, List<AssignmentDto> assignmentsDtos) {
        String result="";
        if(courseRepository.existsByCourseId(courseId)){
            CourseEntity courseEntity = courseRepository.findByCourseId(courseId);
            List<Assignments> assignmentsList = assignmentsDtos.stream().map(assignmentDto -> {
                Assignments assignment = new Assignments();
                assignment.setName(assignmentDto.getTitle());
                assignment.setPoints(assignmentDto.getPoints());
                assignment.setDescription(assignmentDto.getDescription());
                assignment.setDeadLine(assignmentDto.getDeadline());
                assignment.setCourse(courseEntity);
                return assignment;
            }).collect(Collectors.toList());
    
            assignmentRepository.saveAll(assignmentsList);
            courseEntity.getAssignments().addAll(assignmentsList);
            courseRepository.save(courseEntity);
            result="Assignments added successfully!";
        }
        else{
            result="Error! Course with the Id: "+ courseId+" not found\nCreate course before accessing it!";
        }
        return result;
    }

    public List<Assignments> fetchCourseAssignments(String courseId){
        List<Assignments> assignments=new ArrayList<>();
        if(courseRepository.existsByCourseId(courseId)){
            CourseEntity course=fetchCourse(courseId);
            assignments=course.getAssignments();
        }
        return assignments;
    }

}
