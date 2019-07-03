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
	<input type="hidden" name="resourceId" value="${resource.resourceId }">
	<div class="layui-form-item">
		<div class="layui-inline">
        	<label class="layui-form-label">菜单类型</label>
        	<div class="layui-input-block">
	            <input type="radio" name="type" value="0" title="目录" lay-filter="radioType" <c:if test="${resource.type=='0'}">checked</c:if>>
	       		<input type="radio" name="type" value="1" title="菜单" lay-filter="radioType" <c:if test="${resource.type=='1'}">checked</c:if>>
	            <input type="radio" name="type" value="2" title="按钮" lay-filter="radioType" <c:if test="${resource.type=='2'}">checked</c:if>>
            </div>
        </div>
        <div class="layui-inline">
        	<label class="layui-form-label">状态</label>
		    <div class="layui-input-block">
		      <select name="flag" lay-filter="flag">
		        <option value=""></option>
		        <option value="1" <c:if test="${resource.flag=='1'}">selected</c:if>>正常</option>
		        <option value="2" <c:if test="${resource.flag=='2'}">selected</c:if>>禁用</option>
		      </select>
		    </div>
        </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">菜单名称</label>
	  <div class="layui-input-block">
	    <input type="text" name="resname" value="${resource.resname }" lay-verify="required" placeholder="请输入菜单名称" autocomplete="off" class="layui-input">
	  </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">上级菜单</label>
	  <div class="layui-input-block">
	    <input value="${resource.parentResource.resourceId }" id="demo" cyType="treeTool" cyProps="url:'${rootPath}/resourceAction_findAll.action',name:'parentResource.resourceId'" placeholder="请选择上级菜单" class="layui-input"/>
	  </div>
	</div>
	<div class="layui-form-item" id="resurlID">
	  <label class="layui-form-label">菜单URL</label>
	  <div class="layui-input-block">
	    <input type="text" name="resurl" value="${resource.resurl }" lay-verify="" placeholder="请输入菜单URL" autocomplete="off" class="layui-input">
	  </div>
	</div>
	<div class="layui-form-item" id="rescodeID">
	  <label class="layui-form-label">授权标识</label>
	  <div class="layui-input-block">
	    <input type="text" name="rescode" value="${resource.rescode }" lay-verify="" placeholder="请输入授权标识" autocomplete="off" class="layui-input">
	  </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">菜单图标</label>
	  <div class="layui-input-block">
	    <input type="text" name="icon" value="${resource.icon }" lay-verify="required" placeholder="请输入菜单图标" autocomplete="off" class="layui-input">
	  </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">菜单描述</label>
	  <div class="layui-input-block">
	    <input type="text" name="description" value="${resource.description }" lay-verify="" placeholder="请输入菜单描述" autocomplete="off" class="layui-input">
	  </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">排序号</label>
	  <div class="layui-input-block">
	    <input type="text" name="sort" value="${resource.sort }" lay-verify="required|number" placeholder="请输入排序" autocomplete="off" class="layui-input">
	  </div>
	</div>
  
	<div class="page-footer">
    	<div class="btn-list">
        	<div class="btnlist">
            	<button class="layui-btn" lay-submit="" lay-filter="addResourceSubmit" data-url="${rootPath}/resourceAction_edit.action"><i class="fa fa-floppy-o">&nbsp;</i>保存</button>
                <button class="layui-btn" onclick="$t.closeWindow();"><i class="fa fa-undo">&nbsp;</i>返回</button>
            </div>
       	</div>
	</div>
</form>
</div>
</body>
</html>
