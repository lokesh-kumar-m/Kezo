package com.app.Kezos.service;

import com.app.Kezos.Dto.AssignmentDto;

public interface IAssignmentService {
    String removeAssignment(int id);
    String updateDeadLine(AssignmentDto assignmentDto,int id);
}
