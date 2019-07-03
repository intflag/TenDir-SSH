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
	<input type="hidden" name="roleId" value="${role.roleId }">
	<div class="layui-form-item">
		<div class="layui-inline">
        	<label class="layui-form-label">状态</label>
		    <div class="layui-input-block">
		      <select name="flag" lay-filter="flag">
		        <option value=""></option>
		        <option value="1" <c:if test="${role.flag=='1'}">selected</c:if>>正常</option>
		        <option value="2" <c:if test="${role.flag=='2'}">selected</c:if>>禁用</option>
		      </select>
		    </div>
        </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">角色名称</label>
	  <div class="layui-input-block">
	    <input type="text" name="rolename" value="${role.rolename }" lay-verify="required" placeholder="请输入角色名称" autocomplete="off" class="layui-input">
	  </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">角色描述</label>
	  <div class="layui-input-block">
	    <input type="text" name="description" value="${role.description }" lay-verify="" placeholder="请输入角色描述" autocomplete="off" class="layui-input">
	  </div>
	</div>
	<div class="layui-form-item">
	  <label class="layui-form-label">排序号</label>
	  <div class="layui-input-block">
	    <input type="text" name="sort" value="${role.sort }" lay-verify="required|number" placeholder="请输入排序" autocomplete="off" class="layui-input">
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
            	<button class="layui-btn" lay-submit="" lay-filter="addRoleSubmit" data-url="${rootPath}/roleAction_edit.action"><i class="fa fa-floppy-o">&nbsp;</i>保存</button>
                <button class="layui-btn" onclick="$t.closeWindow();"><i class="fa fa-undo">&nbsp;</i>返回</button>
            </div>
       	</div>
	</div>
</form>
</div>
</body>

<script type="text/javascript">
/* chkboxType:{"Y":"ps","N":"s"} */
	var setting = {
		check: {
			enable: true,
			chkDisabledInherit: true,
			chkboxType:{"Y":"p","N":"s"}
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
                    var roleId = '${role.roleId}';
                    //通过角色ID查找角色，从而得到角色关联的资源菜单
                    $.ajax({
			            type: "post",
			            url: rootPath+"/roleAction_findAjax.action?roleId="+roleId,
			            async: false,
			            success: function (R) {
			                if (R.res == 1) {
			                	//var treeNode = '${role.resources}';
			                    var treeNode = R.obj.resources;
			                   	console.info(treeNode);
			                    if (treeNode.length > 0) {
				                    //获取ztree对象
				                    var treeObj = $.fn.zTree.getZTreeObj("resourceTree");
				                    //遍历勾选角色关联的菜单数据
				                    for (var i = 0; i < treeNode.length; i++) {
				                    	//根据角色菜单节点数据的属性搜索，获取与完整菜单树完全匹配的节点JSON对象集合
					                    var nodes = treeObj.getNodesByParam("resourceId", treeNode[i].resourceId, null);
					                    //勾选当前选中的节点
					                    treeObj.checkNode(nodes[0],true,true);
				                    };
			                   };
			                } else {
			                    alert(R.resMsg);
			                }
			            },
			            error: function () {
			                alert("系统错误");
			            }
			        });
                    
                } else {
                    alert(R.resMsg);
                }
            },
            error: function () {
                alert("系统错误");
            }
        });
	});
</script>
</html>
