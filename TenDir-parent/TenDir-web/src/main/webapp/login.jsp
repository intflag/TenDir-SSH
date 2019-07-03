<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<script type="text/javascript">
if(window.self != window.top){
	window.top.location = window.location;
}
</script>
<head>
    <meta charset="utf-8">
    <title>登录--后台管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/plugins/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/common/css/cyStyle.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/login.css" media="all" />
    <!--禁止嵌套,开发时打开该代码-->
    <!--<script type="text/javascript">-->
        <!--if(window !=top){-->
            <!--top.location.href=location.href;-->
        <!--}-->

    <!--</script>-->
</head>
<body>
<video class="video-player" preload="auto" autoplay="autoplay" loop="loop" data-height="1080" data-width="1920" height="1080" width="1920">
    <source src="${pageContext.request.contextPath}/statics/login/login.mp4" type="video/mp4">
    <!--需要视频制作请联系作者qq228112142   土豆作品地址http://id.tudou.com/i/UMTQ5MTY4MzM2MA-->
</video>

<div class="video_mask"></div>
<div class="login">
    <h1>管理员登录</h1>
    <form id="loginForm" class="layui-form" method="post" action="userAction_login.action">
        <div class="layui-form-item"><!-- value="${param.username }" -->
            <input class="layui-input" name="username" placeholder="账户" value="Admin" lay-verify="required"  lay-verType="tips" type="text" autocomplete="off">
        </div>
        <div class="layui-form-item"><!-- value="${param.password }" -->
            <input class="layui-input" name="password" placeholder="口令" value="admin0407"  lay-verify="required"   lay-verType="tips"  type="password" autocomplete="off">
        </div>
        <div class="layui-form-item form_code">
            <input class="layui-input" style="width: 140px;" name="checkCode" placeholder="验证码" lay-verify="required"   lay-verType="tips"  type="text" autocomplete="off">
            <div class="code">
            	<%-- <img id="captcha" src="${pageContext.request.contextPath}/statics/img/code.png" width="116" height="36" onclick="refreshCode(this)"> --%>
            	<img id="loginform:vCode" src="${pageContext.request.contextPath }/validatecode.jsp"
								onclick="javascript:document.getElementById('loginform:vCode').src='${pageContext.request.contextPath }/validatecode.jsp?'+Math.random();" />
								
           	</div>
        </div>								
        <div class="layui-form-item form_code"><span class="ft-warm ft-14"><s:actionerror/></span></div>
        <button class="layui-btn login_btn" lay-submit="" lay-filter="login">登录</button>
    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/plugins/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/login.js"></script>
</body>
</html>
