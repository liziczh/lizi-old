# AppInfoCrawler
使用 WebMagic 编写 Java 爬虫（根据App包名爬取App信息，[豌豆荚>应用宝]）。

WebMagic：
- Downloader：下载页面。
- PageProessor：解析页面。
- Scheduler：管理待抓取的URL。
- Pipeline：数据处理。

功能：
- URL检测。
- 断点续爬。
- 防止ID中断。
- 定时检测爬虫状态。

脚本：
- 启动爬虫：startup。
- 爬虫状态：status。
- 关闭爬虫：shutdown。