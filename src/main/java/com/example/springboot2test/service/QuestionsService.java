package com.example.springboot2test.service;

import com.example.springboot2test.entity.Questions;

import java.util.List;

public interface QuestionsService {

    Questions saveQuestions(Questions questions);

    void removeQuestions(Long id);

    Questions updateQuestions(Questions questions);

    Questions getQuestionsById(Long id);

    List<Questions> listQuestions();
}
