package com.app.Kezos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.Kezos.Dto.AssignmentDto;
import com.app.Kezos.model.Assignments;
import com.app.Kezos.service.Impl.AssignmentServiceImpl;

@RestController
public class AssignmentController {
    @Autowired
    private AssignmentServiceImpl assignmentService;
    private final String endpoint="";

    @GetMapping("kezo/v1/assignments")
    public ResponseEntity<List<Assignments>> courseAssignments(@RequestParam(value="courseId",required=false)String courseId ){
        return new ResponseEntity<>(assignmentService.fetchCourseAssignments(courseId),HttpStatus.OK);
    
    }

    @DeleteMapping("kezo/v1/assignments/{aId}")
    public ResponseEntity<String> dropAssignemnt(@PathVariable("aId") int id){
        String responseString=assignmentService.removeAssignment(id);
        HttpStatus status=responseString.contains("Error")?HttpStatus.BAD_REQUEST:HttpStatus.OK;
        return new ResponseEntity<>(responseString,status);
    }

    @PutMapping("kezo/v1/assignments/{aId}")
    public ResponseEntity<String> updateDeadline(@RequestBody AssignmentDto updatedDto){
        String responseString=assignmentService.updateDeadLine(updatedDto, 0);
        HttpStatus status=responseString.contains("Error")?HttpStatus.BAD_REQUEST:HttpStatus.OK;
        return new ResponseEntity<>(responseString,status);
    }


    @PostMapping("kezo/v1/assignments")
    public ResponseEntity<String> newAssignment(@RequestParam(value="courseId",required = false) String courseId, @RequestBody List<AssignmentDto> assignmentDto ){
        String result=assignmentService.createAssignment(courseId, assignmentDto);
        HttpStatus returnStatus= result.contains("Error")?HttpStatus.BAD_REQUEST:HttpStatus.OK;
        return new ResponseEntity<>( result, returnStatus);
    }

}
