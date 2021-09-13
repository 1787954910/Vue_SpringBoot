package com.example.springboot2test.service;

import com.example.springboot2test.entity.Grades;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GradesServiceTest {

    @Autowired
    private GradesService gradesService;

    @Test
    void saveGrades() {
        Grades grades=new Grades();
        grades.setGrades("90");
        grades.setStudent_id(1L);
        grades.setCourse_id(3L);
//        grades.setUpdateTime('2020-01-01 21:21:21');
        Grades grades1=gradesService.saveGrades(grades);
        System.out.println(grades1);
    }

    @Test
    void removeGrades() {
        gradesService.removeGrades(4L);
    }

    @Test
    void updateGrades() {
        Grades grades=new Grades();
        grades.setId(1L);
        grades.setStudent_id(1L);
        grades.setCourse_id(1L);
        grades.setGrades("88");
//        grades.setUpdateTime('2021-4-30 18:45:00');
        Grades grades1=gradesService.saveGrades(grades);
        System.out.println(grades1);
    }

    @Test
    void getGradesById() {
        Grades grades=gradesService.getGradesById(1L);
        System.out.println(grades);
    }

    @Test
    void listGrades() {
        List<Grades>grades= gradesService.listGrades();
        System.out.println(grades);
    }
}