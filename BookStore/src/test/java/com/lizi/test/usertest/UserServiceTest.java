package com.lizi.test.usertest;

import com.group3.bookstore.pojo.User;
import com.group3.bookstore.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-cfg.xml"})
public class UserServiceTest {
    @Autowired
    private IUserService userService;

    @Test
    public void getUserByEmailAndPassword(){
        User user = userService.getUserByEmailAndPassword("liziczh@qq.com","12345");
        System.out.println(user);
    }

}
