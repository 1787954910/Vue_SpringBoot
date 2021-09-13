package com.example.springboot2test.repository;

import com.example.springboot2test.entity.Admin;
import com.example.springboot2test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long>{

    Admin findByUsername(String username);
}
