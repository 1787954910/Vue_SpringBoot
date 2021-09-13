package com.example.springboot2test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Grades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id自增
    private Long id;
    private String grades;
    private Long course_id;
    private Long student_id;
    @Column(name = "update_time")
    //小写hh是12小时制,大写HH是24小时制
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ManyToOne(targetEntity = Courses.class,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "course_id",referencedColumnName = "ID",insertable = false,updatable = false)
//    @JsonIgnore
    private Courses courses;

    @ManyToOne(targetEntity = Student.class,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "student_id",referencedColumnName = "id",insertable = false,updatable = false)
//    @JsonIgnore
    private Student student;
}
