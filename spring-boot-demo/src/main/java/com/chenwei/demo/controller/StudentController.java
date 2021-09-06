package com.chenwei.demo.controller;

import com.chenwei.demo.model.JsonResult;
import com.chenwei.demo.model.Student;
import com.chenwei.demo.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * CW
 * 2021/8/12 10:06 上午
 **/
@RestController //controller一般情况下不允许写任何的逻辑运算操作！！！！ if else  数学运算   这些都应该在service里进行    前后端交互时返回类型时统一的
//@Controller   指明类内部的方法是返回一个页面而不是JSON     @ResponseBody  被这个标准的类或者方法的返回值会自动转换为JSON并返回给前端，content-type->application/json
@RequestMapping("/student")
public class StudentController {
    @Resource //这个注解标明的成员变量或方法的参数被   自动注入（来源于spring容器）
    StudentService studentService;

    @RequestMapping("/test")//是标明controller内部的方法的请求路径   类+方法   /student/test
    public JsonResult<Student> test(String id) {
        return JsonResult.success(studentService.getStudent(id));
    }
}
