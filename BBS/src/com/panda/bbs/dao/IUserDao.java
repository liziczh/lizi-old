package com.panda.bbs.dao;

import com.panda.bbs.domain.User;

import java.sql.SQLException;

public interface IUserDao {
    // 根据邮箱&密码获取用户
    public User getUserByEmailAndPassword(String email, String password) throws SQLException;
    // 根据手机&密码获取用户
    public User getUserByPhoneNumberAndPassword(String phoneNumber, String password) throws SQLException;
    // 根据用户ID获取用户
    public User getUserByUserId(String userId) throws SQLException;
    // 根据用户名获取用户
    public User getUserByUsername(String username) throws SQLException;
    // 根据Email获取用户
    public User getUserByEmail(String email) throws SQLException;
    // 根据手机号获取用户
    public User getUserByPhoneNumber(String phoneNumber) throws SQLException;
    // 插入新用户
    public void insertUser(User user) throws SQLException;
    // 更新用户信息
    public void updateUser(User user) throws SQLException;
    // 删除用户
    public void deleteUser(String userId) throws SQLException;
}
