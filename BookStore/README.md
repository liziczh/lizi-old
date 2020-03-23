## BookStore

### 系统功能

#### 用户（User）

**1. 登陆（login）**：

- 表单验证（form）：Ajax
- 手机登录（loginByPhoneNumber）：手机验证
- 邮箱登陆（loginByEmail）：邮箱验证
- 人类验证（verifyCode）：图片验证码/滑块验证码

**2. 注册（register）**：

- 表单验证（form）：Ajax
- 手机注册（registerByPhoneNumber）：手机验证
- 邮箱注册（registerByEmail）：邮箱验证
- 人类验证（verifyCode）：图片验证码/滑块验证码

**3. 注销（logout）**：

- 移除 session 中的用户信息。

**4. 自动登陆（autoLogin）**：

- 使用 Cookie 保存登录信息。

**5. 找回密码（forget）**：

- 邮箱验证（verifyByEmail）
- 邮箱验证（verifyByPhoneNumber）
- 表单验证（form）

**6. 个人信息设置（userSettings）**：

- 表单验证（form）：Ajax
- 文件上传：头像上传


#### 收藏夹（Favorites）

- 浏览收藏夹（getFavoritesItemByUserId）：
- 加入收藏夹（addFavoritesItem）：
- 移除收藏项（removeFavoritesItem）：
- 收藏/取消收藏（favorites）：Toggle 按钮，
- 判断是否收藏（isFavorites）：
- 删除所选收藏项（removeSelectedFavoritesItem）：


#### 购物车（Cart）

- 浏览购物车（getCartItemByUserId）：
- 加入购物车（addCartItem）：
- 移除购物项（removeCartItem）：
- 购物项数量+1-1
- 删除所选收藏项（removeSelectedCartItem）：
- 清空购物车（emptyCart）：


#### 商品（Commodity）

- 商品列表展示（getCommodity）：根据分类 / 销量 / 最热 / 最新条件查询。

- 商品详细信息展示（getCommodityById）：主键查询，书籍名称，图片，简介，目录等。

#### 评论（Comment）

- 添加评论（addComment）：
- 删除评论（removeComment）：
- 查看评论（commentDetail）：

#### 订单（Order）

- 查询所有订单（getOrderByUserId）
- 订单详情（getOrderByOrderId）：
- 新增订单（addOrder）：
- 更新订单（updateOrder）：
- 删除订单（removeOrder）：

#### 收货信息（receiver）

- 查看收货信息列表（getReceiverByUserId）：
- 查看收获信息项（getReceiverByReceiverId）：
- 添加收货信息项（addReceiver）：
- 更新收货信息项（modifyReceiver）：
- 删除收货信息项（removeReceiver）：

### 数据库设计

#### 用户（user）：

| 描述     | 名称     | 类型    | 约束         |
| -------- | -------- | ------- | ------------ |
| 用户ID#  | userId   | varchar | pk           |
| 用户名   | username | varchar | not null，uk |
| 密码     | password | varchar | not null     |
| 邮箱     | email    | varchar | not null，uk |
| 手机号   | phoneNumber | varchar | not null，uk |
| 头像     | avatar   | varchar |              |
| 性别     | gender   | varchar |              |
| 加入时间 | joinTime | varchar |              |
| 个人介绍 | bio      | varchar |              |
| 默认收货地址 | defaultReceiverId | varchar |              |
| Token    | token    | varchar |              |

#### 购物项（cartItem）：

| 描述     | 名称           | 类型    | 约束     |
| -------- | -------------- | ------- | -------- |
| 用户ID#  | userId         | varchar | pk       |
| 商品ID   | commodityId    | varchar | pk       |
| 商品数量 | commodityCount | int     | not null |

#### 收藏项（favoritesItem）：

| 描述      | 名称           | 类型    | 约束     |
| --------- | -------------- | ------- | -------- |
| 用户ID# | userId | varchar | pk |
| 商品ID    | commodityId    | varchar | pk |

#### **订单（orderForm）**：

