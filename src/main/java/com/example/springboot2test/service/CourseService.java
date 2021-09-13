package com.example.springboot2test.service;

import com.example.springboot2test.entity.Courses;

import java.util.List;

public interface CourseService {

    Courses saveCourse(Courses courseS);

    void removeCourse(Long id);

    Courses updateCourse(Courses courseS);

    Courses getCourseById(Long id);

    List<Courses>listCourses();

//    Page<Course> findPage(Integer pageNum, Integer pageSize,String name);

}
