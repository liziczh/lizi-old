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
  <title>${requestScope.postDetail.postTitle} - Mouke</title>
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

<div class="layui-container">
  <div class="layui-row layui-col-space15" >
    <div class="layui-col-md10 content detail ">
      <div class="fly-panel detail-box">
        <h1>${requestScope.postDetail.postTitle}</h1>
        <div class="detail-about">
          <a class="fly-avatar" href="${pageContext.request.contextPath}/userServlet?method=getUserAndPostByUserId&userId=${requestScope.postDetail.authorId}">
            <img src="${pageContext.request.contextPath}/res/images/avatar/${requestScope.postDetail.authorAvatar}" alt="${requestScope.postDetail.authorName}">
          </a>
          <div class="fly-detail-user">
            <a href="${pageContext.request.contextPath}/userServlet?method=getUserAndPostByUserId&userId=${requestScope.postDetail.authorId}" class="fly-link">
              <cite>${requestScope.postDetail.authorName}</cite>
            </a>
            <span>创建时间:${requestScope.postDetail.createTime}</span>
          </div>
          <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
            <span>最近更新:${requestScope.postDetail.updateTime}</span>
            <span>&nbsp;<i class="iconfont icon-zan" title="喜欢"></i>&nbsp;${requestScope.postDetail.likeCount}&nbsp;</span>
            <span>&nbsp;<i class="iconfont icon-pinglun1" title="评论"></i>&nbsp;${requestScope.postDetail.commentCount}&nbsp;</span>
            <span>&nbsp;<i class="iconfont icon-yulan1" title="人气"></i>&nbsp;${requestScope.postDetail.postViewCount}&nbsp;</span>
            <c:if test="${sessionScope.user.userId == requestScope.postDetail.authorId}">
              <span class="layui-btn layui-btn-xs jie-admin" type="edit"><a href="${pageContext.request.contextPath}/postServlet?method=toEditPost&postId=${requestScope.postDetail.postId}">编辑</a></span>
              <span class="layui-btn layui-btn-xs jie-admin" type="del"><a href="${pageContext.request.contextPath}/postServlet?method=deletePost&postId=${requestScope.postDetail.postId}">删除</a></span>
            </c:if>
          </div>
        </div>
        <div class="detail-body photos">
          ${requestScope.postDetail.postContent}
        </div>
        <%--百度分享--%>
        <div>
          <br><br>
          <div class="bdsharebuttonbox"><a href="#" class="bds_more" data-cmd="more"></a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a><a href="#" class="bds_tieba" data-cmd="tieba" title="分享到百度贴吧"></a><a href="#" class="bds_douban" data-cmd="douban" title="分享到豆瓣网"></a><a href="#" class="bds_linkedin" data-cmd="linkedin" title="分享到linkedin"></a></div>
          <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"1","bdSize":"16"},"share":{},"image":{"viewList":["qzone","tsina","weixin","tieba","douban","linkedin"],"viewText":"分享到：","viewSize":"16"}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
        </div>
      </div>

      <div class="fly-panel detail-box" id="flyReply">
        <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
          <legend>评论</legend>
        </fieldset>

        <ul class="jieda" id="jieda">
          <c:forEach items="${requestScope.commentDetailsList}" var="commentDetail">
            <li data-id="111">
              <a name="item-1111111111"></a>
              <div class="detail-about detail-about-reply">
                <a class="fly-avatar" href="">
                    <%--评论者头像--%>
                  <img src="${pageContext.request.contextPath}/res/images/avatar/${commentDetail.commentUserAvatar}" alt="${commentDetail.commentUserAvatar} ">
                </a>
                <div class="fly-detail-user">
                  <a href="" class="fly-link">
                      <%--评论者名--%>
                    <cite>${commentDetail.commentUserName}</cite>
                  </a>
                </div>
                <div class="detail-hits">
                  <%--评论更新时间--%>
                  <span>评论更新时间:${commentDetail.commentTime}</span>
                </div>
              </div>
              <div class="detail-body jieda-body photos">
                  <%--评论内容--%>
                  ${commentDetail.commentContent}
              </div>
              <div class="jieda-reply">
              <span class="jieda-zan" type="zan">
                <i class="iconfont icon-zan"><a id="zanComment" href="${pageContext.request.contextPath}/"></a></i>
                <em>${commentDetail.thumbUpNum}</em>
              </span>
                <div class="jieda-admin">
                    <%--<a href="/commentServlet?method=deletecComment&commentId=${commentDetail.commentId}">删除</a>--%>
                    <%--<span type="del" ><a href="/commentServlet?method=deletecComment&commentId=${commentDetail.commentId}"></a>删除</span>--%>
                </div>
              </div>
            </li>
          </c:forEach>
          
          <!-- 无数据时 -->
          <c:if test="${requestScope.commentDetailsList == null}">
            <li class="fly-none">消灭零回复</li>
          </c:if>

        </ul>
        
        <div class="layui-form layui-form-pane">
          <c:if test="${sessionScope.user != null}">
          <form action="${pageContext.request.contextPath}/commentServlet" method="post">
            <input type="hidden" name="method" value="editComment">
            <input type="hidden" name="postId" value="${requestScope.postDetail.postId}">
            <input type="hidden" name="commentUserId" value="${sessionScope.user.userId}">
            <div class="layui-form-item layui-form-text">
              <a name="comment"></a>
              <div class="layui-input-block">
                <textarea id="L_content" name="commentContent" required lay-verify="required" placeholder="请输入内容"  class="layui-textarea fly-editor" style="height: 150px;"></textarea>
                <button type="submit" value="提交评论" class="layui-btn" lay-filter="*">提交评论</button>
              </div>
            </div>
          </form>
          </c:if>
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
}).use(['fly', 'face'], function(){
  var $ = layui.$
  ,fly = layui.fly;

  // 开启编辑器
  $('.detail-body').each(function(){
        var othis = $(this), html = othis.html();
        othis.html(fly.content(html));
    });
});
</script>

</body>
</html>