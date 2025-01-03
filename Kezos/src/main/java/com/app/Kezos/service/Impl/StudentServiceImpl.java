package com.app.Kezos.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
    private IEvaluator tEvaluator;

    public List<StudentEntity> fetchOneOrMany(String sId){
        List<StudentEntity> students=new ArrayList<>();
        if(sId==null){
            students=fetchAllStudents();
        }else{
            students.add(fetchStudentDetails(sId));
        }
        return students;
    }

    @Override
    public List<StudentEntity> fetchAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<CourseEntity> fetchMyCourses(String studentId) {
        StudentEntity student = studentRepository.findByEnrollmentNumber(studentId);
        if (student != null) {
            return student.getCourses();
        }
        else{
            return null;
        }
    }

    @Override
    public StudentEntity fetchStudentDetails(String studentId) {
        return studentRepository.findByEnrollmentNumber(studentId);
    }

    @Override
    public String submitAssignment(String studentId, int aId, String submission) {
        StudentEntity student = studentRepository.findByEnrollmentNumber(studentId);
        Assignments assignment=assignmentService.fetchAssignments(aId);
        HashMap<String,String> courseInfo=new HashMap<>();
        HashMap<String,String> studentInfo=new HashMap<>();
        SimpleDateFormat ft= new SimpleDateFormat("dd-MM-yyyy"); 
        String current = ft.format(new Date()); 
        studentInfo.put("date", current);
        courseInfo.put("date", assignment.getDeadLine());
        studentInfo.put("answer", submission);
        String result="";
        if(tEvaluator.validateAnswer(studentInfo, courseInfo)){
            result="Assignment submitted successfully";
            student.setScore(10);
        }else{
            result="Error\nAssignment Expired";
            student.setScore(0);
        }
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
    public String registerCourse(String courseId){
        CourseEntity course=proxyService.fetchCourse(courseId);
        course.getStudents().add(null);
    }
    
}