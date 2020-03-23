package com.liziczh.erp.dto.enumeration;

/**
 * @Description:  邮件类型
 * @Author: czh
 * @Create: 2018-10-08
 */

public enum EmailType {
   /**
    * 注册成功
    */
   REGISTER("注册成功"),
   /**
    * 忘记密码
    */
   FORGOTPASSWORD("忘记密码"),
   /**
    * 重置密码
    */
   RESETPASSWORD("重置密码"),
   /**
    * 解绑微信
    */
   UNBINDWECHAT("解绑微信");

   private String message ;

   private EmailType( String message ){
      this.message = message ;
   }

   public String getMessage() {
      return message;
   }
   public void setMessage(String message) {
      this.message = message;
   }
}
