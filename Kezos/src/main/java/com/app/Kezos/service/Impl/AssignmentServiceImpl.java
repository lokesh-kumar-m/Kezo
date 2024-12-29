package com.app.Kezos.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Kezos.Dto.AssignmentDto;
import com.app.Kezos.model.Assignments;
import com.app.Kezos.repository.AssignmentRepository;
import com.app.Kezos.service.IAssignmentService;

@Service
public class AssignmentServiceImpl  implements IAssignmentService{
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
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
    
    public Assignments fetchAssignments(int aId){
        return assignmentRepository.findById(aId).get();
    }
}
