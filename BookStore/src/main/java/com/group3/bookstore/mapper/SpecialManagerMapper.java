package com.group3.bookstore.mapper;

import com.group3.bookstore.pojo.Special;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SpecialManagerMapper {
    //获取所有的专题
    public List<Special> getAllSpecial();
    //根据专题名称获取当前专题
    public Special getSpecialBySpecialSort(String specialSort);
    //编辑专题
    public void updateSpecial(Special special);
}
