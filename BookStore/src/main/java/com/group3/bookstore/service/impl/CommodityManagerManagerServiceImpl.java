package com.group3.bookstore.service.impl;

import com.group3.bookstore.mapper.CommodityManagerMapper;
import com.group3.bookstore.pojo.Commodity;
import com.group3.bookstore.service.ICommodityManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommodityManagerManagerServiceImpl implements ICommodityManagerService {
    @Autowired
    CommodityManagerMapper mapper;
    //根据书籍名称获取书籍
    @Override
    public Commodity getCommodityByCommodityName(String commodityName) {
        Commodity commodity = mapper.selectCommodityByCommodityName(commodityName);
        return commodity;
    }
    // 查询所有已有的书籍
    @Override
    public List<Commodity> getAllCommodity() {
        List<Commodity> commodityList = mapper.selectAllCommodity();
        return commodityList;
    }

    //查询正在参与抢购的书籍
    @Override
    public List<Commodity>getCommodityInPurchasePrice() {
        List<Commodity> commodityList = mapper.getCommodityByCommodityPurchase();
        return commodityList;
    }

    //编辑书籍信息
    @Override
    public void modifyCommodity(Commodity commodity) {
        mapper.updateCommodity(commodity);
    }

    //存书入库
    public void stockIn(String commodityName,Commodity commodity){
        if (getCommodityByCommodityName(commodityName) != null){
            //如果数据库中存在该书籍，直接修改书籍的数量信息
            mapper.updateCommodity(commodity);
        }else{
            //如果数据库中不存在该书籍，直接新增该书籍
            mapper.addCommodity(commodity);
        }
    }
    //删除书籍
    @Override
    public void removeCommodityByCommodityName(String commodityName) {
        mapper.deleleCommodityByCommodityName(commodityName);
    }
}
