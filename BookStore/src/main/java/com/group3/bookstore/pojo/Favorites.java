package com.group3.bookstore.pojo;

import java.util.List;
import java.util.Objects;

public class Favorites {
    private String favoritesId;
    private String userId;
    private List<FavoritesItem> favoritesItemList;

    public Favorites() {
    }

    public Favorites(String favoritesId, String userId, List<FavoritesItem> favoritesItemList) {
        this.favoritesId = favoritesId;
        this.userId = userId;
        this.favoritesItemList = favoritesItemList;
    }

    public String getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(String favoritesId) {
        this.favoritesId = favoritesId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<FavoritesItem> getFavoritesItemList() {
        return favoritesItemList;
    }

    public void setFavoritesItemList(List<FavoritesItem> favoritesItemList) {
        this.favoritesItemList = favoritesItemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorites favorites = (Favorites) o;
        return Objects.equals(favoritesId, favorites.favoritesId) &&
                Objects.equals(userId, favorites.userId) &&
                Objects.equals(favoritesItemList, favorites.favoritesItemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(favoritesId, userId, favoritesItemList);
    }

    @Override
    public String toString() {
        return "Favorites{" +
                "favoritesId='" + favoritesId + '\'' +
                ", userId='" + userId + '\'' +
                ", favoritesItemList=" + favoritesItemList +
                '}';
    }
}
