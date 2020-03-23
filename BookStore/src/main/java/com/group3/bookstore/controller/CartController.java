package com.group3.bookstore.controller;

import com.group3.bookstore.pojo.CartItem;
import com.group3.bookstore.pojo.User;
import com.group3.bookstore.service.ICartService;
import com.group3.bookstore.service.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @Autowired
    private ICommodityService commodityService;

    // 查看购物车:请求转发
    @RequestMapping(value = "/getCart")
    public ModelAndView getCart(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<CartItem> cartItemList =  cartService.getCart(user.getUserId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cartItemList",cartItemList);
        // 购物车视图名
        modelAndView.setViewName("user/cart");
        return modelAndView;
    }

    // 加入购物车
    @RequestMapping(value = "/addCartItem")
    @ResponseBody
    public String addCartItem(String commodityId, HttpSession session){
        User user = (User) session.getAttribute("user");
        CartItem cartItem = cartService.getCartItem(user.getUserId(),commodityId);
        if(cartItem == null){
            // 如果未加入购物车，则加入购物车
            cartService.addCartItem(new CartItem(user.getUserId(),commodityId,1));
            return "成功加入购物车";
        }else{
            // 如果已加入购物车，则数量+1
            cartItem.setCommodityCount(cartItem.getCommodityCount()+1);
            cartService.updateCartItem(cartItem);
            return "商品数量+1";
        }
    }

    // 移出购物车
    @RequestMapping(value = "/removeCartItem")
    public String removeCartItem(String commodityId, HttpSession session){
        User user = (User) session.getAttribute("user");
        cartService.removeCartItem(user.getUserId(),commodityId);
        // 转发去查询收藏夹
        return "forward:/cart/getCart.do";
    }

    // 清空购物车
    @RequestMapping(value = "/emptyCartItem")
    public String emptyCartItem(HttpSession session){
        User user = (User) session.getAttribute("user");
        cartService.emptyCart(user.getUserId());
        // 转发去查询收藏夹
        return "forward:/cart/getCart.do";
    }

    // 数量+1
    @RequestMapping(value = "/addOne")
    public String addOne(String commodityId, HttpSession session){
        User user = (User) session.getAttribute("user");
        CartItem cartItem = cartService.getCartItem(user.getUserId(),commodityId);
        if(cartItem != null){
            cartItem.setCommodityCount(cartItem.getCommodityCount()+1);
            System.out.println(cartItem);
            cartService.updateCartItem(cartItem);
        }
        return "forward:/cart/getCart.do";
    }

    // 数量-1
    @RequestMapping(value = "/removeOne")
    public String removeOne(String commodityId, HttpSession session){
        User user = (User) session.getAttribute("user");
        CartItem cartItem = cartService.getCartItem(user.getUserId(),commodityId);
        if(cartItem.getCommodityCount()-1 == 0){
            return "forward:/cart/removeCartItem.do";
        }else{
            cartItem.setCommodityCount(cartItem.getCommodityCount()-1);
            cartService.updateCartItem(cartItem);
            return "forward:/cart/getCart.do";
        }
    }

    // 移除所选项
    public String removeSelectedCartItem(String[] commodityIdArr,HttpSession session){
        User user = (User) session.getAttribute("user");
        for(String commodityId : commodityIdArr){
            cartService.removeCartItem(user.getUserId(),commodityId);
        }
        // 转发去查询购物车
        return "forward:/cart/getCart.do";
    }

    // 统计所选项总额
    @RequestMapping(value = "/countPrice")
    @ResponseBody
    public double countPrice(List<String> selectedItemList, HttpSession session){
        User user = (User) session.getAttribute("user");
        double countPrice = 0;
        for(String commodityId : selectedItemList){
            CartItem cartItem = cartService.getCartItem(user.getUserId(),commodityId);
            double price = commodityService.selectById(commodityId).getCommodityPurchasePrice();
            int count = cartItem.getCommodityCount();
            countPrice += count * price;
        }
        return countPrice;
    }







}
