<%--
  Created by IntelliJ IDEA.
  User: LiziChen
  Date: 2018/8/1
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>忘记密码 - Mouke</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="Mouke,blog,博客分享社区，写文章">
    <meta name="description" content="猫客网">
    <link rel="icon" href="${pageContext.request.contextPath}/res/images/logo.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/global.css">
</head>
<body>

<div class="fly-header layui-bg-black">
    <div class="layui-container">
        <a class="fly-logo" href="${pageContext.request.contextPath}/index.jsp">
            <img src="${pageContext.request.contextPath}/res/images/logo.png" alt="Panda">
        </a>
        <ul class="layui-nav fly-nav layui-hide-xs">
            <li class="layui-nav-item">
                <a href="${pageContext.request.contextPath}/index.jsp"><i class="iconfont icon-shouye"></i>首页</a>
            </li>
            <%--搜索--%>
            <li class="layui-nav-item layui-hide-xs">
                <img src="${pageContext.request.contextPath}/res/images/search.png" alt="Panda">
                <input type="text" style="background:rgba(0, 0, 0, 0); border: 0px;position: absolute;right:20px;top: 22px">
                <img src="${pageContext.request.contextPath}/res/images/searchAction.png" style="position: relative;right:30px">
            </li>
        </ul>
        <c:choose>
            <c:when test="${sessionScope.user==null}"><!--如果 -->
                <%--未登录用户信息--%>
                <ul class="layui-nav fly-nav-user">
                    <!-- 未登入的状态 -->
                    <li class="layui-nav-item">
                        <a class="iconfont icon-touxiang" href="${pageContext.request.contextPath}/uiServlet?method=loginUI"></a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="${pageContext.request.contextPath}/uiServlet?method=loginUI">登入</a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="${pageContext.request.contextPath}/uiServlet?method=registerUI">注册</a>
                    </li>
                </ul>
            </c:when>
            <c:otherwise>  <!--否则 -->
                <%--已登录用户信息--%>
                <ul class="layui-nav fly-nav-user">
                        <%--写文章--%>
                    <li class="layui-nav-item">
                        <a id="write" href="${pageContext.request.contextPath}/uiServlet?method=addPostUI"><i class="iconfont icon-tianjia"></i>随笔</a>
                    </li>
                    <!-- 登入后的状态 -->
                    <li class="layui-nav-item">
                        <a class="fly-nav-avatar" href="javascript:;">
                            <cite class="layui-hide-xs">${sessionScope.user.username}</cite>
                            <img src="${pageContext.request.contextPath}/res/images/avatar/${sessionScope.user.avatar}">
                        </a>
                        <dl class="layui-nav-child">
                            <dd><a href="${pageContext.request.contextPath}/userServlet?method=getUserAndPostByUserId&userId=${sessionScope.user.userId}"><i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>个人主页</a></dd>
                            <dd><a href="${pageContext.request.contextPath}/uiServlet?method=setUI"><i class="layui-icon">&#xe620;</i>基本设置</a></dd>
                            <hr style="margin: 5px 0;">
                            <dd><a href="${pageContext.request.contextPath}/userServlet?method=logout" style="text-align: center;">退出</a></dd>
                        </dl>
                    </li>

                </ul>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<div class="layui-container fly-marginTop">
    <div class="fly-panel fly-panel-user" pad20>
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title">
                <li><a href="${pageContext.request.contextPath}/uiServlet?method=loginUI">登入</a></li>
                <li class="layui-this"><a href="${pageContext.request.contextPath}/uiServlet?method=forgetUI">忘记密码</a></li>
            </ul>
            <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <%--邮箱注册--%>
                    <div class="layui-form layui-form-pane">
                        <form method="post">
                            <div class="layui-form-item">
                                <label for="L_email" class="layui-form-label">邮箱</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_email" name="email" required lay-verify="required" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_vercode" class="layui-form-label">人类验证</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_vercode" name="vercode" required lay-verify="required" placeholder="请回答后面的问题" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid">
                                    <span style="color: #c00;">{{d.vercode}}</span>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <button class="layui-btn" alert="1" lay-filter="*" lay-submit>提交</button>
                            </div>
                        </form>
                    </div>
                    <!-- 重置密码 -->
                    <div class="fly-msg">{{d.username}}，请重置您的密码</div>
                    <div class="layui-form layui-form-pane"  style="margin-top: 30px;">
                      <form action="/user/repass" method="post">
                        <div class="layui-form-item">
                          <label for="L_pass" class="layui-form-label">密码</label>
                          <div class="layui-input-inline">
                            <input type="password" id="L_pass" name="pass" required lay-verify="required" autocomplete="off" class="layui-input">
                          </div>
                          <div class="layui-form-mid layui-word-aux">6到16个字符</div>
                        </div>
                        <div class="layui-form-item">
                          <label for="L_repass" class="layui-form-label">确认密码</label>
                          <div class="layui-input-inline">
                            <input type="password" id="L_repass" name="repass" required lay-verify="required" autocomplete="off" class="layui-input">
                          </div>
                        </div>
                        <div class="layui-form-item">
                          <label for="L_vercode" class="layui-form-label">人类验证</label>
                          <div class="layui-input-inline">
                            <input type="text" id="L_vercode" name="vercode" required lay-verify="required" placeholder="请回答后面的问题" autocomplete="off" class="layui-input">
                          </div>
                          <div class="layui-form-mid">
                            <span style="color: #c00;">{{d.vercode}}</span>
                          </div>
                        </div>
                        <div class="layui-form-item">
                          <input type="hidden" name="username" value="{{d.username}}">
                          <input type="hidden" name="email" value="{{d.email}}">
                          <button class="layui-btn" alert="1" lay-filter="*" lay-submit>提交</button>
                        </div>
                      </form>
                    </div>




                </div>
            </div>
        </div>
    </div>


</div>

<div class="fly-footer">
    <p><a href="${pageContext.request.contextPath}/index.jsp" target="_blank">Panda 社区</a> 2018 &copy; <a href="http://www.layui.com/" target="_blank"> By layUI </a></p>
    <p><a href="${pageContext.request.contextPath}/index.jsp" target="_blank"> 超级无敌IT精英团队制作 </a> &copy; <a href="https://www.liziczh.com/" target="_blank"> 开发者1号：Lizi</a></p>
</div>

<script src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
<script>
    layui.config({
        version: "3.0.0"
        ,base: '${pageContext.request.contextPath}/res/mods/'
    }).extend({
        fly: 'index'
    }).use('fly');
</script>

</body>
</html>
