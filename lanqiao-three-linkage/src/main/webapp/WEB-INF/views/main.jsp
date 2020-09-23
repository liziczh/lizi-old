<%--
  Created by IntelliJ IDEA.
  User: Jehaw-Chen
  Date: 2018/9/3
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Company</title>
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>
</head>
<body>
    <div class="container">
        <div class="main">
            <%--公司--%>
            <div>
                <label for="company" class="lab" > 公司：</label>
                <select id="company" name="" class="form-control">
                    <c:forEach items="${companyList}" var="company">
                        <option value="${company.comId}">${company.comName}</option>
                    </c:forEach>
                </select><br>
            </div>
            <%--分公司--%>
            <div>
                <label for="subcompany" class="lab"> 子公司：</label>
                <select id="subcompany" class="form-control">
                </select><br>
            </div>
            <%--员工--%>
            <div>
                <label for="emp" class="lab"> 员工：</label>
                <select id="emp" class="form-control">
                </select><br>
            </div>
        </div>
    </div>

<script>
        $(function () {
            // 总公司下拉框改变事件
            $("#company").change(function () {
                $.get(
                    '${pageContext.request.contextPath}/company/getSubCompany.do?comId='+$(this).val(),
                    function (subComData) {
                        $("#subcompany").empty();
                        for(var i = 0;i < subComData.length;i++){
                            $("#subcompany").append("<option value='"+subComData[i]["subComId"]+"'>"+subComData[i]["subComName"]+"</option>")
                        }
                    },
                    'json'
                )

            })
            $("#subcompany").change(function () {
                $.get(
                    '${pageContext.request.contextPath}/company/getEmp.do?subComId='+$(this).val(),
                    function (empData) {
                        $("#emp").empty();
                        for(var i = 0;i < empData.length;i++){
                            $("#emp").append("<option value='"+empData[i]["empId"]+"'>"+empData[i]["empName"]+"</option>")
                        }
                    },
                    'json'
                )
            })
        })

    </script>
</body>
</html>
