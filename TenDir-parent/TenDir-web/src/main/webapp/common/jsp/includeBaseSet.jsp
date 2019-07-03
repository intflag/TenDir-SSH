<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<title>十方</title>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>

<script>
    var rootPath = '${rootPath}';
</script>

<script src="${rootPath}/statics/libs/jquery-1.7.2.js"></script>
<link rel="stylesheet" href="${rootPath}/statics/css/font-awesome.min.css">
<!--layui ztree样式-->                   
<link rel="stylesheet" href="${rootPath}/statics/plugins/layui/css/layui.css" media="all">
<script src="${rootPath}/statics/plugins/layer/layer.js"></script>
<script src="${rootPath}/statics/plugins/layui/layui.js"></script>
<script src="${rootPath}/statics/plugins/ztree/jquery.ztree.all.min.js"></script>
<link rel="stylesheet" href="${rootPath}/statics/plugins/ztree/css/metroStyle/metroStyle.css">
<!--js组件-->
<script src="${rootPath}/common/js/pageGrid.js"></script>
<script src="${rootPath}/common/js/selectTool.js"></script>
<script src="${rootPath}/common/js/radioTool.js"></script>
<script src="${rootPath}/common/js/checkboxTool.js"></script>
<script src="${rootPath}/common/js/treeTool.js"></script>
<script src="${rootPath}/common/js/labelTool.js"></script>
<script src="${rootPath}/common/js/linkSelectTool.js"></script>
<script src="${rootPath}/common/js/uploadTool.js"></script>
<script src="${rootPath}/common/js/HuploadTool.js"></script>
<script src="${rootPath}/common/js/tplTool.js"></script>
<script src="${rootPath}/common/js/editGrid.js"></script>
<!--全局-->
<script src="${rootPath}/common/js/whole/cyLayer.js"></script>
<script src="${rootPath}/common/js/whole/common.js"></script>
<script src="${rootPath}/common/js/whole/setting.js"></script>
<script src="${rootPath}/common/js/whole/utils.js"></script>
<script src="${rootPath}/common/js/whole/monitor.js"></script>
<!--样式-->
<link rel="stylesheet" href="${rootPath}/common/css/cyStyle.css">
<link rel="stylesheet" href="${rootPath}/common/css/cyType.css">
<link rel="stylesheet" href="${rootPath}/common/css/tenDirStyle.css">

<script type="text/javascript" src="${rootPath}/frontframe/demo/js/list.js"></script>
<!-- 引入 ECharts 文件 -->
<script src="${rootPath}/statics/plugins/echarts/echarts.js"></script>