package com.example.springboot2test.service;

import com.example.springboot2test.entity.Authority;
import com.example.springboot2test.repository.AuthorityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class AuthorityServiceImpl implements AuthorityService{

    @Autowired
    private AuthorityRepository authorityRepository;

    public Authority getAuthorityById(Long id) {
        return authorityRepository.getOne(id);
    }

    @Override
    public List<Authority> listAuthority() {
        return authorityRepository.findAll();
    }


}
