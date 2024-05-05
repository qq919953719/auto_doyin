package com.mcz.douyin.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class UseAccountBean {


    @JSONField(name = "msg")
    private String msg;
    @JSONField(name = "code")
    private Integer code;
    @JSONField(name = "data")
    private Integer data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }
}