| 描述    | 名称       | 类型    | 约束 |
| ------- | ---------- | ------- | ---- |
| 订单ID# | orderId | varchar | pk |
| 用户ID    | userId | varchar | fk |
| 收货信息Id# | receiverId | varchar | fk |
| 订单状态 | orderStatus | varchar | not null |
| 下单时间 | orderTime | varchar |  |
| 支付时间 | payTime | varchar |  |
| 发货时间 | sendTime | varchar |  |
| 收货时间    | receiverTime | varchar |          |
| 订单总额 | totalPrice | decimal | not null |
| 备注 | remark              | varchar |          |

订单状态：待付款，待发货，待收货，待评价，退款。

#### 收货信息（receiver）：

| 描述       | 名称      | 类型    | 约束         |
| ---------- | --------- | ------- | ----------- |
| 收货信息ID | receiverId | varchar | pk          |
| 用户ID     | userId    | varchar | fk           |
| 收货人姓名  | receiverName | varchar | not null  |
| 收货电话 | receiverPhoneNumber | varchar | not null |
| 省份       | province  | varchar | not null |
| 市         | city      | varchar | not null |
| 县         | county    | varchar | not null |
| 街道       | street    | varchar | not null |

#### **订单项（orderItem）**：

| 描述        | 名称            | 类型    | 约束     |
| ---------- | --------------- | ------- | -------- |
| 订单ID#     | orderId         | varchar |  pk    |
| 商品ID      | commodityId     | varchar | not null |
| 商品数量     | commodityCount  | varchar | not null |

#### **商品（commodity）**：

| 描述     | 名称          | 类型       | 约束       |
| -------- | ----------- | --------- | ---------- |
| 商品ID# | commodityId | varchar    | pk          |
| 商品名称 | commodityName | varchar | not null |
| 商品描述 | commodityDescription | varchar | not null |
| 商品图片 | commodityImg | varchar | not null |
| 分类ID | categoryId | varchar | not null |
| 商品简介 | commodityIntroduction | varchar | not null |
| 商品目录 | commodityContents | varchar | not null |
| 商品定价 | commodityPrice | decimal | not null |
| 商品抢购价 | commodityPurchasePrice | decimal |  |
| 作者 | authorName | varchar | not null |
| 出版社 | publishHouse | varchar | not null |
| 出版时间 | publishDate | varchar | not null |
| 商品数量 | commodityCount | int |          |
| 销售量 | salesCount | int |          |
| 收藏量 | collectCount | int |              |
| 评论数 | commentCount | int |          |

#### **分类（category）**：

| 描述     | 名称              | 类型    | 约束         |
| -------- | ----------------- | ------- | ------------ |
| 分类ID#  | categoryId        | varchar | pk           |
| 分类名称 | categoryName      | varchar | not null，uk |
| 父分类ID | parrentCategoryId | varchar | not null，uk |

#### **评论（comment）**：

| 描述     | 名称             | 类型    | 约束     |
| -------- | ---------------- | ------- | -------- |
| 评论ID#  | commentId        | varchar | pk       |
| 用户ID   | userId           | varchar | not null |
| 书籍ID   | commodityId      | varchar | not null |
| 评分     | star             | int     | not null |
| 评论内容 | commentContent   | varchar | not null |
| 评论时间 | commentDate      | varchar | not null |
| 父评论ID | parrentCommentId | varchar |          |
| 点赞数   | likeCount        | int     |          |

#### **点赞（like）**：

| 描述   | 名称      | 类型    | 约束 |
| ------ | --------- | ------- | ---- |
| 用户ID | userId    | varchar | pk   |
| 评论ID | commentId | varchar | pk   |

#### **广告（ad）**：

| 描述     | 名称   | 类型    | 约束     |
| -------- | ------ | ------- | -------- |
| 广告ID   | adId   | varchar | pk       |
| 广告简介 | adName | varchar | not null |
| 广告图片 | adImg  | varchar | not null |

#### 专题（special）

| 描述     | 名称        | 类型    | 约束     |
| -------- | ----------- | ------- | -------- |
| 专题ID   | specialId   | varchar | pk       |
| 专题分类 | specialSort | varchar | not null |
| 轮播图   | sowingMap   | varchar | not null |