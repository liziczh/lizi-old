package com.group3.bookstore.service.impl;

import com.group3.bookstore.mapper.FavoritesItemMapper;
import com.group3.bookstore.pojo.FavoritesItem;
import com.group3.bookstore.service.IFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FavoritesServiceImpl implements IFavoritesService {

    @Autowired
    private FavoritesItemMapper favoritesItemMapper;

    // 判断是否存在该购物项
    @Override
    public boolean isExistingFavoritesItem(String userId, String commodityId) {
        FavoritesItem favoritesItem = favoritesItemMapper.getFavoritesItemByUserIdAndCommodityId(userId,commodityId);
        if (favoritesItem == null){
            return false;
        }else{
            return true;
        }
    }

    // 查看收藏夹
    @Override
    public List<FavoritesItem> getFavorites(String UserId) {
        List<FavoritesItem> favoritesItemList = favoritesItemMapper.getFavoritesItemByUserId(UserId);
        return favoritesItemList;
    }
    // 加入收藏夹
    @Override
    public void addFavoritesItem(FavoritesItem favoritesItem) {
        favoritesItemMapper.insertFavoritesItem(favoritesItem);
    }
    // 移出收藏夹
    @Override
    public void removeFavoritesItem(String userId, String commodityId) {
        favoritesItemMapper.deleteFavoritesItem(userId,commodityId);
    }
}
