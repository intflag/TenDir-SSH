<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<html>
<head>
    <title>按钮样式</title>
    <script src="../../statics/libs/jquery-1.10.2.min.js"></script>
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
        <th>主题</th>
        <th>组合</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            <button class="layui-btn layui-btn-primary">原始按钮</button>
        </td>
        <td>class="layui-btn layui-btn-primary"</td>
    </tr>
    <tr>
        <td>
            <button class="layui-btn">默认按钮</button>
        </td>
        <td>class="layui-btn"</td>
    </tr>
    <tr>
        <td>
            <button class="layui-btn layui-btn-normal">百搭按钮</button>
        </td>
        <td>class="layui-btn layui-btn-normal"</td>
    </tr>
    <tr>
        <td>
            <button class="layui-btn layui-btn-warm">暖色按钮</button>
        </td>
        <td>class="layui-btn layui-btn-warm"</td>
    </tr>
    <tr>
        <td>
            <button class="layui-btn layui-btn-danger">警告按钮</button>
        </td>
        <td>class="layui-btn layui-btn-danger"</td>
    </tr>
    <tr>
        <td>
            <button class="layui-btn layui-btn-disabled">禁用按钮</button>
        </td>
        <td>class="layui-btn layui-btn-disabled"</td>
    </tr>
    </tbody>
</table>


<table class="layui-table">
    <thead>
    <tr>
        <th>尺寸</th>
        <th>组合</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            <button class="layui-btn layui-btn-big">大型按钮</button>
        </td>
        <td>class="layui-btn layui-btn-big"</td>
    </tr>
    <tr>
        <td>
            <button class="layui-btn">默认按钮</button>
        </td>
        <td>class="layui-btn"</td>
    </tr>
    <tr>
        <td>
            <button class="layui-btn layui-btn-sm">小型按钮</button>
        </td>
        <td>class="layui-btn layui-btn-sm"</td>
    </tr>
    <tr>
        <td>
            <button class="layui-btn layui-btn-xs">迷你按钮</button>
        </td>
        <td>class="layui-btn layui-btn-xs"</td>
    </tr>
    </tbody>
</table>

<table class="layui-table">
    <thead>
    <tr>
        <th>尺寸</th>
        <th>组合</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            <button class="layui-btn layui-btn-big layui-btn-normal">大型百搭</button>
        </td>
        <td>class="layui-btn layui-btn-big layui-btn-normal"</td>
    </tr>
    <tr>
        <td>
            <button class="layui-btn layui-btn-warm">正常暖色</button>
        </td>
        <td>class="layui-btn layui-btn-warm"</td>
    </tr>
    <tr>
        <td>
            <button class="layui-btn layui-btn-sm layui-btn-danger">小型警告</button>
        </td>
        <td>class="layui-btn layui-btn-sm layui-btn-danger"</td>
    </tr>
    <tr>
        <td>
            <button class="layui-btn layui-btn-xs layui-btn-disabled">禁用</button>
        </td>
        <td>class="layui-btn layui-btn-xs layui-btn-disabled"</td>
    </tr>
    </tbody>
</table>
</body>
</html>
