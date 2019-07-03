<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/jsp/includeForm.jsp" %>
  	<script src="${rootPath}/statics/js/system/role/role.js" charset="utf-8"></script>
</head>
<body>
<div class="layui-field-box" style="margin-top: 10px;">
<form id="roleForm" class="layui-form" action="">
	<input type="hidden" id="resourceIds" name="resourceIds" value="">
	<input type="hidden" name="roleId" value="">
	<div class="layui-form-item">
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
	  <label class="layui-form-label">角色名称</label>
	  <div class="layui-input-block">
	    <input type="text" id="rolename" name="rolename" value="" lay-verify="required" placeholder="请输入角色名称" autocomplete="off" class="layui-input">
	  </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">角色描述</label>
	  <div class="layui-input-block">
	    <input type="text" name="description" value="" lay-verify="" placeholder="请输入角色描述" autocomplete="off" class="layui-input">
	  </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">排序号</label>
	  <div class="layui-input-block">
	    <input type="text" name="sort" value="" lay-verify="required|number" placeholder="请输入排序" autocomplete="off" class="layui-input">
	  </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">授权</label>
	  <div class="layui-input-block">
	    <ul id="resourceTree" class="ztree"></ul>
	  </div>
	</div>
  
	<div class="page-footer">
    	<div class="btn-list">
        	<div class="btnlist">
            	<button class="layui-btn" lay-submit="" lay-filter="addRoleSubmit" data-url="${rootPath}/roleAction_add.action"><i class="fa fa-floppy-o">&nbsp;</i>保存</button>
                <button class="layui-btn" onclick="$t.closeWindow();"><i class="fa fa-undo">&nbsp;</i>返回</button>
            </div>
       	</div>
	</div>
</form>
</div>
</body>
<script type="text/javascript">
	var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: false
			},
			key: {
	            url: "nourl",
	            name: "resname"
	        }
		}
	};

	$(function() {
		$.ajax({
            type: "post",
            url: rootPath+"/resourceAction_findAll.action",
            async: false,
            success: function (R) {
                if (R.res == 1) {
                    ztree = $.fn.zTree.init($("#resourceTree"), setting, R.obj);
                    /* var node = ztree.getNodeByParam("resourceId", _value);
                    if (node != null) {
                        //获取下拉树要显示的值
                        //valueName=node.name;
                        valueName=node.resname;
                        // 选中下拉树默认节点
                        ztree.selectNode(node);
                        $(this).val(node.resname);
                    } */
                } else {
                    alert(R.resMsg);
                }
            },
            error: function () {
                alert("系统错误");
            }
        });
		$("#rolename").blur(function(){
			var rolename = $(this).val();
			if (rolename.length > 0) {
				$.ajax({
				    url: rootPath+"/roleAction_findByRoleName.action",
				    async: false,
				    type: 'post',
				    data: { rolename : rolename },
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
