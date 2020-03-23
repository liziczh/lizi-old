package com.group3.bookstore.service.impl;

import com.group3.bookstore.mapper.UserMapper;
import com.group3.bookstore.pojo.User;
import com.group3.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    // 根据用户ID获取用户
    @Override
    public User getUserByUserId(String userId) {
        User user = null;
        try {
            user = userMapper.getUserByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // 邮箱登录
    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        try {
            user = userMapper.getUserByEmailAndPassword(email,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // 手机登录
    @Override
    public User getUserByPhoneNumberAndPassword(String phoneNumber, String password) {
        User user = null;
        try {
            user = userMapper.getUserByPhoneNumberAndPassword(phoneNumber,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // 注册
    @Override
    public void register(User user) {
        try {
            userMapper.insertUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 编辑个人资料
    @Override
    public void modifyUser(User user) {
        try {
            userMapper.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除账号
    @Override
    public void logoff(String userId) {
        try {
            userMapper.deleteUser(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 判断用户名是否已存在
    @Override
    public boolean isExistingUsername(String username) {
        User user = null;
        try {
            user = userMapper.getUserByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user!=null){
            return true;
        }
        return false;
    }

    // 判断邮箱是否已存在
    @Override
    public boolean isExistingEmail(String email) {
        User user = null;
        try {
            user = userMapper.getUserByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user!=null){
            return true;
        }
        return false;
    }

    // 判断手机号是否已存在
    @Override
    public boolean isExistingPhoneNumber(String phoneNumber) {
        User user = null;
        try {
            user = userMapper.getUserByPhoneNumber(phoneNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user!=null){
            return true;
        }
        return false;
    }
}
