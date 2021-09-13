package com.example.springboot2test.repository;

import com.example.springboot2test.entity.Courses;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CoursesRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void findAllCourses(){
        List<Courses>courses=courseRepository.findAll();
        assertNotNull(courses);
    }

    @Test
    void findAll(){
        System.out.println(courseRepository.findAll());
    }

    @Test
    void save(){
        Courses courseS = new Courses();
        courseS.setCourse("web");
        Courses courses1 = courseRepository.save(courseS);//没有id为添加
        System.out.println(courses1);
    }

    @Test
    void findById(){
        Courses courseS = courseRepository.findById(1L).get();
        System.out.println(courseS);
    }

    @Test
    void updateCourse(){
        Courses courseS = new Courses();
        courseS.setId(5L);
        courseS.setCourse("WEB");
        Courses courses1 = courseRepository.save(courseS); //有id为修改
        System.out.println(courses1);
    }

    @Test
    void deleteCourse(){
        courseRepository.deleteById(4L);
    }
}
