package com.example.springboot2test.repository;

import com.example.springboot2test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student,Long> {

    Student findByUsername(String username);

}
