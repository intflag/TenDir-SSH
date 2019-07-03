<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>十方</title>
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/statics/img/favicon.ico" media="screen"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/plugins/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/plugins/layer/skin/default/layer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/common/css/cyStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/plugins/ContextJS/css/context.standalone.green.css">

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo" style="font-size: 20px;">TenDir快速开发框架</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <%-- <li class="layui-nav-item layui-this"><a href="javascript:createMenu('${pageContext.request.contextPath}/resourceAction_findMenu.action');">系统设置</a></li> --%>
            <li class="layui-nav-item">
                <a href="javascript:fullScreen();"><i class="fa fa-expand"></i>全屏</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它开源系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="https://gitee.com/intflag/BiMai" target="_blank">BiMai-必买商城</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:reward();">捐赠作者</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:lockScreen();"><i class="fa fa-lock"></i>锁屏</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">通知<span class="layui-badge">9</span></a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="statics/images/index/head.jpg" class="layui-nav-img">
                    ${loginUser.username}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;"  onclick="Page.openWidth('修改基本资料','50%','${pageContext.request.contextPath}/userAction_find.action?userId=<s:property value="#session.loginUser.userId"/>','test1')">安全设置</a></dd>
                    <!-- <dd><a href="javascript:;"  class="cy-page" data-url="http://www.baidu.com/">安全设置</a></dd> -->
                </dl>
            </li>
            <li id="logoutBtn" class="layui-nav-item" onclick="logoutFun()"><a href="javascript:void(0)">退出</a></li>
        </ul>
    </div>
    <div class="toggle-collapse"></div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <div class="layui-form component">
                <!-- <div class="search-menu-back">
                    <input type="text" placeholder="菜单名称 / url" value="" id="menuSearch" class="layui-input menu-search">
                    <span class="menu-search-clear" style="display: none"><i class="layui-icon">&#x1006;</i>  </span>
                </div> -->
            </div>
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree">
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 1px;">
            <div id="navTab" class="layui-tab layui-tab-brief" lay-allowClose="true" lay-filter="tabs">
                <div class="layui-tab-left"><i class="layui-icon">&#xe65a;</i></div>
                <ul class="layui-tab-title ">
                    <%-- <li class="layui-this main-tab" data-url="${pageContext.request.contextPath}/page/main/main.jsp">我的主页</li> --%>
                    <li class="layui-this main-tab" data-url="${pageContext.request.contextPath}/page_count_appCount_list.action">应用统计</li>

                </ul>
                <div class="layui-tab-right"><i class="layui-icon">&#xe65b;</i></div>

            </div>

        </div>


        <div id="main" style="padding-top: 10px;">
            <%-- <iframe scrolling="yes" frameborder="0" class="cy-show" src="${pageContext.request.contextPath}/page/main/main.jsp"></iframe> --%>
            <iframe scrolling="yes" frameborder="0" class="cy-show" src="${pageContext.request.contextPath}/page_count_appCount_list.action"></iframe>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © www.intflag.cn
    </div>
</div>
<script src="${pageContext.request.contextPath}/statics/libs/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/plugins/ContextJS/js/context.js"></script>
<script src="${pageContext.request.contextPath}/statics/plugins/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/statics/plugins/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/common/js/whole/utils.js"></script>
<script src="${pageContext.request.contextPath}/common/js/whole/cyLayer.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/navTab.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/index.js"></script>
<script type="text/javascript">
// 退出登录
function logoutFun() {
	layer.confirm('确定要退出系统吗？', {
		skin: 'layui-layer-molv',
	  	btn: ['去意已决','留下看看'] //按钮
	}, function(){
	  	location.href="${pageContext.request.contextPath}/userAction_logout.action";
	});
}
</script>
</body>
</html>
