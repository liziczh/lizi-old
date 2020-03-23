package com.group3.bookstore.service;

import com.group3.bookstore.pojo.FavoritesItem;

import java.util.List;

public interface IFavoritesService {
    // 判断是否存在该购物项
    public boolean isExistingFavoritesItem(String userId,String commodityId);
    // 查看收藏夹
    public List<FavoritesItem> getFavorites(String userId);
    // 加入收藏夹
    public void addFavoritesItem(FavoritesItem favoritesItem);
    // 移出收藏夹
    public void removeFavoritesItem(String userId, String commodityId);

}
