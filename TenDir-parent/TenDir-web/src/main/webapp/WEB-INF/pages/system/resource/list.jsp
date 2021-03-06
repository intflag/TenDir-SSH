<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@include file="/common/jsp/includeList.jsp" %>
  <script src="${rootPath}/statics/js/system/resource/resource.js" charset="utf-8"></script>
 </head>
<body> 
 
<div class="layui-btn-group demoTable">
  <button class="layui-btn" data-type="add"><i class="fa fa-plus">&nbsp;</i>新增</button>
  <button class="layui-btn" style="margin-left: 5px!important;" data-type="edit"><i class="fa fa-pencil-square-o">&nbsp;</i>修改</button>
  <button class="layui-btn layui-btn-danger" style="margin-left: 5px!important;" data-type="deleteBatch"><i class="fa fa-trash-o">&nbsp;</i>批量删除</button>
</div>

<div class="demoTable layui-form-pane" style="display: inline-block;float: right;">
<label class="layui-form-label">关键字</label>
  <div class="layui-inline">
    <input type="text" id="keyWord" name="keyWord" value="${keyWord }" lay-verify="required" placeholder="请输入关键词查询" autocomplete="off" class="layui-input">
  </div>
  <button class="layui-btn" data-type="searchBtn"><i class="fa fa-search">&nbsp;</i>查询</button>
</div>
 
<table class="layui-hide" id="resourceTable" lay-filter="resourceT"></table>
 
<script type="text/html" id="checkPRes">
  {{#  if(d.parentResource != null){ }}
    {{ d.parentResource.resname }}  
  {{#  } else { }}
    {{"无"}}
  {{#  } }}
</script>            
<script type="text/html" id="checkType">
  {{#  if(d.type == "0"){ }}
	<span class="layui-badge layui-bg-green">目录</span>
  {{#  } }}
  {{#  if(d.type == "1"){ }}
	<span class="layui-badge layui-bg-green">菜单</span>
  {{#  } }}
  {{#  if(d.type == "2"){ }}
	<span class="layui-badge layui-bg-green">按钮</span>
  {{#  } }}
</script>            
<script type="text/html" id="checkFlag">
  {{#  if(d.flag == "1"){ }}
    <span class="layui-badge">正常</span>
  {{#  } }}
  {{#  if(d.flag == "2"){ }}
    <span class="layui-badge layui-bg-cyan">禁用</span>
  {{#  } }}
</script>            
<script type="text/html" id="checkIcon">
  <i class="{{d.icon}}"></i>
</script>            

</body>
</html>