package com.example.springboot2test.service;

import com.example.springboot2test.entity.Courses;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoursesServiceTest {

    @Autowired
    private CourseService courseService;

    @Test
    void saveCourse() {
        Courses course =new Courses();
        course.setCourse("springboot");
//        course.setTeacher_id();
        Courses courses1 =courseService.saveCourse(course);
        System.out.println(courses1);
    }

    @Test
    void removeCourse() {
        courseService.removeCourse(7L);
    }

    @Test
    void updateCourse() {
        Courses courseS =new Courses();
        courseS.setId(6L);
        courseS.setCourse("SpringBoot2");
        courseS.setTeacher_id(5L);
        Courses courses1 =courseService.updateCourse(courseS);
        System.out.println(courses1);
    }

    @Test
    void getCourseById() {

    }

    @Test
    void listCourses() {
    }
}