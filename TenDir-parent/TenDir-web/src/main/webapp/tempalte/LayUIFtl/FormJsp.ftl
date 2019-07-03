<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/common/jsp/includeForm.jsp"%>
<script src="statics/js/app/${classNameLow}/${classNameLow}.js" charset="utf-8"></script>
</head>
<body>
	<div class="layui-field-box" style="margin-top: 10px;">
		<form class="layui-form" action="">
			<input type="hidden" name="${entityId}" value='<s:property value="#request.${classNameLow}.${entityId}"/>'>
			<#list cloums as c>
			<div class="layui-form-item">
				<label class="layui-form-label">${c.columnComment}</label>
				<div class="layui-input-block">
					<input type="text" id="${c.columnName}" name="${c.columnName}" value='<s:property value="#request.${classNameLow}.${c.columnName}"/>'
						lay-verify="required" placeholder="请输入${c.columnComment}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			</#list>
			<div class="page-footer">
				<div class="btn-list">
					<div class="btnlist">
						<button class="layui-btn" lay-submit="" lay-filter="edit${classNameUp}Submit"
							data-url="${classNameLow}Action_edit.action">
							<i class="fa fa-floppy-o">&nbsp;</i>保存
						</button>
						<button class="layui-btn" onclick="$t.closeWindow();">
							<i class="fa fa-undo">&nbsp;</i>返回
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
