package com.chenwei.demo.common;

import com.chenwei.demo.model.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * CW
 * 2021/8/12 2:40 下午
 **/
@RestControllerAdvice //AOP  全局异常处理， 处理所有带了@RestController注解的类的异常
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public JsonResult handle(Exception e) {
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            return JsonResult.error(businessException.getCode(), businessException.getMsg());
        }
        return JsonResult.error();
    }
}
