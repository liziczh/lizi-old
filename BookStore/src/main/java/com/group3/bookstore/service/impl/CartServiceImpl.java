package com.group3.bookstore.service.impl;

import com.group3.bookstore.mapper.CartItemMapper;
import com.group3.bookstore.pojo.CartItem;
import com.group3.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartItemMapper cartItemMapper;
    // 获取一个购物项

    @Override
    public CartItem getCartItem(String userId, String commodityId) {
        CartItem cartItem = cartItemMapper.getCartItemByUserIdAndCommodityId(userId,commodityId);
        return cartItem;
    }

    // 查看购物车
    @Override
    public List<CartItem> getCart(String userId) {
        List<CartItem> cartItemList = cartItemMapper.getCartItemByUserId(userId);
        return cartItemList;
    }
    // 加入购物车
    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemMapper.insertCartItem(cartItem);
    }
    // 移出购物车
    @Override
    public void removeCartItem(String userId, String commodityId) {
        cartItemMapper.deleteCartItem(userId, commodityId);
    }
    // 购物项数量
    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemMapper.updateCartItem(cartItem);
    }
    // 清空
    @Override
    public void emptyCart(String userId) {
        cartItemMapper.emptyCartItem(userId);
    }
}
