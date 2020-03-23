package com.liziczh.erp.common.code;

/**
 * @Description 鉴权中心自定义http code   200+自定义http code
 * @Author hw
 * @Date 2018/5/28
 */
public class UaaRestCode {

   public static final int WECHAT_LOGIN_SUCCESS = 200200;   // 微信登陆成功
   public static final int WECHAT_NOT_BIND = 200203;  // 用户未绑定
   public static final int WECHAT_BIND_EXIST = 200205; // 账户已绑定其他微信
   public static final int WECHAT_BIND_USER_EXIST = 200206; // 微信已绑定其他用户

   public static final int WECHAT_UNAUTHORIZED = 200401; // 用户未授权
   public static final int WECHAT_BIND_TIMEOUT = 200403; // 微信绑定超时
   public static final int WECHAT_INVALID_CODE = 200429; // 微信扫码登陆错误（微信官网返回）
   public static final int WECHAT_LOGIN_ERROR = 200500;  // 登录失败
   public static final int WECHAT_BIND_USER_ERROR = 200501; // 微信已绑定其他用户

   public static final Integer EX_TOKEN_ERROR_CODE = 201400;   // 用户token失效异常
   public static final Integer EX_USER_INVALID_CODE = 201401;   // 用户token异常

   public static final int USERNAME_OR_PASSWORD_ERROR = 202400; // 用户名或密码错误

}
