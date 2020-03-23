package com.group3.bookstore.service;

import com.group3.bookstore.pojo.Commodity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICommodityManagerService {
    //根据书籍名称获取书籍
    public Commodity getCommodityByCommodityName(String commodityName);
    //获取所有书籍
    public List<Commodity> getAllCommodity();
    //获取正在参与抢购的书籍
    public List<Commodity> getCommodityInPurchasePrice();
    //编辑书籍信息
    public void modifyCommodity(Commodity commodity);
    //存书入库
    public void stockIn(String commodityName,Commodity commodity);
    //删除某一本书籍所有信息
    public void removeCommodityByCommodityName(String commodityName);
}
