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
    <title>基本设置 - Mouke</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="Mouke,blog,博客分享社区，写文章">
    <meta name="description" content="猫客网">
    <link rel="icon" href="${pageContext.request.contextPath}/res/images/logo.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/global.css">
    <script src="${pageContext.request.contextPath}/res/jquery/jquery-1.12.4.js"></script>
    <!--引入webupload CSS-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/webuploader/webuploader.css">
    <!--引入webupload JS-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/webuploader/webuploader.js"></script>
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

<div class="layui-container fly-marginTop fly-user-main">
  <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
    <li class="layui-nav-item">
      <a href="${pageContext.request.contextPath}/userServlet?method=getUserAndPostByUserId&userId=${sessionScope.user.userId}">
        <i class="layui-icon">&#xe770;</i>
        我的主页
      </a>
    </li>
    <li class="layui-nav-item">
      <a href="${pageContext.request.contextPath}/uiServlet?method=mypostUI">
        <i class="layui-icon">&#xe612;</i>
        我的帖子
      </a>
    </li>
    <li class="layui-nav-item layui-this">
      <a href="${pageContext.request.contextPath}/uiServlet?method=setUI">
        <i class="layui-icon">&#xe60a;</i>
        基本设置
      </a>
    </li>
    <li class="layui-nav-item">
      <a href="user/message.jsp">
        <i class="layui-icon">&#xe611;</i>
        我的消息
      </a>
    </li>
  </ul>

  <div class="site-tree-mobile layui-hide">
    <i class="layui-icon">&#xe602;</i>
  </div>
  <div class="site-mobile-shade"></div>
  
  <div class="site-tree-mobile layui-hide">
    <i class="layui-icon">&#xe602;</i>
  </div>
  <div class="site-mobile-shade"></div>
    <div class="fly-panel fly-panel-user" pad20>
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title" id="LAY_mine">
                <li class="layui-this" lay-id="info">我的资料</li>
                <li lay-id="avatar">头像</li>
                <li lay-id="pass">密码</li>
                <li lay-id="bind">帐号绑定</li>
            </ul>
            <div class="layui-tab-content" style="padding: 20px 0;">
                <div class="layui-form layui-form-pane layui-tab-item layui-show">
                    <form action="${pageContext.request.contextPath}/userServlet" method="post">
                        <input type="hidden" name="method" value="modifyUser">
                        <div class="layui-form-item">
                            <label for="L_username" class="layui-form-label">昵称</label>
                            <div class="layui-input-inline">
                                <input type="text" id="L_username" name="username" value="${sessionScope.user.username}" required lay-verify="required" autocomplete="on" class="layui-input">
                            </div>
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <c:choose>
                                        <c:when test="${sessionScope.user.gender != '女'}">
                                            <input type="radio" name="sex" value="男" checked title="男">
                                            <input type="radio" name="sex" value="女" title="女">
                                        </c:when>
                                        <c:otherwise>
                                            <input type="radio" name="sex" value="男" title="男">
                                            <input type="radio" name="sex" value="女" checked title="女">
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label for="L_email" class="layui-form-label">邮箱</label>
                            <div class="layui-input-inline">
                                <input type="email" id="L_email" name="email" value="${sessionScope.user.email}" required lay-verify="email" autocomplete="off" class="layui-input">
                            </div>
                            <%--<div class="layui-form-mid layui-word-aux">如果您在邮箱已激活的情况下，变更了邮箱，需<a href="activate.html" style="font-size: 12px; color: #4f99cf;">重新验证邮箱</a>。</div>--%>
                        </div>

                        <div class="layui-form-item">
                            <label for="L_phoneNumber" class="layui-form-label">手机</label>
                            <div class="layui-input-inline">
                                <input type="text" id="L_phoneNumber" name="phoneNumber" value="${sessionScope.user.phoneNumber}" required autocomplete="on" class="layui-input">
                            </div>
                            <%--<div class="layui-form-mid layui-word-aux">如果您在邮箱已激活的情况下，变更了邮箱，需<a href="activate.html" style="font-size: 12px; color: #4f99cf;">重新验证邮箱</a>。</div>--%>
                        </div>

                        <div class="layui-form-item">
                            <label for="L_city" class="layui-form-label">城市</label>
                            <div class="layui-input-inline">
                                <input type="text" id="L_city" name="city" autocomplete="off" value="${sessionScope.user.location}" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label for="L_sign" class="layui-form-label">签名</label>
                            <div class="layui-input-block">
                                <textarea placeholder="随便写些什么刷下存在感" id="L_sign"  name="sign" autocomplete="off" class="layui-textarea" style="height: 80px;">${sessionScope.user.bio}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <input type="submit" value="确认修改" class="layui-btn" lay-filter="*">
                        </div>
                    </form>
                </div>
                <%--上传头像--%>
                <div class="layui-form layui-form-pane layui-tab-item">
                    <div class="layui-form-item">
                        <div class="avatar-add">
                            <div id="filePicker">选择图片</div>
                            <div id="imgArea"><img src="${pageContext.request.contextPath}/res/images/avatar/${sessionScope.user.avatar}"></div>
                        </div>
                    </div>
                </div>

                <div class="layui-form layui-form-pane layui-tab-item">
                    <form action="/user/repass" method="post">
                        <div class="layui-form-item">
                            <label for="L_nowpass" class="layui-form-label">当前密码</label>
                            <div class="layui-input-inline">
                                <input type="password" id="L_nowpass" name="nowpass" required lay-verify="required" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label for="L_pass" class="layui-form-label">新密码</label>
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
                            <button class="layui-btn" key="set-mine" lay-filter="*" lay-submit>确认修改</button>
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
<script>
    $(function () {
        // 初始化Web Uploader
        var uploader = WebUploader.create({
            // 选完文件后，是否自动上传。
            auto: true,
            // swf文件路径
            swf: '${pageContext.request.contextPath}/res/webuploader/Uploader.swf',
            // 文件接收服务端。
            server: '${pageContext.request.contextPath}/userServlet?method=uploadAvatar',
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#filePicker',
            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });
        // 当有文件添加进来的时候
        uploader.on( 'fileQueued', function( file ) {
            var $li = $(
                '<div id="' + file.id + '" class="file-item thumbnail">' +
                '<img>' +
                '</div>'
                ),
                $img = $li.find('img');


            // $list为容器jQuery实例
            $("#imgArea").append( $li );

            // 创建缩略图
            // 如果为非图片文件，可以不用调用此方法。
            // thumbnailWidth x thumbnailHeight 为 100 x 100
            uploader.makeThumb( file, function( error, src ) {
                if ( error ) {
                    $img.replaceWith('<span>不能预览</span>');
                    return;
                }

                $img.attr( 'src', src );
            }, 168, 168 );
        });
    })

</script>
<script src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
<script>
layui.config({
  version: "2.0.0"
  ,base: '${pageContext.request.contextPath}/res/mods/'
}).extend({
  fly: 'index'
}).use('fly');
</script>

</body>
</html>