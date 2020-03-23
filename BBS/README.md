# BBS 系统

基于JavaWeb实现的微社区。BBS（Bulletin Board System，电子布告栏系统）。

## BBS 系统功能设计

**用户（User）**：

- √ 登陆（login）：表单验证（form），人类验证（verifyCode）。
- √ 注册（register）：表单验证（form），人类验证（verifyCode），user 数据插入。
- √ 注销（logout）：移除 session 中的用户信息，`session.removeAttribute("user")` 。
- × 自动登陆（autoLogin）：使用 Cookie 保存登录信息。
- × 找回密码（forget）：邮箱验证（mail），表单验证（form），user 数据更新。
- √ 个人主页（homePage）：user 数据查询。
- √ 个人信息设置（userSettings）：表单验证（form），user 数据更新，头像上传。

**帖子（Post）**：

- √ 浏览帖子（postList）：post 数据查询。
- √ 分类（dir）：根据**分类（dir）**条件查询。
- √ 排序（order）：根据**排序方式（order）**条件查询。
- √ 分页（pagenation）：根据**当前页（currentPage）**&**每页数据条数（pageSize）**条件查询。
- √ 读帖（post）：**帖子信息（Post）**&**评论信息（Comment）**。
- √ 发表新帖（addPost）：表单提交（form），post 数据插入。
- √ 编辑帖子（modifyPost）：表单提交（form），post 数据更新。
- √ 删帖（removePost）：post 数据删除（伪删）。
- × 回收站（recycle_bin）：post 伪删数据的存储，30条限制。
- × 点赞（like）：post 数据更新，ajax。
- √ 阅读量（postViews）：post 阅读量+1。
- √ 分享（share）：百度分享。
- × 全局搜索（search）：solr。

**评论（Comment）**：评论帖子 & 评论评论

- √ 添加评论（addComment）：表单提交，comment 数据插入，post 评论数+1，ajax。
- × 删除评论（removeComment）：comment 数据删除，post 评论数-1，ajax。

**关注（Follow）**：用户关注 & 用户粉丝

- × 关注（follow）：follow 数据插入
- × 取消关注（unfollow）：follow 数据删除

**收藏（Collection）**：收藏帖子

- × 收藏帖子（collectPost）：colletion 数据插入。
- × 我的收藏（myCollection）：colletion 数据展示。
- × 取消收藏（cancelCollect）：colletion 数据删除。

## 数据库

**用户（User）**：

| 描述       | name        | 约束         |
| ---------- | ----------- | ------------ |
| 用户ID#    | userId      | pk           |
| 用户名     | username    | not null，uk |
| 密码       | password    | not null     |
| 邮箱       | email       | not null，uk |
| 手机号     | phoneNumber | not null，uk |
| 头像       | avatar      |              |
| 性别       | gender      |              |
| 加入时间   | joinTime    |              |
| 位置       | location    |              |
| 个性签名   | bio         |              |
| 打赏二维码 | reward      |              |
| 背景图     | background  |              |

**帖子（Post）**：

| 描述     | name          | 约束     |
| -------- | ------------- | -------- |
| 帖子ID#  | postId        | pk       |
| 作者ID   | userId        | not null |
| 标题     | postTitle     | not null |
| 内容     | postContent   | not null |
| 创建时间 | createTime    | not null |
| 更新时间 | updateTime    | not null |
| 点赞数   | likeCount     |          |
| 评论数   | commentCount  |          |
| 阅读量   | postViewCount |          |

**评论（Comment）**：

| 描述       | name           | 约束         |
| ---------- | -------------- | ------------ |
| 评论ID#    | commentId      | pk           |
| 评论人ID   | commentatorId  | not null，uk |
| 帖子ID     | postId         | not null，uk |
| 评论内容   | commentContent | not null   |
| 评论时间   | commentTime    | not null     |
| 回复评论id | replyId        |              |

**关注关系（Follow）**：

| 描述        | name      | 约束 |
| ----------- | --------- | ---- |
| 关注关系ID# | follow    |      |
| 读者        | readerId |      |
| 作者        | authorId |      |


**分类（categories）**：

分类id（CategoryID）#，分类名称（CategoryName），



**标签（tags）**：

标签id（tagID）#，标签名（tagName）。







## 总结

我个人负责**帖子模块（Post）**，帖子浏览 / 根据分类&排序&分页查询 / 帖子展示 / 发表新帖 / 编辑帖子 / 删帖 / 阅读量 / 分享。帖子模块基本功能没有问题，可以使用。

**思维问题**：

- 对前后端分离没有清晰认识
- 项目耦合度有点高



---

配个 tomcat 直接 run 就行了......