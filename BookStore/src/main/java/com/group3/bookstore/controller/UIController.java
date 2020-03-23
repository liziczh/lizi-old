package com.group3.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UIController {

    // login页面跳转
    @RequestMapping(value = "/loginUI", method = RequestMethod.GET)
    public String loginUI(){
        return "user/login";
    }

    // register页面跳转
    @RequestMapping(value = "/registerUI", method = RequestMethod.GET)
    public String registerUI(){
        return "user/register";
    }

    // commodity
    @RequestMapping(value = "/commodityUI", method = RequestMethod.GET)
    public String commodityUI(){
        return "commodity/commodity";
    }

    //sortgoods.jsp页面跳转
    @RequestMapping(value = "/sortgoodsUI", method = RequestMethod.GET)
    public String sortgoodsUI(){ return "product/sortgoods"; }
    //goodsinfo.jsp页面跳转
    @RequestMapping(value = "/goodsinfoUI", method = RequestMethod.GET)
    public String goodsinfoUI(){ return "product/goodsinfo"; }
    //search.jsp页面跳转
    @RequestMapping(value = "/searchUI", method = RequestMethod.GET)
    public String searchUI(){ return "product/search"; }



}
