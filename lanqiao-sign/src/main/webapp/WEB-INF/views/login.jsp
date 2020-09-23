<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>
</head>
<body>
<div class="container">
    <form class="form-signin" action="/login.do" method="post">
        <h2>请登录</h2>
        <div class="form-group">
            <label for="username">用户名：</label>
            <input type="text" id="username" class="form-control" name="username" value="" placeholder="手机/邮箱" autofocus>
            <span id="usernameTips" class="tips"></span>
        </div>
        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" id="password" class="form-control" name="password" value="" placeholder="密码">
            <span id="passwordTips" class="tips"></span>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
    </form>
</div>

<script>
    $(function () {
        $("#username").blur(function () {
            var usernamePatten = /^[a-zA-Z]([-_a-zA-Z0-9]{5,19})+$/;
            if($(this).val() == null || $(this).val() == ""){
                $("#usernameTips").text("用户名为空");
            }else if(!$(this).val().match(usernamePatten)){
                $("#usernameTips").text("用户名不符合规则，用户名应为4到16位（字母，数字，下划线，减号）");
            }else{
                $("#usernameTips").text("");
            }
        })

        $("#password").blur(function () {
            var passwordPatten = /^[a-zA-Z]\w{5,17}$/;
            if($(this).val() == null || $(this).val() == ""){
                $("#passwordTips").text("密码为空");
            }else if(!$(this).val().match(passwordPatten)){
                $("#passwordTips").text("密码不符合规则，以字母开头，长度在6-18之间，只能包含字符、数字和下划线");
            }else{
                $("#passwordTips").text("");
            }
        })


    })


</script>
</body>
</html>
