package com.lizi.user.service;

import com.lizi.user.pojo.User;

public interface IUserService {
    // 登陆
    public User login(String username, String password);
}
