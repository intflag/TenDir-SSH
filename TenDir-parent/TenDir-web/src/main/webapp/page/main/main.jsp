<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<html>
<head>
    <script src="${pageContext.request.contextPath}/statics/libs/jquery-1.10.2.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/font-awesome.min.css">
    <!--layui ztree样式-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/plugins/layui/css/layui.css" media="all">
    <script src="${pageContext.request.contextPath}/statics/plugins/layer/layer.js"></script>
    <script src="${pageContext.request.contextPath}/statics/plugins/layui/layui.js"></script>
    <!--样式-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/common/css/cyStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/common/css/cyType.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend> 框架说明</legend>
</fieldset>
<blockquote class="layui-elem-quote">
    1.基于原生layui源码,替换layui文件即可完成升级！<br>
    2.前端声明式组件封装、附带文档编写 ctrl+c ctrl+v 即可使用。封装数据源，可通过url、枚举、字典直接渲染组件。代码量极少且易维护。<br>
    3.引入vue组件,组件库更全面！<br>
    4.常用js方法、layui代码封装,省略layui部分繁琐的代码！&nbsp;<a class="a-link" target="_blank" href="http://www.layui.com">》》》layui官网入口</a><br>
</blockquote>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend> 如何获得、交流？</legend>
</fieldset>
<blockquote class="layui-elem-quote">
    1.码云地址 <a  style="color:#2991D9;"  target="_blank" href="https://gitee.com/leiyuxi/cy-ui">https://gitee.com/leiyuxi/cy-ui</a>  <br>
    2.其他案例 <a  style="color:#2991D9;"  target="_blank" href="http://fly.layui.com/case/u/5129040">http://fly.layui.com/case/u/5129040</a> <br>
    3.加q群 了解框架最新动态<span style="color:#2991D9;" >275846351 &nbsp;&nbsp;&nbsp;<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=5b2ec31ee55abc44722cf8b2c7f7807d5b44d9a08da06de2c589c305e4742364"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="cy-security" title="cy-security"></a></span><br>
</blockquote>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>版本更新记录</legend>
</fieldset>
<ul class="layui-timeline">
    <li class="layui-timeline-item">
        <i class="ft-green layui-icon layui-timeline-axis layui-anim layui-anim-rotate layui-anim-loop">&#x1002;</i>
        <div class="layui-timeline-content layui-text">
            <h3 class="layui-timeline-title"></h3>
            <p class="ft-green">熬夜更新中...</p>
            <ul>
                <li>cy-mall</li>
            </ul>
        </div>
    </li>

    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
        <div class="layui-timeline-content layui-text">
            <div class="layui-timeline-title">
                <h3 class="layui-timeline-title">2018-02-27</h3>
                <ul>
                    <li>增加锁屏功能</li>
                    <li>列表页增加排序列上移下移功能(后台逻辑需自己实现)，详情见使用场景</li>
                </ul>
            </div>
        </div>
    </li>

    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
        <div class="layui-timeline-content layui-text">
            <h3 class="layui-timeline-title">2017-12-26</h3>
            <p class="a-link">cy-ui</p>
        </div>
    </li>

    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
        <div class="layui-timeline-content layui-text">
            <div class="layui-timeline-title">
                <h3 class="layui-timeline-title">过去</h3>
                <ul>
                    <li>cy-security</li>
                    <li>cy-fast</li>
                </ul>
            </div>
        </div>
    </li>
</ul>
</body>
</html>
