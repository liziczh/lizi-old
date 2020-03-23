package com.group3.bookstore.mapper;

import com.group3.bookstore.pojo.FavoritesItem;

import java.util.List;

public interface FavoritesItemMapper {
    // 获取一个收藏项
    public FavoritesItem getFavoritesItemByUserIdAndCommodityId(String userId, String commodityId);
    // 根据收藏夹ID获取所有收藏项
    public List<FavoritesItem> getFavoritesItemByUserId(String userId);
    // 新增收藏项
    public void insertFavoritesItem(FavoritesItem favoritesItem);
    // 删除收藏项
    public void deleteFavoritesItem(String userId, String commodityId);

}
