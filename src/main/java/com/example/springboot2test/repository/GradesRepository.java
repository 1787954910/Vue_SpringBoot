package com.example.springboot2test.repository;

import com.example.springboot2test.entity.Grades;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradesRepository extends JpaRepository<Grades,Long> {
}
