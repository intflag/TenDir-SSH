<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/jsp/includeForm.jsp" %>
  	<script src="${rootPath}/statics/js/system/resource/resource.js" charset="utf-8"></script>
</head>
<body>
<div class="layui-field-box" style="margin-top: 10px;">
<form id="resourceForm" class="layui-form" action="">
	<input type="hidden" name="resourceId" value="">
	<div class="layui-form-item">
		<div class="layui-inline">
        	<label class="layui-form-label">菜单类型</label>
        	<div class="layui-input-block">
	            <input type="radio" name="type" value="0" title="目录" lay-filter="radioType">
	       		<input type="radio" name="type" value="1" title="菜单" lay-filter="radioType" checked="">
	            <input type="radio" name="type" value="2" title="按钮" lay-filter="radioType">
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
	  <label class="layui-form-label">菜单名称</label>
	  <div class="layui-input-block">
	    <input type="text" id="resname" name="resname" value="" lay-verify="required" placeholder="请输入菜单名称" autocomplete="off" class="layui-input">
	  </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">上级菜单</label>
	  <div class="layui-input-block">
	    <input value="" id="demo" cyType="treeTool" cyProps="url:'${rootPath}/resourceAction_findAll.action',name:'parentResource.resourceId'" placeholder="请选择上级菜单" class="layui-input"/>
	  </div>
	</div>
	<div class="layui-form-item" id="resurlID">
	  <label class="layui-form-label">菜单URL</label>
	  <div class="layui-input-block">
	    <input type="text" name="resurl" value="" lay-verify="" placeholder="请输入菜单URL" autocomplete="off" class="layui-input">
	  </div>
	</div>
	<div class="layui-form-item" id="rescodeID">
	  <label class="layui-form-label">授权标识</label>
	  <div class="layui-input-block">
	    <input type="text" name="rescode" value="" lay-verify="" placeholder="请输入授权标识" autocomplete="off" class="layui-input">
	  </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">菜单图标</label>
	  <div class="layui-input-block">
	    <input type="text" name="icon" value="" lay-verify="required" placeholder="请输入菜单图标" autocomplete="off" class="layui-input">
	  </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">菜单描述</label>
	  <div class="layui-input-block">
	    <input type="text" name="description" value="" lay-verify="" placeholder="请输入菜单描述" autocomplete="off" class="layui-input">
	  </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">排序号</label>
	  <div class="layui-input-block">
	    <input type="text" name="sort" value="" lay-verify="required|number" placeholder="请输入排序" autocomplete="off" class="layui-input">
	  </div>
	</div>
  
	<div class="page-footer">
    	<div class="btn-list">
        	<div class="btnlist">
            	<button class="layui-btn" lay-submit="" lay-filter="addResourceSubmit" data-url="${rootPath}/resourceAction_add.action"><i class="fa fa-floppy-o">&nbsp;</i>保存</button>
                <button class="layui-btn" onclick="$t.closeWindow();"><i class="fa fa-undo">&nbsp;</i>返回</button>
            </div>
       	</div>
	</div>
</form>
</div>
</body>
<script type="text/javascript">
$(function(){
	$("#resname").blur(function(){
		var resname = $(this).val();
		if (resname.length > 0) {
			$.ajax({
			    url: rootPath+"/resourceAction_findByResName.action",
			    async: false,
			    type: 'post',
			    data: {resname : resname},
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
