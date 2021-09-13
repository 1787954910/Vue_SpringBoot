package com.example.springboot2test.service;

import com.example.springboot2test.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    void saveStudent() {
        Student student=new Student();
        student.setName("张三");
        student.setGender("女");
        student.setUsername("stu4");
        student.setPassword("123456");
        student.setPhone("19989902553");
        Student student1=studentService.saveStudent(student);
        System.out.println(student1);
    }

    @Test
    void removeStudent() {
        studentService.removeStudent(4L);
    }

    @Test
    void updateStudent() {
        Student student=new Student();
        student.setId(3L);
        student.setName("张四");
        student.setGender("女");
        student.setUsername("stu4");
        student.setPassword("123456");
        student.setPhone("19989102554");
        student.setAuthority_id(3L);
        Student student1=studentService.saveStudent(student);
        System.out.println(student1);
    }

    @Test
    void getStudentById() {
        Student student=studentService.getStudentById(1L);
        System.out.println(student);
    }

    @Test
    void listStudents() {
        List<Student>students=studentService.listStudents();
        System.out.println(students);
    }
}