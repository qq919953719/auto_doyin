package com.mcz.douyin.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @name douyintest
 * @class name：zhepan.com.mytest.bean *
 * @class describe *
 * @anthor chuangcui 邮箱:919953719@qq.com
 * @time 2023/9/16 17:45
 */

public class AutoDataBean {

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
        @JSONField(name = "device")
        private DeviceDTO device;
        @JSONField(name = "msgList")
        private List<MsgListDTO> msgList;
        @JSONField(name = "commentList")
        private List<MsgListDTO> commentList;
        @JSONField(name = "targetUserList")
        private List<DeviceDTO> targetUserList;

        public DeviceDTO getDevice() {
            return device;
        }

        public void setDevice(DeviceDTO device) {
            this.device = device;
        }

        public List<MsgListDTO> getMsgList() {
            return msgList;
        }

        public void setMsgList(List<MsgListDTO> msgList) {
            this.msgList = msgList;
        }

        public List<MsgListDTO> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<MsgListDTO> commentList) {
            this.commentList = commentList;
        }

        public List<DeviceDTO> getTargetUserList() {
            return targetUserList;
        }

        public void setTargetUserList(List<DeviceDTO> targetUserList) {
            this.targetUserList = targetUserList;
        }

        public static class DeviceDTO {
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

        public static class MsgListDTO {
            @JSONField(name = "msg")
            private String msg;

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }
        }
    }
}
