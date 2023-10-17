package com.mcz.douyin.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @name auto_mcz
 * @class name：com.mcz.douyin.bean *
 * @class describe *
 * @anthor chuangcui 邮箱:919953719@qq.com
 * @time 2023/10/17 17:29
 */

public class OnOffBean {


    @JSONField(name = "msg")
    private String msg;
    @JSONField(name = "code")
    private int code;
    @JSONField(name = "data")
    private DataDTO data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        @JSONField(name = "deviceID")
        private String deviceID;
        @JSONField(name = "account")
        private String account;
        @JSONField(name = "msgOn")
        private String msgOn;
        @JSONField(name = "growUpOn")
        private String growUpOn;

        public String getDeviceID() {
            return deviceID;
        }

        public void setDeviceID(String deviceID) {
            this.deviceID = deviceID;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getMsgOn() {
            return msgOn;
        }

        public void setMsgOn(String msgOn) {
            this.msgOn = msgOn;
        }

        public String getGrowUpOn() {
            return growUpOn;
        }

        public void setGrowUpOn(String growUpOn) {
            this.growUpOn = growUpOn;
        }
    }
}
