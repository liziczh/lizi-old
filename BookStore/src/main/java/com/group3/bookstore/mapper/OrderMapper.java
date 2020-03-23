package com.group3.bookstore.mapper;

import com.group3.bookstore.pojo.OrderForm;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface OrderMapper {

    // 查看用户订单
    public List<OrderForm> getOrderByUserId(String userId);
    // 查看单个订单
    public OrderForm getOrderByOrderId(String orderId);
    // 新增订单
    public void insertOrder(OrderForm orderForm);
    // 更新订单
    public void updateOrder(OrderForm orderForm);
    // 删除订单
    public void deleteOrder(String orderId);

}
