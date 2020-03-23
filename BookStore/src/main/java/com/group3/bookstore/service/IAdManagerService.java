package com.group3.bookstore.service;

import com.group3.bookstore.pojo.Ad;

import java.util.List;

public interface IAdManagerService {
    //增一条广告
    public void addAd(Ad ad);
    //根据广告名称删除一条广告
    public void removeAdByAdName(String adName);
    //修改广告信息
    public void modityAd(Ad ad);
    //查询所有的广告
    public List<Ad> findAllAd();
    //根据广告名称查询某一广告
    public Ad findAdByAdName();
}
