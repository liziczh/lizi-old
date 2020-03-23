<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jehaw-Chen
  Date: 2018/8/31
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/assets/jquery/jquery-1.12.4.js"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("#top-part").load("top-part.jsp");
        // 搜索框
        $('#search-part-btn').focus(function(){
            window.location.href="search.jsp";
        })
        $("#search-part").load("search-part.jsp");
    </script>
    <style>
        .container {
            background: cornsilk;
        }
        .red-font{
            color: #c63205;
            font-size: 16px;
            margin: 0 5px;
        }
        hr{
            margin: 0;
        }
        /***************************/
        .table>tbody>tr>th{
            border-top:0;
            text-align: center;
        }
        .table>tbody>tr>td{
            position: relative;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        .table>tbody>tr>td:first-child{
            border-left: 1px solid #ddd;
        }
        .table>tbody>tr>td:last-child{
            border-right: 1px solid #ddd;
        }
        table tr td img{
            width: 100%;
        }
        .checkbox-row{
            display: inline-block;
            position: relative;
        }
        .ls-checkbox {
            display: inline-block;
            width: 25px;
            height: 25px;
            opacity: 0;
            z-index: 100000;
        }
        .ls-checkbox + label {
            border: 1px solid #cacece;
            padding: 10px;
            border-radius: 3px;
            display: inline-block;
            position: absolute;
            top: 5px;
            left: 1px;
        }
        .ls-checkbox:checked + label {
            background-color: #fff;
            border: 1px solid #adb8c0;
            color: #ffb723;
        }
        .ls-checkbox:checked + label:after {
            content: '\2714';
            font-size: 18px;
            position: absolute;
            top: -1px;
            left: 3px;
            color: #ffb723;
        }
        .jiesuan{
            height: 50px;
            background: #eee;
            margin: 50px 0;
        }
        .jiesuan .jiesuan-left{
            display: inline-block;
            width: 40%;
            padding-left: 25px;
            height: 50px;
            line-height: 50px;
        }
        .jiesuan .jiesuan-right{
            display: inline-block;
            text-align: right;
            width: 55%;
        }
        .jiesuan .jiesuan-right>span{
            margin:0 20px 0 10px;
        }
    </style>
</head>
<body>
<!--顶部导航-->
<div id="top-part">
    <ul class="list-group list-group-1" id="top">
        <li class="list-group-item list1"><a href="index.html">首页</a></li>
        <c:choose>
            <c:when test="${user == null}">
                <li class="list-group-item list1"><a href="${pageContext.request.contextPath}/loginUI.do">登录</a></li>
                <li class="list-group-item list1"><a href="${pageContext.request.contextPath}/registerUI.do"> 注册</a></li>
            </c:when>
            <c:otherwise>
                <li role="presentation" class="dropdown list-group-item list1">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                        Hi,${sessionScope.user.username}
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="user.html">个人设置</a></li>
                        <li><a href="#">切换账号</a></li>
                        <li><a href="${pageContext.request.contextPath}/user/logout.do">退出</a></li>
                    </ul>
                </li>
                <li class="list-group-item list1"><a href="${pageContext.request.contextPath}/favorites/getFavorites.do"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true" style="color:#c63205"></span> 收藏夹</a></li>
                <li class="list-group-item list1"><a href="${pageContext.request.contextPath}/cart/getCart.do"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true" style="color:#c63205"></span> 购物车</a></li>
                <li class="list-group-item list1"><a href="order.html"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 我的订单</a></li>
                <li class="list-group-item list1"><a href="${pageContext.request.contextPath}/book/search.do?name="> 物品栏</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
<div class="search-part">
    <div class="input-group">
        <input type="text" class="form-control" id="search-part-btn" placeholder="Search for...">
        <span class="input-group-btn">
        <button class="btn btn-search" type="button">搜索</button>
        </span>
    </div>
</div>
<%--购物车--%>

<!--主体内容-->
<form action="${pageContext.request.contextPath}/cart/removeSelectedCartItem.do" method="post">
<div class="container">
    <h3>购物车</h3>
    <c:choose>
        <c:when test="${cartItemList.size() == 0}">
            <h4>购物车空空如也~</h4>
        </c:when>
        <c:otherwise>
        <table class="table" style="margin-top: 10px">
            <thead style="text-align: center;">
                <th><input type="checkbox" id="checkall" class="checkall" onclick="allnone()"></th>
                <th colspan="2">商品信息</th>
                <th>单价</th>
                <th>数量</th>
                <th>小计</th>
                <th>操作</th>
            </thead>
            <tbody>
            <c:forEach items="${cartItemList}" var="cartItem">
                <tr>
                    <td class="col-sm-1" >
                        <br>
                        <input type="checkbox" class="ls-checkbox" name="selectedItem">
                        <div class="checkbox-row">
                            <input type="checkbox" class="ls-checkbox" name="selectedItem">
                        </div>
                    </td>
                    <td class="col-sm-1">
                        <img src="${pageContext.request.contextPath}/assets/images/${cartItem.commodityId}" alt="">
                    </td>
                    <td><br>${cartItem.commodityId}</td>
                    <td><br><a href="${pageContext.request.contextPath}/cart/removeOne.do?commodityId=${cartItem.commodityId}">-</a>${cartItem.commodityCount}<a href="${pageContext.request.contextPath}/cart/addOne.do?commodityId=${cartItem.commodityId}">+</a></td>
                    <td><br>${cartItem.commodityCount}</td>
                    <td><br><a href="${pageContext.request.contextPath}/cart/removeCartItem.do?commodityId=${cartItem.commodityId}"><span class="glyphicon glyphicon-trash"></span></a></td>
                </tr>
                <tr>
                    <th></th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
</div>
<div class="container" style="background: #fff; padding: 0">
    <div class="jiesuan">
        <div class="jiesuan-left" >

            <button type="submit" class="btn btn-default" >删除</button>
            <button type="button" class="btn btn-default" href="${pageContext.request.contextPath}/cart/emptyCartItem.do">清空</button>
        </div>
        <div class="jiesuan-right" >
            <span>已选商品<span class="red-font">0</span>件</span>
            <span>总金额(不含运费)：¥<span class="red-font" >0.00</span>元</span>
            <button type="button" class="btn btn-danger">结算</button>
        </div>
    </div>
</div>
</form>
</c:otherwise>
</c:choose>

</body>
<script type="text/javascript">
    $("#top-part").load("top-part.html");
    $("#search-part").load("search-part.html");
</script>
<script>
    var loves=document.getElementsByName("selectedItem");
    var ckb1=document.getElementById("checkall");
    var j=0;
    function alls(){
        for(var i=0;i<loves.length;i++){
            var love1=loves[i];
            love1.checked=true;
        }
        ckb1.checked=true;
    }
    function none(){
        for(var i=0;i<loves.length;i++){
            var love1=loves[i];
            love1.checked=false;
        }
        ckb1.checked=false;
    }
    function allnone(){
        if(ckb1.checked==true){
            alls();
        }else{
            none();
        }
    }
    for(var i=0;i<loves.length;i++){
        var love1=loves[i];
        love1.onclick=function(){
            if(this.checked==true){
                j++;
                if(j==loves.length)
                    ckb1.checked=true;
            }else if(this.checked==false){
                j--;
                if(j!=loves.length)
                    ckb1.checked=false;
            }
        }
    }
</script>
</html>