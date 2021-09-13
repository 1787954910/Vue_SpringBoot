package com.example.springboot2test.service;

import com.example.springboot2test.entity.Questions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionsServiceTest {

    @Autowired
    private QuestionsService questionsService;

    @Test
    void saveQuestions() {
        Questions questions=new Questions();
        questions.setQuestion("11");
        questions.setPick1("11");
        questions.setPick2("11");
        questions.setPick3("11");
        questions.setPick4("11");
        questions.setCorrect("11");
        questions.setCourse_id(1L);
        Questions questions1=questionsService.saveQuestions(questions);
        System.out.println(questions1);
    }

    @Test
    void removeQuestions() {
    }

    @Test
    void updateQuestions() {
    }

    @Test
    void getQuestionsById() {
    }

    @Test
    void listQuestions() {
    }
}