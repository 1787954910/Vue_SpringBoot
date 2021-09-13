package com.example.springboot2test.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Login {

    @Id
    private Long id;
    private String username;
    private String password;

}
