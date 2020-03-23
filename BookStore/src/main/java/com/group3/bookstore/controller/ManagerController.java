package com.group3.bookstore.controller;

import com.alibaba.fastjson.JSON;
import com.group3.bookstore.pojo.Ad;
import com.group3.bookstore.pojo.Commodity;
import com.group3.bookstore.pojo.Special;
import com.group3.bookstore.service.IAdManagerService;
import com.group3.bookstore.service.ICommodityManagerService;
import com.group3.bookstore.service.ISpecialManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class ManagerController {
    @Autowired
    private IAdManagerService iAdManagerService;
    @Autowired
    private ICommodityManagerService iCommodityManagerService;
    @Autowired
    private ISpecialManagerService iSpecialManagerService;

    //失去焦点时，判断数据库中是否存在该商品,存在将返回该商品信息
    @RequestMapping(value = "/isExitCommodity",method = RequestMethod.GET)
    public ModelAndView isExitCommodity(String commodityName){
        Commodity commodity = iCommodityManagerService.getCommodityByCommodityName(commodityName);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/admin");
        modelAndView.addObject("commodity",commodity);
        return modelAndView;
    };

    //书籍入库
    @RequestMapping(value = "/stockCommodityIn",method = RequestMethod.POST)
    public ModelAndView stockCommodityIn(String commodityName,Commodity commodity){
        String commodityId = String.valueOf(UUID.randomUUID());
        commodity.setCommodityId(commodityId);
        iCommodityManagerService.stockIn(commodityName,commodity);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/admin");
        return modelAndView;
    };

    //修改书籍信息
    @RequestMapping(value = "/editCommodityInfo",method = RequestMethod.GET)
    public void editCommodityInfo(Commodity commodity){
        iCommodityManagerService.modifyCommodity(commodity);
    };

    //删除书籍
    @RequestMapping(value = "/deleteCommodity",method = RequestMethod.GET)
    public void deleteCommodity(String commodityName){
        iCommodityManagerService.removeCommodityByCommodityName(commodityName);
    }

    //查询正在处于限时抢购的书籍
    @RequestMapping(value = "/getCommodityInFlashSale",method = RequestMethod.GET)
    public ModelAndView getCommodityInFlashSale(){
        List<Commodity> purchaseCommodityList = iCommodityManagerService.getCommodityInPurchasePrice();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/admin");
        modelAndView.addObject("purchaseCommodityList",purchaseCommodityList);
        return modelAndView;
    }

    //查询所有的书籍
    @RequestMapping(value = "/getAllCommodity",method = RequestMethod.GET)
    public ModelAndView getAllCommodity(){
        List<Commodity> commodityList = iCommodityManagerService.getAllCommodity();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/admin");
        modelAndView.addObject("commodityList",commodityList);
        return modelAndView;
    }

    //managerUI
    @RequestMapping(value = "/managerAdvertisement", method = RequestMethod.GET)
    public String managerUI(){
        return "manager/admin";
    }

    //查询所有的广告
    @RequestMapping(value = "/findAllAdvertisement",method = RequestMethod.GET)
    public ModelAndView findAllAdvertisement(){
        List<Ad> adlist = iAdManagerService.findAllAd();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/admin");
        modelAndView.addObject("adlist",adlist);
        return modelAndView;
    }
    //广告插入
    @RequestMapping(value = "/insertAdvertisement",method = RequestMethod.GET)
    public ModelAndView insertAdvertisementUI(Ad ad){
        String adId = String.valueOf(UUID.randomUUID());
        ad.setAdId(adId);
        System.out.println(ad);
        iAdManagerService.addAd(ad);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/admin");
        modelAndView.addObject("ad",ad);
        return modelAndView;
    }

    //广告编辑
    @RequestMapping(value = "/editAdvertisement",method = RequestMethod.GET)
    public ModelAndView edittAdvertisementUI(Ad ad){
        iAdManagerService.addAd(ad);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/admin");
        modelAndView.addObject("ad",ad);
        return modelAndView;
    }
    //广告删除
    @RequestMapping(value = "/deleteAdvertisement")
    public String deleteAdvertisementUI(String adId){
        System.out.println(adId);
        iAdManagerService.removeAdByAdName(adId);
        return  "forward:/admin/findAdvertisement.do";
    }

    //获取所有的专题
    @RequestMapping(value = "getAllSpecial",method = RequestMethod.GET)
    public ModelAndView getAllSpecial(){
        List<Special> specialList = iSpecialManagerService.findAllSpecial();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/admin");
        modelAndView.addObject("specialList",specialList);
        return modelAndView;
    }

}
