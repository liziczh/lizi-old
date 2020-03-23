package com.group3.bookstore.mapper;

import com.group3.bookstore.pojo.Commodity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface CommodityManagerMapper {
    //增加一些书籍
    public void addCommodity(Commodity commodity);
    //根据名称查询书书籍
    public Commodity selectCommodityByCommodityName(String commodityName);
    //查询当前数据库中的所有的书籍
    public List<Commodity> selectAllCommodity();
    //编辑书籍
    public void updateCommodity(Commodity commodity);
    //根据名称删除书籍
    public void deleleCommodityByCommodityName(String commodityName);
    //获取当前限时抢购的书籍
    public List<Commodity> getCommodityByCommodityPurchase();
}
