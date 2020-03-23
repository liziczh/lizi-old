package com.group3.bookstore.service;

import com.group3.bookstore.pojo.Commodity;

import java.sql.SQLException;
import java.util.List;

public interface ICommodityService {
    //查找
    public List<Commodity> selectAll(int currentpage,String name,String category , String order);
    //按出版时间 10个
    public List<Commodity> selectByTime();
    //按销量 10个
    public List<Commodity> selectBySaleCount();

    //查找一个
    public Commodity selectById(String commodityId);
    //添加
    public boolean addOne(Commodity commodity);
    //删除
    public boolean deleteById(String commodityId);
    //更新
    public boolean updateById(String commodityId, Commodity commodity);
}
