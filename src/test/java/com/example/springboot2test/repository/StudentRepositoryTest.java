package com.example.springboot2test.repository;

import com.example.springboot2test.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void findAllStudents(){
        List<Student> students=studentRepository.findAll();
        assertNotNull(students);
    }

    @Test
    void findAll(){
        System.out.println(studentRepository.findAll());
    }

    @Test
    void save(){
        Student student = new Student();
        student.setName("张五");
        student.setGender("女");
        student.setPassword("123456");
        student.setUsername("stu4");
        student.setPhone("19989102558");
        Student student1 = studentRepository.save(student);//没有id为添加
        System.out.println(student1);
    }

    @Test
    void findById(){
        Student student = studentRepository.findById(1L).get();
        System.out.println(student);
    }

    @Test
    void updateStudent(){
        Student student = new Student();
        student.setId(1L);
        student.setName("张一");
        student.setGender("男");
        student.setPassword("123456");
        student.setUsername("stu1");
        student.setPhone("19989102559");
        Student student1 = studentRepository.save(student); //有id为修改
        System.out.println(student1);
    }

    @Test
    void deleteStudent(){
        studentRepository.deleteById(5L);
    }
}
