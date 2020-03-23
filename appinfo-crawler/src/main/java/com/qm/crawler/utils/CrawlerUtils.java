package com.qm.crawler.utils;

import com.qm.crawler.constant.AppInfoConstant;
import com.qm.crawler.domain.PackageEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Lizi
 */
public class CrawlerUtils {
    public static final Logger logger = LoggerFactory.getLogger(CrawlerUtils.class);

    /**
     * 关闭爬虫：直接杀死进程，关掉项目。
     */
    public static void shutdown() {
        try {
            String cmd_delete = "ps -ef | grep appInfo-crawler | grep -v grep | awk '{print $2}'  | sed -e \"s/^/kill -9 /g\" | sh -";
            String[] cmds_rm = new String[]{"sh", "-c", cmd_delete};
            Process process = Runtime.getRuntime().exec(cmds_rm);
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("爬取完毕，退出程序");
    }

    /**
     * 判断URL地址是否可连接
     *
     * @param address URL地址
     * @return boolean 是否可连接
     */
    public static boolean isConnect(String address) {
        boolean isConn = false;
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            int status = conn.getResponseCode();
            if (status == AppInfoConstant.HTTP_OK) {
                isConn = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isConn;
    }

}
