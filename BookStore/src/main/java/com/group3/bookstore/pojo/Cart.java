package com.group3.bookstore.pojo;

import java.util.List;
import java.util.Objects;

public class Cart {
    private String cartId;
    private String userId;
    private float totalPrice;
    private List<CartItem> cartItemList;

    public Cart() {
    }

    public Cart(String cartId, String userId, float totalPrice, List<CartItem> cartItemList) {
        this.cartId = cartId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.cartItemList = cartItemList;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Float.compare(cart.totalPrice, totalPrice) == 0 &&
                Objects.equals(cartId, cart.cartId) &&
                Objects.equals(userId, cart.userId) &&
                Objects.equals(cartItemList, cart.cartItemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, userId, totalPrice, cartItemList);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId='" + cartId + '\'' +
                ", userId='" + userId + '\'' +
                ", totalPrice=" + totalPrice +
                ", cartItemList=" + cartItemList +
                '}';
    }
}
