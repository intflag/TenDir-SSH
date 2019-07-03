<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@include file="/common/jsp/includeList.jsp" %>
  <script src="${rootPath}/statics/js/task/mail/mail.js" charset="utf-8"></script>
 </head>
<body> 
 
<blockquote class="layui-elem-quote">
	注意：请确保已开启SMTP服务授权
</blockquote>

<!-- 邮件配置 -->
<div class="layui-form-item layui-form-pane">
	<div class="layui-inline">
		<label class="layui-form-label">SMTP服务器</label>
	  	<div class="layui-input-block">
	    	<input type="text" id="smtpServer" name="smtpServer" value="smtp.163.com" lay-verify="required" placeholder="请输入SMTP服务器" autocomplete="off" class="layui-input">
	  	</div>	
	</div>
	<div class="layui-inline">
		<label class="layui-form-label">发件人</label>
	  	<div class="layui-input-block">
	    	<input type="text" id="username" name="username" value="15848644100@163.com" lay-verify="required" placeholder="请输入发件人邮箱" autocomplete="off" class="layui-input">
	  	</div>	
	</div>
	<div class="layui-inline">
		<label class="layui-form-label">授权密码</label>
	  	<div class="layui-input-block">
	    	<input type="password" id="password" name="password" value="include047740" lay-verify="required" placeholder="请输入授权密码" autocomplete="off" class="layui-input">
	  	</div>	
	</div>
</div>
<div class="layui-form-item layui-form-text">
  	<label class="layui-form-label">发送内容</label>
  	<div class="layui-input-block">
    	<textarea placeholder="请输入内容" id="sendContent" name="sendContent" class="layui-textarea">
尊敬的用户您好：<br><br>
您正在验证TenDir平台 账号邮箱，如非本人操作，请忽略此邮件。<br><br>
来自：TenDir快速开发框架，联系QQ：1598749808<br><br>
    	</textarea>
  	</div>
</div>
<div class="layui-form-item layui-form-pane">
	<div class="layui-inline">
		<label class="layui-form-label">发送标题</label>
	  	<div class="layui-input-block">
	    	<input type="text" id="sendTitle" name="sendTitle" value="TenDir-Mail-API" lay-verify="required" placeholder="请输入发送标题" autocomplete="off" class="layui-input">
	  	</div>	
	</div>
	<div class="layui-inline">
		<label class="layui-form-label">收件人</label>
	  	<div class="layui-input-block">
	    	<input type="text" id="sendAddress" name="sendAddress" value="" lay-verify="required" placeholder="请输入收件人邮箱" autocomplete="off" class="layui-input">
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
<table class="layui-hide" id="mailTable" lay-filter="mailT"></table>
 
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