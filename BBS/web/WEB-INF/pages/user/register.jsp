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
  <title>注册 - Mouke</title>
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
        <li><a href="${pageContext.request.contextPath}/uiServlet?method=loginUI">登入</a></li>
        <li class="layui-this">注册</li>
      </ul>
      <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
        <div class="layui-tab-item layui-show">
          <div class="layui-form layui-form-pane">
            <form id="register-form" action="${pageContext.request.contextPath}/userServlet" method="post">
              <input type="hidden" name="method" value="register">
              <div class="layui-form-item">
                <label for="L_email" class="layui-form-label">邮箱</label>
                <div class="layui-input-inline">
                  <input type="text" id="L_email" name="email" value="${requestScope.email}" placeholder="邮箱" required lay-verify="email" autocomplete="on" class="layui-input">
                </div>
                <div class="layui-input-inline">
                  <span class="layui-form-mid" style="color: #c00;">${errors.emailMsg}</span>
                </div>
              </div>
              <div class="layui-form-item">
                <label for="L_username" class="layui-form-label">昵称</label>
                <div class="layui-input-inline">
                  <input type="text" id="L_username" name="username" value="${requestScope.username}" required lay-verify="required" autocomplete="on" class="layui-input">
                </div>
                <div class="layui-input-inline">
                  <span class="layui-form-mid" style="color: #c00;">${errors.usernameMsg}</span>
                </div>
              </div>
              <div class="layui-form-item">
                <label for="L_pass" class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="L_pass" name="pass" value="${requestScope.password}" placeholder="" required lay-verify="required" autocomplete="on" class="layui-input">
                </div>
                <div class="layui-input-inline">
                  <span class="layui-form-mid" style="color: #c00;">${errors.passwordMsg}</span>
                </div>
              </div>
              <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label">确认密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="L_repass" name="repass" value="${requestScope.confirmPassword}" required lay-verify="required" autocomplete="on" class="layui-input">
                </div>
                <div class="layui-input-inline">
                  <span class="layui-form-mid" style="color: #c00;">${errors.confirmPasswordMsg}</span>
                </div>
              </div>
              <div class="layui-form-item">
                <label for="L_phoneNumber" class="layui-form-label">手机</label>
                <div class="layui-input-inline">
                  <input type="text" id="L_phoneNumber" name="phoneNumber" value="${requestScope.phoneNumber}" required lay-verify="required" autocomplete="on" class="layui-input">
                </div>
                <div class="layui-input-inline">
                  <span class="layui-form-mid" style="color: #c00;">${errors.phoneNumberMsg}</span>
                </div>
              </div>
              <div class="layui-form-item">
                <label for="L_vercode" class="layui-form-label">人类验证</label>
                <div class="layui-input-inline">
                  <input type="text" id="L_vercode" name="vercode" required lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-input-inline">
                  <img id="registerImg" src="${pageContext.request.contextPath}/verifyCodeServlet" alt="验证码" height="38px" onclick="changeImg()">
                </div>
                <div class="layui-input-inline">
                  <span class="layui-form-mid" style="color: #c00;">${errors.verifyCodeMsg}</span>
                </div>
              </div>
              <div class="layui-form-item">
                <input type="submit" id="registerBtn" value="立即注册" class="layui-btn" lay-filter="*">
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

</div>

<div class="fly-footer">
  <p><a href="${pageContext.request.contextPath}/postServlet?method=init" target="_blank">Panda 社区</a> 2018 &copy; <a href="http://www.layui.com/" target="_blank"> By layUI </a></p>
  <p><a href="${pageContext.request.contextPath}/postServlet?method=init" target="_blank"> 超级无敌IT精英团队制作 </a> &copy; <a href="https://www.liziczh.com/" target="_blank"> 开发者1号：Lizi</a></p>
</div>

<script src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
<script>
    layui.use(['jquery'], function(){
        var $ = jQuery = layui.$;

        <%--// 登录错误提示--%>
        <%--$("#registerBtn").on("submit", function () {--%>
            <%--$.get(--%>
                <%--'${pageContext.request.contextPath}/userServlet?method=register'--%>
                <%--,function (data) {--%>
                    <%--alert(data);--%>
                    <%--if(data == null){--%>
                        <%--$("#register-form").submit();--%>
                    <%--}--%>
                    <%--layer.msg(data, {shift: 6});--%>
                <%--}--%>
            <%--)--%>
            <%--return false;--%>
        <%--})--%>

        // 刷新验证码
        $("#registerImg").on("click",function () {
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