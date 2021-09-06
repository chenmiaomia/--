package com.chenwei.demo.model;

import lombok.Data;

/**
 * CW
 * 2021/8/12 10:22 上午
 **/
@Data
public class JsonResult<T> {
    private static final String SUCCESS_CODE = "000000";
    private static final String SUCCESS_MSG = "操作成功";
    private static final String ERROR_MSG = "操作失败";
    private static final String ERROR_CODE = "999999";
    private String msg;
    private String code;
    private T data;

    public JsonResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public JsonResult<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public JsonResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> JsonResult<T> success(T data) {
        return new JsonResult<T>().setCode(SUCCESS_CODE).setMsg(SUCCESS_MSG).setData(data);
    }

    public static JsonResult error() {
        return new JsonResult().setCode(ERROR_CODE).setMsg(ERROR_MSG);
    }

    public static JsonResult error(String code, String msg) {
        return new JsonResult().setCode(code).setMsg(msg);
    }
}
