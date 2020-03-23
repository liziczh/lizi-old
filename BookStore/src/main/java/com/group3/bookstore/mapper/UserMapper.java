package com.group3.bookstore.mapper;


import com.group3.bookstore.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

public interface UserMapper {
    // 根据邮箱&密码获取用户
    public User getUserByEmailAndPassword(@Param("email")String email, @Param("password") String password) throws SQLException;
    // 根据手机&密码获取用户
    public User getUserByPhoneNumberAndPassword(@Param("phoneNumber") String phoneNumber, @Param("password") String password) throws SQLException;
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
