package com.example.springboot2test.controller;

import com.example.springboot2test.entity.Admin;
import com.example.springboot2test.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private AdminService adminService;

    //增加
    @PostMapping("/saveAdmin")
    public String save(@RequestBody Admin admin){
        Admin admin1=adminService.saveAdmin(admin);
        if (admin1 != null)
            return "success";
        else
            return "error";
    }

    //删除
    @DeleteMapping("/deleteAdmin/{id}")
    public void deleteAdmin(@PathVariable Long id){
        adminService.removeAdmin(id);
    }

    //更新/修改
    @PutMapping("/updateAdmin")
    public String updateAdmin(@RequestBody Admin admin){
        Admin admin1=adminService.saveAdmin(admin);
        if (admin1 != null)
            return "success";
        else
            return "error";
    }

    //根据id查询
    @GetMapping("/findAdminById/{id}")
    public Admin findAdminById(@PathVariable Long id){
        return adminService.getAdminById(id);
    }

    @GetMapping("/findAllAdmin")
    public List<Admin> findAll() {
        return adminService.listAdmins();
    }

}
