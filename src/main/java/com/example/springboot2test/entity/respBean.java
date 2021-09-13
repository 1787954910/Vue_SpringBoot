package com.example.springboot2test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//公共返回对象
@Data
@NoArgsConstructor
@AllArgsConstructor
public class respBean {

    private int code;
    private String message;
    private Object obj;


    //成功返回结果
    public static respBean success(String message){
        return new respBean(200,message,null);
    }

    //成功返回结果
    public static respBean success(String message, Object obj){
        return new respBean(200,message,obj);
    }

    //失败返回结果
    public static respBean error(String message){
        return new respBean(500,message,null);
    }

    //失败返回结果
    public static respBean error(String message, Object obj){
        return new respBean(500,message,obj);
    }
}
