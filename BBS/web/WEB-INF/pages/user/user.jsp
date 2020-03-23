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
  <title>个人主页 - ${requestScope.user.username} - 熊猫客</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="Panda,java,互联网社区">
  <meta name="description" content="Panda社区">
  <link rel="icon" href="${pageContext.request.contextPath}/res/images/logo.png">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/layui/css/layui.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/global.css">
</head>
<body style="margin-top: 65px;">

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

<div class="layui-container">
  <%--用户信息--%>
  <div class="layui-col-md12 fly-home-jie">
    <div class="fly-home fly-panel" style="background-image:url('${pageContext.request.contextPath}/res/images/bg-image/${requestScope.user.background}');">
  <img src="${pageContext.request.contextPath}/res/images/avatar/${requestScope.user.avatar}" alt="${requestScope.user.username}">
  <i class="iconfont icon-renzheng" title="认证用户"></i>
  <h1>
    ${requestScope.user.username}
    <%--性别--%>
      <c:if test="${requestScope.user.gender == '男'}">
        <i class="iconfont icon-nan"></i>
      </c:if>
      <c:if test="${requestScope.user.gender == '女'}">
        <i class="iconfont icon-nv"></i>
      </c:if>

  </h1>
  <%--个人介绍--%>
  <p style="padding: 10px 0; color: #5FB878;">${requestScope.user.bio}</p>

  <p class="fly-home-info">
    <i class="iconfont icon-shijian"></i><span>${requestScope.user.joinTime} 加入</span>
    <c:if test="${requestScope.user.location != null}">
      <i class="iconfont icon-chengshi"></i><span>${requestScope.user.location}</span>
    </c:if>

  </p>
      <c:if test="${requestScope.user.bio != null}">
        <p class="fly-home-sign">bio：${requestScope.user.bio}</p>
      </c:if>

</div>
  </div>
  <%--用户近期文章--%>
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md12 fly-home-jie">
      <div class="fly-panel">
        <h3 class="fly-panel-title">${requestScope.user.username}的随笔</h3>
        <ul class="jie-row">
          <c:choose>
            <c:when test="${userPostList == null}">
              <%--如果没有文章--%>
              <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><i style="font-size:14px;">没有发表任何求解</i></div>
            </c:when>
            <c:otherwise>
              <%--否则输出所有文章--%>
              <c:forEach items="${userPostList}" var="post">
                <li>
                  <a href="${pageContext.request.contextPath}/postServlet?method=getPostDetailByPostId&postId=${post.postId}" class="jie-title"> ${post.postTitle}</a>
                  <i>${post.createTime}</i>
                  <em>${post.postViewCount}阅/${post.commentCount}答</em>
                </li>
              </c:forEach>
            </c:otherwise>
          </c:choose>

        </ul>

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