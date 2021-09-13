package com.example.springboot2test.repository;

import com.example.springboot2test.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    void findAllTeacher(){
        List<Teacher> teachers=teacherRepository.findAll();
        System.out.println(teachers);
    }

    @Test
    void saveTeacher(){
        Teacher teacher = new Teacher();
        teacher.setName("梁老师");
        teacher.setGender("女");
        teacher.setPassword("654321");
        teacher.setUsername("tea4");
        teacher.setPhone("19989201551");
        Teacher teacher1 = teacherRepository.save(teacher);//没有id为添加
        System.out.println(teacher1);
    }

    @Test
    void deleteTeacher(){
        teacherRepository.deleteById(4L);
    }


    @Test
    void updateTeacher(){
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("李老师");
        teacher.setGender("女");
        teacher.setPassword("654321");
        teacher.setUsername("tea1");
        teacher.setPhone("19989201553");
        Teacher teacher1 = teacherRepository.save(teacher); //有id为修改
        System.out.println(teacher1);
    }

    @Test
    void findTeacherById(){
        Teacher teacher = teacherRepository.findById(1L).get();
        System.out.println(teacher);
    }
}
