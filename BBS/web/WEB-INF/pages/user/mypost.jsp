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
    <title>我的文章 - Mouke</title>
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

<div class="layui-container fly-marginTop fly-user-main">
    <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
        <li class="layui-nav-item">
            <a href="${pageContext.request.contextPath}/userServlet?method=getUserAndPostByUserId&userId=${sessionScope.user.userId}">
                <i class="layui-icon">&#xe770;</i>
                我的主页
            </a>
        </li>
        <li class="layui-nav-item layui-this">
            <a href="${pageContext.request.contextPath}/uiServlet?method=mypostUI">
                <i class="layui-icon">&#xe612;</i>
                我的帖子
            </a>
        </li>
        <li class="layui-nav-item">
            <a href="${pageContext.request.contextPath}/uiServlet?method=setUI">
                <i class="layui-icon">&#xe60a;</i>
                基本设置
            </a>
        </li>
        <li class="layui-nav-item">
            <a href="user/message.jsp">
                <i class="layui-icon">&#xe611;</i>
                我的消息
            </a>
        </li>
    </ul>

  <div class="site-tree-mobile layui-hide">
    <i class="layui-icon">&#xe602;</i>
  </div>
  <div class="site-mobile-shade"></div>
  
  <div class="site-tree-mobile layui-hide">
    <i class="layui-icon">&#xe602;</i>
  </div>
  <div class="site-mobile-shade"></div>
    <div class="fly-panel fly-panel-user" pad20="">
        <div class="layui-tab layui-tab-brief" lay-filter="user" id="LAY_uc">
            <ul class="layui-tab-title" id="LAY_mine">
                <li data-type="mine-jie" lay-id="index" class="layui-this">我发的帖</li>
                <li data-type="collection" data-url="/collection/find/" lay-id="collection" class="">我收藏的帖</li>
            </ul> <div class="layui-tab-content" id="LAY_ucm" style="padding: 5px 0;">
            <div class="layui-tab-item layui-show">
                <table class="layui-hide" id="LAY_mySendCard">

                </table><div class="layui-form layui-border-box layui-table-view" lay-filter="LAY-table-1" style=" ">
                <div class="layui-table-box"><div class="layui-table-header">
                    <table cellspacing="0" cellpadding="0" border="0" class="layui-table" lay-skin="line">
                        <thead>
                        <tr>
                            <th data-field="title" data-minwidth="300">
                                <div class="layui-table-cell laytable-cell-1-title">
                                    <span>帖子标题</span>
                                </div>
                            </th>
                            <th data-field="status">
                                <div class="layui-table-cell laytable-cell-1-status" align="center"><span>状态</span></div></th><th data-field="status">
                            <div class="layui-table-cell laytable-cell-1-status" align="center"><span>结贴</span></div>
                        </th>
                            <th data-field="time">
                                <div class="layui-table-cell laytable-cell-1-time" align="center"><span>发表时间</span></div>
                            </th>
                            <th data-field="4">
                                <div class="layui-table-cell laytable-cell-1-4"><span>数据</span></div>
                            </th>
                            <th data-field="5">
                                <div class="layui-table-cell laytable-cell-1-5"><span>操作</span>
                                </div>
                            </th>
                        </tr>
                        </thead>
                    </table>
                </div>
                    <div class="layui-table-body layui-table-main">
                        <table cellspacing="0" cellpadding="0" border="0" class="layui-table" lay-skin="line">
                            <tbody><tr data-index="0" class="">
                                <td data-field="title" data-content="真的真的恨的" data-minwidth="300">
                                    <div class="layui-table-cell laytable-cell-1-title">
                                        <a href="/jie/31732/" target="_blank" class="layui-table-link">真的真的恨的</a>
                                    </div>
                                </td>
                                <td data-field="status" align="center" data-content="0">
                                    <div class="layui-table-cell laytable-cell-1-status">
                                        <span style="color: #999;">正常</span>
                                    </div>
                                </td>
                                <td data-field="status" align="center" data-content="0">
                                    <div class="layui-table-cell laytable-cell-1-status">
                                        <span style="color: #ccc;">未结</span>
                                    </div>
                                </td>
                                <td data-field="time" align="center" data-content="1533820797666">
                                    <div class="layui-table-cell laytable-cell-1-time">2分钟前</div>
                                </td>
                                <td data-field="4" data-content="">
                                    <div class="layui-table-cell laytable-cell-1-4"><span style="font-size: 12px;">3阅/0答</span></div>
                                </td>
                                <td data-field="5" data-content="">
                                    <div class="layui-table.laytable-cell-1-5-cell laytable-cell-1-5">
                                        <a class="layui-btn layui-btn-xs" href="${pageContext.request.contextPath}/postServlet?method=toEditPost&postId=${requestScope.postDetail.postId}" target="_blank">编辑</a>
                                        <a class="layui-btn layui-btn-xs" href="${pageContext.request.contextPath}/postServlet?method=deletePost&postId=${requestScope.postDetail.postId}" target="_blank">删除</a>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div><div class="layui-table-page">
                <div id="layui-table-page1">
                    <div class="layui-box layui-laypage layui-laypage-default" id="layui-laypage-1">
                        <a href="javascript:;" class="layui-laypage-prev layui-disabled" data-page="0">
                            <i class="layui-icon"></i>
                        </a>
                        <span class="layui-laypage-curr">
                            <em class="layui-laypage-em"></em>
                            <em>1</em>
                        </span><a href="javascript:;" class="layui-laypage-next layui-disabled" data-page="2">
                        <i class="layui-icon"></i>
                    </a><span class="layui-laypage-skip">到第<input type="text" min="1" value="1" class="layui-input">页<button type="button" class="layui-laypage-btn">确定</button></span>
                        <span class="layui-laypage-count">共 1 条</span>
                    </div>
                </div>
            </div>
                <style>.laytable-cell-1-title{ width: 140px; }.laytable-cell-1-status{ width: 100px; }.laytable-cell-1-status{ width: 100px; }.laytable-cell-1-time{ width: 120px; }.laytable-cell-1-4{ width: 100px; }.laytable-cell-1-5{ width: 133px; }</style></div> </div> <div class="layui-tab-item" style=""> <table class="layui-hide" id="LAY_myCollectioncard"></table><div class="layui-form layui-border-box layui-table-view" lay-filter="LAY-table-3" style=" "><div class="layui-table-box"><div class="layui-table-header"><table cellspacing="0" cellpadding="0" border="0" class="layui-table" lay-skin="line"><thead><tr><th data-field="title" data-minwidth="300"><div class="layui-table-cell laytable-cell-3-title"><span>帖子标题</span></div></th><th data-field="collection_timestamp"><div class="layui-table-cell laytable-cell-3-collection_timestamp" align="center"><span>收藏时间</span></div></th></tr></thead></table></div><div class="layui-table-body layui-table-main"><table cellspacing="0" cellpadding="0" border="0" class="layui-table" lay-skin="line"><tbody></tbody></table><div class="layui-none">无数据</div></div></div><div class="layui-table-page layui-hide"><div id="layui-table-page3"></div></div><style>.laytable-cell-3-title{ width: 793px; }.laytable-cell-3-collection_timestamp{ width: 120px; }</style></div> </div> </div> </div>
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
  version: "2.0.0"
  ,base: '${pageContext.request.contextPath}/res/mods/'
}).extend({
  fly: 'index'
}).use('fly');
</script>

</body>
</html>