package com.group3.bookstore.service.impl;

import com.group3.bookstore.mapper.CommodityMapper;
import com.group3.bookstore.pojo.Commodity;
import com.group3.bookstore.service.ICommodityService;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("CommodityService")
public class CommodityServiceImpl implements ICommodityService {
    @Autowired
    private CommodityMapper mapper;
    @Override
    public List<Commodity> selectAll(int currentpage,String name,String category , String order){
        List<Commodity> commodities  = mapper.selectAll(currentpage*10,name,category,order);
        return commodities;
    }


    @Override
    public List<Commodity> selectByTime() {
        List<Commodity> commodities = mapper.selectByTime();
        return commodities;
    }

    @Override
    public List<Commodity> selectBySaleCount() {
        List<Commodity> commodities = mapper.selectBySalesCount();
        return commodities;
    }

    @Override
    public Commodity selectById(String commodityId) {
        return mapper.selectById(commodityId);
    }


    @Override
    public boolean addOne(Commodity commodity) {
        if(commodity != null)
        {
            mapper.addCommodity(commodity);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(String commodityId) {
        mapper.deleteCommodity(commodityId);
        return true;
    }

    @Override
    public boolean updateById(String commodityId, Commodity commodity) {
        if (commodity.getCommodityId().equals(commodityId))
        {
            mapper.updateCommodity(commodity);
            return true;
        }
        return false;
    }
}
