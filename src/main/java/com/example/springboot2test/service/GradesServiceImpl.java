package com.example.springboot2test.service;

import com.example.springboot2test.entity.Grades;
import com.example.springboot2test.repository.GradesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GradesServiceImpl implements GradesService{

    @Autowired
    private GradesRepository gradesRepository;

    @Override
    public Grades saveGrades(Grades grades) {
        return gradesRepository.save(grades);
    }

    @Override
    public void removeGrades(Long id) {
        gradesRepository.deleteById(id);
    }

    @Override
    public Grades updateGrades(Grades grades) {
        return gradesRepository.save(grades);
    }

    @Override
    public Grades getGradesById(Long id) {
        return gradesRepository.findById(id).orElse(null);
    }

    @Override
    public List<Grades> listGrades() {
        return gradesRepository.findAll();
    }
}
