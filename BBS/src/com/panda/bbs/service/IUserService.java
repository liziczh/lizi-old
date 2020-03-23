package com.panda.bbs.service;

import com.panda.bbs.domain.User;

public interface IUserService {
    // 根据用户ID获取用户
    public User getUserByUserId(String userId);
    // 邮箱登录
    public User getUserByEmailAndPassword(String email, String password);
    // 手机登录
    public User getUserByPhoneNumberAndPassword(String phoneNumber, String password);
    // 注册
    public void register(User user);
    // 编辑个人资料
    public void editProfile(User user);
    // 删除账号
    public void logoff(String userId);
    // 判断用户名是否已存在
    public boolean isExistingUsername(String username);
    // 判断邮箱是否已存在
    public boolean isExistingEmail(String email);
    // 判断手机号是否已存在
    public boolean isExistingPhoneNumber(String phoneNumber);

}
