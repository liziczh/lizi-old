<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jehaw-Chen
  Date: 2018/8/31
  Time: 0:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>收藏夹</title>
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/assets/jquery/jquery-1.12.4.js"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>
</head>
<body>
    <h3>收藏夹</h3>
    <c:if test="${favoritesItemList.size() != 0}">
        <form action="${pageContext.request.contextPath}/favorites/removeSelectedFavoritesItem.do" method="post">
        <table border="1px">
            <thead>
            <th>商品ID</th>
            <th>可选</th>
            </thead>
            <tbody>
            <c:forEach items="${favoritesItemList}" var="favoritesItem">
                <input type="hidden" name="commodityIdArr" />
                <tr>
                    <td>${favoritesItem.commodityId}</td>
                    <td><input type="checkbox" name="commodityIdArr" value="${favoritesItem.commodityId}"></td>
                    <td><a href="${pageContext.request.contextPath}/favorites/removeFavoritesItem.do?commodityId=${favoritesItem.commodityId}">移出收藏夹</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <input type="submit" value="删除" />
        </form>
    </c:if>



</body>
</html>
