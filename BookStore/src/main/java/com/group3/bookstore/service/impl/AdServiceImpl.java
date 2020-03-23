package com.group3.bookstore.service.impl;

import com.group3.bookstore.mapper.AdMapper;
import com.group3.bookstore.pojo.Ad;
import com.group3.bookstore.service.IAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdServiceImpl implements IAdService {
    @Autowired
    AdMapper mapper;
    @Override
    public void addAd(Ad ad) {
        mapper.insertAd(ad);
    }

    @Override
    public void removeAdByAdName(String adName) {
        mapper.deleteAdByAdName(adName);
    }

    @Override
    public void modityAd(String adName,String adImg) {
        mapper.updateAd(adName, adImg);
    }

    @Override
    public List<Ad> findAllAd() {
        List<Ad> adList = mapper.getAllAd();
        return adList;
    }

    @Override
    public Ad findAdByAdName() {
        Ad ad = mapper.getAdByAdName();
        return ad;
    }
}
