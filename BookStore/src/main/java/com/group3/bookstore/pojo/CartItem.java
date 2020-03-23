package com.group3.bookstore.pojo;

import java.util.Objects;

public class CartItem {
    private String userId;
    private String commodityId;
    private int commodityCount;

    public CartItem() {
    }

    public CartItem(String userId, String commodityId, int commodityCount) {
        this.userId = userId;
        this.commodityId = commodityId;
        this.commodityCount = commodityCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        CartItem cartItem = (CartItem) o;
        return commodityCount == cartItem.commodityCount &&
                Objects.equals(userId, cartItem.userId) &&
                Objects.equals(commodityId, cartItem.commodityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, commodityId, commodityCount);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "userId='" + userId + '\'' +
                ", commodityId='" + commodityId + '\'' +
                ", commodityCount=" + commodityCount +
                '}';
    }
}
