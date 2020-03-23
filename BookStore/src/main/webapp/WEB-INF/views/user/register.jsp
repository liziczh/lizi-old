<%--
  Created by IntelliJ IDEA.
  User: Jehaw-Chen
  Date: 2018/8/28
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/assets/jquery/jquery-1.12.4.js"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>
</head>
<body>
<form class="form-signin" action="/user/register.do" method="post">
    <h2>请注册</h2>
    <div class="form-group">
        <label for="username">用户昵称：</label>
        <input type="text" id="username" class="form-control" name="username" value="${username}" placeholder="用户昵称" required autofocus>
        <span style="color: #c12">${errors.usernameMsg}</span>
    </div>
    <div class="form-group">
        <label for="password">密码：</label>
        <input type="password" id="password" class="form-control" name="password" value="${password}" placeholder="密码" required>
        <span style="color: #c12">${errors.passwordMsg}</span>
    </div>
    <div class="form-group">
        <label for="confirmPassword">确认密码</label>
        <input type="password" id="confirmPassword" class="form-control" name="confirmPassword" value="${confirmPassword}" placeholder="确认密码" required>
        <span style="color: #c12">${errors.confirmPasswordMsg}</span>
    </div>
    <div class="form-group">
        <label for="email">邮箱：</label>
        <input type="email" id="email" class="form-control" name="email" value="${email}" placeholder="邮箱" required>
        <span style="color: #c12">${errors.emailMsg}</span>
    </div>
    <div class="form-group">
        <label for="phoneNumber">手机：</label>
        <input type="tel" id="phoneNumber" class="form-control" name="phoneNumber" value="${phoneNumber}" placeholder="手机" required>
        <span style="color: #c12">${errors.phoneNumberMsg}</span>
    </div>
    <div class="form-group">
        <label for="verifyCode" style="display: block;">验证码：</label>
        <input type="text" id="verifyCode" class="form-control" name="verifyCode" placeholder="验证码" width="60%" required style="display: inline; width: 60%;">
        <img id="verifyCodeImg" src="${pageContext.request.contextPath}/verifyCode.do" alt="验证码" height="38px" style="display: inline; margin-left: 20px;" onclick="changeImg()">
        <div style="color: #c12">${errors.verifyCodeMsg}</div>
    </div>
    <br>
    <button class="form-submit btn-lg btn-block" style="background-image: linear-gradient(to bottom, #c61012 0%, #c61012 100%);color: #fff;" type="submit">注册</button>
</form>WW
<script>
    $(function () {
        $("#verifyCodeImg").on("click",function () {
            $(this).attr("src","${pageContext.request.contextPath}/verifyCode.do?xxx="+Math.random());
        })
    })
</script>
</body>
</html>
