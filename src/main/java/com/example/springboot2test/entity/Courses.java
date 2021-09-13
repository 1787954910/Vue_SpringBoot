package com.example.springboot2test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Courses implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id自增
    private Long id;
    private String course;
    private Long teacher_id;
    @OneToOne(targetEntity = Teacher.class,cascade = CascadeType.DETACH)
    @JoinColumn(name = "teacher_id",referencedColumnName = "id",insertable = false,updatable = false)
    @JsonIgnore
    private Teacher teacher;

    @OneToMany(mappedBy = "courses")
    private List<Questions>questions;
}
