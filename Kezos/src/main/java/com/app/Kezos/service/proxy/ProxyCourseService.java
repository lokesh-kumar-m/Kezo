package com.app.Kezos.service.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.app.Kezos.Dto.AssignmentDto;
import com.app.Kezos.Dto.CourseDto;
import com.app.Kezos.model.Assignments;
import com.app.Kezos.model.CourseEntity;
import com.app.Kezos.service.ICourseService;
import com.app.Kezos.service.Impl.CourseServiceImpl;

import jakarta.transaction.Transactional;

@Configuration
public class ProxyCourseService implements ICourseService{

    @Autowired
    private CourseServiceImpl courseServiceImpl;

    private List<CourseEntity> allCourses=new ArrayList<>();

    @Override
    public List<CourseEntity> fetchAllCourses() {
        if(allCourses.size()==0){
            allCourses=courseServiceImpl.fetchAllCourses();
        }
        return allCourses;
    }

    @Override
    public CourseEntity fetchCourse(String courseId) {
        Predicate<? super CourseEntity> predicate=course->course.getCourseId().equalsIgnoreCase(courseId);
        return allCourses.stream().filter(predicate).findFirst().orElse(null);
    }

    @Override
    public String createCourse(CourseDto courseDto) {
        String result="";
        Predicate<? super CourseEntity> predicate=course->course.getCourseId().equalsIgnoreCase(courseDto.getCourseId());
        CourseEntity course=allCourses.stream().filter(predicate).findFirst().orElse(null);
        if(course==null){
            result=courseServiceImpl.createCourse(courseDto);
            course=courseServiceImpl.fetchCourse(courseDto.getCourseId());
            allCourses.add(course);
        }else{
            result="Error!\nCourse already exists";
        }
        return result;
    }

    @Override
    public String removeCourse(String courseId) {
        String result="";
        Predicate<? super CourseEntity> predicate=course->course.getCourseId().equalsIgnoreCase(courseId);
        CourseEntity course=allCourses.stream().filter(predicate).findFirst().orElse(null);
        if(course==null){
            result="Error\n course does not exist";
        }else{
            result=courseServiceImpl.removeCourse(courseId);
            allCourses.removeIf(predicate);
        }
        return result;
    }

    @Override
    @Transactional
    public String createAssignment(String courseId, List<AssignmentDto> assignmentsDtos) {
        String result="";
        Predicate<? super CourseEntity> predicate=course->course.getCourseId().equalsIgnoreCase(courseId);
        CourseEntity course=allCourses.stream().filter(predicate).findFirst().orElse(null);
        if(course!=null){
            result=courseServiceImpl.createAssignment(courseId, assignmentsDtos);
            allCourses=courseServiceImpl.fetchAllCourses();
            allCourses.forEach(c -> {Hibernate.initialize(c.getAssignments());
                Hibernate.initialize(c.getStudents());});
        }else{
            result="Error!\nCourse does not exist";
        }
        return result;
    }

    @Override
    public List<Assignments> fetchCourseAssignments(String courseId) {
        Predicate<? super CourseEntity> predicate=course->course.getCourseId().equalsIgnoreCase(courseId);
        CourseEntity course=allCourses.stream().filter(predicate).findFirst().orElse(null);
        return course.getAssignments();
    }
    
}
