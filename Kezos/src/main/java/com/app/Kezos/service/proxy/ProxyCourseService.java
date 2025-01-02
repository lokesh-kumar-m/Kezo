package com.app.Kezos.service.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.app.Kezos.Dto.CourseDto;
import com.app.Kezos.model.CourseEntity;
import com.app.Kezos.service.ICourseService;
import com.app.Kezos.service.Impl.CourseServiceImpl;


@Configuration
public class ProxyCourseService implements ICourseService{

    @Autowired
    private CourseServiceImpl courseServiceImpl;
    private List<CourseEntity> allCourses=new ArrayList<>();
    private Logger logger=LoggerFactory.getLogger(getClass());

    public List<CourseEntity> fetchOneOrAll(String courseId){
        List<CourseEntity> responseList=new ArrayList<>();
        if(courseId==null){
            responseList=fetchAllCourses();
        }else{
            responseList.add(fetchCourse(courseId));
        }
        return responseList;
    }
    // public CourseEntity courseTest(String courseId){
    //     return courseServiceImpl.fetchCourse(courseId);
    // }
    @Override
    public List<CourseEntity> fetchAllCourses() {
        if(allCourses.size()==0){
            allCourses=courseServiceImpl.fetchAllCourses();
        }
        return allCourses;
    }

    @Override
    public CourseEntity fetchCourse(String courseId) {
        return findCourse(courseId);
    }

    @Override
    public String createCourse(CourseDto courseDto) {
        String result="";
        CourseEntity course=findCourse(courseDto.getCourseId());
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
        CourseEntity course=findCourse(courseId);
        if(course==null){
            result="Error\n course does not exist";
        }else{
            result=courseServiceImpl.removeCourse(courseId);
            allCourses.removeIf(c->c.getCourseId().equalsIgnoreCase(courseId));
        }
        return result;
    }

    @Override
    public boolean existingCourse(String id) {
       return courseServiceImpl.existingCourse(id);
    }

    private CourseEntity findCourse(String courseId){
        Predicate<? super CourseEntity> predicate=course->course.getCourseId().equalsIgnoreCase(courseId);
        return allCourses.stream().filter(predicate).findFirst().orElse(null);
    }
    
}
