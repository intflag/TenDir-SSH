<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/jsp/includeForm.jsp" %>
  	<script src="${rootPath}/statics/js/system/user/user.js" charset="utf-8"></script>
</head>
<body>
<div class="layui-field-box" style="margin-top: 10px;">
<form class="layui-form" action="">
	<input type="hidden" name="userId" value="">
  <div class="layui-form-item">
    <label class="layui-form-label">账户</label>
    <div class="layui-input-block">
      <input type="text" id="username" name="username" value="" lay-verify="required" placeholder="请输入账户登录名" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">昵称</label>
    <div class="layui-input-block">
      <input type="text" name="nickname" value="" lay-verify="required" placeholder="请输入昵称" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">密码</label>
    <div class="layui-input-block">
      <input type="password" name="password" value="" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
      <label class="layui-form-label">手机</label>
      <div class="layui-input-block">
        <input type="tel" name="telephone" value="" lay-verify="required|phone" placeholder="请输入正确的手机号" autocomplete="off" class="layui-input">
      </div>
  </div>
  <div class="layui-form-item">
      <label class="layui-form-label">邮箱</label>
      <div class="layui-input-block">
        <input type="text" name="email" value="" lay-verify="email" placeholder="请输入正确的邮箱" autocomplete="off" class="layui-input">
      </div>
  </div>
  
  	<div class="layui-form-item">
		<div class="layui-inline">
        	<label class="layui-form-label">性别</label>
        	<div class="layui-input-block">
        		<s:property value="#user.gender"/>
	       		<input type="radio" name="gender" value="1" title="男" checked="">
	            <input type="radio" name="gender" value="2" title="女" >
            </div>
        </div>
		<div class="layui-inline">
        	<label class="layui-form-label">状态</label>
		    <div class="layui-input-block">
		      <select name="flag" lay-filter="flag">
		        <option value=""></option>
		        <option value="1" selected="">正常</option>
		        <option value="2">禁用</option>
		      </select>
		    </div>
        </div>
	</div>
	
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">所属角色</label>
	         <div class="layui-input-block" id="haveRole"></div>
        </div>
     </div>
     
	<div class="page-footer">
    	<div class="btn-list">
        	<div class="btnlist">
            	<button class="layui-btn" lay-submit="" lay-filter="addUserSubmit" data-url="${rootPath}/userAction_add.action"><i class="fa fa-floppy-o">&nbsp;</i>保存</button>
                <button class="layui-btn" onclick="$t.closeWindow();"><i class="fa fa-undo">&nbsp;</i>返回</button>
            </div>
       	</div>
	</div>
</form>
</div>
</body>
<script type="text/javascript">
$(function(){
	$("#haveRole").html("");
	$.ajax({
	    url: rootPath+"/roleAction_findAll.action",
	    async: false,
	    type: 'post',
	    success: function (R) {
	        if (R.res == 1) {
	            data = R.obj;
	            if (data.length > 0) {
	            	for (var i = 0; i < data.length; i++) {
	            		$("#haveRole").append('<input type="checkbox" name="roleIds" lay-skin="primary" title="'+data[i].rolename+'" value="'+data[i].roleId+'">');
	            	}
	            }
	        } else {
	            data = {};
	            alert(R.resMsg);
	        }
	    }
	});
	$("#username").blur(function(){
		var username = $(this).val();
		if (username.length > 0) {
			$.ajax({
			    url: rootPath+"/userAction_findByName.action",
			    async: false,
			    type: 'post',
			    data: {username:username},
			    success: function (resDate) {
			        if (resDate.res == 1) {
			        	layer.msg(resDate.resMsg, {icon: 1});
			        } else {
			        	layer.msg(resDate.resMsg, {icon: 5});
			        }
			    }
			});
		}
	});
});
</script>
</html>
