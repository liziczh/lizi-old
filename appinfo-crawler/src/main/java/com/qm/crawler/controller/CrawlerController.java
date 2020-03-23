package com.qm.crawler.controller;

import com.qm.crawler.repository.AppRepository;
import com.qm.crawler.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/crawler")
public class CrawlerController {

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private AppRepository appRepository;

    @GetMapping("/status")
    public String getStatus() {
        long packageCount = packageRepository.count();
        long appCount = appRepository.count();
        double rate = new BigDecimal((float) appCount / packageCount * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return "Current:" + appCount + ",Total:" + packageCount + ",Rate:" + rate + "%";
    }

}
