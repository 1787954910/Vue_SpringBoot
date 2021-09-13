package com.example.springboot2test.repository;

import com.example.springboot2test.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Questions,Long> {
}
