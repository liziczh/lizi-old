package com.lizi.test.usertest;

import com.group3.bookstore.pojo.CartItem;
import com.group3.bookstore.service.ICartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-cfg.xml"})
public class CartServiceTest {
    @Autowired
    ICartService cartService;

    @Test
    public void removeCartItemTest(){
        cartService.removeCartItem("1","1");
    }

    @Test
    public void updateCartItemTest(){
        cartService.updateCartItem(new CartItem("1","1",5));
    }
}
