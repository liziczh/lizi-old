package com.lizi.test.usertest;

import com.group3.bookstore.service.IFavoritesService;
import com.group3.bookstore.service.impl.FavoritesServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-cfg.xml"})
public class FavoritesServiceTest {
    @Autowired
    private IFavoritesService favoritesService;

    @Test
    public void removeFavoritesItemTest(){
        favoritesService.removeFavoritesItem("1","2");
    }

}
