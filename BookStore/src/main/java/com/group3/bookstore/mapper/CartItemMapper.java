package com.group3.bookstore.mapper;

import com.group3.bookstore.pojo.CartItem;

import java.util.List;

public interface CartItemMapper {
    // 获取一个购物项
    public CartItem getCartItemByUserIdAndCommodityId(String userId, String commodityId);
    // 获取用户所有购物项
    public List<CartItem> getCartItemByUserId(String userId);
    // 新增购物项
    public void insertCartItem(CartItem cartItem);
    // 更新购物项数量
    public void updateCartItem(CartItem cartItem);
    // 移除购物项
    public void deleteCartItem(String userId, String commodityId);
    // 清空购物车
    public void emptyCartItem(String userId);
}
