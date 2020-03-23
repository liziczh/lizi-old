package com.group3.bookstore.service.impl;

import com.group3.bookstore.mapper.SpecialManagerMapper;
import com.group3.bookstore.pojo.Special;
import com.group3.bookstore.service.ISpecialManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SpecialManagerManagerServiceImpl implements ISpecialManagerService {
    @Autowired
    SpecialManagerMapper mapper;

    @Override
    public List<Special> findAllSpecial() {
        List<Special> specialList = mapper.getAllSpecial();
        return specialList;
    }

    @Override
    public Special findSpecialBySpecialSort(String specialSort) {
        Special special = mapper.getSpecialBySpecialSort(specialSort);
        return special;
    }

    @Override
    public void modifySpecial(Special special) {
        mapper.updateSpecial(special);
    }
}
