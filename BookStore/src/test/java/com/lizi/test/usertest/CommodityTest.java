package com.lizi.test.usertest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-cfg.xml"})
public class CommodityTest {
    @Test
    public void UUIDTest(){
        System.out.println(UUID.randomUUID());
    }

}
