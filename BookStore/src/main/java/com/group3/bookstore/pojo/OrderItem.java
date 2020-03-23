package com.group3.bookstore.pojo;

import java.util.Objects;

public class OrderItem {

    private String orderId;
    private String commodityId;
    private int commodityCount;

    public OrderItem() {
    }

    public OrderItem(String orderId, String commodityId, int commodityCount) {
        this.orderId = orderId;
        this.commodityId = commodityId;
        this.commodityCount = commodityCount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public int getCommodityCount() {
        return commodityCount;
    }

    public void setCommodityCount(int commodityCount) {
        this.commodityCount = commodityCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return commodityCount == orderItem.commodityCount &&
                Objects.equals(orderId, orderItem.orderId) &&
                Objects.equals(commodityId, orderItem.commodityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, commodityId, commodityCount);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderId='" + orderId + '\'' +
                ", commodityId='" + commodityId + '\'' +
                ", commodityCount=" + commodityCount +
                '}';
    }

}
