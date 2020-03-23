package com.group3.bookstore.service;

import com.group3.bookstore.mapper.OrderMapper;
import com.group3.bookstore.pojo.OrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface IOrderService {

    // 查看我的所有订单
    public List<OrderForm> getOrderByUserId(String userId);
    // 查看单个订单
    public OrderForm getOrderByOrderId(String orderId);
    // 新增订单
    public void addOrder(OrderForm orderForm);
    // 更新订单
    public void modifyOrder(OrderForm orderForm);
    // 删除订单
    public void removeOrder(String orderId);

}
