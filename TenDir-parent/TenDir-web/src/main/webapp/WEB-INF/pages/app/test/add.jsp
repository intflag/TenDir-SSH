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
			<div class="layui-form-item">
				<label class="layui-form-label">名称</label>
				<div class="layui-input-block">
					<input type="text" id="tname" name="tname" value=""
						lay-verify="required" placeholder="请输入名称" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">密码</label>
				<div class="layui-input-block">
					<input type="text" id="tpwd" name="tpwd" value=""
						lay-verify="required" placeholder="请输入密码" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">邮箱</label>
				<div class="layui-input-block">
					<input type="text" id="email" name="email" value=""
						lay-verify="required" placeholder="请输入邮箱" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">电话</label>
				<div class="layui-input-block">
					<input type="text" id="phone" name="phone" value=""
						lay-verify="required" placeholder="请输入电话" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">地址</label>
				<div class="layui-input-block">
					<input type="text" id="address" name="address" value=""
						lay-verify="required" placeholder="请输入地址" autocomplete="off"
						class="layui-input">
				</div>
			</div>

			<div class="page-footer">
				<div class="btn-list">
					<div class="btnlist">
						<button class="layui-btn" lay-submit="" lay-filter="addTestSubmit"
							data-url="testAction_add.action">
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
