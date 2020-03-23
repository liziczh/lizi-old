package com.group3.bookstore.controller;

import com.group3.bookstore.pojo.FavoritesItem;
import com.group3.bookstore.pojo.User;
import com.group3.bookstore.service.IFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/favorites")
public class FavoritesController {

    @Autowired
    private IFavoritesService favoritesService;

    // 查看收藏夹
    @RequestMapping(value = "/getFavorites")
    public ModelAndView getFavorites(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<FavoritesItem> favoritesItemList =  favoritesService.getFavorites(user.getUserId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("favoritesItemList",favoritesItemList);
        // 收藏夹视图名
        modelAndView.setViewName("user/favorites");
        return modelAndView;
    }

    // 加入收藏
    @RequestMapping(value = "/addFavoritesItem")
    @ResponseBody
    public String addFavoritesItem(String commodityId, HttpSession session){
        User user = (User) session.getAttribute("user");
        // 如果未被收藏,加入收藏
        if(!favoritesService.isExistingFavoritesItem(user.getUserId(),commodityId)){
            FavoritesItem favoritesItem = new FavoritesItem(user.getUserId(),commodityId);
            favoritesService.addFavoritesItem(favoritesItem);
            return "收藏成功";
        }else{
            return "已收藏";
        }
    }

    // 移出收藏夹
    @RequestMapping(value = "/removeFavoritesItem")
    public String removeFavoritesItem(String commodityId, HttpSession session){
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        System.out.println(commodityId);
        favoritesService.removeFavoritesItem(user.getUserId(),commodityId);
        // 转发去查询收藏夹
        return "forward:/favorites/getFavorites.do";
    }

    // 加入/移除收藏
    @RequestMapping(value = "/favorites")
    @ResponseBody
    public String favorites(String commodityId, HttpSession session){
        User user = (User) session.getAttribute("user");
        FavoritesItem favoritesItem = new FavoritesItem(user.getUserId(),commodityId);
        if(!favoritesService.isExistingFavoritesItem(user.getUserId(),commodityId)){
            // 如果未被收藏,则加入收藏
            favoritesService.addFavoritesItem(favoritesItem);
            return "取消收藏";
        }else{
            // 如果已被收藏,则取消收藏
            favoritesService.removeFavoritesItem(user.getUserId(),commodityId);
            return "收藏";
        }
    }

    // 判断是否已收藏
    @RequestMapping(value = "/isFavorites")
    @ResponseBody
    public String isFavorites(String commodityId,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(favoritesService.isExistingFavoritesItem(user.getUserId(),commodityId)){
            // 如果已被收藏
            return "取消收藏";
        }else {
            // 如果未被收藏
            return "收藏";
        }
    }

    // 删除所选项
    @RequestMapping(value = "/removeSelectedFavoritesItem")
    public String removeSelectedFavoritesItem(String[] commodityIdArr,HttpSession session){
        User user = (User) session.getAttribute("user");
        for(String commodityId : commodityIdArr){
            favoritesService.removeFavoritesItem(user.getUserId(),commodityId);
        }
        // 转发去查询收藏夹
        return "forward:/favorites/getFavorites.do";
    }

}
