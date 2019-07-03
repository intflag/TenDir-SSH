<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@include file="/common/jsp/includeList.jsp" %>
  <script src="${rootPath}/statics/js/system/log/log.js" charset="utf-8"></script>
 </head>
<body> 
 
<blockquote class="layui-elem-quote">
	注意：日志无法手动进行 [增加] 和 [修改] 操作！	
</blockquote>

<div class="layui-btn-group demoTable">
  <button class="layui-btn layui-btn-danger" style="margin-left: 5px!important;" data-type="deleteBatch"><i class="fa fa-trash-o">&nbsp;</i>批量删除</button>
</div>

<div class="demoTable layui-form-pane" style="display: inline-block;float: right;">
<label class="layui-form-label">关键字</label>
  <div class="layui-inline">
    <input type="text" id="keyWord" name="keyWord" value="${keyWord }" lay-verify="required" placeholder="请输入关键词查询" autocomplete="off" class="layui-input">
  </div>
  <button class="layui-btn" data-type="searchBtn"><i class="fa fa-search">&nbsp;</i>查询</button>
</div>
<table class="layui-hide" id="logTable" lay-filter="logT"></table>
 
<script type="text/html" id="checkFlag">
  {{#  if(d.flag == "1"){ }}
    <span class="layui-badge">成功</span>
  {{#  } }}
  {{#  if(d.flag == "2"){ }}
    <span class="layui-badge layui-bg-cyan">失败</span>
  {{#  } }}
</script>            

</body>
</html>