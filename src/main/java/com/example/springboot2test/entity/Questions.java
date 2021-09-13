package com.example.springboot2test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id自增
    private Long id;
    private String question;
    private String correct;
    private String pick1;
    private String pick2;
    private String pick3;
    private String pick4;
    private Long course_id;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "course_id",referencedColumnName = "id",insertable = false,updatable = false)
    @JsonIgnore
    private Courses courses;//所属课程
}
