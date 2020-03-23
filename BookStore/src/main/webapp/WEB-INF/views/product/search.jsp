<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>网上购物系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sub-menu.css">
    <style>
        /*顶部导航栏*/
        ul.list-group.list-group-1 {
            text-align: right;
            padding: 5px 30px 0 0;
            background: #eee;
        }
        li.list-group-item.list1 {
            display: inline-block;
            border: 0;
            background: #eee;
        }

        ul.list-group.list-group-1 a{
            color: #0e0e0e;

        }
        /******login弹出层样式****/

        .form{
            width: 350px;
            background-color: #fff;
            height: 220px;
            margin: 10px auto;
        }
        .form>div{
            position: relative;
            line-height: 100%;
            margin-bottom: 8px;
        }
        .form>div input{
            width: 100%;
            height: 35px;
            border-radius: 3px;
            border: 1px solid #ddd;
            text-indent: 40px;
        }
        .form>div label{
            position: absolute;
            left: 0;
            top:10px;
            width: 30px;
            text-align: right;
            color: #808080;
        }
        .form .yanzhengma input{
            width: 50%;
            text-indent: 10px;
        }
        .form .yanzhengma img{
            height: 30px;
            width: 75px;
            font-size: 0;
            vertical-align: middle;
        }
        .form .yanzhengma a{
            font-size: small;
            text-decoration: none;
            color: #00a5e0;
        }
        .form>div.chk label{
            width: 100px;
            padding-left: 20px;
        }
        .form>div.chk input{
            width: 22px;
            height: 22px;
            margin-top: 8px;
        }
        .form>div.chk a{
            float: right;
            margin-top: 9px;
            text-decoration: none;
            color: #00a5e0;
        }
        .buts button{
            width: 48%;
            float: left;
        }
        .buts button:first-child{
            margin-right: 4%;
        }
        /*顶部导航栏结束*/
        /*搜索框*/
        #search-part{
            width: 35%;
            margin: 0 auto;
            padding: 30px 0 50px 0;
            background: #fff;
        }
        .form-control{
            height: 47px;
        }
        .btn-search{
            padding: 9px 26px;
            background: #e4393c;
            color: #fff;
            font-size: 19px;
        }
        .btn-search:hover {
            color: #fff;
            background-color: #f66060;
        }
        /*搜索框结束*/
        .container {
            background: cornsilk;
        }
        .row.row-search {
            padding: 30px 0 50px 0;
            background: #fff;
        }
        .form-control{
            height: 47px;
        }
        .btn-search{
            padding: 9px 26px;
            background: #e4393c;
            color: #fff;
            font-size: 19px;
        }
        .btn-search:hover {
            color: #fff;
            background-color: #f66060;
        }
        .goodslist div  img{
            width: 100%;
        }
        .goodslist div p{
            text-align: center;
            margin: 5px 0 ;
            color: #4d4d4d;
        }
    </style>
</head>
<body>
<!--顶部导航-->
<div id="top-part">
    <ul class="list-group list-group-1" id="top">
        <li class="list-group-item list1"><a href="index.html">首页</a></li>
        <li class="list-group-item list1">登录【<a href="${pageContext.request.contextPath}/loginUI.do" data-toggle="modal" data-target="#myModalLogin" style="font-size: small;color:#e4393c ">请登录</a>】</li>
        <li role="presentation" class="dropdown list-group-item list1">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                xx,欢迎登陆
            </a>
            <ul class="dropdown-menu">
                <li><a href="user.html">个人设置</a></li>
                <li><a href="#">切换账号</a></li>
                <li><a href="#">退出</a></li>
            </ul>
        </li>
        <li class="list-group-item list1"><a href="${pageContext.request.contextPath}/registerUI.do"> 注册</a></li>
        <li class="list-group-item list1"><a href="cart.html"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true" style="color:#c63205"></span> 购物车</a></li>
        <li class="list-group-item list1"><a href="order.html"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 我的订单</a></li>
    </ul>
    <div class="modal fade" id="myModalLogin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">欢迎登陆</h4>
                </div>
                <div class="modal-body">
                    <form action="" class="form">
                        <div class="name">
                            <label for="username"><span class="glyphicon glyphicon-user"></span></label>
                            <input type="text" placeholder="用户名" id="username" >
                        </div>
                        <div class="password">
                            <label for="userpwd"><span class="glyphicon glyphicon-lock"></span></label>
                            <input type="text" placeholder="请输入密码"  id="userpwd">
                        </div>
                        <div class="yanzhengma">
                            <input type="text" placeholder="输入验证码"  id="yanzhengma" width="50%">
                            <img src="/assets/images/yanzhengma.JPG" alt="" height="35" width="80">
                            <a href="#">换一张</a>
                        </div>
                        <div class="chk">
                            <label for="ch1" width="60px">10天免登陆</label>
                            <input type="checkbox" id="ch1">
                            <a href="">免费注册</a>
                        </div>
                        <div class="buts">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-success">登陆</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%--搜索框--%>
<div id="search-part">

</div>
<!--主体内容-->
<div class="container">
    <!--第一块，search模块-->
    <div class="row row-search" >
        <div class="col-md-4">
        </div>
        <div class="col-md-5">
            <div class="input-group">
                <input type="text" class="form-control" autofocus placeholder="Search for...">
                <span class="input-group-btn">
                    <button class="btn btn-search" type="button">搜索</button>
                  </span>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12" style="height: 43px;line-height: 43px;color: #fff;font-size: 16px; background: #f66060">
            全部与“XX”相关的商品：
        </div>
    </div>
    <div class="row goodslist">
        <div class="col-md-3" >
            <a href="#">
                <img src="/assets/images/5.jpg" alt="">
                <p>海澜之家夏季v领纯色修身t恤</p>
                <p>￥99</p>
            </a>
        </div>
        <div class="col-md-3" >
            <a href="#">
                <img src="/assets/images/5.jpg" alt="">
                <p>海澜之家夏季v领纯色修身t恤</p>
                <p>￥99</p>
            </a>
        </div>
        <div class="col-md-3" >
            <a href="#">
                <img src="/assets/images/5.jpg" alt="">
                <p>海澜之家夏季v领纯色修身t恤</p>
                <p>￥99</p>
            </a>
        </div>
        <div class="col-md-3" >
            <a href="#">
                <img src="/assets/images/5.jpg" alt="">
                <p>海澜之家夏季v领纯色修身t恤</p>
                <p>￥99</p>
            </a>
        </div>
        <div class="col-md-3" >
            <a href="#">
                <img src="/assets/images/5.jpg" alt="">
                <p>海澜之家夏季v领纯色修身t恤</p>
                <p>￥99</p>
            </a>
        </div>
        <div class="col-md-3" >
        <a href="#">
            <img src="/assets/images/5.jpg" alt="">
            <p>海澜之家夏季v领纯色修身t恤</p>
            <p>￥99</p>
        </a>
    </div>
        <div class="col-md-3" >
            <a href="#">
                <img src="/assets/images/5.jpg" alt="">
                <p>海澜之家夏季v领纯色修身t恤</p>
                <p>￥99</p>
            </a>
        </div>
        <div class="col-md-3" >
            <a href="#">
                <img src="/assets/images/5.jpg" alt="">
                <p>海澜之家夏季v领纯色修身t恤</p>
                <p>￥99</p>
            </a>
        </div>
    </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.0.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $("#top-part").load("top-part.html");
</script>

</html>