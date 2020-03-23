package com.group3.bookstore.service;

import com.group3.bookstore.pojo.CartItem;

import java.util.List;

public interface ICartService {
    // 判断是否存在该购物项
    public CartItem getCartItem(String userId,String commodityId);
    // 查看购物车
    public List<CartItem> getCart(String userId);
    // 加入购物车
    public void addCartItem(CartItem cartItem);
    // 购物项数量
    public void updateCartItem(CartItem cartItem);
    // 移出购物车
    public void removeCartItem(String userId, String commodityId);
    // 清空
    public void emptyCart(String userId);


}
