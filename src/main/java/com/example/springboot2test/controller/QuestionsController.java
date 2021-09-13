package com.example.springboot2test.controller;

import com.example.springboot2test.entity.Questions;
import com.example.springboot2test.entity.Student;
import com.example.springboot2test.repository.QuestionsRepository;
import com.example.springboot2test.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
//@PreAuthorize("hasAuthority('ROLE_ADMIN,ROLE_TEACHER')")
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;

    @GetMapping("/findAllQuestions")
    public List<Questions> findAll() {
        return questionsService.listQuestions();
    }

    //增加
    @PostMapping("/saveQuestions")
    public String save(@RequestBody Questions questions){
        Questions questions1=questionsService.saveQuestions(questions);
        if (questions1 != null)
            return "success";
        else
            return "error";
    }

    @DeleteMapping("/deleteQuestionsById/{id}")
    public void deleteById(@PathVariable("id") Long id){
        questionsService.removeQuestions(id);
    }

    @PutMapping("/updateQuestions")
    public String update(@RequestBody Questions questions){
        Questions questions1=questionsService.saveQuestions(questions);
        if (questions1 != null)
            return "success";
        else
            return "error";
    }

    @GetMapping("/findQuestionsById/{id}")
    public Questions findById(@PathVariable("id") Long id){
        return questionsService.getQuestionsById(id);
    }

}
