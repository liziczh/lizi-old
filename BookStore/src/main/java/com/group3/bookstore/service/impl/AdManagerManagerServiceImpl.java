package com.group3.bookstore.service.impl;

import com.group3.bookstore.mapper.AdManagerMapper;
import com.group3.bookstore.pojo.Ad;
import com.group3.bookstore.service.IAdManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdManagerManagerServiceImpl implements IAdManagerService {
    @Autowired
    AdManagerMapper mapper;
    @Override
    public void addAd(Ad ad) {
        mapper.insertAd(ad);
    }

    @Override
    public void removeAdByAdName(String adName) {
        mapper.deleteAdByAdName(adName);
    }

    @Override
    public void modityAd(Ad ad) {
        mapper.updateAd(ad);
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
