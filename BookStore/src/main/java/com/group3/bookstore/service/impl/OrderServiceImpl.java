package com.group3.bookstore.service.impl;

import com.group3.bookstore.mapper.OrderMapper;
import com.group3.bookstore.pojo.OrderForm;
import com.group3.bookstore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderForm> getOrderByUserId(String userId) {
        List<OrderForm> orderFormList = orderMapper.getOrderByUserId(userId);
        return orderFormList;
    }

    @Override
    public OrderForm getOrderByOrderId(String orderId) {
        OrderForm orderForm = orderMapper.getOrderByOrderId(orderId);
        return orderForm;
    }

    @Override
    public void addOrder(OrderForm orderForm) {
        orderMapper.insertOrder(orderForm);
    }

    @Override
    public void modifyOrder(OrderForm orderForm) {
        orderMapper.updateOrder(orderForm);
    }

    @Override
    public void removeOrder(String orderId) {
        orderMapper.deleteOrder(orderId);
    }

}
