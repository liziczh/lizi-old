package com.group3.bookstore.service;

import com.group3.bookstore.pojo.Special;

import java.util.List;

public interface ISpecialManagerService {
    //获取所有的专题
    public List<Special> findAllSpecial();
    //根据专题名称获取当前专题
    public Special findSpecialBySpecialSort(String specialSort);
    //编辑专题
    public void modifySpecial(Special special);
}
