package com.example.springboot2test.controller;

import com.example.springboot2test.entity.*;
import com.example.springboot2test.service.AdminServiceImpl;
import com.example.springboot2test.service.StudentServiceImpl;
import com.example.springboot2test.service.TeacherServiceImpl;
import com.example.springboot2test.util.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
@Slf4j
public class LoginController {

    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private TeacherServiceImpl teacherService;
    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Login login) {

        System.out.println(login.getUsername());
        System.out.println(login.getPassword());

        Map<String, Object> map = new HashMap<>();
        try {
            Admin admin = (Admin) adminService.loadUserByUsername(login.getUsername());
            Teacher teacher = (Teacher) teacherService.loadUserByUsername(login.getUsername());
            Student student = (Student) studentService.loadUserByUsername(login.getUsername());
            if (admin != null) {
                if (!login.getPassword().equals(admin.getPassword())) {
                    map.put("msg", "密码错误！");
                } else {
                    Map<String, String> payload = new HashMap<>();
                    payload.put("name", admin.getName());
                    //生产JWT的令牌
                    String token = JWTUtils.getToken(payload);
                    map.put("state", true);
                    map.put("msg", "认证成功！");
                    map.put("authorities",admin.getAuthorities());
                    map.put("user",admin);
                    map.put("token", token);
                }
            }
            else if (teacher != null) {
                if (!login.getPassword().equals(teacher.getPassword())) {
                    map.put("msg", "密码错误！");
                } else {
                    Map<String, String> payload = new HashMap<>();
                    payload.put("name", teacher.getName());
                    //生产JWT的令牌
                    String token = JWTUtils.getToken(payload);
                    map.put("state", true);
                    map.put("msg", "认证成功！");
                    map.put("authorities",teacher.getAuthorities());
                    map.put("user",teacher);
                    map.put("token", token);
                }
            }
            else if (student != null){
                if (!login.getPassword().equals(student.getPassword())) {
                    map.put("msg", "密码错误！");
                } else {
                    Map<String, String> payload = new HashMap<>();
                    payload.put("username", student.getUsername());
                    payload.put("password",student.getPassword());
                    //生产JWT令牌
                    String token = JWTUtils.getToken(payload);
                    map.put("state", true);
                    map.put("msg", "认证成功！");
                    map.put("authorities",student.getAuthorities());
                    map.put("user",student);
                    map.put("token", token);
                    ResponseEntity.ok(token);
                }
            }
        else {
                map.put("state", false);
                map.put("msg", "用户不存在！");
            }
        }
        catch (Exception e) {
            map.put("state", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }
}
