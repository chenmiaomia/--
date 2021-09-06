package com.chenwei.demo.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * CW
 * 2021/8/12 10:02 上午
 **/
@Data//lombok提供的注解，直接帮我们生成bean类的所有get、set方法
@Slf4j //lombok提供的注解,直接帮我们注入log对象，实现日志打印
//@AllArgsConstructor //lombok提供的注解,直接生成全参构造函数
//@NoArgsConstructor //lombok提供的注解，帮我们生成无参构造函数
public class Student {
    private String id;
    private String name;
    private String age;
    private String gender;
    private Date createTime;

    public Student setId(String id) {
        this.id = id;
        return this;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public Student setAge(String age) {
        this.age = age;
        return this;
    }

    public Student setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Student setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
