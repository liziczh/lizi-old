package com.lizi.test.usertest;

import com.group3.bookstore.mapper.FavoritesItemMapper;
import com.group3.bookstore.pojo.FavoritesItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-cfg.xml"})
public class FavoritesMapperTest {
    @Autowired
    FavoritesItemMapper favoritesItemMapper;

    @Test
    public void getFavoritesItemByUserId(){
        List<FavoritesItem> favoritesItemList = favoritesItemMapper.getFavoritesItemByUserId("1");
        System.out.println(favoritesItemList);
    }
    @Test
    public void deleteFavoritesItem(){
        favoritesItemMapper.deleteFavoritesItem("1","1");
    }


}
