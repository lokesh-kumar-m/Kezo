package com.app.Kezos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.Kezos.Dto.AssignmentDto;
import com.app.Kezos.service.Impl.AssignmentServiceImpl;

@RestController
public class AssignmentController {
    @Autowired
    private AssignmentServiceImpl assignmentService;

    @DeleteMapping("kezo/v1/assignments/{aId}")
    public ResponseEntity<String> dropAssignemnt(@PathVariable("aId") int id){
        return new ResponseEntity<>(assignmentService.removeAssignment(id),HttpStatus.OK);
    }

    @PutMapping("kezo/v1/assignments/{aId}")
    public String updateDeadline(@RequestBody AssignmentDto updatedDto){
        
        return assignmentService.updateDeadLine(updatedDto, 0);
    }

}
