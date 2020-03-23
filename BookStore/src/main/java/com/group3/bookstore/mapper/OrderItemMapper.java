package com.group3.bookstore.mapper;

import com.group3.bookstore.pojo.OrderItem;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface OrderItemMapper {

    // 根据订单号查询所有订单项
    public List<OrderItem> getOrderItemByOrderId(String orderId);
    // 新增订单项
    public void insertOrderItem(OrderItem orderItem);
    // 根据订单号删除订单项
    public void deleteOrderItem(String orderId);
}
