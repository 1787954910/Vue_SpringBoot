package com.example.springboot2test.repository;

import com.example.springboot2test.entity.Student;
import com.example.springboot2test.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    Teacher findByUsername(String username);
}
