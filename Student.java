package com.chenwei.demo.model;

import lombok.Data;

import java.util.Date;

/**
 * CW
 * 2021/8/12 9:19 上午
 **/
@Data
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
