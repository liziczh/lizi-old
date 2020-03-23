package com.panda.bbs.dao.impl;

import com.panda.bbs.dao.IUserDao;
import com.panda.bbs.domain.User;
import com.panda.bbs.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
    // 根据邮箱&密码获取用户
    @Override
    public User getUserByEmailAndPassword(String email, String password) throws SQLException {
        String sql = "select * from user where email = ? and password = ?";
        User user = queryRunner.query(sql,new BeanHandler<User>(User.class),email,password);
        return user;
    }
    // 根据手机&密码获取用户
    @Override
    public User getUserByPhoneNumberAndPassword(String phoneNumber, String password) throws SQLException {
        String sql = "select * from user where phoneNumber = ? and password = ?";
        User user = queryRunner.query(sql,new BeanHandler<User>(User.class),phoneNumber,password);
        return user;
    }
    // 根据用户ID获取用户
    @Override
    public User getUserByUserId(String userId) throws SQLException {
        String sql = "select * from user where userId = ?";
        User user = queryRunner.query(sql,new BeanHandler<User>(User.class),userId);
        return user;
    }

    // 根据用户名获取用户
    @Override
    public User getUserByUsername(String username) throws SQLException {
        String sql = "select * from user where username = ?";
        User user = queryRunner.query(sql,new BeanHandler<User>(User.class),username);
        return user;
    }
    // 根据Email获取用户
    @Override
    public User getUserByEmail(String email) throws SQLException {
        String sql = "select * from user where email = ?";
        User user = queryRunner.query(sql,new BeanHandler<User>(User.class),email);
        return user;
    }
    // 根据手机号获取用户
    @Override
    public User getUserByPhoneNumber(String phoneNumber) throws SQLException {
        String sql = "select * from user where phoneNumber = ?";
        User user = queryRunner.query(sql,new BeanHandler<User>(User.class),phoneNumber);
        System.out.println(user);
        return user;
    }

    // 插入新用户
    @Override
    public void insertUser(User user) throws SQLException {
        String sql="insert into user values(?,?,?,?,?,?,?,?,?,?,?,?)";
        queryRunner.update(sql,user.getUserId(),user.getUsername(),user.getPassword(),user.getEmail(),user.getPhoneNumber(),user.getAvatar(),user.getGender(),user.getJoinTime(),user.getLocation(),user.getBio(),user.getReward(),user.getBackground());
    }
    // 更新用户信息
    @Override
    public void updateUser(User user) throws SQLException {
        String sql="update user set username = ? ,password = ?, email = ?, phoneNumber = ?,avatar = ?,gender = ?, location = ?, bio = ?, reward = ?, background = ? where userId = ?";
        queryRunner.update(sql,user.getUsername(),user.getPassword(),user.getEmail(), user.getPhoneNumber(), user.getAvatar(), user.getGender(), user.getLocation(), user.getBio(), user.getReward(), user.getBackground(), user.getUserId());
    }
    // 删除用户
    @Override
    public void deleteUser(String userId) throws SQLException {
        String sql="delete from user where userId = ?";
        queryRunner.update(sql,userId);
    }
}
