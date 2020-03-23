package com.group3.bookstore.mapper;

import com.group3.bookstore.pojo.Ad;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdManagerMapper {
    //增一条广告
    public void insertAd(Ad ad);
    //根据广告名称删除一条广告
    public void deleteAdByAdName(String adName);
    //修改广告信息
    public void updateAd(Ad ad);
    //查询所有的广告
    public List<Ad> getAllAd();
    //根据广告名称查询某一广告
    public Ad getAdByAdName();
}
