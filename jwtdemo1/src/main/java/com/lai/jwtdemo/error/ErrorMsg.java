package com.lai.jwtdemo.error;

/**
 * @Author: bbdw
 * @Date: 2019/7/18 15:16
 * @Version 1.0
 */
public class ErrorMsg {
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public ErrorMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
