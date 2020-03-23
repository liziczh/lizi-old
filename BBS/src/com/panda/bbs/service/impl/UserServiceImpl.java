package com.panda.bbs.service.impl;

import com.panda.bbs.dao.IPostDao;
import com.panda.bbs.dao.IUserDao;
import com.panda.bbs.dao.impl.PostDaoImpl;
import com.panda.bbs.dao.impl.UserDaoImpl;
import com.panda.bbs.domain.User;
import com.panda.bbs.service.IPostService;
import com.panda.bbs.service.IUserService;

import java.sql.SQLException;

public class UserServiceImpl implements IUserService {
    IUserDao userDao = new UserDaoImpl();
    // 根据用户名获取用户
    @Override
    public User getUserByUserId(String userId) {
        User user = null;
        try {
            user = userDao.getUserByUserId(userId);
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
            user = userDao.getUserByEmailAndPassword(email,password);
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
            user = userDao.getUserByPhoneNumberAndPassword(phoneNumber,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // 注册
    @Override
    public void register(User user) {
        try {
            userDao.insertUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 编辑个人资料
    @Override
    public void editProfile(User user) {
        try {
            userDao.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 删除账号
    @Override
    public void logoff(String userId) {
        try {
            userDao.deleteUser(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 判断用户名是否已存在
    @Override
    public boolean isExistingUsername(String username) {
        User user = null;
        try {
            user = userDao.getUserByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(user != null){
            return true;
        }else{
            return false;
        }
    }
    // 判断邮箱是否已存在
    @Override
    public boolean isExistingEmail(String email) {
        User user = null;
        try {
            user = userDao.getUserByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(user != null){
            return true;
        }else{
            return false;
        }
    }
    // 判断手机号是否已存在
    @Override
    public boolean isExistingPhoneNumber(String phoneNumber) {
        User user = null;
        try {
            user = userDao.getUserByPhoneNumber(phoneNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(user != null){
            return true;
        }else{
            return false;
        }
    }
}
