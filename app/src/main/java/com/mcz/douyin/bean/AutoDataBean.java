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

    @JSONField(name = "code")
    private int code;
    @JSONField(name = "msg")
    private String msg;
    @JSONField(name = "time")
    private String time;
    @JSONField(name = "data")
    private List<DataDTO> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO {
        @JSONField(name = "id")
        private int id;
        @JSONField(name = "dy_name")
        private String dyName;
        @JSONField(name = "department_id")
        private int departmentId;
        @JSONField(name = "createtime")
        private int createtime;
        @JSONField(name = "comment")
        private String comment;

        public int getProbability() {
            return probability;
        }

        public void setProbability(int probability) {
            this.probability = probability;
        }

        @JSONField(name = "probability")
        private int probability;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDyName() {
            return dyName;
        }

        public void setDyName(String dyName) {
            this.dyName = dyName;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }
}
