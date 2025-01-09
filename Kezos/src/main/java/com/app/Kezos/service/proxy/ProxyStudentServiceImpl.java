package com.app.Kezos.service.proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.Kezos.model.StudentEntity;
import com.app.Kezos.service.IStudentService;
import com.app.Kezos.service.Impl.StudentServiceImpl;

public class ProxyStudentServiceImpl implements IStudentService{

    private HashMap<String,StudentEntity> students=new HashMap<>();
    @Autowired
    private StudentServiceImpl studentService;

    @Override
    public List<StudentEntity> fetchOneOrMany(String sId) {
        List<StudentEntity> studentList=new ArrayList<>();
        if(students.size()==0){
            studentList=studentService.fetchOneOrMany(sId);
            for (StudentEntity studentElement : studentList) {
                if(studentElement!=null){
                    students.put(studentElement.getEnrollmentNumber(),studentElement);
                }
            }
        }
        
    }

    @Override
    public StudentEntity fetchStudentDetails(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fetchStudentDetails'");
    }

    @Override
    public String submitAssignment(String studentId, int aId, String submission) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'submitAssignment'");
    }

    @Override
    public String fetchStudentScore(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fetchStudentScore'");
    }

    
}
