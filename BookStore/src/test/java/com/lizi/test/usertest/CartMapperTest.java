package com.lizi.test.usertest;

import com.group3.bookstore.mapper.CartItemMapper;
import com.group3.bookstore.pojo.CartItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-cfg.xml"})
public class CartMapperTest {
    @Autowired
    CartItemMapper cartItemMapper;

    @Test
    public void getCartItemByUserIdTest(){
        List<CartItem> cartItemList = cartItemMapper.getCartItemByUserId("1");
        System.out.println(cartItemList);
    }

    @Test
    public void deleteCartItemTest(){
        cartItemMapper.deleteCartItem("1","1");
    }

    @Test
    public void updateCartItemTest(){
        cartItemMapper.updateCartItem(new CartItem("1","1",3));
    }


}
