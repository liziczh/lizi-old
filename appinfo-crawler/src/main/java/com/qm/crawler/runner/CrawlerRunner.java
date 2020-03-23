package com.qm.crawler.runner;

import com.qm.crawler.constant.AppInfoConstant;
import com.qm.crawler.crawler.AppInfoDownloader;
import com.qm.crawler.crawler.AppInfoPageProcesser;
import com.qm.crawler.crawler.AppInfoPipeline;
import com.qm.crawler.domain.PackageEntity;
import com.qm.crawler.repository.AppRepository;
import com.qm.crawler.repository.PackageRepository;
import com.qm.crawler.utils.CrawlerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Lizi
 */
@Component
public class CrawlerRunner implements ApplicationRunner {
    public static final Logger logger = LoggerFactory.getLogger(CrawlerRunner.class);

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private AppInfoPageProcesser appInfoPageProcesser;

    @Autowired
    private AppInfoPipeline appInfoPipeline;

    @Override
    public void run(ApplicationArguments args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            Spider spider = Spider.create(appInfoPageProcesser);

            @Override
            public void run() {
                // 未运行状态，则启动爬虫。
                if (!spider.getStatus().equals(Spider.Status.Running)) {
                    spider = Spider.create(appInfoPageProcesser).setDownloader(new AppInfoDownloader()).addPipeline(appInfoPipeline);
                    spider.addUrl(AppInfoConstant.WDJ_URL_PREFIX).thread(5).runAsync();
                    logger.info("启动爬虫");
                }
            }
        }, 0, 1000);
    }

    private String getInitUrl() {
        long packageCount = packageRepository.count();
        long appCount = appRepository.count();
        if (packageCount > appCount) {
            int id = (int) (appCount + 1);
            PackageEntity packageEntity = getPackageEntity(id);
            String address = AppInfoConstant.WDJ_URL_PREFIX + packageEntity.getPackageName();
            return address;
        } else {
            CrawlerUtils.shutdown();
            return null;
        }
    }

    /**
     * 递归获取包实体，避免ID中断问题
     *
     * @param id 包实体ID
     * @return PackageEntity 包实体
     */
    private PackageEntity getPackageEntity(int id) {
        if (packageRepository.findById(id).isPresent()) {
            return packageRepository.findById(id).get();
        } else {
            id++;
            return getPackageEntity(id);
        }
    }

}
