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
  <title>登录 - Mouke</title>
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
  </div>
</div>

<div class="layui-container fly-marginTop">
  <div class="fly-panel fly-panel-user" pad20>
    <div class="layui-tab layui-tab-brief" lay-filter="user">
      <ul class="layui-tab-title">
        <li class="layui-this">登入</li>
        <li><a href="${pageContext.request.contextPath}/uiServlet?method=registerUI">注册</a></li>
      </ul>
      <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
        <div class="layui-tab-item layui-show">
          <div class="layui-form layui-form-pane">
            <form id="login-form" action="${pageContext.request.contextPath}/userServlet?method=login" method="post">
              <input type="hidden" name="method" value="login">
              <div class="layui-form-item">
                <label for="L_email" class="layui-form-label">邮箱</label>
                <div class="layui-input-inline">
                  <input type="text" id="L_email" name="account" value="${account}" required lay-verify="required" autocomplete="on" class="layui-input">
                </div>
                <div class="layui-input-inline">
                  <span class="layui-form-mid" style="color: #c00;">${errors.accountMsg}</span>
                </div>
              </div>
              <div class="layui-form-item">
                <label for="L_pass" class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="L_pass" name="pass" value="${password}" required lay-verify="required" autocomplete="on" class="layui-input">
                </div>
                <div class="layui-input-inline">
                  <span class="layui-form-mid" style="color: #c00;">${errors.passwordMsg}</span>
                </div>
              </div>
              <div class="layui-form-item">
                <label for="L_vercode" class="layui-form-label">人类验证</label>
                <div class="layui-input-inline">
                  <input type="text" id="L_vercode" name="vercode" required lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-input-inline"  style="width: 100px;">
                  <img id="loginImg" src="${pageContext.request.contextPath}/verifyCodeServlet" alt="验证码" height="38px" onclick="changeImg()">
                </div>
                <div class="layui-input-inline">
                  <span class="layui-form-mid" style="color: #c00;">${errors.verifyCodeMsg}</span>
                </div>
              </div>
              <div class="layui-form-item">
                <input id="loginBtn" type="submit" name="submit" value="立即登录" class="layui-btn" lay-filter="*">
                <%--<button id="loginBtn" class="layui-btn" lay-filter="*" lay-submit>立即登录</button>--%>
                <span style="padding-left:20px;">
                  <a href="${pageContext.request.contextPath}/uiServlet?method=forgetUI">忘记密码？</a>
                </span>
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
    layui.use(['jquery','layer'], function(){
        var $ = jQuery = layui.$;

        <%--// 登录错误提示--%>
        <%--$('#loginBtn').on('click',function () {--%>
            <%--$.post(--%>
                <%--'${pageContext.request.contextPath}/userServlet'--%>
                <%--,data='method=login&'+$('#login-form').serialize()--%>
                <%--,function (data) {--%>
                    <%--alert(data);--%>
                    <%--if(data.status = '0'){--%>
                        <%--layer.msg(data.msg, {shift: 6});--%>
                    <%--}else{--%>
                        <%--layer.msg(data.msg, {shift: 6});--%>
                        <%--window.location.href = "${pageContext.request.contextPath}/index.jsp";--%>
                    <%--}--%>
                <%--}--%>
            <%--)--%>
        <%--})--%>

        // 刷新验证码
        $("#loginImg").on("click",function () {
            $(this).attr("src","${pageContext.request.contextPath}/verifyCodeServlet?xxx="+Math.random());
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