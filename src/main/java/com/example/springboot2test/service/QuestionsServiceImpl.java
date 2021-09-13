package com.example.springboot2test.service;

import com.example.springboot2test.entity.Questions;
import com.example.springboot2test.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuestionsServiceImpl implements QuestionsService{

    @Autowired
    private QuestionsRepository questionsRepository;

    @Override
    public Questions saveQuestions(Questions questions) {
        return questionsRepository.save(questions);
    }

    @Override
    public void removeQuestions(Long id) {
        questionsRepository.deleteById(id);
    }

    @Override
    public Questions updateQuestions(Questions questions) {
        return questionsRepository.save(questions);
    }

    @Override
    public Questions getQuestionsById(Long id) {
        return questionsRepository.findById(id).orElse(null);
    }

    @Override
    public List<Questions> listQuestions() {
        return questionsRepository.findAll();
    }
}
