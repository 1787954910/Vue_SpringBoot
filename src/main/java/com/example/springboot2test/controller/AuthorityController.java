package com.example.springboot2test.controller;

import com.example.springboot2test.entity.Authority;
import com.example.springboot2test.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping
@RestController
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    @GetMapping("/Authorities")
    public List<Authority> findAll(){
        return authorityService.listAuthority();
    }
}
