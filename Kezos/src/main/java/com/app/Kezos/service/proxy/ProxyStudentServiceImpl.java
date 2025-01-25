package com.app.Kezos.service.proxy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.app.Kezos.Dto.StudentDto;
import com.app.Kezos.model.Assignments;
import com.app.Kezos.model.CourseEntity;
import com.app.Kezos.model.StudentEntity;
import com.app.Kezos.service.IStudentService;
import com.app.Kezos.service.Impl.AssignmentServiceImpl;
import com.app.Kezos.service.Impl.StudentServiceImpl;

@Configuration
public class ProxyStudentServiceImpl implements IStudentService{

    private HashMap<String,StudentEntity> students=new HashMap<>();
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private ProxyCourseService courseProxy;
    @Autowired
    private AssignmentServiceImpl assignmentService;

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
        }else{
            if(students.containsKey(sId)){
                studentList.add(students.get(sId));
            }else{
                for(Map.Entry<String,StudentEntity> s:students.entrySet()){
                    studentList.add(s.getValue());
                } 
            }
        }
        return studentList;
    }


    @Override
    public String submitAssignment(String studentId, int aId, String submission) {
        StudentEntity student = students.get(studentId);
        Assignments assignment=assignmentService.fetchAssignments(aId);
        SimpleDateFormat ft= new SimpleDateFormat("dd-MM-yyyy"); 
        String current = ft.format(new Date()); 
        String result="";
        if(studentService.timeValidator(current, assignment.getDeadLine())){
            result="Assignment submitted successfully";
            if(studentService.answerValidator(assignment.getName(),submission)){
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
    public String fetchStudentScore(String id) {
        if (students.containsKey(id)) {
            return students.get(id).getScore()+"";
        }else{
            return "Error\nNo submissions found for the student.";
        }
    }
    public String registerStudent(StudentDto studentDto){
        String responseString="";
        String unuiqId=studentDto.getEnrollmentNumber().substring(4).toLowerCase();
        if(!students.containsKey(unuiqId)){
            responseString=studentService.registerStudent(studentDto);
            StudentEntity s=studentService.fetchOneOrMany(unuiqId).get(0);
            students.put(s.getEnrollmentNumber(), s);
        }else{
            responseString="Existing User";
        }
        return responseString;
    }

    public String registerCourse(String enrollmentId,String courseId){
        String resultString="";
        if (!students.containsKey(enrollmentId)) {
            resultString= "Error: Student not found in proxy cache!";
        }else{
            StudentEntity student = students.get(enrollmentId);
        CourseEntity course = courseProxy.fetchOneOrAll(courseId).get(0);
        if (course.getStudents().contains(student)) {
            resultString= "Error: Student already registered for the course!";
        }else{
            resultString  = studentService.registerCourse(courseId, enrollmentId);
            if (resultString.equals("registered to course")) {
                course.getStudents().add(student);
                student.getCourses().add(course);
                checkpoint(student);
                courseProxy.checkpoint(course);
            }
        }
        }
    
        return resultString;
    }

    public void checkpoint(StudentEntity student){
        studentService.checkpoint(student);
    }
    
}
