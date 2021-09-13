package com.example.springboot2test.controller;

import com.example.springboot2test.entity.Courses;
import com.example.springboot2test.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/course")
//@PreAuthorize("hasAuthority('ROLE_ADMIN,ROLE_TEACHER')")
public class CourseController {

    @Autowired
    private CourseService courseService;

    //增加
    @PostMapping("/saveCourse")
    public String save(@RequestBody Courses courses){
        Courses courses1 =courseService.saveCourse(courses);
        if (courses1 != null)
            return "success";
        else
            return "error";
    }

    //删除
    @DeleteMapping("/deleteCourse/{id}")
    public void deleteCourse(@PathVariable Long id){
        courseService.removeCourse(id);
    }

    //更新/修改
    @PutMapping("/updateCourse")
    public String updateCourse(@RequestBody Courses courses){
        Courses courses1 =courseService.saveCourse(courses);
        if (courses1 != null)
            return "success";
        else
            return "error";
    }

    //根据id查询
    @GetMapping("/findCourseById/{id}")
    public Courses findCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

    @GetMapping("/findAllCourse")
    public List<Courses> findAll() {
        return courseService.listCourses();
    }
}