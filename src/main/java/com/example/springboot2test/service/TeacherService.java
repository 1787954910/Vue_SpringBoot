package com.example.springboot2test.service;


import com.example.springboot2test.entity.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher saveTeacher(Teacher teacher);

    void removeTeacher(Long id);

    Teacher updateTeacher(Teacher teacher);

    Teacher getTeacherById(Long id);

    List<Teacher> listTeachers();
}
