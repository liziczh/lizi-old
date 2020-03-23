<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="GB18030"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>网上购物系统</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <style>
        .container {
            background: cornsilk;
        }
        img{
            width: 100%;
        }
        ul{
            border: 0;
        }
        .list-group-item{
            margin-bottom: 0;
            border: 0;
            border-bottom: 1px dashed #ddd;
            background-color: inherit;
        }
        .list2{
            border-bottom: 0;
        }
        .panel-default>.panel-heading {
            color: #e4393c;
            background-color: #f7f7f7;
            border-color: #fff;
        }
        .panel-default {
            border-color: #fff;
        }
        .panel-group .panel-heading+.panel-collapse>.panel-body {
            border-top: 1px solid #fff;
        }
        .save {
            padding: 6px 30px;
        }
    </style>
</head>

<body>

<!--顶部导航-->
<div id="top-part"></div>
<!--主体内容-->
<div class="container">
    <div class="row">
        <div class="col-md-3" style="background: #f7f7f7">
            <ul class="list-group">
                <li  class="list-group-item" id="userimg-part"></li>
                <li class="list-group-item">图书管理
                    <ul class="list-group">
                        <li class="list-group-item list2">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">进货管理</a>
                        </li>
                        <li class="list-group-item list2">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">库存管理</a>
                        </li>
                        <li class="list-group-item list2">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">限时优惠商品</a>
                        </li>
                    </ul>
                </li>
                <li class="list-group-item">
                    专题广告
                    <ul class="list-group">
                        <li class="list-group-item list2">
                            <a data-toggle="collapse"  data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">专题管理</a>
                        </li>
                        <li class="list-group-item list2">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">广告管理</a>
                        </li>
                    </ul>
                </li>
                <li class="list-group-item">
                    订单管理
                    <ul class="list-group">
                        <!-- 可以看见用户下的单，然后决定是否接单，然后进行发货反馈 -->
                        <li class="list-group-item list2">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="false" aria-controls="collapseSix">接单</a>
                        </li>
                        <!-- 同意退货 -->
                        <li class="list-group-item list2">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven">退换货</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="col-md-8">
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                进货管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <div class="col-md-offset-1 col-md-7">
                                <form class="form-horizontal" style="margin-top: 40px" action="${pageContext.request.contextPath}/admin/stockCommodityIn.do" method="post">
                                    <div class="form-group">
                                        <label for="input1-1" class="col-sm-2 control-label">图书名称</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-1" name="commodityName" placeholder="图书名称">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-2" class="col-sm-2 control-label">图书作者</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-2" name="authorName" placeholder="图书作者">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-3" class="col-sm-2 control-label">图书图片</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-3" name="commodityImg">
                                            <br>
                                            <button id="upLoadAvatar-in"> 上传图片</button>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-4" class="col-sm-2 control-label">出版社名</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-4" name="publishHouse" placeholder="出版社名">
                                        </div>
                                    </div>
                                    <!-- 测试下拉 -->
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">图书分类</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-14" name="categoryId" placeholder="图书分类">
                                        </div>
                                        <!--<ul class="nav navbar-nav" name="categoryId">
                                            <li class="dropdown">
                                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                    第一级分类
                                                    <b class="caret"></b>
                                                </a>
                                                <ul class="dropdown-menu">
                                                    <li><a href="#">人文社科</a></li>
                                                    <li><a href="#">文艺</a></li>
                                                    <li><a href="#">感情</a></li>
                                                    <li><a href="#">心理/励志</a></li>
                                                    <li><a href="#">教育</a></li>
                                                    <li><a href="#">科技</a></li>
                                                    <li><a href="#">生活</a></li>
                                                </ul>
                                            </li>
                                            <li class="dropdown">
                                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                    第二级分类
                                                    <b class="caret"></b>
                                                </a>
                                                <ul class="dropdown-menu">
                                                    <li><a href="#">小说</a></li>
                                                    <li><a href="#">青春文学</a></li>
                                                    <li><a href="#">文学</a></li>
                                                    <li><a href="#">艺术</a></li>
                                                    <li><a href="#">传记</a></li>
                                                </ul>
                                            </li>
                                            <li class="dropdown">
                                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                    第三级分类
                                                    <b class="caret"></b>
                                                </a>
                                                <ul class="dropdown-menu">
                                                    <li><a href="#">侦探/悬疑</a></li>
                                                    <li><a href="#">情感都市</a></li>
                                                    <li><a href="#">科幻/魔幻</a></li>
                                                    <li><a href="#">作品集</a></li>
                                                    <li><a href="#">外国小说</a></li>
                                                </ul>
                                            </li>
                                        </ul>-->
                                    </div>
                                    <!-- end -->
                                    <div class="form-group">
                                        <label for="input1-6" class="col-sm-2 control-label">图书描述</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-6" name="commodityDescription" placeholder="图书描述">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-7" class="col-sm-2 control-label">图书简介</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="commodityIntroduction" id="input1-7" placeholder="图书简介">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-8" class="col-sm-2 control-label">图书目录</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="commodityContents" id="input1-8" placeholder="图书目录">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-9" class="col-sm-2 control-label">图书数量</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="commodityCount" id="input1-9" placeholder="图书数量">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-10" class="col-sm-2 control-label">图书定价</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-10" name="commodityPrice" placeholder="图书定价">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-11" class="col-sm-2 control-label">图书抢购价</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-11" name="commodityPurchasePrice" placeholder="图书抢购价">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-12" class="col-sm-2 control-label">出版时间</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-12" name="publishDate" placeholder="出版时间">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-13" class="col-sm-2 control-label">图书销量</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-13" name="salesCount" placeholder="图书销量">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-3 col-sm-8">
                                            <button type="submit" class="btn btn-success save">保存</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 库存管理 -->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                库存管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <div>
                                <table class="table  table-bordered table-hover">
                                    <thead >
                                    <tr class="row  mx-0" >
                                        <th class="store-manager">图书名称</th>
                                        <th class="store-manager">图书作者</th>
                                        <th class="store-manager">出版社名</th>
                                        <th class="store-manager">图书数量</th>
                                        <th class="store-manager">图书价格</th>
                                        <th class="store-manager">图书分类</th>
                                        <th class="store-manager">图书编辑</th>
                                        <th class="store-manager">图书删除</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${commodityList}" var="commodity" varStatus="status">
                                            <tr id="commodity" class="row  mx-0" data-toggle="collapse" href="#collapseTwo-One">
                                                <td class="store-manager">${commodity.commodityName}</td>
                                                <td class="store-manager">${commodity.authorName}</td>
                                                <td class="store-manager">${commodity.publishHouse}</td>
                                                <td class="store-manager">${commodity.commodityPrice}</td>
                                                <td class="store-manager">${commodity.commodityPurchasePrice}</td>
                                                <td class="store-manager">${commodity.categoryId}</td>
                                                <td class="store-manager btn-primary " data-toggle="modal" data-target="#myModal-stockEdit" click="editCommodity(this)">编辑</td>
                                                <td class="store-manager btn-warning">删除</td>
                                                <input type="hidden" value="${commodity.commodityImg}">
                                                <input type="hidden" value="${commodity.commodityDescription}">
                                                <input type="hidden" value="${commodity.commodityIntroduction}">
                                                <input type="hidden" value="${commodity.commodityContents}">
                                                <input type="hidden" value="${commodity.commodityCount}">
                                                <input type="hidden" value="${commodity.publishDate}">
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <!-- 模态框（Modal） -->
                                <div class="modal fade" id="myModal-stockEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <form action="${pageContext.request.contextPath}/admin/editCommodityInfo.do">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                    <p><h1>书籍信息</h1></p>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="form-group">
                                                        <label for="authorName1" class="col-sm-2 control-label middle-font">图书作者</label>
                                                        <input type="text" class="form-control" id="authorName1" name="authorName1" >
                                                    </div>
                                                    <div>
                                                        <label for="commodityImg1" class="col-sm-2 control-label middle-font">图书图片</label>
                                                        <input type="text" class="form-control" id="commodityImg1" name="commodityImg1"><br>
                                                        <button id="upLoadAvatar"> 上传图片</button><br>
                                                    </div>
                                                    <div>
                                                        <br>
                                                        <label for="publishHouse1" class="col-sm-2 control-label middle-font">出版社名</label>
                                                        <input type="text" class="form-control" id="publishHouse1" name="publishHouse1" >
                                                    </div>
                                                    <div>
                                                        <br>
                                                        <label for="categoryId1" class="col-sm-2 control-label middle-font" style="width: 100%;">图书分类</label>
                                                        <input type="text" class="form-control" id="categoryId1" name="categoryId1">
                                                    </div>
                                                    <%--<div class="form-group">--%>
                                                        <%--<ul class="nav navbar-nav" style="width: 100%;">--%>
                                                            <%--<li class="dropdown">--%>
                                                                <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown">--%>
                                                                    <%--第一级分类--%>
                                                                    <%--<b class="caret"></b>--%>
                                                                <%--</a>--%>
                                                                <%--<ul class="dropdown-menu">--%>
                                                                    <%--<li><a href="#">人文社科</a></li>--%>
                                                                    <%--<li><a href="#">文艺</a></li>--%>
                                                                    <%--<li><a href="#">感情</a></li>--%>
                                                                    <%--<li><a href="#">心理/励志</a></li>--%>
                                                                    <%--<li><a href="#">教育</a></li>--%>
                                                                    <%--<li><a href="#">科技</a></li>--%>
                                                                    <%--<li><a href="#">生活</a></li>--%>
                                                                <%--</ul>--%>
                                                            <%--</li>--%>
                                                            <%--<li class="dropdown">--%>
                                                                <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown">--%>
                                                                    <%--第二级分类--%>
                                                                    <%--<b class="caret"></b>--%>
                                                                <%--</a>--%>
                                                                <%--<ul class="dropdown-menu">--%>
                                                                    <%--<li><a href="#">小说</a></li>--%>
                                                                    <%--<li><a href="#">青春文学</a></li>--%>
                                                                    <%--<li><a href="#">文学</a></li>--%>
                                                                    <%--<li><a href="#">艺术</a></li>--%>
                                                                    <%--<li><a href="#">传记</a></li>--%>
                                                                <%--</ul>--%>
                                                            <%--</li>--%>
                                                            <%--<li class="dropdown">--%>
                                                                <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown">--%>
                                                                    <%--第三级分类--%>
                                                                    <%--<b class="caret"></b>--%>
                                                                <%--</a>--%>
                                                                <%--<ul class="dropdown-menu">--%>
                                                                    <%--<li><a href="#">侦探/悬疑</a></li>--%>
                                                                    <%--<li><a href="#">情感都市</a></li>--%>
                                                                    <%--<li><a href="#">科幻/魔幻</a></li>--%>
                                                                    <%--<li><a href="#">作品集</a></li>--%>
                                                                    <%--<li><a href="#">外国小说</a></li>--%>
                                                                <%--</ul>--%>
                                                            <%--</li>--%>
                                                        <%--</ul>--%>
                                                    <%--</div><br>--%>
                                                    <div>
                                                        <br>
                                                        <label for="commodityDescription1" class="col-sm-2 control-label middle-font">书籍描述</label>
                                                        <input type="text" class="form-control" id="commodityDescription1" >
                                                    </div>
                                                    <div>
                                                        <br>
                                                        <label for="commodityIntroduction1" class="col-sm-2 control-label middle-font">图书简介</label>
                                                        <input type="text" class="form-control" id="commodityIntroduction1" >
                                                    </div>
                                                    <div>
                                                        <br>
                                                        <label for="commodityContents1" class="col-sm-2 control-label middle-font">图书目录</label>
                                                        <input type="text" class="form-control" id="commodityContents1">
                                                    </div>
                                                    <div>
                                                        <br>
                                                        <label for="commodityCount1" class="col-sm-2 control-label middle-font">图书数量</label>
                                                        <input type="text" class="form-control" id="commodityCount1" >
                                                    </div>
                                                    <div >
                                                        <br>
                                                        <label for="commodityPrice1" class="col-sm-2 control-label middle-font">图书定价</label>
                                                        <input type="text" class="form-control" id="commodityPrice1" >
                                                    </div>
                                                    <div >
                                                        <br>
                                                        <label for="commodityPurchasePrice1" class="col-sm-2 control-label middle-font">图书抢购价</label>
                                                        <input type="text" class="form-control" id="commodityPurchasePrice1">
                                                    </div>
                                                    <div >
                                                        <br>
                                                        <label for="publishDate1" class="col-sm-2 control-label middle-font">出版时间</label>
                                                        <input type="text" class="form-control" id="publishDate1" >
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                    <button type="submit" class="btn btn-primary">提交更改</button>
                                                </div>
                                            </div><!-- /.modal-content -->
                                        </div><!-- /.modal -->
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 限时优惠商品 -->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                限时优惠商品
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                        <div class="panel-body">
                            <div class="col-md-21">
                                <table class="table  table-bordered table-hover">
                                    <thead >
                                    <tr class="row  mx-0" >
                                        <th class="store-manager" >图书名称</th>
                                        <th class="store-manager">图书作者</th>
                                        <th class="store-manager">出版社名</th>
                                        <th class="store-manager">图书数量</th>
                                        <th class="store-manager">图书价格</th>
                                        <th class="store-manager">图书分类</th>
                                        <th class="store-manager">图书编辑</th>
                                        <th class="store-manager">图书删除</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${purchaseCommodityList}" var="purchaseCommodity" varStatus="status">
                                        <tr class="row  mx-0" data-toggle="collapse" href="#collapseTwo-One">
                                            <td class="store-manager">${purchaseCommodity.commodityName}</td>
                                            <td class="store-manager">${purchaseCommodity.authorName}</td>
                                            <td class="store-manager">${purchaseCommodity.publishHouse}</td>
                                            <td class="store-manager">${purchaseCommodity.commodityPrice}</td>
                                            <td class="store-manager">${purchaseCommodity.commodityPurchasePrice}</td>
                                            <td class="store-manager">${purchaseCommodity.categoryId}</td>
                                            <td class="store-manager btn-primary" data-toggle="modal" data-target="#myModal-favourable">编辑</td>
                                            <td class="store-manager btn-warning">删除</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <div class="well">
                                    <a href="#" id="example" class="btn btn-success" rel="popover" data-content="点此添加优惠商品" >添加优惠商品</a>
                                </div>
                                <!-- 模态框（Modal） -->
                                <div class="modal fade" id="myModal-favourable" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                <p><h1>优惠书籍</h1></p>
                                            </div>
                                            <div class="modal-body">
                                                <div class="form-group">
                                                    <br>
                                                    <label for="commodityName" class="col-sm-2 control-label middle-font">图书名称</label>
                                                    <input type="text" class="form-control" id="commodityName" placeholder="图书名称">
                                                </div>
                                                <div class="form-group">
                                                    <br>
                                                    <label for="authorName" class="col-sm-2 control-label middle-font">图书作者</label>
                                                    <input type="text" class="form-control" id="authorName" placeholder="图书作者">
                                                </div>
                                                <div>
                                                    <br>
                                                    <label for="publishHouse" class="col-sm-2 control-label middle-font">出版社名</label>
                                                    <input type="text" class="form-control" id="publishHouse" placeholder="出版社名">
                                                </div>
                                                <div>
                                                    <br>
                                                    <label for="commodityCount" class="col-sm-2 control-label middle-font">图书数量</label>
                                                    <input type="text" class="form-control" id="commodityCount" placeholder="图书数量">
                                                </div>
                                                <div >
                                                    <br>
                                                    <label for="commodityPrice" class="col-sm-2 control-label middle-font">图书价格</label>
                                                    <input type="text" class="form-control" id="commodityPrice" placeholder="图书价格">
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                <button type="submit" class="btn btn-primary">提交更改</button>
                                            </div>
                                        </div><!-- /.modal-content -->
                                    </div><!-- /.modal -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 专题管理 -->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFour">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                专题管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                        <div class="panel-body">
                            <div class="">
                                <table class="table  table-bordered table-hover">
                                    <thead class="store-manager">
                                    <tr>
                                        <th class="store-manager">专题编号</th>
                                        <th class="store-manager">专题分类</th>
                                        <th class="store-manager">专题编辑</th>
                                        <th class="store-manager">专题删除</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${specialList}" var="special" varStatus="status">
                                            <tr>
                                                <td class="store-manager">${special.specialId}</td>
                                                <td class="store-manager">${special.specialSort}</td>
                                                <td class="store-manager btn-primary" id="specialEdit" data-toggle="modal" data-target="#myModal-editSpecial">编辑</td>
                                                <td class="store-manager btn-warning">删除</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <!-- 模态框（Modal） -->
                                <div class="modal fade" id="myModal-editSpecial" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                <p><h1>专题管理</h1></p>
                                            </div>
                                            <div class="modal-body">
                                                <div class="form-group">
                                                    <br>
                                                    <label for="input4-1" class="col-sm-2 control-label middle-font">专题分类</label>
                                                    <input type="text" class="form-control" id="input4-1" placeholder="专题分类">
                                                </div>
                                                <div class="form-group">
                                                    <br>
                                                    <label for="input4-2" class="col-sm-2 control-label middle-font">专题图片</label>
                                                    <input type="text" class="form-control" id="input4-2" placeholder="专题图片">
                                                    <button type="button" class="btn btn-success">上传图片</button>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                <button type="submit" class="btn btn-primary">提交更改</button>
                                            </div>
                                        </div><!-- /.modal-content -->
                                    </div><!-- /.modal -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 广告管理 -->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFive">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                广告管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
                        <div class="panel-body">
                            <div>
                                <table class="table  table-bordered table-hover">
                                    <thead class="store-manager w-100">
                                    <tr>
                                        <th class="store-manager" >广告名称</th>
                                        <th class="store-manager">广告图片</th>
                                        <th class="store-manager">广告编辑</th>
                                        <th class="store-manager">广告删除</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="item" items="${adlist}" varStatus="staturs">
                                        <tr>
                                            <td class="store-manager">${item.adName}</td>
                                            <td class="store-manager">${item.adImg}</td>
                                            <td class="store-manager btn-primary" id="adEdit" data-toggle="modal" data-target="#myModal-advertisement" onclick="editInfo(this)">编辑</td>                                        <td class="store-manager btn-warning">删除</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <div class="well">
                                    <a href="${pageContext.request.contextPath}/admin/insertAdvertisement.do" id="addAdvertisement" class="btn btn-success" rel="popover" data-content="点此添加广告" >添加广告</a>
                                </div>
                                <!-- 模态框（Modal） -->
                                <div class="modal fade" id="myModal-advertisement" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <form>
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                    <p><h1>广告管理</h1></p>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="form-group">
                                                        <br>
                                                        <label for="adName" class="col-sm-2 control-label middle-font">广告名称</label>
                                                        <input type="text" class="form-control" id="adName" placeholder="广告名称">
                                                    </div>
                                                    <div class="form-group">
                                                        <br>
                                                        <label for="adImg" class="col-sm-2 control-label middle-font">广告图片</label>
                                                        <input type="text" class="form-control" id="adImg" placeholder="广告图片">
                                                        <input type="hidden" id="adId">
                                                        <button type="button" class="btn btn-success">上传图片</button>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal"><a href="/admin/findAdvertisement">关闭</a>
                                                    </button>
                                                    <button type="submit" class="btn btn-primary"><a href="${pageContext.request.contextPath}/admin/insertAdvertisement.do"></a>添加</button>
                                                </div>
                                            </div><!-- /.modal-content -->
                                        </form>
                                    </div><!-- /.modal -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 接单 -->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingSix">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="false" aria-controls="collapseSix">接单</a>
                        </h4>
                    </div>
                    <div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
                        <div class="panel-body">
                            <div >
                                <table class="table  table-bordered table-hover">
                                    <thead class="store-manager w-100">
                                    <tr>
                                        <th class="store-manager">买家名称</th>
                                        <th class="store-manager">联系方式</th>
                                        <th class="store-manager">图书名称</th>
                                        <th class="store-manager">图书数量</th>
                                        <th class="store-manager">总价</th>
                                        <th class="store-manager">收货地址</th>
                                        <th class="store-manager">编辑订单</th>
                                        <th class="store-manager">确认接单</th>
                                        <th class="store-manager">拒绝接单</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr >
                                        <td class=" store-manager">今生今世</td>
                                        <td class="store-manager">7417417474741</td>
                                        <td class="store-manager">白夜行</td>
                                        <td class="store-manager">1</td>
                                        <td class="store-manager">50</td>
                                        <td class="store-manager">山西省晋中市五金山镇太原师范学院</td>
                                        <td class="store-manager btn-primary" data-toggle="modal" data-target="#myModal-order">编辑</td>
                                        <td class="store-manager btn-success">确认接单</td>
                                        <td class="store-manager btn-warning">拒绝接单</td>
                                    </tr>
                                    <tr >
                                        <td class=" store-manager">今生今世</td>
                                        <td class="store-manager">7417417474741</td>
                                        <td class="store-manager">白夜行</td>
                                        <td class="store-manager">1</td>
                                        <td class="store-manager">50</td>
                                        <td class="store-manager">山西省晋中市五金山镇太原师范学院</td>
                                        <td class="store-manager btn-primary" data-toggle="modal" data-target="#myModal-order">编辑</td>
                                        <td class="store-manager btn-success">确认接单</td>
                                        <td class="store-manager btn-warning">拒绝接单</td>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class="modal fade" id="myModal-order" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                <p><h1>订单信息</h1></p>
                                            </div>
                                            <div class="modal-body">
                                                <div class="form-group">
                                                    <br>
                                                    <label for="input6-1" class="col-sm-2 control-label middle-font">买家名称</label>
                                                    <input type="text" class="form-control" id="input6-1" >
                                                </div>
                                                <div class="form-group">
                                                    <br>
                                                    <label for="input6-2" class="col-sm-2 control-label middle-font">联系方式</label>
                                                    <input type="text" class="form-control" id="input6-2">
                                                </div>
                                                <div class="form-group">
                                                    <br>
                                                    <label for="input6-3" class="col-sm-2 control-label middle-font">图书名称</label>
                                                    <input type="text" class="form-control" id="input6-3" >
                                                </div>
                                                <div class="form-group">
                                                    <br>
                                                    <label for="input6-4" class="col-sm-2 control-label middle-font">数量</label>
                                                    <input type="text" class="form-control" id="input6-4" >
                                                </div>
                                                <div class="form-group">
                                                    <br>
                                                    <label for="input6-5" class="col-sm-2 control-label middle-font">总价</label>
                                                    <input type="text" class="form-control" id="input6-5" >
                                                </div>
                                                <div class="form-group">
                                                    <br>
                                                    <label for="input6-6" class="col-sm-2 control-label middle-font">收货地址</label>
                                                    <input type="text" class="form-control" id="input6-6" >
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                                </button>
                                                <button type="button" class="btn btn-primary">提交更改</button>
                                            </div>
                                        </div><!-- /.modal-content -->
                                    </div><!-- /.modal -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 退换货 -->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingSeven">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven">
                                退换货
                            </a>
                        </h4>
                    </div>
                    <div id="collapseSeven" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSeven">
                        <div class="panel-body">
                            <div>
                                <table class="table  table-bordered table-hover">
                                    <thead class="store-manager w-100">
                                    <tr>
                                        <th class="store-manager">买家名称</th>
                                        <th class="store-manager">图书名称</th>
                                        <th class="store-manager">图书数量</th>
                                        <th class="store-manager">总价</th>
                                        <th class="store-manager">退货原因</th>
                                        <th class="store-manager">货物状态</th>
                                        <th class="store-manager">收货地址</th>
                                        <th class="store-manager">同意退货</th>
                                        <th class="store-manager">拒绝退货</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr >
                                        <td class=" store-manager">今生今世</td>
                                        <td class="store-manager">白夜行</td>
                                        <td class="store-manager">1</td>
                                        <td class="store-manager">50</td>
                                        <td class="store-manager">七天无理由退货</td>
                                        <td class="store-manager">仅退款</td>
                                        <td class="store-manager">山西省晋中市五金山镇太原师范学院</td>
                                        <td class="store-manager btn-success">同意退货</td>
                                        <td class="store-manager btn-warning">拒绝退货</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">
    $("#top-part").load("top-part.html");
    $("#search-part").load("search-part.html");
    $("#userimg-part").load("userimg-part.html");
    $(function (){
        $("#example").popover({trigger: 'focus'});
    });

    function editCommodity(obj) {
        var tds= $(obj).parent().find('td');
        var inputs = $(obj).parent().find('input');
        alert(tds);
        alert(inputs);
        $('#authorName1').val(tds[1].innerText);
        $('#commodityImg1').val(inputs[0].innerText);
        $('#publishHouse1').val(tds[2].innerText);
        $('#categoryId1').val(tds[5].innerText);
        $('#commodityDescription1').val(inputs[1].innerText);
        $('#commodityIntroduction1').val(inputs[2].innerText);
        $('#commodityContents1').val(inputs[3].innerText);
        $('#commodityCount1').val(inputs[4].innerText);
        $('#commodityPrice1').val(tds[3].innerText);
        $('#commodityPurchasePrice1').val(tds[4].innerText);
        $('#publishDate1').val(inputs[5].innerText);
        $('#myModal-stockEdit').modal('show');
    }

    function editInfo(obj) {
        var tds= $(obj).parent().find('td');
        $('#adName').val(tds[0].innerText);
        $('#adImg').val(tds[1].innerText);
        $('#adId').val(tds[2].innerText);
        $('#myModal-editAadvertisementodal').modal('show');
    }

    // $(document).ready(function(){
    //     $.ajax({
    //         url:'/admin/getAllCommodity.do',
    //         type:'Post',
    //         dataType:'json',
    //         success:function(data){
    //             //将json字符串转换成json对象
    //             var commodityList = eval("("+data+")");
    //             $.each(commodityList,function (index,item) {
    //                 $("#commodity").append("<td class=\"store-manager\">"+item[0]+"</td>");
    //             })
    //         }
    //     });
    // })
</script>

</html>