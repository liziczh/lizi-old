package com.group3.bookstore.mapper;

import com.group3.bookstore.pojo.Commodity;

import java.sql.SQLException;
import java.util.List;

public interface CommodityMapper {
    //查找书籍
        //分页 模糊查询 分类 排序
    public List<Commodity> selectAll(int currentpage,String name,String category , String order);
        //按出版时间 10个
    public List<Commodity> selectByTime();
        //按销量 10个
    public List<Commodity> selectBySalesCount();

    //查找一个
    public Commodity selectById(String commodityId);
    //添加
    public boolean addCommodity(Commodity book);
    //删除
    public boolean deleteCommodity(String id);
    //更新
    public boolean updateCommodity(Commodity book);


}
