package com.example.springboot2test.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ForgetPsw {

    @Id
    private Long id;
    private String username;
    private String phone;
    private String code;
}
