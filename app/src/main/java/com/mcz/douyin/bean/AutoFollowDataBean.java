package com.mcz.douyin.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class AutoFollowDataBean {

    @JSONField(name = "code")
    private Integer code;
    @JSONField(name = "msg")
    private String msg;
    @JSONField(name = "time")
    private String time;
    @JSONField(name = "data")
    private DataDTO data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        @JSONField(name = "id")
        private Integer id;
        @JSONField(name = "user_id")
        private Integer userId;
        @JSONField(name = "follow_name")
        private String followName;
        @JSONField(name = "createtime")
        private Integer createtime;
        @JSONField(name = "updatetime")
        private Integer updatetime;
        @JSONField(name = "deletetime")
        private Object deletetime;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getFollowName() {
            return followName;
        }

        public void setFollowName(String followName) {
            this.followName = followName;
        }

        public Integer getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Integer createtime) {
            this.createtime = createtime;
        }

        public Integer getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(Integer updatetime) {
            this.updatetime = updatetime;
        }

        public Object getDeletetime() {
            return deletetime;
        }

        public void setDeletetime(Object deletetime) {
            this.deletetime = deletetime;
        }
    }
}
