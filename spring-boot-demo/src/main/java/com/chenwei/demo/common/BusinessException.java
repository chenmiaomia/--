package com.chenwei.demo.common;

import lombok.Data;

/**
 * CW
 * 2021/8/12 2:40 下午
 **/
@Data
public class BusinessException extends RuntimeException {
    private String code;
    private String msg;

    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
