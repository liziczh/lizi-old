<%--
  Created by IntelliJ IDEA.
  User: Jehaw-Chen
  Date: 2018/8/31
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品</title>
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/assets/jquery/jquery-1.12.4.js"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>
</head>
<body>
<table>
    <thead>
    <th>商品ID</th>
    <th>商品数量</th>
    <th>可选</th>
    </thead>
    <tbody>
        <tr>
            <td>1</td>
            <td>1</td>
            <td><a id="fav" href="${pageContext.request.contextPath}/favorites/favorites">收藏/取消收藏</a></td>
            <td><a href="${pageContext.request.contextPath}/favorites/addFavoritesItem.do?commodityId=1">加入收藏夹</a></td>
            <td><a href="${pageContext.request.contextPath}/cart/addCartItem.do?commodityId=1">加入购物车</a></td>
        </tr>
        <tr>
            <td>2</td>
            <td>2</td>
            <td><a  href="${pageContext.request.contextPath}/favorites/favorites">收藏/取消收藏</a></td>
            <td><a href="${pageContext.request.contextPath}/favorites/addFavoritesItem.do?commodityId=2">加入收藏夹</a></td>
            <td><a href="${pageContext.request.contextPath}/cart/addCartItem.do?commodityId=2">加入购物车</a></td>
        </tr>
        <tr>
            <td>3</td>
            <td>3</td>
            <td><a  href="${pageContext.request.contextPath}/favorites/favorites">收藏/取消收藏</a></td>
            <td><a href="${pageContext.request.contextPath}/favorites/addFavoritesItem.do?commodityId=3">加入收藏夹</a></td>
            <td><a href="${pageContext.request.contextPath}/cart/addCartItem.do?commodityId=3">加入购物车</a></td>
        </tr>
        <tr>
            <td>4</td>
            <td>4</td>
            <td><a href="${pageContext.request.contextPath}/favorites/favorites">收藏/取消收藏</a></td>
            <td><a href="${pageContext.request.contextPath}/favorites/addFavoritesItem.do?commodityId=4">加入收藏夹</a></td>
            <td><a href="${pageContext.request.contextPath}/cart/addCartItem.do?commodityId=4">加入购物车</a></td>
        </tr>
    </tbody>
</table>
<script>
    $(function () {
        $.get(
            '${pageContext.request.contextPath}/favorites/isFavorites',
            function (data) {
                $("#fav").text(data);
                if(location.href.indexOf('#loaded')==-1){
                    location.href=location.href+"#loaded";
                    location.reload();
                }
            }
        )
        $("#fav").click(function () {
            $.get(
                '${pageContext.request.contextPath}/favorites/favorites',
                function (data) {
                    $("#fav").text(data);
                }
            )
        })
    })
</script>
</body>
</html>
