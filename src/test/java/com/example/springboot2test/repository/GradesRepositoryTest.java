package com.example.springboot2test.repository;

import com.example.springboot2test.entity.Grades;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GradesRepositoryTest {

    @Autowired
    private GradesRepository gradesRepository;

    @Test
    void findAllGrades(){
        List<Grades> grades=gradesRepository.findAll();
        System.out.println(grades);
    }

//    @Test
//    void saveGrades(){
//        Grades grades = new Grades();
//        grades.setName("赵六");
//        grades.setCourse("操作系统");
//        grades.setGrades("77");
//        Grades grades1 = gradesRepository.save(grades);//没有id为添加
//        System.out.println(grades1);
//    }
//
//    @Test
//    void deleteGrades(){
//        gradesRepository.deleteById(3L);
//    }
//
//
//    @Test
//    void updateGrades(){
//        Grades grades = new Grades();
//        grades.setId(4L);
//        grades.setName("赵六");
//        grades.setCourse("操作系统");
//        grades.setGrades("75");
//        Grades grades1 = gradesRepository.save(grades); //有id为修改
//        System.out.println(grades1);
//    }
//
//    @Test
//    void findGradesById(){
//        Grades grades = gradesRepository.findById(1L).get();
//        System.out.println(grades);
//    }
}
