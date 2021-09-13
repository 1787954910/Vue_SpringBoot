package com.example.springboot2test.service;

import com.example.springboot2test.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherServiceTest {
    @Autowired
    private TeacherService teacherService;

    @Test
    void saveTeacher() {
    }

    @Test
    void removeTeacher() {
    }

    @Test
    void updateTeacher() {
        Teacher teacher=new Teacher();
        teacher.setId(6L);
        teacher.setPhone("19989102666");
        teacher.setName("1111");
        teacher.setGender("男");
        teacher.setUsername("1111");
        teacher.setPassword("111111");
        teacher.setAuthority_id(2L);
        teacherService.updateTeacher(teacher);
    }

    @Test
    void getTeacherById() {
    }

    @Test
    void listTeachers() {
    }
}