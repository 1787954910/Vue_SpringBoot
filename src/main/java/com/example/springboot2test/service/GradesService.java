package com.example.springboot2test.service;

import com.example.springboot2test.entity.Grades;

import java.util.List;

public interface GradesService {

    Grades saveGrades(Grades grades);

    void removeGrades(Long id);

    Grades updateGrades(Grades grades);

    Grades getGradesById(Long id);

    List<Grades> listGrades();
}
