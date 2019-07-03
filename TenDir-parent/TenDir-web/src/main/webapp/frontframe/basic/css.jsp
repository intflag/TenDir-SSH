<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<html>
<head>
    <title>工具类</title>
    <script src="../../statics/libs/jquery-1.7.2.js"></script>
    <link rel="stylesheet" href="../../statics/css/font-awesome.min.css">
    <!--layui ztree样式-->
    <link rel="stylesheet" href="../../statics/plugins/layui/css/layui.css" media="all">
    <script src="../../statics/plugins/layer/layer.js"></script>
    <script src="../../statics/plugins/layui/layui.js"></script>
    <script src="../../statics/plugins/ztree/jquery.ztree.all.min.js"></script>
    <link rel="stylesheet" href="../../statics/plugins/ztree/css/metroStyle/metroStyle.css">
    <!--js组件-->
    <script src="../../common/js/pageGrid.js"></script>
    <script src="../../common/js/selectTool.js"></script>
    <script src="../../common/js/radioTool.js"></script>
    <script src="../../common/js/checkboxTool.js"></script>
    <script src="../../common/js/treeTool.js"></script>
    <script src="../../common/js/labelTool.js"></script>
    <script src="../../common/js/linkSelectTool.js"></script>
    <script src="../../common/js/uploadTool.js"></script>
    <script src="../../common/js/HuploadTool.js"></script>
    <script src="../../common/js/tplTool.js"></script>
    <script src="../../common/js/editGrid.js"></script>
    <!--全局-->
    <script src="../../common/js/whole/cyLayer.js"></script>
    <script src="../../common/js/whole/common.js"></script>
    <script src="../../common/js/whole/setting.js"></script>
    <script src="../../common/js/whole/utils.js"></script>
    <script src="../../common/js/whole/monitor.js"></script>
    <!--样式-->
    <link rel="stylesheet" href="../../common/css/cyStyle.css">
    <link rel="stylesheet" href="../../common/css/cyType.css">

</head>
<body>
<table class="layui-table">
    <thead>
    <tr>
        <th>class</th>
        <th>描述</th>
        <th>备注</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>class="label-120"</td>
        <td>表单列左侧文字宽度 120px</td>
        <td></td>
    </tr>
    <tr>
        <td>class="label-150"</td>
        <td>表单列左侧文字宽度 150px </td>
        <td></td>
    </tr>
    <tr>
        <td>class="label-180"</td>
        <td>表单列左侧文字宽度 180px</td>
        <td></td>
    </tr>

    <tr>
        <td>class="span_must"</td>
        <td>表单列左侧文字必填样式</td>
        <td></td>
    </tr>
    <tr>
        <td>class="label_span"</td>
        <td>表单列左侧文字非必填样式</td>
        <td></td>
    </tr>
    <tr>
        <td colspan="3">...</td>

    </tr>
    </tbody>
</table>

</body>
</html>
