package com.example.springboot2test.controller;

import com.example.springboot2test.entity.Teacher;
import com.example.springboot2test.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    //增加
    @PostMapping("/saveTeacher")
    public String save(@RequestBody Teacher teacher){
        Teacher teacher1=teacherService.saveTeacher(teacher);
        if (teacher1 != null)
            return "success";
        else
            return "error";
    }

    //删除
    @DeleteMapping("/deleteTeacher/{id}")
    public void deleteTeacher(@PathVariable Long id){
        teacherService.removeTeacher(id);
    }

    //更新/修改
    @PutMapping("/updateTeacher")
    public String updateTeacher(@RequestBody Teacher teacher){
        Teacher teacher1=teacherService.saveTeacher(teacher);
        if (teacher1 != null)
            return "success";
        else
            return "error";
    }

    //根据id查询
    @GetMapping("/findTeacherById/{id}")
    public Teacher findTeacherById(@PathVariable Long id){
        return teacherService.getTeacherById(id);
    }

    @GetMapping("/findAllTeacher")
    public List<Teacher> findAll() {
        return teacherService.listTeachers();
    }
}
