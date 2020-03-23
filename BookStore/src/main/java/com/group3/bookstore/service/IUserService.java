package com.group3.bookstore.service;


import com.group3.bookstore.pojo.User;

public interface IUserService {
    // 根据用户ID获取用户
    public User getUserByUserId(String userId);
    // 邮箱登录
    public User getUserByEmailAndPassword(String email, String password);
    // 手机登录
    public User getUserByPhoneNumberAndPassword(String phoneNumber, String password);
    // 注册
    public void register(User user);
    // 修改个人资料
    public void modifyUser(User user);
    // 删除账号
    public void logoff(String userId);
    // 判断用户名是否已存在
    public boolean isExistingUsername(String username);
    // 判断邮箱是否已存在
    public boolean isExistingEmail(String email);
    // 判断手机号是否已存在
    public boolean isExistingPhoneNumber(String phoneNumber);

}
