<%--
  Created by IntelliJ IDEA.
  User: Jehaw-Chen
  Date: 2018/8/28
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/assets/jquery/jquery-1.12.4.js"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>
</head>
<body>
<div class="container">
    <form class="form-signin" action="/user/login.do" method="post">
        <h2>请登录</h2>
        <div class="form-group">
            <label for="account">手机/邮箱：</label>
            <input type="text" id="account" class="form-control" name="account" value="${account}" placeholder="手机/邮箱" required autofocus>
            <span style="color: #c12">${errors.accountMsg}</span>
        </div>
        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" id="password" class="form-control" name="password" value="${password}" placeholder="密码" required>
            <span style="color: #c12">${errors.passwordMsg}</span>
        </div>
        <div class="form-group">
            <label for="verifyCode" style="display: block;">验证码：</label>
            <input type="text" id="verifyCode" class="form-control" name="verifyCode" placeholder="验证码" width="60%" required style="display: inline; width: 60%;">
            <img id="verifyCodeImg" src="${pageContext.request.contextPath}/verifyCode.do" alt="验证码" height="38px" style="display: inline; margin-left: 20px;" onclick="changeImg()">
            <div style="color: #c12">${errors.verifyCodeMsg}</div>
        </div>
        <div style="padding-bottom:10px">
            <span><a href="${pageContext.request.contextPath}/loginUI.do">忘记密码</a></span>
            <span style="float: right;"><a href="${pageContext.request.contextPath}/registerUI.do">前去注册</a></span>
        </div>

        <button class="form-submit btn-lg btn-block" style="background-image: linear-gradient(to bottom, #c61012 0%, #c61012 100%);color: #fff;" type="submit">登陆</button>
    </form>
</div>

<script>
    $(function () {
        $("#verifyCodeImg").on("click",function () {
            $(this).attr("src","${pageContext.request.contextPath}/verifyCode.do?xxx="+Math.random());
        })
    })
</script>
</body>
</html>
