package com.group3.bookstore.pojo;

import java.util.List;
import java.util.Objects;

public class Order {
    private String orderId;
    private String userId;
    private String orderTime;
    private String payTime;
    private String sendTime;
    private float totalPrice;
    private String receiver;
    private String receiverPhoneNumber;
    private String receiverAddress;
    private String remark;
    private List<OrderItem> orderItemList;

    public Order() {
    }

    public Order(String orderId, String userId, String orderTime, String payTime, String sendTime, float totalPrice, String receiver, String receiverPhoneNumber, String receiverAddress, String remark, List<OrderItem> orderItemList) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderTime = orderTime;
        this.payTime = payTime;
        this.sendTime = sendTime;
        this.totalPrice = totalPrice;
        this.receiver = receiver;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.receiverAddress = receiverAddress;
        this.remark = remark;
        this.orderItemList = orderItemList;
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

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
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

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Float.compare(order.totalPrice, totalPrice) == 0 &&
                Objects.equals(orderId, order.orderId) &&
                Objects.equals(userId, order.userId) &&
                Objects.equals(orderTime, order.orderTime) &&
                Objects.equals(payTime, order.payTime) &&
                Objects.equals(sendTime, order.sendTime) &&
                Objects.equals(receiver, order.receiver) &&
                Objects.equals(receiverPhoneNumber, order.receiverPhoneNumber) &&
                Objects.equals(receiverAddress, order.receiverAddress) &&
                Objects.equals(remark, order.remark) &&
                Objects.equals(orderItemList, order.orderItemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, orderTime, payTime, sendTime, totalPrice, receiver, receiverPhoneNumber, receiverAddress, remark, orderItemList);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", payTime='" + payTime + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", totalPrice=" + totalPrice +
                ", receiver='" + receiver + '\'' +
                ", receiverPhoneNumber='" + receiverPhoneNumber + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", remark='" + remark + '\'' +
                ", orderItemList=" + orderItemList +
                '}';
    }

}
