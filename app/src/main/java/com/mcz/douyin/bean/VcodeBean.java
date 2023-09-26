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

   @JSONField(name = "code")
   private int code;
   @JSONField(name = "msg")
   private String msg;
   @JSONField(name = "time")
   private String time;
   @JSONField(name = "data")
   private DataDTO data;

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

   public DataDTO getData() {
      return data;
   }

   public void setData(DataDTO data) {
      this.data = data;
   }

   public static class DataDTO {
      @JSONField(name = "userinfo")
      private UserinfoDTO userinfo;

      public UserinfoDTO getUserinfo() {
         return userinfo;
      }

      public void setUserinfo(UserinfoDTO userinfo) {
         this.userinfo = userinfo;
      }

      public static class UserinfoDTO {
         @JSONField(name = "id")
         private int id;
         @JSONField(name = "username")
         private String username;
         @JSONField(name = "nickname")
         private String nickname;
         @JSONField(name = "mobile")
         private String mobile;
         @JSONField(name = "avatar")
         private String avatar;
         @JSONField(name = "score")
         private int score;
         @JSONField(name = "token")
         private String token;
         @JSONField(name = "user_id")
         private int userId;
         @JSONField(name = "createtime")
         private int createtime;
         @JSONField(name = "expiretime")
         private int expiretime;
         @JSONField(name = "expires_in")
         private int expiresIn;

         public int getId() {
            return id;
         }

         public void setId(int id) {
            this.id = id;
         }

         public String getUsername() {
            return username;
         }

         public void setUsername(String username) {
            this.username = username;
         }

         public String getNickname() {
            return nickname;
         }

         public void setNickname(String nickname) {
            this.nickname = nickname;
         }

         public String getMobile() {
            return mobile;
         }

         public void setMobile(String mobile) {
            this.mobile = mobile;
         }

         public String getAvatar() {
            return avatar;
         }

         public void setAvatar(String avatar) {
            this.avatar = avatar;
         }

         public int getScore() {
            return score;
         }

         public void setScore(int score) {
            this.score = score;
         }

         public String getToken() {
            return token;
         }

         public void setToken(String token) {
            this.token = token;
         }

         public int getUserId() {
            return userId;
         }

         public void setUserId(int userId) {
            this.userId = userId;
         }

         public int getCreatetime() {
            return createtime;
         }

         public void setCreatetime(int createtime) {
            this.createtime = createtime;
         }

         public int getExpiretime() {
            return expiretime;
         }

         public void setExpiretime(int expiretime) {
            this.expiretime = expiretime;
         }

         public int getExpiresIn() {
            return expiresIn;
         }

         public void setExpiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
         }
      }
   }
}
