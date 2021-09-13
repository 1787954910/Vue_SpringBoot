package com.example.springboot2test.service;

import com.example.springboot2test.entity.Student;
import com.example.springboot2test.entity.respBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);

    void removeStudent(Long id);

    Student updateStudent(Student student);

    Student getStudentById(Long id);

    List<Student> listStudents();

}
