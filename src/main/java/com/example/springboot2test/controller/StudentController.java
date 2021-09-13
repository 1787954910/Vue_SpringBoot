package com.example.springboot2test.controller;

import com.example.springboot2test.entity.Student;
import com.example.springboot2test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
//@PreAuthorize("hasAuthority('ROLE_ADMIN,ROLE_TEACHER,ROLE_STUDENT')")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //增加
    @PostMapping("/saveStudent")
    public String save(@RequestBody Student student){
        Student student1=studentService.saveStudent(student);
        if (student1 != null)
            return "success";
        else
            return "error";
    }

    //删除
    @DeleteMapping("/deleteStudent/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.removeStudent(id);
    }

    //更新/修改
    @PutMapping("/updateStudent")
    public String updateStudent(@RequestBody Student student){
        Student student1=studentService.saveStudent(student);
        if (student1 != null)
            return "success";
        else
            return "error";
    }

    //根据id查询
    @GetMapping("/findStudentById/{id}")
    public Student findStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @GetMapping("/findAllStudent")
    public List<Student> findAll() {
        return studentService.listStudents();
    }

}
