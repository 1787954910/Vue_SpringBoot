package com.example.springboot2test.controller;

import com.example.springboot2test.entity.Grades;
import com.example.springboot2test.service.GradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
//@PreAuthorize("hasAuthority('ROLE_ADMIN,ROLE_TEACHER')")
public class GradesController {

    @Autowired
    private GradesService gradesService;

    //增加
    @PostMapping("/saveGrades")
    public String save(@RequestBody Grades grades){
        Grades grades1=gradesService.saveGrades(grades);
        if (grades1 != null)
            return "success";
        else
            return "error";
    }

    //删除
    @DeleteMapping("/deleteGrades/{id}")
    public void deleteGrades(@PathVariable Long id){
        gradesService.removeGrades(id);
    }

    //更新/修改
    @PutMapping("/updateGrades")
    public String updateGrades(@RequestBody Grades grades){
        Grades grades1=gradesService.saveGrades(grades);
        if (grades1 != null)
            return "success";
        else
            return "error";
    }

    //根据id查询
    @GetMapping("/findGradesById/{id}")
    public Grades findGradesById(@PathVariable Long id){
        return gradesService.getGradesById(id);
    }

    @GetMapping("/findAllGrades")
    public List<Grades> findAll() {
        return gradesService.listGrades();
    }

}
