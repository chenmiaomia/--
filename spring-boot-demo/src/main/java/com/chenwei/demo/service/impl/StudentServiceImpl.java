package com.chenwei.demo.service.impl;

import com.chenwei.demo.common.BusinessException;
import com.chenwei.demo.mapper.StudentMapper;
import com.chenwei.demo.model.Student;
import com.chenwei.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * CW
 * 2021/8/12 10:16 上午
 **/
@Service //在service内部可以执行所有的业务逻辑操作但不允许直接操作数据库
public class StudentServiceImpl implements StudentService {
    //    @Resource
    StudentMapper studentMapper; //操作数据库

    @Override
    public Student getStudent(String id) {
        if ("1".equals(id)) {
            return new Student().setName("张三").setId("1").setAge("18").setGender("男").setCreateTime(new Date());
        }
        throw new BusinessException("000001", "学生不存在");
    }
}
