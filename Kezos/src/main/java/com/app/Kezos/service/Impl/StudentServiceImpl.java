package com.app.Kezos.service.Impl;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.app.Kezos.Dto.StudentDto;
import com.app.Kezos.model.Assignments;
import com.app.Kezos.model.CourseEntity;
import com.app.Kezos.model.StudentEntity;
import com.app.Kezos.repository.StudentRepository;
import com.app.Kezos.service.IStudentService;
import com.app.Kezos.service.helper.IEvaluator;
import com.app.Kezos.service.proxy.ProxyCourseService;

@Service
public class StudentServiceImpl implements IStudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AssignmentServiceImpl assignmentService;
    @Autowired
    private ProxyCourseService proxyService;
    @Qualifier("answerValidator")
    private IEvaluator aEvaluator;
    @Qualifier("timeValidator")
    private IEvaluator tEvaluator;
    
    @Override
    public List<StudentEntity> fetchOneOrMany(String sId){
        List<StudentEntity> students=new ArrayList<>();
        if(sId==null){
            students=fetchAllStudents();
        }else{
            students.add(fetchStudentDetails(sId));
        }
        return students;
    }

    private List<StudentEntity> fetchAllStudents() {
        return studentRepository.findAll();
    }

    private StudentEntity fetchStudentDetails(String studentId) {
        return studentRepository.findByEnrollmentNumber(studentId);
    }

    public String registerStudent(StudentDto studentDto){
        String responseString="";
        String unuiqId=studentDto.getEnrollmentNumber().substring(4).toLowerCase();
        if(!studentRepository.existsByEnrollmentNumber(unuiqId)){
            StudentEntity student=new StudentEntity();
            student.setFirstName(studentDto.getFirstName());
            student.setLastName(studentDto.getLastName());
            student.setEnrollmentNumber(unuiqId);
            student.seteMail(studentDto.geteMail());
            student.setScore(0);
            student.setContactNumber(studentDto.getContactNumber());
            student.setCourses(new ArrayList<>());
            studentRepository.save(student);
            responseString="student registered !";
        }else{
            responseString="Error!\nStudent already registered";
        }
        return responseString;
    }

    public String registerCourse(String courseId,String enrollmentId){
        String resultString="";
        StudentEntity student=studentRepository.findByEnrollmentNumber(enrollmentId);
        CourseEntity course=proxyService.fetchCourse(courseId);
        List<StudentEntity> registeredStudents=course.getStudents();
        if(registeredStudents.contains(student)||student.getCourses().contains(course)){
            resultString="Error\nalready Registered!";
        }else{
            registeredStudents.add(student);
            proxyService.checkpoint(course);
            student.getCourses().add(course);
            studentRepository.save(student);
        }
        return resultString;
    }
    
    // public String removeStudent(){
        
    // }
    
    @Override
    public String submitAssignment(String studentId, int aId, String solution) {
        StudentEntity student = studentRepository.findByEnrollmentNumber(studentId);
        Assignments assignment=assignmentService.fetchAssignments(aId);
        SimpleDateFormat ft= new SimpleDateFormat("dd-MM-yyyy"); 
        String current = ft.format(new Date()); 
        String result="";
        if(timeValidator(current, assignment.getDeadLine())){
            result="Assignment submitted successfully";
            if(answerValidator(assignment.getName(),solution)){
                student.setScore(10);
            }else{
                student.setScore(6);
            }
        }else{
            result="Error\nAssignment Expired";
            student.setScore(0);
        }
        checkpoint(student);
        return result;
    }

    @Override
    public String fetchStudentScore(String studentId) {
        StudentEntity student = studentRepository.findByEnrollmentNumber(studentId);
        if (student==null) {
            return "Error\nNo submissions found for the student.";
        }
        return student.getScore()+"";
    }

    public boolean timeValidator(String current,String courseInfo){
        return tEvaluator.validateAnswer(current, courseInfo);
    }
    public boolean answerValidator(String name,String solution){
        return aEvaluator.validateAnswer(name, solution);
    }

    public void checkpoint(StudentEntity student){
        studentRepository.save(student);
    }
}