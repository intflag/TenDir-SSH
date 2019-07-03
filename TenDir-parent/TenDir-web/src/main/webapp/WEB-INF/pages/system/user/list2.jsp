<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@include file="/common/jsp/includeBaseSet.jsp" %>
<!-- ${pageContext.request.contextPath}/statics/json/adminTest.json -->
</head>
<body>
<div class="layui-fluid" style="margin-top: 20px;">
  <div class="layui-row">
    <div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
		<div class="layui-btn-group">
		    <button class="layui-btn" onclick="Page.open('添加学生','${pageContext.request.contextPath}/frontframe/demo/add.jsp')">
		        <i class="fa fa-plus">&nbsp;</i>增加
		    </button>
		
		    <button class="layui-btn layui-btn-green" onclick="Msg.success('操作成功')">
		        <i class="fa fa-check-square-o">&nbsp;</i>启用
		    </button>
		    <button class="layui-btn  layui-btn-danger" onclick="Msg.success('操作成功')">
		        <i class="fa fa-expeditedssl">&nbsp;</i>禁用
		    </button>
		</div>    
    </div>
    <div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
		<form class="layui-form" action="">
		    <div class="layui-form-item">
		        <label class="layui-form-label">关键字:</label>
		        <div class="layui-input-inline">
		            <input type="text" name="addressVague" placeholder="请输入关键字" class="layui-input">
		        </div>
		        <button class="layui-btn search-btn" table-id="studentTable" lay-submit="" lay-filter="search">
		            <i class="fa fa-search">&nbsp;</i>查询
		        </button>
		    </div>
		
		</form>
    </div>
  </div>
</div>  
<div class="layui-fluid">
<div class="layui-form nowrap">
    <table class="layui-table" id="studentTable" cyType="pageGrid"
           cyProps="url:'${pageContext.request.contextPath}/frontframe/json/student.json',checkbox:'true',pageColor:'#2991d9'">
        <thead>
        <tr>
            <!--复选框-->
            <th width="1%" param="{type:'checkbox'}">
                <input type="checkbox" lay-skin="primary" lay-filter="allChoose">
            </th>
            <!--isPrimary：是否是主键-->
            <th width="5%" param="{name:'stuId',isPrimary:'true',hide:'true'}">id</th>

            <th width="10%" param="{name:'name'}">姓名</th>

            <th width="5%" param="{name:'age'}">年龄</th>

            <th width="10%" param="{name:'address'}">地址</th>

            <th width="5%" param="{name:'sex',enumCode:'SexEnum'}">性别</th>

            <th width="5%" param="{name:'yingjie',codeName:'whether'}">是否是应届</th>

            <th width="5%" param="{name:'state',codeName:'state',render:'Render.customState'}">状态</th>

            <th width="10%" param="{name:'note'}">备注</th>

            <th width="2%" param="{name:'sort',sortBtn:'true'}">排序</th>

            <th width="10%" param="{operate:'true',buttons:'Render.state,Render.edit,Render.delete'}">
                操作
            </th>
        </tr>
        </thead>
    </table>
</div>
</div>

</body>
</html>
