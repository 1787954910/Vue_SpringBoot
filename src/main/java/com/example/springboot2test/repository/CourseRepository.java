package com.example.springboot2test.repository;

import com.example.springboot2test.entity.Courses;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Courses,Long> {
}
