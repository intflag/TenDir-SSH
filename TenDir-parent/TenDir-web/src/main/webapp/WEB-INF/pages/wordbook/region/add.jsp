<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/jsp/includeForm.jsp" %>
  	<script src="${rootPath}/statics/js/wordbook/region/region.js" charset="utf-8"></script>
</head>
<body>
<div class="layui-field-box" style="margin-top: 10px;">
<form id="regionForm" class="layui-form" action="">
	<input type="hidden" name="regionId" value="">
	<div class="layui-form-item">
		<div class="layui-inline">
        	<label class="layui-form-label">地区级别</label>
        	<div class="layui-input-block">
	            <input type="radio" name="regionLevel" value="0" title="国家" lay-filter="radioType">
	       		<input type="radio" name="regionLevel" value="1" title="省/自治区" lay-filter="radioType" checked="">
	            <input type="radio" name="regionLevel" value="2" title="市/自治州" lay-filter="radioType">
	            <input type="radio" name="regionLevel" value="3" title="区/县" lay-filter="radioType">
            </div>
        </div>
        <div class="layui-inline">
        	<label class="layui-form-label">状态</label>
		    <div class="layui-input-block">
		      <select name="regionFlag" lay-filter="flag">
		        <option value=""></option>
		        <option value="1" selected="">正常</option>
		        <option value="2">禁用</option>
		      </select>
		    </div>
        </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">地区名称</label>
	  <div class="layui-input-block">
	    <input type="text" id="regionName" name="regionName" value="" lay-verify="required" placeholder="请输入地区名称" autocomplete="off" class="layui-input">
	  </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">地区代码</label>
	  <div class="layui-input-block">
	    <input type="text" name="regionId" value="" lay-verify="required" placeholder="请输入地区代码" autocomplete="off" class="layui-input">
	  </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">上级地区</label>
	  <div class="layui-input-block">
	    <input value="" id="demo" cyType="treeTool" cyProps="url:'${rootPath}/regionAction_findAll.action',name:'region.regionId'" placeholder="请选择上级地区" class="layui-input"/>
	  </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">纬度</label>
	  <div class="layui-input-block">
	    <input type="text" name="locationLat" value="" lay-verify="" placeholder="请输入纬度" autocomplete="off" class="layui-input">
	  </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">经度</label>
	  <div class="layui-input-block">
	    <input type="text" name="locationLng" value="" lay-verify="" placeholder="请输入经度" autocomplete="off" class="layui-input">
	  </div>
	</div>
	
	<div class="page-footer">
    	<div class="btn-list">
        	<div class="btnlist">
            	<button class="layui-btn" lay-submit="" lay-filter="addRegionSubmit" data-url="${rootPath}/regionAction_add.action"><i class="fa fa-floppy-o">&nbsp;</i>保存</button>
                <button class="layui-btn" onclick="$t.closeWindow();"><i class="fa fa-undo">&nbsp;</i>返回</button>
            </div>
       	</div>
	</div>
</form>
</div>
</body>
<script type="text/javascript">
$(function(){
	$("#regionName").blur(function(){
		var regionName = $(this).val();
		if (regionName.length > 0) {
			$.ajax({
			    url: rootPath+"/regionAction_findByRegionName.action",
			    async: false,
			    type: 'post',
			    data: {regionName : regionName},
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
