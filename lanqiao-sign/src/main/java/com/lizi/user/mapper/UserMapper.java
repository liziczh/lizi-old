package com.lizi.user.mapper;

import com.lizi.user.pojo.User;

public interface UserMapper {
    // 根据用户名&密码获取用户
    public User getUserByUsernameAndPassword(String username, String password);
}
