package com.group3.bookstore.pojo;

import java.util.Objects;
import java.util.Set;

public class OrderForm {
    private String orderId;
    private String userId;
    private String receiverId;
    private String orderStatus;
    private String createTime;
    private String payTime;
    private String sendTime;
    private String receiverTime;
    private double totalPrice;
    private String remark;
    private Set<OrderItem> orderItemSet;

    public OrderForm() {
    }

    public OrderForm(String orderId, String userId, String receiverId, String orderStatus, String createTime, String payTime, String sendTime, String receiverTime, float totalPrice, String remark) {
        this.orderId = orderId;
        this.userId = userId;
        this.receiverId = receiverId;
        this.orderStatus = orderStatus;
        this.createTime = createTime;
        this.payTime = payTime;
        this.sendTime = sendTime;
        this.receiverTime = receiverTime;
        this.totalPrice = totalPrice;
        this.remark = remark;
    }

    public OrderForm(String orderId, String userId, String receiverId, String orderStatus, String createTime, String payTime, String sendTime, String receiverTime, double totalPrice, String remark, Set<OrderItem> orderItemSet) {
        this.orderId = orderId;
        this.userId = userId;
        this.receiverId = receiverId;
        this.orderStatus = orderStatus;
        this.createTime = createTime;
        this.payTime = payTime;
        this.sendTime = sendTime;
        this.receiverTime = receiverTime;
        this.totalPrice = totalPrice;
        this.remark = remark;
        this.orderItemSet = orderItemSet;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getReceiverTime() {
        return receiverTime;
    }

    public void setReceiverTime(String receiverTime) {
        this.receiverTime = receiverTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<OrderItem> getOrderItemSet() {
        return orderItemSet;
    }

    public void setOrderItemSet(Set<OrderItem> orderItemSet) {
        this.orderItemSet = orderItemSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderForm orderForm = (OrderForm) o;
        return Double.compare(orderForm.totalPrice, totalPrice) == 0 &&
                Objects.equals(orderId, orderForm.orderId) &&
                Objects.equals(userId, orderForm.userId) &&
                Objects.equals(receiverId, orderForm.receiverId) &&
                Objects.equals(orderStatus, orderForm.orderStatus) &&
                Objects.equals(createTime, orderForm.createTime) &&
                Objects.equals(payTime, orderForm.payTime) &&
                Objects.equals(sendTime, orderForm.sendTime) &&
                Objects.equals(receiverTime, orderForm.receiverTime) &&
                Objects.equals(remark, orderForm.remark) &&
                Objects.equals(orderItemSet, orderForm.orderItemSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, receiverId, orderStatus, createTime, payTime, sendTime, receiverTime, totalPrice, remark, orderItemSet);
    }

    @Override
    public String toString() {
        return "OrderForm{" +
                "orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", createTime='" + createTime + '\'' +
                ", payTime='" + payTime + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", receiverTime='" + receiverTime + '\'' +
                ", totalPrice=" + totalPrice +
                ", remark='" + remark + '\'' +
                ", orderItemSet=" + orderItemSet +
                '}';
    }
}
