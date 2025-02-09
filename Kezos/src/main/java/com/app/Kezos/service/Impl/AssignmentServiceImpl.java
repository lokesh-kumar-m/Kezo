package com.app.Kezos.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.app.Kezos.Dto.AssignmentDto;
import com.app.Kezos.model.Assignments;
import com.app.Kezos.model.CourseEntity;
import com.app.Kezos.repository.AssignmentRepository;
import com.app.Kezos.service.IAssignmentService;
import com.app.Kezos.service.proxy.ProxyCourseService;


@Service
public class AssignmentServiceImpl  implements IAssignmentService{
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private ProxyCourseService proxyCourseService;

    @Override
    @CacheEvict(value = "assignments", key = "#assignmentId")
    public String removeAssignment(int id) {
        String result="";
        if(assignmentRepository.existsById(id))
        {
            assignmentRepository.deleteById(id);
            result="Deleted Succesfully!";
        }
        else{
            result="Error!\nAssignment with the id: "+id+" was not found.";
        }
        return result;
    }

    @Override
    @CachePut(value = "assignments", key = "#assignment.id")
    public String updateDeadLine(AssignmentDto assignmentDto, int id) {
        String result="";
        if(assignmentRepository.existsById(id)){
            assignmentRepository.updateDeadLine(id,assignmentDto.getDeadline());
            result="Updated Sucessfully!";
        }else{
            result="Error\nAssignment does not exists!";
        }
        return result;
    }
    @Cacheable(value = "assignments", key = "#aId")
    public Assignments fetchAssignments(int aId){
        System.out.println("from DB");
        return assignmentRepository.findById(aId).get();
    }

    public String createAssignment(String courseId, List<AssignmentDto> assignmentsDtos) {
        String result="";
        if(proxyCourseService.existingCourse(courseId)){
            CourseEntity courseEntity = proxyCourseService.fetchOneOrAll(courseId).get(0);
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
            result="Assignments added successfully!";
        }
        else{
            result="Error!\nCourse with the Id: "+ courseId+" not found\nCreate course before accessing it!";
        }
        return result;
    }
    
    public List<Assignments> fetchCourseAssignments(String courseId){
        List<Assignments> assignments=new ArrayList<>();
        if(courseId==null){
            assignments=assignmentRepository.findAll();
        }else{
            if(proxyCourseService.existingCourse(courseId)){
                CourseEntity course=proxyCourseService.fetchCourse(courseId);
                assignments=course.getAssignments();
            }
        }
        return assignments;
    }
}
