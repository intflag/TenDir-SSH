<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/common/jsp/includeForm.jsp"%>
<script src="statics/js/app/test/test.js" charset="utf-8"></script>
</head>
<body>
	<div class="layui-field-box" style="margin-top: 10px;">
		<form class="layui-form" action="">
			<input type="hidden" name="testId" value='<s:property value="#request.test.testId"/>'>
			<div class="layui-form-item">
				<label class="layui-form-label">名称</label>
				<div class="layui-input-block">
					<input type="text" id="tname" name="tname" value='<s:property value="#request.test.tname"/>'
						lay-verify="required" placeholder="请输入名称" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">密码</label>
				<div class="layui-input-block">
					<input type="text" id="tpwd" name="tpwd" value='<s:property value="#request.test.tpwd"/>'
						lay-verify="required" placeholder="请输入密码" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">邮箱</label>
				<div class="layui-input-block">
					<input type="text" id="email" name="email" value='<s:property value="#request.test.email"/>'
						lay-verify="required" placeholder="请输入邮箱" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">电话</label>
				<div class="layui-input-block">
					<input type="text" id="phone" name="phone" value='<s:property value="#request.test.phone"/>'
						lay-verify="required" placeholder="请输入电话" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">地址</label>
				<div class="layui-input-block">
					<input type="text" id="address" name="address" value='<s:property value="#request.test.address"/>'
						lay-verify="required" placeholder="请输入地址" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="page-footer">
				<div class="btn-list">
					<div class="btnlist">
						<button class="layui-btn" lay-submit="" lay-filter="editTestSubmit"
							data-url="testAction_edit.action">
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
