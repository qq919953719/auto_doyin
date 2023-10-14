package com.mcz.douyin.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @name douyintest
 * @class name：zhepan.com.mytest.bean *
 * @class describe *
 * @anthor chuangcui 邮箱:919953719@qq.com
 * @time 2023/9/16 13:32
 */

public class VcodeBean {

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
      @JSONField(name = "token")
      private String token;

      public String getToken() {
         return token;
      }

      public void setToken(String token) {
         this.token = token;
      }
   }
}
