package com.group3.bookstore.pojo;

import java.util.Objects;

public class FavoritesItem {
    private String userId;
    private String commodityId;

    public FavoritesItem() {
    }

    public FavoritesItem(String userId, String commodityId) {
        this.userId = userId;
        this.commodityId = commodityId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritesItem that = (FavoritesItem) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(commodityId, that.commodityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, commodityId);
    }

    @Override
    public String toString() {
        return "FavoritesItem{" +
                "userId='" + userId + '\'' +
                ", commodityId='" + commodityId + '\'' +
                '}';
    }
}
