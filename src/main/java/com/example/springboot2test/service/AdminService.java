package com.example.springboot2test.service;

import com.example.springboot2test.entity.Admin;

import java.util.List;

public interface AdminService {

    Admin saveAdmin(Admin admin);

    void removeAdmin(Long id);

    Admin updateAdmin(Admin admin);

    Admin getAdminById(Long id);

    List<Admin> listAdmins();
}
