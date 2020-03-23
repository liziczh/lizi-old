package com.qm.crawler.crawler;

import com.qm.crawler.constant.AppInfoConstant;
import com.qm.crawler.domain.AppEntity;
import com.qm.crawler.domain.PackageEntity;
import com.qm.crawler.repository.AppRepository;
import com.qm.crawler.repository.PackageRepository;
import com.qm.crawler.utils.CrawlerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * WebMagic流程：给定种子URL，启动爬虫，爬取页面->添加NextURL入队列->爬取页面->...。
 * AppInfoCrawler需求：启动爬虫，给定URL->爬取页面->给定URL->爬取页面...。
 * AppInfoCrawler流程：启动爬虫，初始化给定URL，爬取页面->添加NextURL入队列->爬取页面->
 * - 初始化URL
 * 1. 检验URL是否可连接
 * 2. 下载页面
 * 3. 检测页面是否正常
 * 4. 爬取数据
 * 5. 保存数据
 * 6. 添加下一条URL
 * 循环1-6。
 */
@Component
public class AppInfoPageProcesser implements PageProcessor {

    public static final Logger logger = LoggerFactory.getLogger(AppInfoPageProcesser.class);

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private AppRepository appRepository;

    private Site site = Site.me().setCharset("utf-8").setRetryTimes(1).setSleepTime(10);
    private boolean isInited = false; // 是否已初始化爬虫
    private int id; // 数据序号
    private int totalNumber; // 总数
    private PackageEntity packageEntity; // 请求数据：包实体

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        if (!isInited) {
            // 初始化
            init(page);
        } else {
            // 爬取数据
            if (page.getRequest().getUrl().startsWith(AppInfoConstant.WDJ_URL_PREFIX)) {
                // 判断页面是否可用
                String wdjAppName = page.getHtml().xpath("//div[@class='app-info']/p[@class='app-name']/span[@class='title']/text()").toString();
                if (wdjAppName != null && !"".equals(wdjAppName)) {
                    crawlFromWDJ(page);
                } else {
                    // 转应用宝URL前往查询
                    String address = AppInfoConstant.YYB_URL_PREFIX + packageEntity.getPackageName();
                    page.addTargetRequest(address);
                }
            } else if (page.getRequest().getUrl().startsWith(AppInfoConstant.YYB_URL_PREFIX)) {
                // 判断页面是否可用
                String yybAppName = page.getHtml().xpath("//div[@class='det-ins-data']/div[@class='det-name']/div[@class='det-name-int']/text()").toString();
                if (yybAppName != null && !"".equals(yybAppName)) {
                    crawlFromYYB(page);
                } else {
                    crawlNotFound(page);
                }
            }
        }
    }

    /**
     * 初始化URL
     */
    private void init(Page page) {
        long packageCount = packageRepository.count();
        long appCount = appRepository.count();
        // 判断是否已经爬完
        if (packageCount > appCount) {
            // 未爬完，给定一个URL
            id = (int) (appCount + 1);
            totalNumber = (int) packageCount;
            packageEntity = getPackageEntity(id);
            String address = AppInfoConstant.WDJ_URL_PREFIX + packageEntity.getPackageName();
            page.addTargetRequest(address);
            isInited = true;
        } else {
            CrawlerUtils.shutdown();
        }
    }

    /**
     * 添加下一条URL
     */
    private void addNextURL(Page page) {
        long appCount = appRepository.count();
        // 判断是否已经爬完
        if (totalNumber > appCount) {
            // 添加下一条URL
            id++;
            packageEntity = getPackageEntity(id);
            // 豌豆夹URL
            String address = AppInfoConstant.WDJ_URL_PREFIX + packageEntity.getPackageName();
            page.addTargetRequest(address);
        } else {
            CrawlerUtils.shutdown();
        }
    }

    /**
     * 为PackageEntity赋值：解决ID中断问题
     */
    private PackageEntity getPackageEntity(int id) {
        if (packageRepository.findById(id).isPresent()) {
            return packageRepository.findById(id).get();
        } else {
            id++;
            return getPackageEntity(id);
        }
    }

    /*-------------------------------爬取数据，数据后处理，存入数据库----------------------------------------------------*/

    /**
     * 爬取豌豆荚APP数据
     */
    private void crawlFromWDJ(Page page) {
        // step1 爬取数据
        String appName = page.getHtml().xpath("//div[@class='app-info']/p[@class='app-name']/span[@class='title']/text()").toString();
        List<String> categoryList = page.getHtml().xpath("//div[@class='infos']/dl[@class='infos-list']/dd[@class='tag-box']/a/text()").all();
        String appCompany = page.getHtml().xpath("//div[@class='infos']/dl[@class='infos-list']/dd/span[@class='dev-sites']/text()").toString();
        String appIcon = page.getHtml().xpath("//div[@class='app-icon']/img/@src").toString();
        String source = "豌豆荚";
        // step2 存入数据库
        String categories = "";
        if (categoryList != null && !"".equals(categoryList.get(0))) {
            for (String category : categoryList) {
                categories += category + ";";
            }
        } else {
            categories = null;
        }
        AppEntity appEntity = new AppEntity(source, packageEntity.getPackageName(), appName, packageEntity.getAppName(), categories, appCompany, appIcon);
        appRepository.save(appEntity);
        logger.info("[ID=" + id + "]: " + appEntity);
        // step3 添加下一条URL
        addNextURL(page);
    }

    /**
     * 爬取应用宝APP数据
     */
    private void crawlFromYYB(Page page) {
        // step1 爬取数据
        String appName = page.getHtml().xpath("//div[@class='det-ins-data']/div[@class='det-name']/div[@class='det-name-int']/text()").toString();
        List<String> categoryList = page.getHtml().xpath("//div[@class='det-ins-data']/div[@class='det-insnum-line']/div[@class='det-type-box']/a/text()").all();
        String appCompany = page.getHtml().xpath("//div[@class='det-othinfo-container J_Mod']/div[6]/text()").toString();
        String appIcon = page.getHtml().xpath("//div[@class='det-icon']/img/@src").toString();
        String source = "应用宝";
        // step2 存入数据库
        String categories = "";
        if (categoryList != null && !"".equals(categoryList.get(0))) {
            for (String category : categoryList) {
                categories += category + ";";
            }
        } else {
            categories = null;
        }
        AppEntity appEntity = new AppEntity(source, packageEntity.getPackageName(), appName, packageEntity.getAppName(), categories, appCompany, appIcon);
        appRepository.save(appEntity);
        logger.info("[ID=" + id + "]: " + appEntity);
        // step3 添加下一条URL
        addNextURL(page);
    }

    /**
     * 未爬到信息，保存请求包信息
     */
    private void crawlNotFound(Page page) {
        // step2 存入数据库
        AppEntity appEntity = new AppEntity();
        appEntity.setPackageName(packageEntity.getPackageName());
        appEntity.setAppName(packageEntity.getAppName());
        appRepository.save(appEntity);
        logger.info("[ID=" + id + "]: " + appEntity);
        // step3 添加下一条URL
        addNextURL(page);
    }

}
