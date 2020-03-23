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

</head>
<body>
    <script>
        window.location = "${pageContext.request.contextPath}/postServlet?method=init";
    </script>

</body>
</html>
