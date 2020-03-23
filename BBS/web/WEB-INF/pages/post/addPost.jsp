<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LiziChen
  Date: 2018/7/28
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>发表新帖 - Mouke</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="Mouke,blog,博客分享社区，写文章">
  <meta name="description" content="猫客网">
  <link rel="icon" href="${pageContext.request.contextPath}/res/images/logo.png">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/layui/css/layui.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/global.css">
</head>
<body>

<div class="fly-header layui-bg-black">
  <div class="layui-container">
    <a class="fly-logo" href="${pageContext.request.contextPath}/index.jsp">
      <img src="${pageContext.request.contextPath}/res/images/logo.png" alt="Panda">
    </a>
    <ul class="layui-nav fly-nav layui-hide-xs">
      <li class="layui-nav-item">
        <a href="${pageContext.request.contextPath}/index.jsp"><i class="iconfont icon-shouye"></i>首页</a>
      </li>
      <%--搜索--%>
      <li class="layui-nav-item layui-hide-xs">
        <img src="${pageContext.request.contextPath}/res/images/search.png" alt="Panda">
        <input type="text" style="background:rgba(0, 0, 0, 0); border: 0px;position: absolute;right:20px;top: 22px">
        <img src="${pageContext.request.contextPath}/res/images/searchAction.png" style="position: relative;right:30px">
      </li>
    </ul>
    <c:choose>
      <c:when test="${sessionScope.user==null}"><!--如果 -->
        <%--未登录用户信息--%>
        <ul class="layui-nav fly-nav-user">
          <!-- 未登入的状态 -->
          <li class="layui-nav-item">
            <a class="iconfont icon-touxiang" href="${pageContext.request.contextPath}/uiServlet?method=loginUI"></a>
          </li>
          <li class="layui-nav-item">
            <a href="${pageContext.request.contextPath}/uiServlet?method=loginUI">登入</a>
          </li>
          <li class="layui-nav-item">
            <a href="${pageContext.request.contextPath}/uiServlet?method=registerUI">注册</a>
          </li>
        </ul>
      </c:when>
      <c:otherwise>  <!--否则 -->
        <%--已登录用户信息--%>
        <ul class="layui-nav fly-nav-user">
            <%--写文章--%>
          <li class="layui-nav-item">
            <a id="write" href="${pageContext.request.contextPath}/uiServlet?method=addPostUI"><i class="iconfont icon-tianjia"></i>随笔</a>
          </li>
          <!-- 登入后的状态 -->
          <li class="layui-nav-item">
            <a class="fly-nav-avatar" href="javascript:;">
              <cite class="layui-hide-xs">${sessionScope.user.username}</cite>
              <img src="${pageContext.request.contextPath}/res/images/avatar/${sessionScope.user.avatar}">
            </a>
            <dl class="layui-nav-child">
              <dd><a href="${pageContext.request.contextPath}/userServlet?method=getUserAndPostByUserId&userId=${sessionScope.user.userId}"><i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>个人主页</a></dd>
              <dd><a href="${pageContext.request.contextPath}/uiServlet?method=setUI"><i class="layui-icon">&#xe620;</i>基本设置</a></dd>
              <hr style="margin: 5px 0;">
              <dd><a href="${pageContext.request.contextPath}/userServlet?method=logout" style="text-align: center;">退出</a></dd>
            </dl>
          </li>

        </ul>
      </c:otherwise>
    </c:choose>
  </div>
</div>

<div class="layui-container fly-marginTop">
  <div class="fly-panel" pad20 style="padding-top: 5px;">
    <!--<div class="fly-none">没有权限</div>-->
    <div class="layui-form layui-form-pane">
      <div class="layui-tab layui-tab-brief" lay-filter="user">
        <ul class="layui-tab-title">
          <li class="layui-this">发表新帖</li>
        </ul>
        <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
          <div class="layui-tab-item layui-show">
            <form action="${pageContext.request.contextPath}/postServlet" method="post">
              <input type="hidden" name="method" value="addPost">
              <div class="layui-row layui-col-space15 layui-form-item">
                <div class="layui-col-md3">
                  <label class="layui-form-label">所在专栏</label>
                  <div class="layui-input-block">
                    <select lay-verify="required" name="class" lay-filter="column"> 
                      <option></option>
                      <option value="互联网">互联网</option>
                      <option value="读书">读书</option>
                      <option value="电影">电影</option>
                      <option value="音乐">音乐</option>
                      <option value="旅行">旅行</option>
                      <option value="小说">小说</option>
                      <option value="杂谈">杂谈</option>
                      <option value="其他">其他</option>
                    </select>
                  </div>
                </div>
                <div class="layui-col-md9">
                  <label for="L_title" class="layui-form-label">标题</label>
                  <div class="layui-input-block">
                    <input type="text" id="L_title" name="title" required lay-verify="required" placeholder="文章标题" autocomplete="off" class="layui-input">
                  </div>
                </div>
              </div>
              <div class="layui-form-item layui-form-text">
                <div class="layui-input-block">
                  <textarea id="L_content" name="content" required lay-verify="required" placeholder="文章内容" class="layui-textarea fly-editor" style="height: 260px;"></textarea>
                </div>
              </div>
              <div class="layui-form-item">
                <input type="submit" value="立即发布" class="layui-btn" lay-filter="*">
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>


<div class="fly-footer">
  <p><a href="${pageContext.request.contextPath}/index.jsp" target="_blank">Panda 社区</a> 2018 &copy; <a href="http://www.layui.com/" target="_blank"> By layUI </a></p>
  <p><a href="${pageContext.request.contextPath}/index.jsp" target="_blank"> 超级无敌IT精英团队制作 </a> &copy; <a href="https://www.liziczh.com/" target="_blank"> 开发者1号：Lizi</a></p>
</div>


<script src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
<script>
layui.config({
  version: "3.0.0"
  ,base: '${pageContext.request.contextPath}/res/mods/'
}).extend({
  fly: 'index'
}).use('fly');
</script>

</body>
</html>