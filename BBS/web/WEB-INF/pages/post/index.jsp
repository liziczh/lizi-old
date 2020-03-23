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
    <title>猫客 - Mouke</title>
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
              <a href="${pageContext.request.contextPath}/index.jsp"><i class="iconfont icon-shouye"></i>发现</a>
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

    <div class="fly-panel fly-column layui-container">
        <ul class="layui-clear tab">
            <li class="layui-hide-xs"><a href="${pageContext.request.contextPath}/postServlet?method=init">首页</a></li>
            <li class="layui-hide-xs"><a href="${pageContext.request.contextPath}/postServlet?method=getPostDetailByDirAndOrder&dir=互联网&order=${sessionScope.order}&currentPage=1">互联网</a></li>
            <li class="layui-hide-xs"><a href="${pageContext.request.contextPath}/postServlet?method=getPostDetailByDirAndOrder&dir=读书&order=${sessionScope.order}&currentPage=1">读书</a></li>
            <li class="layui-hide-xs"><a href="${pageContext.request.contextPath}/postServlet?method=getPostDetailByDirAndOrder&dir=电影&order=${sessionScope.order}&currentPage=1">电影</a></li>
            <li class="layui-hide-xs"><a href="${pageContext.request.contextPath}/postServlet?method=getPostDetailByDirAndOrder&dir=音乐&order=${sessionScope.order}&currentPage=1">音乐</a></li>
            <li class="layui-hide-xs"><a href="${pageContext.request.contextPath}/postServlet?method=getPostDetailByDirAndOrder&dir=旅行&order=${sessionScope.order}&currentPage=1">旅行</a></li>
            <li class="layui-hide-xs"><a href="${pageContext.request.contextPath}/postServlet?method=getPostDetailByDirAndOrder&dir=小说&order=${sessionScope.order}&currentPage=1">小说</a></li>
            <li class="layui-hide-xs"><a href="${pageContext.request.contextPath}/postServlet?method=getPostDetailByDirAndOrder&dir=杂谈&order=${sessionScope.order}&currentPage=1">杂谈</a></li>
            <li class="layui-hide-xs"><a href="${pageContext.request.contextPath}/postServlet?method=getPostDetailByDirAndOrder&dir=其他&order=${sessionScope.order}&currentPage=1">其他</a></li>
            <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><span class="fly-mid"></span></li>
            <!-- 用户登入后显示 -->
            <c:if test="${sessionScope.user != null}">
                <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="${pageContext.request.contextPath}/uiServlet?method=indexUI">我发表的贴</a></li>
                <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="${pageContext.request.contextPath}/uiServlet?method=indexUI">我收藏的贴</a></li>
            </c:if>
        </ul>
    </div>

    <div class="layui-container">
      <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">
          <div class="fly-panel" style="margin-bottom: 0;">
            <div class="fly-panel-title fly-filter">
                <c:choose>
                    <c:when test="${sessionScope.dir == ''}">
                        <a href="#" class="layui-this">综合</a>
                    </c:when>
                    <c:otherwise>
                        <a href="#" class="layui-this">${sessionScope.dir}</a>
                    </c:otherwise>
                </c:choose>
              <span  class="fly-filter-right layui-hide-xs tab">
                <a class="" href="${pageContext.request.contextPath}/postServlet?method=getPostDetailByDirAndOrder&dir=${sessionScope.dir}&order=按最新&currentPage=1" >按最新</a>
                <span class="fly-mid"></span>
                <a href="${pageContext.request.contextPath}/postServlet?method=getPostDetailByDirAndOrder&dir=${sessionScope.dir}&order=按热议&currentPage=1">按热议</a>
              </span>
            </div>
            <ul class="fly-list">
               <c:forEach items="${sessionScope.allPostDetailList}" var="postDetail">
                    <li>
                        <%--头像--%>
                        <a href="${pageContext.request.contextPath}/userServlet?method=getUserAndPostByUserId&userId=${postDetail.authorId}" class="fly-avatar">
                            <img src="${pageContext.request.contextPath}/res/images/avatar/${postDetail.authorAvatar}" alt="作者:${postDetail.authorName}">
                        </a>
                        <h2>
                            <%--标题--%>
                            <a href="${pageContext.request.contextPath}/postServlet?method=getPostDetailByPostId&postId=${postDetail.postId}">
                                ${postDetail.postTitle}
                            </a>
                        </h2>
                        <div class="fly-list-info">
                            <%--作者名--%>
                            <a href="${pageContext.request.contextPath}/userServlet?method=getUserAndPostByUserId&userId=${postDetail.authorId}" link>
                                <cite>${postDetail.authorName}</cite>
                            </a>
                            <%--创建时间--%>
                            <span>${postDetail.createTime}</span>
                            <%--点赞--%>
                            <span class="fly-list-kiss layui-hide-xs" title="点赞">
                                <i class="iconfont icon-zan"></i>
                                <%--点赞数--%>
                                ${postDetail.getLikeCount()}
                            </span>
                            <%--评论--%>
                            <span class="fly-list-nums">
                                <i class="iconfont icon-pinglun1" title="评论"></i>
                                    <%--评论数--%>
                                    ${postDetail.commentCount}
                            </span>
                        </div>
                    </li>
               </c:forEach>
            </ul>

            <div style="text-align: center">
              <div class="laypage-main">
                  <a href="${pageContext.request.contextPath}/postServlet?method=prevPage" >上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
                  <span>${sessionScope.currentPage}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                  <a href="${pageContext.request.contextPath}/postServlet?method=nextPage" >下一页</a>
              </div>
            </div>
          </div>
        </div>

        <div class="layui-col-md4">
          <dl class="fly-panel fly-list-one">
            <dt class="fly-panel-title">本周热议</dt>
              <c:forEach items="${sessionScope.allPostDetailList}" var="postDetail" begin="0" end="4">
                  <dd>
                      <a href="${pageContext.request.contextPath}/postServlet?method=getPostDetailByPostId&postId=${postDetail.postId}">${postDetail.postTitle}</a>
                      <span><i class="iconfont icon-pinglun1"></i> ${postDetail.commentCount}</span>
                  </dd>
              </c:forEach>
          </dl>

          <div class="fly-panel">
            <div class="fly-panel-title">
              广告
            </div>
            <div class="fly-panel-main">
              <a href="" target="_blank" class="fly-zanzhu" style="background-color: #393D49;">虚席以待</a>
            </div>
          </div>

          <div class="fly-panel fly-link">
            <h3 class="fly-panel-title">友情链接</h3>
            <dl class="fly-panel-main">
                <dd><a href="http://www.layui.com/" target="_blank">layui</a><dd>
                <dd><a href="https://liziczh.com/" target="_blank">liziczh</a><dd>
                <dd><a href="mailto:liziczh@qq.com" class="fly-link">申请友链</a><dd>
            </dl>
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
        layui.use(['jquery'], function(){
            var $ = jQuery = layui.$;

            // 点击变色
            $(".tab li").on("click",function () {
                console.log($(this));
                $(this).addClass("layui-this").siblings().removeClass("layui-this");
            })

        });
    </script>
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
