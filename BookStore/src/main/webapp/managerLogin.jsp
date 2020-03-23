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
    <title>管理员登录</title>
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/assets/jquery/jquery-1.12.4.js"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>
</head>
<body>
<div class="container">
    <form class="form-signin" action="/managerAdmin/login.do" method="post">
        <h2>管理员登录</h2>
        <div class="form-group">
            <label for="account">账号：</label>
            <input type="text" id="account" class="form-control" name="account" value="${account}" placeholder="手机/邮箱" required autofocus>
            <span style="color: #c12">${errors.accountMsg}</span>
        </div>
        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" id="password" class="form-control" name="password" value="${password}" placeholder="密码" required>
            <span style="color: #c12">${errors.passwordMsg}</span>
        </div>
        <div class="form-group">
            <label for="verifyCode">验证码：</label>
            <input type="text" id="verifyCode" class="form-control" name="verifyCode" placeholder="验证码" width="200px" required>
            <img id="verifyCodeImg" src="${pageContext.request.contextPath}/verifyCode.do" alt="验证码" height="38px" onclick="changeImg()">
            <span style="color: #c12">${errors.verifyCodeMsg}</span>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
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
