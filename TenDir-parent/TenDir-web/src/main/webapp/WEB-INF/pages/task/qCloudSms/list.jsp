<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@include file="/common/jsp/includeList.jsp" %>
  <script src="${rootPath}/statics/js/task/qCloudSms/qCloudSms.js" charset="utf-8"></script>
 </head>
<body> 
 
<blockquote class="layui-elem-quote">
	注意：请确保已开通腾讯云短信服务
</blockquote>

<!-- 短信配置 -->
<div class="layui-form-item layui-form-pane">
	<div class="layui-inline">
		<label class="layui-form-label">AppId</label>
	  	<div class="layui-input-block">
	    	<input type="text" id="appId" name="appId" value="1400078040" lay-verify="required" placeholder="请输入AppId" autocomplete="off" class="layui-input">
	  	</div>	
	</div>
	<div class="layui-inline">
		<label class="layui-form-label">AppKey</label>
	  	<div class="layui-input-block">
	    	<input type="password" id="appKey" name="appKey" value="9e337670add77ffc4bec135530cae62f" lay-verify="required" placeholder="请输入AppKey" autocomplete="off" class="layui-input">
	  	</div>	
	</div>
	<div class="layui-inline">
		<label class="layui-form-label">模板 ID</label>
	  	<div class="layui-input-block">
	    	<input type="text" id="templetId" name="templetId" value="107991" lay-verify="required" placeholder="请输入模板 ID" autocomplete="off" class="layui-input">
	  	</div>	
	</div>
</div>
<div class="layui-form-item layui-form-pane">
	<div class="layui-inline">
		<label class="layui-form-label">验证码</label>
	  	<div class="layui-input-block">
	    	<input type="text" id="content" name="content" value="" lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input">
	  	</div>	
	</div>
	<div class="layui-inline">
		<label class="layui-form-label">收件人</label>
	  	<div class="layui-input-block">
	    	<input type="text" id="address" name="address" value="" lay-verify="required" placeholder="请输入收件人电话" autocomplete="off" class="layui-input">
	  	</div>	
	</div>
	<div class="layui-inline demoTable" >
		<button class="layui-btn" data-type="sendBtn"><i class="fa fa-paper-plane">&nbsp;</i>发送</button>
	</div>
</div>

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
<table class="layui-hide" id="smsTable" lay-filter="smsT"></table>
 
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