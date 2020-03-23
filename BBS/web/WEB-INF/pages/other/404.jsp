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
  <title>404 - 熊猫客</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="Panda,java,互联网社区">
  <meta name="description" content="Panda社区">
  <link rel="icon" href="/res/images/logo.png">
  <link rel="stylesheet" href="/res/layui/css/layui.css">
  <link rel="stylesheet" href="/res/css/global.css">
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
	<div class="fly-panel">
	  <div class="fly-none">
	    <h2><i class="iconfont icon-404"></i></h2>
	    <p>页面或者数据被 纸飞机 运到火星了，啥都看不到了…</p>
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