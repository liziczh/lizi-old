<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="GB18030"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>���Ϲ���ϵͳ</title>
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

<!--��������-->
<div id="top-part"></div>
<!--��������-->
<div class="container">
    <div class="row">
        <div class="col-md-3" style="background: #f7f7f7">
            <ul class="list-group">
                <li  class="list-group-item" id="userimg-part"></li>
                <li class="list-group-item">ͼ�����
                    <ul class="list-group">
                        <li class="list-group-item list2">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">��������</a>
                        </li>
                        <li class="list-group-item list2">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">������</a>
                        </li>
                        <li class="list-group-item list2">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">��ʱ�Ż���Ʒ</a>
                        </li>
                    </ul>
                </li>
                <li class="list-group-item">
                    ר����
                    <ul class="list-group">
                        <li class="list-group-item list2">
                            <a data-toggle="collapse"  data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">ר�����</a>
                        </li>
                        <li class="list-group-item list2">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">������</a>
                        </li>
                    </ul>
                </li>
                <li class="list-group-item">
                    ��������
                    <ul class="list-group">
                        <!-- ���Կ����û��µĵ���Ȼ������Ƿ�ӵ���Ȼ����з������� -->
                        <li class="list-group-item list2">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="false" aria-controls="collapseSix">�ӵ�</a>
                        </li>
                        <!-- ͬ���˻� -->
                        <li class="list-group-item list2">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven">�˻���</a>
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
                                ��������
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <div class="col-md-offset-1 col-md-7">
                                <form class="form-horizontal" style="margin-top: 40px" action="${pageContext.request.contextPath}/admin/stockCommodityIn.do" method="post">
                                    <div class="form-group">
                                        <label for="input1-1" class="col-sm-2 control-label">ͼ������</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-1" name="commodityName" placeholder="ͼ������">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-2" class="col-sm-2 control-label">ͼ������</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-2" name="authorName" placeholder="ͼ������">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-3" class="col-sm-2 control-label">ͼ��ͼƬ</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-3" name="commodityImg">
                                            <br>
                                            <button id="upLoadAvatar-in"> �ϴ�ͼƬ</button>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-4" class="col-sm-2 control-label">��������</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-4" name="publishHouse" placeholder="��������">
                                        </div>
                                    </div>
                                    <!-- �������� -->
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">ͼ�����</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-14" name="categoryId" placeholder="ͼ�����">
                                        </div>
                                        <!--<ul class="nav navbar-nav" name="categoryId">
                                            <li class="dropdown">
                                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                    ��һ������
                                                    <b class="caret"></b>
                                                </a>
                                                <ul class="dropdown-menu">
                                                    <li><a href="#">�������</a></li>
                                                    <li><a href="#">����</a></li>
                                                    <li><a href="#">����</a></li>
                                                    <li><a href="#">����/��־</a></li>
                                                    <li><a href="#">����</a></li>
                                                    <li><a href="#">�Ƽ�</a></li>
                                                    <li><a href="#">����</a></li>
                                                </ul>
                                            </li>
                                            <li class="dropdown">
                                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                    �ڶ�������
                                                    <b class="caret"></b>
                                                </a>
                                                <ul class="dropdown-menu">
                                                    <li><a href="#">С˵</a></li>
                                                    <li><a href="#">�ഺ��ѧ</a></li>
                                                    <li><a href="#">��ѧ</a></li>
                                                    <li><a href="#">����</a></li>
                                                    <li><a href="#">����</a></li>
                                                </ul>
                                            </li>
                                            <li class="dropdown">
                                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                    ����������
                                                    <b class="caret"></b>
                                                </a>
                                                <ul class="dropdown-menu">
                                                    <li><a href="#">��̽/����</a></li>
                                                    <li><a href="#">��ж���</a></li>
                                                    <li><a href="#">�ƻ�/ħ��</a></li>
                                                    <li><a href="#">��Ʒ��</a></li>
                                                    <li><a href="#">���С˵</a></li>
                                                </ul>
                                            </li>
                                        </ul>-->
                                    </div>
                                    <!-- end -->
                                    <div class="form-group">
                                        <label for="input1-6" class="col-sm-2 control-label">ͼ������</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-6" name="commodityDescription" placeholder="ͼ������">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-7" class="col-sm-2 control-label">ͼ����</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="commodityIntroduction" id="input1-7" placeholder="ͼ����">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-8" class="col-sm-2 control-label">ͼ��Ŀ¼</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="commodityContents" id="input1-8" placeholder="ͼ��Ŀ¼">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-9" class="col-sm-2 control-label">ͼ������</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="commodityCount" id="input1-9" placeholder="ͼ������">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-10" class="col-sm-2 control-label">ͼ�鶨��</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-10" name="commodityPrice" placeholder="ͼ�鶨��">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-11" class="col-sm-2 control-label">ͼ��������</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-11" name="commodityPurchasePrice" placeholder="ͼ��������">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-12" class="col-sm-2 control-label">����ʱ��</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-12" name="publishDate" placeholder="����ʱ��">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input1-13" class="col-sm-2 control-label">ͼ������</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input1-13" name="salesCount" placeholder="ͼ������">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-3 col-sm-8">
                                            <button type="submit" class="btn btn-success save">����</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ������ -->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                ������
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <div>
                                <table class="table  table-bordered table-hover">
                                    <thead >
                                    <tr class="row  mx-0" >
                                        <th class="store-manager">ͼ������</th>
                                        <th class="store-manager">ͼ������</th>
                                        <th class="store-manager">��������</th>
                                        <th class="store-manager">ͼ������</th>
                                        <th class="store-manager">ͼ��۸�</th>
                                        <th class="store-manager">ͼ�����</th>
                                        <th class="store-manager">ͼ��༭</th>
                                        <th class="store-manager">ͼ��ɾ��</th>
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
                                                <td class="store-manager btn-primary " data-toggle="modal" data-target="#myModal-stockEdit" click="editCommodity(this)">�༭</td>
                                                <td class="store-manager btn-warning">ɾ��</td>
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
                                <!-- ģ̬��Modal�� -->
                                <div class="modal fade" id="myModal-stockEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <form action="${pageContext.request.contextPath}/admin/editCommodityInfo.do">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                    <p><h1>�鼮��Ϣ</h1></p>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="form-group">
                                                        <label for="authorName1" class="col-sm-2 control-label middle-font">ͼ������</label>
                                                        <input type="text" class="form-control" id="authorName1" name="authorName1" >
                                                    </div>
                                                    <div>
                                                        <label for="commodityImg1" class="col-sm-2 control-label middle-font">ͼ��ͼƬ</label>
                                                        <input type="text" class="form-control" id="commodityImg1" name="commodityImg1"><br>
                                                        <button id="upLoadAvatar"> �ϴ�ͼƬ</button><br>
                                                    </div>
                                                    <div>
                                                        <br>
                                                        <label for="publishHouse1" class="col-sm-2 control-label middle-font">��������</label>
                                                        <input type="text" class="form-control" id="publishHouse1" name="publishHouse1" >
                                                    </div>
                                                    <div>
                                                        <br>
                                                        <label for="categoryId1" class="col-sm-2 control-label middle-font" style="width: 100%;">ͼ�����</label>
                                                        <input type="text" class="form-control" id="categoryId1" name="categoryId1">
                                                    </div>
                                                    <%--<div class="form-group">--%>
                                                        <%--<ul class="nav navbar-nav" style="width: 100%;">--%>
                                                            <%--<li class="dropdown">--%>
                                                                <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown">--%>
                                                                    <%--��һ������--%>
                                                                    <%--<b class="caret"></b>--%>
                                                                <%--</a>--%>
                                                                <%--<ul class="dropdown-menu">--%>
                                                                    <%--<li><a href="#">�������</a></li>--%>
                                                                    <%--<li><a href="#">����</a></li>--%>
                                                                    <%--<li><a href="#">����</a></li>--%>
                                                                    <%--<li><a href="#">����/��־</a></li>--%>
                                                                    <%--<li><a href="#">����</a></li>--%>
                                                                    <%--<li><a href="#">�Ƽ�</a></li>--%>
                                                                    <%--<li><a href="#">����</a></li>--%>
                                                                <%--</ul>--%>
                                                            <%--</li>--%>
                                                            <%--<li class="dropdown">--%>
                                                                <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown">--%>
                                                                    <%--�ڶ�������--%>
                                                                    <%--<b class="caret"></b>--%>
                                                                <%--</a>--%>
                                                                <%--<ul class="dropdown-menu">--%>
                                                                    <%--<li><a href="#">С˵</a></li>--%>
                                                                    <%--<li><a href="#">�ഺ��ѧ</a></li>--%>
                                                                    <%--<li><a href="#">��ѧ</a></li>--%>
                                                                    <%--<li><a href="#">����</a></li>--%>
                                                                    <%--<li><a href="#">����</a></li>--%>
                                                                <%--</ul>--%>
                                                            <%--</li>--%>
                                                            <%--<li class="dropdown">--%>
                                                                <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown">--%>
                                                                    <%--����������--%>
                                                                    <%--<b class="caret"></b>--%>
                                                                <%--</a>--%>
                                                                <%--<ul class="dropdown-menu">--%>
                                                                    <%--<li><a href="#">��̽/����</a></li>--%>
                                                                    <%--<li><a href="#">��ж���</a></li>--%>
                                                                    <%--<li><a href="#">�ƻ�/ħ��</a></li>--%>
                                                                    <%--<li><a href="#">��Ʒ��</a></li>--%>
                                                                    <%--<li><a href="#">���С˵</a></li>--%>
                                                                <%--</ul>--%>
                                                            <%--</li>--%>
                                                        <%--</ul>--%>
                                                    <%--</div><br>--%>
                                                    <div>
                                                        <br>
                                                        <label for="commodityDescription1" class="col-sm-2 control-label middle-font">�鼮����</label>
                                                        <input type="text" class="form-control" id="commodityDescription1" >
                                                    </div>
                                                    <div>
                                                        <br>
                                                        <label for="commodityIntroduction1" class="col-sm-2 control-label middle-font">ͼ����</label>
                                                        <input type="text" class="form-control" id="commodityIntroduction1" >
                                                    </div>
                                                    <div>
                                                        <br>
                                                        <label for="commodityContents1" class="col-sm-2 control-label middle-font">ͼ��Ŀ¼</label>
                                                        <input type="text" class="form-control" id="commodityContents1">
                                                    </div>
                                                    <div>
                                                        <br>
                                                        <label for="commodityCount1" class="col-sm-2 control-label middle-font">ͼ������</label>
                                                        <input type="text" class="form-control" id="commodityCount1" >
                                                    </div>
                                                    <div >
                                                        <br>
                                                        <label for="commodityPrice1" class="col-sm-2 control-label middle-font">ͼ�鶨��</label>
                                                        <input type="text" class="form-control" id="commodityPrice1" >
                                                    </div>
                                                    <div >
                                                        <br>
                                                        <label for="commodityPurchasePrice1" class="col-sm-2 control-label middle-font">ͼ��������</label>
                                                        <input type="text" class="form-control" id="commodityPurchasePrice1">
                                                    </div>
                                                    <div >
                                                        <br>
                                                        <label for="publishDate1" class="col-sm-2 control-label middle-font">����ʱ��</label>
                                                        <input type="text" class="form-control" id="publishDate1" >
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">�ر�</button>
                                                    <button type="submit" class="btn btn-primary">�ύ����</button>
                                                </div>
                                            </div><!-- /.modal-content -->
                                        </div><!-- /.modal -->
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ��ʱ�Ż���Ʒ -->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                ��ʱ�Ż���Ʒ
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                        <div class="panel-body">
                            <div class="col-md-21">
                                <table class="table  table-bordered table-hover">
                                    <thead >
                                    <tr class="row  mx-0" >
                                        <th class="store-manager" >ͼ������</th>
                                        <th class="store-manager">ͼ������</th>
                                        <th class="store-manager">��������</th>
                                        <th class="store-manager">ͼ������</th>
                                        <th class="store-manager">ͼ��۸�</th>
                                        <th class="store-manager">ͼ�����</th>
                                        <th class="store-manager">ͼ��༭</th>
                                        <th class="store-manager">ͼ��ɾ��</th>
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
                                            <td class="store-manager btn-primary" data-toggle="modal" data-target="#myModal-favourable">�༭</td>
                                            <td class="store-manager btn-warning">ɾ��</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <div class="well">
                                    <a href="#" id="example" class="btn btn-success" rel="popover" data-content="�������Ż���Ʒ" >����Ż���Ʒ</a>
                                </div>
                                <!-- ģ̬��Modal�� -->
                                <div class="modal fade" id="myModal-favourable" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                <p><h1>�Ż��鼮</h1></p>
                                            </div>
                                            <div class="modal-body">
                                                <div class="form-group">
                                                    <br>
                                                    <label for="commodityName" class="col-sm-2 control-label middle-font">ͼ������</label>
                                                    <input type="text" class="form-control" id="commodityName" placeholder="ͼ������">
                                                </div>
                                                <div class="form-group">
                                                    <br>
                                                    <label for="authorName" class="col-sm-2 control-label middle-font">ͼ������</label>
                                                    <input type="text" class="form-control" id="authorName" placeholder="ͼ������">
                                                </div>
                                                <div>
                                                    <br>
                                                    <label for="publishHouse" class="col-sm-2 control-label middle-font">��������</label>
                                                    <input type="text" class="form-control" id="publishHouse" placeholder="��������">
                                                </div>
                                                <div>
                                                    <br>
                                                    <label for="commodityCount" class="col-sm-2 control-label middle-font">ͼ������</label>
                                                    <input type="text" class="form-control" id="commodityCount" placeholder="ͼ������">
                                                </div>
                                                <div >
                                                    <br>
                                                    <label for="commodityPrice" class="col-sm-2 control-label middle-font">ͼ��۸�</label>
                                                    <input type="text" class="form-control" id="commodityPrice" placeholder="ͼ��۸�">
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">�ر�</button>
                                                <button type="submit" class="btn btn-primary">�ύ����</button>
                                            </div>
                                        </div><!-- /.modal-content -->
                                    </div><!-- /.modal -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ר����� -->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFour">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                ר�����
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                        <div class="panel-body">
                            <div class="">
                                <table class="table  table-bordered table-hover">
                                    <thead class="store-manager">
                                    <tr>
                                        <th class="store-manager">ר����</th>
                                        <th class="store-manager">ר�����</th>
                                        <th class="store-manager">ר��༭</th>
                                        <th class="store-manager">ר��ɾ��</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${specialList}" var="special" varStatus="status">
                                            <tr>
                                                <td class="store-manager">${special.specialId}</td>
                                                <td class="store-manager">${special.specialSort}</td>
                                                <td class="store-manager btn-primary" id="specialEdit" data-toggle="modal" data-target="#myModal-editSpecial">�༭</td>
                                                <td class="store-manager btn-warning">ɾ��</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <!-- ģ̬��Modal�� -->
                                <div class="modal fade" id="myModal-editSpecial" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                <p><h1>ר�����</h1></p>
                                            </div>
                                            <div class="modal-body">
                                                <div class="form-group">
                                                    <br>
                                                    <label for="input4-1" class="col-sm-2 control-label middle-font">ר�����</label>
                                                    <input type="text" class="form-control" id="input4-1" placeholder="ר�����">
                                                </div>
                                                <div class="form-group">
                                                    <br>
                                                    <label for="input4-2" class="col-sm-2 control-label middle-font">ר��ͼƬ</label>
                                                    <input type="text" class="form-control" id="input4-2" placeholder="ר��ͼƬ">
                                                    <button type="button" class="btn btn-success">�ϴ�ͼƬ</button>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">�ر�</button>
                                                <button type="submit" class="btn btn-primary">�ύ����</button>
                                            </div>
                                        </div><!-- /.modal-content -->
                                    </div><!-- /.modal -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ������ -->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFive">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                ������
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
                        <div class="panel-body">
                            <div>
                                <table class="table  table-bordered table-hover">
                                    <thead class="store-manager w-100">
                                    <tr>
                                        <th class="store-manager" >�������</th>
                                        <th class="store-manager">���ͼƬ</th>
                                        <th class="store-manager">���༭</th>
                                        <th class="store-manager">���ɾ��</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="item" items="${adlist}" varStatus="staturs">
                                        <tr>
                                            <td class="store-manager">${item.adName}</td>
                                            <td class="store-manager">${item.adImg}</td>
                                            <td class="store-manager btn-primary" id="adEdit" data-toggle="modal" data-target="#myModal-advertisement" onclick="editInfo(this)">�༭</td>                                        <td class="store-manager btn-warning">ɾ��</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <div class="well">
                                    <a href="${pageContext.request.contextPath}/admin/insertAdvertisement.do" id="addAdvertisement" class="btn btn-success" rel="popover" data-content="�����ӹ��" >��ӹ��</a>
                                </div>
                                <!-- ģ̬��Modal�� -->
                                <div class="modal fade" id="myModal-advertisement" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <form>
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                    <p><h1>������</h1></p>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="form-group">
                                                        <br>
                                                        <label for="adName" class="col-sm-2 control-label middle-font">�������</label>
                                                        <input type="text" class="form-control" id="adName" placeholder="�������">
                                                    </div>
                                                    <div class="form-group">
                                                        <br>
                                                        <label for="adImg" class="col-sm-2 control-label middle-font">���ͼƬ</label>
                                                        <input type="text" class="form-control" id="adImg" placeholder="���ͼƬ">
                                                        <input type="hidden" id="adId">
                                                        <button type="button" class="btn btn-success">�ϴ�ͼƬ</button>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal"><a href="/admin/findAdvertisement">�ر�</a>
                                                    </button>
                                                    <button type="submit" class="btn btn-primary"><a href="${pageContext.request.contextPath}/admin/insertAdvertisement.do"></a>���</button>
                                                </div>
                                            </div><!-- /.modal-content -->
                                        </form>
                                    </div><!-- /.modal -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- �ӵ� -->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingSix">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="false" aria-controls="collapseSix">�ӵ�</a>
                        </h4>
                    </div>
                    <div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
                        <div class="panel-body">
                            <div >
                                <table class="table  table-bordered table-hover">
                                    <thead class="store-manager w-100">
                                    <tr>
                                        <th class="store-manager">�������</th>
                                        <th class="store-manager">��ϵ��ʽ</th>
                                        <th class="store-manager">ͼ������</th>
                                        <th class="store-manager">ͼ������</th>
                                        <th class="store-manager">�ܼ�</th>
                                        <th class="store-manager">�ջ���ַ</th>
                                        <th class="store-manager">�༭����</th>
                                        <th class="store-manager">ȷ�Ͻӵ�</th>
                                        <th class="store-manager">�ܾ��ӵ�</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr >
                                        <td class=" store-manager">��������</td>
                                        <td class="store-manager">7417417474741</td>
                                        <td class="store-manager">��ҹ��</td>
                                        <td class="store-manager">1</td>
                                        <td class="store-manager">50</td>
                                        <td class="store-manager">ɽ��ʡ���������ɽ��̫ԭʦ��ѧԺ</td>
                                        <td class="store-manager btn-primary" data-toggle="modal" data-target="#myModal-order">�༭</td>
                                        <td class="store-manager btn-success">ȷ�Ͻӵ�</td>
                                        <td class="store-manager btn-warning">�ܾ��ӵ�</td>
                                    </tr>
                                    <tr >
                                        <td class=" store-manager">��������</td>
                                        <td class="store-manager">7417417474741</td>
                                        <td class="store-manager">��ҹ��</td>
                                        <td class="store-manager">1</td>
                                        <td class="store-manager">50</td>
                                        <td class="store-manager">ɽ��ʡ���������ɽ��̫ԭʦ��ѧԺ</td>
                                        <td class="store-manager btn-primary" data-toggle="modal" data-target="#myModal-order">�༭</td>
                                        <td class="store-manager btn-success">ȷ�Ͻӵ�</td>
                                        <td class="store-manager btn-warning">�ܾ��ӵ�</td>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class="modal fade" id="myModal-order" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                <p><h1>������Ϣ</h1></p>
                                            </div>
                                            <div class="modal-body">
                                                <div class="form-group">
                                                    <br>
                                                    <label for="input6-1" class="col-sm-2 control-label middle-font">�������</label>
                                                    <input type="text" class="form-control" id="input6-1" >
                                                </div>
                                                <div class="form-group">
                                                    <br>
                                                    <label for="input6-2" class="col-sm-2 control-label middle-font">��ϵ��ʽ</label>
                                                    <input type="text" class="form-control" id="input6-2">
                                                </div>
                                                <div class="form-group">
                                                    <br>
                                                    <label for="input6-3" class="col-sm-2 control-label middle-font">ͼ������</label>
                                                    <input type="text" class="form-control" id="input6-3" >
                                                </div>
                                                <div class="form-group">
                                                    <br>
                                                    <label for="input6-4" class="col-sm-2 control-label middle-font">����</label>
                                                    <input type="text" class="form-control" id="input6-4" >
                                                </div>
                                                <div class="form-group">
                                                    <br>
                                                    <label for="input6-5" class="col-sm-2 control-label middle-font">�ܼ�</label>
                                                    <input type="text" class="form-control" id="input6-5" >
                                                </div>
                                                <div class="form-group">
                                                    <br>
                                                    <label for="input6-6" class="col-sm-2 control-label middle-font">�ջ���ַ</label>
                                                    <input type="text" class="form-control" id="input6-6" >
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">�ر�
                                                </button>
                                                <button type="button" class="btn btn-primary">�ύ����</button>
                                            </div>
                                        </div><!-- /.modal-content -->
                                    </div><!-- /.modal -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- �˻��� -->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingSeven">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven">
                                �˻���
                            </a>
                        </h4>
                    </div>
                    <div id="collapseSeven" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSeven">
                        <div class="panel-body">
                            <div>
                                <table class="table  table-bordered table-hover">
                                    <thead class="store-manager w-100">
                                    <tr>
                                        <th class="store-manager">�������</th>
                                        <th class="store-manager">ͼ������</th>
                                        <th class="store-manager">ͼ������</th>
                                        <th class="store-manager">�ܼ�</th>
                                        <th class="store-manager">�˻�ԭ��</th>
                                        <th class="store-manager">����״̬</th>
                                        <th class="store-manager">�ջ���ַ</th>
                                        <th class="store-manager">ͬ���˻�</th>
                                        <th class="store-manager">�ܾ��˻�</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr >
                                        <td class=" store-manager">��������</td>
                                        <td class="store-manager">��ҹ��</td>
                                        <td class="store-manager">1</td>
                                        <td class="store-manager">50</td>
                                        <td class="store-manager">�����������˻�</td>
                                        <td class="store-manager">���˿�</td>
                                        <td class="store-manager">ɽ��ʡ���������ɽ��̫ԭʦ��ѧԺ</td>
                                        <td class="store-manager btn-success">ͬ���˻�</td>
                                        <td class="store-manager btn-warning">�ܾ��˻�</td>
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
    //             //��json�ַ���ת����json����
    //             var commodityList = eval("("+data+")");
    //             $.each(commodityList,function (index,item) {
    //                 $("#commodity").append("<td class=\"store-manager\">"+item[0]+"</td>");
    //             })
    //         }
    //     });
    // })
</script>

</html>