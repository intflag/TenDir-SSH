<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@include file="/common/jsp/includeList.jsp" %>
  <script src="${rootPath}/statics/js/system/user/user.js" charset="utf-8"></script>
 </head>
<body> 
 
<div class="layui-btn-group demoTable">
  <button class="layui-btn" data-type="getCheckData">获取选中行数据</button>
  <button class="layui-btn" data-type="getCheckLength">获取选中数目</button>
  <button class="layui-btn" data-type="isAll">验证是否全选</button>
</div>
 
<table class="layui-hide" id="userTable" lay-filter="demo"></table>
 
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
   
<script type="text/html" id="switchTpl">
  <!-- 这里的 checked 的状态只是演示 -->
  <input type="checkbox" name="gender" value="{{d.gender}}" lay-skin="switch" lay-text="女|男" lay-filter="sexDemo" {{ d.gender == '2' ? 'checked' : '' }}>
</script>            
<script type="text/html" id="checkboxTpl">
  <!-- 这里的 checked 的状态只是演示 -->
  <input type="checkbox" name="flag" value="{{d.flag}}" title="正常" lay-filter="lockDemo" {{ d.flag == '1' ? 'checked' : '' }}>
</script>  

</body>
</html>