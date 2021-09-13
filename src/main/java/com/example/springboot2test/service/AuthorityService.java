package com.example.springboot2test.service;

import com.example.springboot2test.entity.Authority;

import java.util.List;

public interface AuthorityService {

    Authority getAuthorityById(Long id);
    List<Authority> listAuthority();
}
