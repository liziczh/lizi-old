package com.lizi.test.usertest;

import com.group3.bookstore.mapper.UserMapper;
import com.group3.bookstore.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-cfg.xml"})
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void getUserByEmailAndPasswordTest() throws SQLException {
        User user = userMapper.getUserByEmailAndPassword("liziczh@qq.com","12345");
        System.out.println(user);
    }



}
