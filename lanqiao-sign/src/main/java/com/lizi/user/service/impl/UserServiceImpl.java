package com.lizi.user.service.impl;

import com.lizi.user.mapper.UserMapper;
import com.lizi.user.pojo.User;
import com.lizi.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        User user = userMapper.getUserByUsernameAndPassword(username,password);
        return user;
    }
}
