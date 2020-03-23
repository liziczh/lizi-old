package com.group3.bookstore.service.impl;

import com.group3.bookstore.mapper.SpecialMapper;
import com.group3.bookstore.pojo.Special;
import com.group3.bookstore.service.ISpecialService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SpecialServiceImpl implements ISpecialService {
    @Autowired
    SpecialMapper mapper;

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
