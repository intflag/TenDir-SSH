<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/common/jsp/includeForm.jsp"%>
<script
	src="${rootPath}/statics/js/generator/codeGenerator/codeGenerator.js"
	charset="utf-8"></script>
</head>
<body>

	<div class="layui-field-box">
		<blockquote class="layui-elem-quote">
			注意：请在开发环境下使用此功能，谨慎操作！</blockquote>
		<form class="layui-form layui-form-pane" action="">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label" style="color: #F00">数据库表</label>
					<div class="layui-input-block">
						<select name="tableName" lay-filter="selectTableName"
							lay-verify="required" id="tableName">
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label" style="color: #F00">表主键</label>
					<div class="layui-input-block">
						<select name="pkColumn" lay-verify="required" id="pkColumn">
						</select>
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label" style="color: #F00">添加菜单</label>
					<div class="layui-input-block">
						<select name="addmenu" id="addmenu" lay-verify="required">
							<option value="1" selected="">是</option>
							<option value="0">否</option>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label" style="color: #F00">上一级菜单</label>
					<div class="layui-input-inline">
						<input value="" id="demo" cyType="treeTool" cyProps="url:'${rootPath}/resourceAction_findAll.action',name:'pid'" placeholder="请选择上级菜单" class="layui-input"/>
					</div>
					<!-- <div class="layui-input-inline">
						<button class="layui-btn layui-btn-normal" type="button"
							id="treeSelect">请选择</button>
					</div> -->
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label" style="color: #F00">Java类名称</label>
				<div class="layui-input-block">
					<input type="text" id="classNames" name="classNames" value=""
						lay-verify="required" placeholder="请输入Java类名称" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="color: #F00">功能说明</label>
				<div class="layui-input-block">
					<input type="text" name="functionComment" value=""
						lay-verify="required" placeholder="请输入功能说明" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="color: #F00">工程根路径</label>
				<div class="layui-input-block">
					<input type="text" name="classPath" value="D:/work/JavaEE/WebProject5/TenDir-parent"
						lay-verify="required" placeholder="请输入工程根路径" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="color: #F00">作者</label>
				<div class="layui-input-block">
					<input type="text" name="author" value="刘国鑫QQ1598749808"
						lay-verify="required" placeholder="请输入作者" autocomplete="off"
						class="layui-input">
				</div>
			</div>


			<div class="page-footer">
				<div class="btn-list">
					<div class="btnlist">
						<button class="layui-btn" lay-submit=""
							lay-filter="addGeneratorSubmit"
							data-url="${rootPath}/codeGeneratorAction_createCode.action">
							<i class="fa fa-floppy-o">&nbsp;</i>保存
						</button>
						<button class="layui-btn" onclick="$t.closeWindow();">
							<i class="fa fa-undo">&nbsp;</i>返回
						</button>
					</div>
				</div>
			</div>
		</form>
		<%-- <form class="layui-form layui-form-pane" style="margin-top: 20px;" method="post" action="">
		
	</form> --%>
	</div>
</body>
<script type="text/javascript">
	layui.use(['code'], function(){
		layui.code({
			elem:"pre",
			title:'struts.xml配置',
			skin:"notepad",
			encode:true,
			about:false
		});
	});
	$(function() {
		$("#tableName").html("");
		$("#tableName").append('<option value=""></option>');
		$.ajax({
			url : rootPath + "/codeGeneratorAction_findAllTable.action",
			async : false,
			type : 'post',
			success : function(R) {
				if (R.res == 1) {
					data = R.obj;
					if (data.length > 0) {
						for (var i = 0; i < data.length; i++) {
							$("#tableName").append(
									'<option value="'+data[i].tableName+'">'
											+ data[i].tableName + '</option>');
						}
					}
				} else {
					data = {};
					alert(R.resMsg);
				}
			}
		});

	});
</script>
</html>
