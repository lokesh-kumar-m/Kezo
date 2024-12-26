package com.app.Kezos.service;

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

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AssignmentRepository assignmentsRepository;


    public List<CourseEntity> fetchAllCourses() {
        return courseRepository.findAll();
    }

    public CourseEntity fetchCourse(String courseId){
        return courseRepository.findByCourseId(courseId);
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

        courseEntity.setStudents(new ArrayList<>());
        courseRepository.save(courseEntity);
        return "Course added successfully!";
    }

    public String addAssignments(String courseId, List<AssignmentDto> assignmentsDtoList) {
        String result="";
        if(courseRepository.existsByCourseId(courseId)){
            CourseEntity courseEntity = courseRepository.findByCourseId(courseId);
            List<Assignments> assignmentsList = assignmentsDtoList.stream().map(assignmentDto -> {
                Assignments assignment = new Assignments();
                assignment.setName(assignmentDto.getTitle());
                assignment.setPoints(assignmentDto.getPoints());
                assignment.setDescription(assignmentDto.getDescription());
                assignment.setDeadLine(assignmentDto.getDeadline());
                assignment.setCourse(courseEntity);
                return assignment;
            }).collect(Collectors.toList());
    
            assignmentsRepository.saveAll(assignmentsList);
            courseEntity.getAssignments().addAll(assignmentsList);
            courseRepository.save(courseEntity);
            result="Assignments added successfully!";
        }
        else{
            result="Course with the Id: "+ courseId+" not found\nCreate course before accessing it!";
        }
        return result;
    }
    public String removeAssignment(int id){
        String result="";
        if(assignmentsRepository.existsById(id))
        {
            assignmentsRepository.deleteById(id);
            result="Deleted Succesfully!";
        }
        else{
            result="Assignment with the id: "+id+" was not found.";
        }
        return result;
    }
}
