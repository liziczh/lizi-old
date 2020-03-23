<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=GB18030"
         pageEncoding="GB18030"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>网上购物系统</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
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
<!--<div id="search-part"></div>-->

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
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">专题管理</a>

                        </li>
                        <!-- <li class="list-group-item list2">
                            <span class="badge">14</span>
                            待付款
                        </li> -->
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
                                <form class="form-horizontal" style="margin-top: 40px">
                                    <div class="form-group">
                                        <label for="input1-1" class="col-sm-2 control-label ">图书名称</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-1" placeholder="图书名称">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-2" class="col-sm-2 control-label">图书作者</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-2" placeholder="图书作者">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-3" class="col-sm-2 control-label">图书图片</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-3">
                                            <br>
                                            <button id="upLoadAvatar-in"> 上传图片</button>

                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-4" class="col-sm-2 control-label">出版社名</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-4" placeholder="出版社名">
                                        </div>
                                    </div>

                                    <!-- 测试下拉 -->
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">图书分类</label>
                                        <ul class="nav navbar-nav">

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
                                        </ul>
                                    </div>
                                    <!-- end -->
                                    <div class="form-group">
                                        <label for="input1-6" class="col-sm-2 control-label">图书描述</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-6" placeholder="图书描述">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-7" class="col-sm-2 control-label">图书简介</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-7" placeholder="图书简介">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-8" class="col-sm-2 control-label">图书目录</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-8" placeholder="图书目录">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-9" class="col-sm-2 control-label">图书数量</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-9" placeholder="图书数量">
                                        </div>
                                    </div>



                                    <div class="form-group">
                                        <label for="input1-10" class="col-sm-2 control-label">图书定价</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-10" placeholder="图书定价">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-11" class="col-sm-2 control-label">图书抢购价</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-11" placeholder="图书抢购价">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-12" class="col-sm-2 control-label">出版时间</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-12" placeholder="出版时间">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-13" class="col-sm-2 control-label">图书销量</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-13" placeholder="图书销量">
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <div class="col-sm-offset-3 col-sm-8">
                                            <button type="button" class="btn btn-success save">重置</button>
                                            <button type="button" class="btn btn-success save">保存</button>
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
                                        <c:forEach items="${commodityList}" var="commodity" varStatus="status">
                                            <tr class="row  mx-0" data-toggle="collapse" href="#collapseTwo-One">
                                                <td class="store-manager">${commodity.commodityName}</td>
                                                <td class="store-manager">${commodity.authorName}</td>
                                                <td class="store-manager">${commodity.publishHouse}</td>
                                                <td class="store-manager">${commodity.commodityPrice}</td>
                                                <td class="store-manager">${commodity.commodityPurchasePrice}</td>
                                                <td class="store-manager">${commodity.categoryId}</td>
                                                <td class="store-manager btn-primary">编辑</td>
                                                <td class="store-manager btn-warning">删除</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                                <!-- 模态框（Modal） -->
                                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                    &times;
                                                </button>
                                                <p><h1>书籍信息</h1></p>
                                            </div>
                                            <div class="modal-body">
                                                <div class="form-group">

                                                    <label for="input2-1" class="col-sm-2 control-label middle-font">图书作者</label>
                                                    <input type="text" class="form-control" id="input2-1" placeholder="图书作者">
                                                </div>
                                                <div>
                                                    <label for="input2-2" class="col-sm-2 control-label middle-font">图书图片</label>
                                                    <input type="text" class="form-control" id="input2-2"><br>
                                                    <button id="upLoadAvatar"> 上传图片</button><br>
                                                </div>
                                                <div>
                                                    <br>
                                                    <label for="input2-3" class="col-sm-2 control-label middle-font">出版社名</label>
                                                    <input type="text" class="form-control" id="input2-3" placeholder="出版社名">
                                                </div>
                                                <div>
                                                    <br>
                                                    <label  class="col-sm-2 control-label middle-font" style="width: 100%;">图书分类</label>


                                                </div>
                                                <div class="form-group">
                                                    <ul class="nav navbar-nav" style="width: 100%;">

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
                                                    </ul>
                                                </div>

                                                <br>
                                                <div>
                                                    <br>
                                                    <label for="input2-5" class="col-sm-2 control-label middle-font">书籍描述</label>
                                                    <input type="text" class="form-control" id="input2-5" placeholder="书籍描述">
                                                </div>
                                                <div>
                                                    <br>
                                                    <label for="input2-6" class="col-sm-2 control-label middle-font">图书简介</label>
                                                    <input type="text" class="form-control" id="input2-6" placeholder="图书简介">
                                                </div>
                                                <div>
                                                    <br>
                                                    <label for="input2-7" class="col-sm-2 control-label middle-font">图书目录</label>
                                                    <input type="text" class="form-control" id="input2-7" placeholder="图书目录">
                                                </div>
                                                <div>
                                                    <br>
                                                    <label for="input2-8" class="col-sm-2 control-label middle-font">图书数量</label>
                                                    <input type="text" class="form-control" id="input2-8" placeholder="图书数量">
                                                </div>
                                                <div >
                                                    <br>
                                                    <label for="input2-9" class="col-sm-2 control-label middle-font">图书定价</label>
                                                    <input type="text" class="form-control" id="input2-9" placeholder="图书定价">
                                                </div>
                                                <div >
                                                    <br>
                                                    <label for="input2-10" class="col-sm-2 control-label middle-font">图书抢购价</label>
                                                    <input type="text" class="form-control" id="input2-10" placeholder="图书抢购价">
                                                </div>
                                                <div >
                                                    <br>
                                                    <label for="input2-11" class="col-sm-2 control-label middle-font">出版时间</label>
                                                    <input type="text" class="form-control" id="input2-11" placeholder="出版时间">
                                                </div>
                                                <div >
                                                    <br>
                                                    <label for="input2-12" class="col-sm-2 control-label middle-font">图书销量</label>
                                                    <input type="text" class="form-control" id="input2-12" placeholder="图书销量">
                                                </div>
                                                <div >
                                                    <br>
                                                    <label for="input2-13" class="col-sm-2 control-label middle-font">图书分类</label>
                                                    <div class="dropdown">
                                                        <a href="#" class=" dropdown-toggle" data-toggle="dropdown">
                                                            <input type="text" class="form-control" id="input2-13" placeholder="图书分类">
                                                        </a>
                                                        <ul class="dropdown-menu" aria-labelledby="input2-13">
                                                            <li class="divider"></li>
                                                            <li><a href="#">小说</a></li>
                                                            <li class="divider"></li>
                                                            <li><a href="#">悬疑推理</a></li>
                                                        </ul>
                                                    </div>

                                                </div>


                                            </div>

                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                                </button>
                                                <button type="button" class="btn btn-primary">
                                                    提交更改
                                                </button>
                                            </div>
                                        </div><!-- /.modal-content -->
                                    </div><!-- /.modal -->
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
                                    <thead class="store-manager w-100">
                                    <tr class="row  mx-0">
                                        <th class="store-manager" >图书名称</th>
                                        <th class="store-manager">图书作者</th>
                                        <th class="store-manager">出版社名</th>
                                        <th class="store-manager">图书图片</th>
                                        <th class="store-manager">图书数量</th>
                                        <th class="store-manager">图书价格</th>
                                        <th class="store-manager">图书分类</th>
                                        <th class="store-manager">图书编辑</th>
                                        <th class="store-manager">图书删除</th>
                                    </tr>

                                    </thead>
                                    <tbody>
                                    <tr class="row  mx-0">
                                        <td class=" store-manager">白夜行</td>
                                        <td class="store-manager">东野奎吾</td>
                                        <td class="store-manager">出版社名</td>
                                        <td class="store-manager">图书图片</td>
                                        <td class="store-manager">图书数量</td>
                                        <td class="store-manager">图书价格</td>
                                        <td class="store-manager">图书分类</td>
                                        <td class="store-manager btn-primary" data-toggle="modal" data-target="#myModal-youhui">编辑</td>
                                        <td class="store-manager btn-warning">删除</td>
                                    </tr>
                                    <tr class="row  mx-0">
                                        <td class=" store-manager">白夜行</td>
                                        <td class="store-manager">东野奎吾</td>
                                        <td class="store-manager">出版社名</td>
                                        <td class="store-manager">图书图片</td>
                                        <td class="store-manager">图书数量</td>
                                        <td class="store-manager">图书价格</td>
                                        <td class="store-manager">图书分类</td>
                                        <td class="store-manager btn-primary" data-toggle="modal" data-target="#myModal-youhui">编辑</td>
                                        <td class="store-manager btn-warning">删除</td>
                                    </tr>
                                    <tr class="row  mx-0">
                                        <td class=" store-manager">白夜行</td>
                                        <td class="store-manager">东野奎吾</td>
                                        <td class="store-manager">出版社名</td>
                                        <td class="store-manager">图书图片</td>
                                        <td class="store-manager">图书数量</td>
                                        <td class="store-manager">图书价格</td>
                                        <td class="store-manager">图书分类</td>
                                        <td class="store-manager btn-primary" data-toggle="modal" data-target="#myModal-youhui">编辑</td>
                                        <td class="store-manager btn-warning">删除</td>
                                    </tr>
                                    </tbody>

                                </table>


                                <div class="well">
                                    <a href="#" id="example" class="btn btn-success" rel="popover" data-content="点此添加优惠商品" >添加优惠商品</a>
                                </div>
                                <!-- 模态框（Modal） -->
                                <div class="modal fade" id="myModal-youhui" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                    &times;
                                                </button>
                                                <p><h1>优惠书籍</h1></p>
                                            </div>
                                            <div class="modal-body">
                                                <div class="form-group">
                                                    <br>
                                                    <label for="input3-1" class="col-sm-2 control-label middle-font">图书名称</label>
                                                    <input type="text" class="form-control" id="input3-1" placeholder="图书名称">
                                                </div>
                                                <div class="form-group">
                                                    <br>
                                                    <label for="input3-2" class="col-sm-2 control-label middle-font">图书作者</label>
                                                    <input type="text" class="form-control" id="input3-2" placeholder="图书作者">
                                                </div>

                                                <div>
                                                    <br>
                                                    <label for="input3-3" class="col-sm-2 control-label middle-font">出版社名</label>
                                                    <input type="text" class="form-control" id="input3-3" placeholder="出版社名">
                                                </div>

                                                <div>
                                                    <br>
                                                    <label for="input3-4" class="col-sm-2 control-label middle-font">图书数量</label>
                                                    <input type="text" class="form-control" id="input3-4" placeholder="图书数量">
                                                </div>
                                                <div >
                                                    <br>
                                                    <label for="input3-5" class="col-sm-2 control-label middle-font">图书价格</label>
                                                    <input type="text" class="form-control" id="input3-5" placeholder="图书价格">
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
                                        <th class="store-manager"></th>
                                        <th class="store-manager"></th>
                                    </tr>

                                    </thead>
                                    <tbody>
                                    <tr >
                                        <td class="store-manager">1</td>
                                        <td class="store-manager">悬疑推理</td>
                                        <td class="store-manager btn-primary" data-toggle="modal" data-target="#myModal-zhuanti">编辑</td>
                                        <td class="store-manager btn-warning">删除</td>
                                    </tr>
                                    <tr >
                                        <td class="store-manager">2</td>
                                        <td class="store-manager">人文社科</td>
                                        <td class="store-manager btn-primary" data-toggle="modal" data-target="#myModal-zhuanti">编辑</td>
                                        <td class="store-manager btn-warning">删除</td>
                                    </tr>
                                    <tr>
                                        <td class="store-manager">3</td>
                                        <td class="store-manager">玄幻</td>
                                        <td class="store-manager btn-primary" data-toggle="modal" data-target="#myModal-zhuanti">编辑</td>
                                        <td class="store-manager btn-warning">删除</td>
                                    </tr>
                                    <tr>
                                        <td class="store-manager">4</td>
                                        <td class="store-manager">科技教育</td>
                                        <td class="store-manager btn-primary" data-toggle="modal" data-target="#myModal-zhuanti">编辑</td>
                                        <td class="store-manager btn-warning">删除</td>
                                    </tr>
                                    <tr>
                                        <td class="store-manager">5</td>
                                        <td class="store-manager">生活</td>
                                        <td class="store-manager btn-primary" data-toggle="modal" data-target="#myModal-zhuanti">编辑</td>
                                        <td class="store-manager btn-warning">删除</td>
                                    </tr>
                                    <tr>
                                        <td class="store-manager">6</td>
                                        <td class="store-manager">言情小说</td>
                                        <td class="store-manager btn-primary" data-toggle="modal" data-target="#myModal-zhuanti">编辑</td>
                                        <td class="store-manager btn-warning">删除</td>
                                    </tr>
                                    <tr>
                                        <td class="store-manager">7</td>
                                        <td class="store-manager">职场交往</td>
                                        <td class="store-manager btn-primary" data-toggle="modal" data-target="#myModal-zhuanti">编辑</td>
                                        <td class="store-manager btn-warning">删除</td>
                                    </tr>
                                    <tr>
                                        <td class="store-manager">8</td>
                                        <td class="store-manager">政治军事</td>
                                        <td class="store-manager btn-primary" data-toggle="modal" data-target="#myModal-zhuanti">编辑</td>
                                        <td class="store-manager btn-warning">删除</td>
                                    </tr>
                                    </tbody>

                                </table>
                                <!-- 模态框（Modal） -->
                                <div class="modal fade" id="myModal-zhuanti" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                    &times;
                                                </button>
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
                                        <th class="store-manager"></th>
                                        <th class="store-manager"></th>
                                    </tr>

                                    </thead>
                                    <tbody>

                                    <c:forEach var="item" items="${adlist}" varStatus="staturs" >
                                        <tr>
                                        <td class=" store-manager">${item.adName}</td>
                                        <td class="store-manager">${item.adImg}</td>
                                        <td class="store-manager btn-primary" data-toggle="modal" data-target="#myModal-advertisement">编辑</td>
                                        <td class="store-manager btn-warning">删除</td>
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
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                        &times;
                                                    </button>
                                                    <p><h1>广告管理</h1></p>
                                                </div>
                                                <div class="modal-body">

                                                    <div class="form-group">
                                                        <br>
                                                        <label for="input5-1" class="col-sm-2 control-label middle-font">广告名称</label>
                                                        <input type="text" class="form-control" id="input5-1" name="adName" placeholder="广告名称">
                                                    </div>

                                                    <div class="form-group">
                                                        <br>
                                                        <label for="input5-2" class="col-sm-2 control-label middle-font">广告图片</label>
                                                        <input type="text" class="form-control" id="input5-2" name="adImg" placeholder="广告图片">
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
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
                                接单
                            </a>
                        </h4>
                    </div>
                    <div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
                        <div class="panel-body">
                            <div >
                                <table class="table  table-bordered table-hover">
                                    <thead class="store-manager w-100">
                                    <tr>
                                        <th class="store-manager" >买家名称</th>
                                        <th class="store-manager">联系方式</th>
                                        <th class="store-manager">图书名称</th>
                                        <th class="store-manager">图书数量</th>
                                        <th class="store-manager">总价</th>
                                        <th class="store-manager">收货地址</th>
                                        <th class="store-manager"></th>
                                        <th class="store-manager"></th>
                                        <th class="store-manager"></th>
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
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                    &times;
                                                </button>
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
                                        <th class="store-manager" >买家名称</th>
                                        <th class="store-manager">图书名称</th>
                                        <th class="store-manager">图书数量</th>
                                        <th class="store-manager">总价</th>
                                        <th class="store-manager">退货原因</th>
                                        <th class="store-manager">货物状态</th>
                                        <th class="store-manager">收货地址</th>
                                        <th class="store-manager"></th>
                                        <th class="store-manager"></th>
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
<!-- <script src="js/jquery-1.11.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/2.0.0/jquery.min.js"></script>
<script src="/try/bootstrap/twitter-bootstrap-v2/js/bootstrap-tooltip.js"></script>
<script src="/try/bootstrap/twitter-bootstrap-v2/js/bootstrap-popover.js"></script> -->
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

</script>


</html>