package com.lizi.test.usertest;

import com.group3.bookstore.mapper.OrderMapper;
import com.group3.bookstore.pojo.OrderForm;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderTest {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void getOrderByOrderIdTest(){
        OrderForm orderForm = orderMapper.getOrderByOrderId("1");
        System.out.println(orderForm);
    }


}
