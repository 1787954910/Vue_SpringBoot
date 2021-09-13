package com.example.springboot2test.repository;

import com.example.springboot2test.entity.Questions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class QuestionsRepositoryTest {

    @Autowired
    private QuestionsRepository questionsRepository;

    @Test
    void findAllQuestions(){
        List<Questions>questions=questionsRepository.findAll();
        System.out.println(questions);
    }

    @Test
    void saveQuestions(){
        Questions questions = new Questions();
        questions.setQuestion("windows的目录结果采用的是（）");
        questions.setPick1("树形结构");
        questions.setPick2("线型结构");
        questions.setPick3("层次结构");
        questions.setPick4("网状结构");
        questions.setCorrect("树形结构");
        Questions questions1 = questionsRepository.save(questions);//没有id为添加
        System.out.println(questions1);
    }

    @Test
    void deleteQuestions(){
        questionsRepository.deleteById(3L);
    }


    @Test
    void updateQuestions(){
        Questions questions = new Questions();
        questions.setId(4L);
        questions.setQuestion("Windows的目录结果采用的是（）");
        questions.setPick1("树形结构");
        questions.setPick2("线型结构");
        questions.setPick3("层次结构");
        questions.setPick4("网状结构");
        questions.setCorrect("树形结构");
        Questions questions1 = questionsRepository.save(questions); //有id为修改
        System.out.println(questions1);
    }

    @Test
    void findQuestionsById(){
        Questions questions = questionsRepository.findById(1L).get();
        System.out.println(questions);
    }
}
