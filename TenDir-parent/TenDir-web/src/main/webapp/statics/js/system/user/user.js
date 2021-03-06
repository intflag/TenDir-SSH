layui.use('table', function(){
  var table = layui.table;
  
  table.render({
    elem: '#userTable'
    ,page:true
    ,height:400
    ,url:rootPath+'/userAction_pageQuery.action'
   	,request: {
	  pageName: 'currentPage' //页码的参数名称，默认：page
	  ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
	}  
    ,response: {
   	  countName: 'total' //数据总数的字段名称，默认：count
   	  ,dataName: 'rows' //数据列表的字段名称，默认：data
   	}  
    ,cellMinWidth: 120 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
    ,cols: [[
   	  {type:'checkbox', fixed: 'left'}
      ,{field:'username', title: '登录名',fixed: 'left', align: 'center'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
      ,{field:'nickname', title: '昵称',fixed: 'left', align: 'center'}
      ,{field:'gender', title: '性别',templet: '#checkGender', unresize: true, align: 'center', sort: true}
      ,{field:'email', title: '邮箱', align: 'center'} //单元格内容水平居中
      ,{field:'telephone', title: '电话', align: 'center'} //单元格内容水平居中
      ,{field:'flag', title: '状态', align: 'center',templet: '#checkFlag', unresize: true, sort: true}
      ,{field:'cdateStr', title: '创建时间', sort: true, align: 'center'}
      ,{field:'mdateStr', title: '修改时间', sort: true, align: 'center'}
    ]]
    ,id : 'userReload'
  });
  
  //监听表格复选框选择
  table.on('checkbox(userT)', function(obj){
    //console.log(obj)
  });
  
  var $ = layui.$, active = {
    searchBtn: function() {
    	/*layer.msg('加载中', {
	        icon: 16
	        ,anim: -1
	        ,fixed: false
	      });*/
    	var keyWord = $('#keyWord');
        
        //执行重载
        table.reload('userReload', {
          page: {
            curr: 1 //重新从第 1 页开始
          }
          ,where: {
        	  keyWord: keyWord.val()
          }
        });
    }
    ,add: function(){ //新增
    	layer.open({
            id:"addUser",
            type: 2,
            title: "新增用户",
            shadeClose: false,
            shade: [0.3, '#000'],
            maxmin: true, //开启最大化最小化按钮
            area: ['100%', '100%'],
            content: rootPath+"/page_system_user_add.action",
            end : function() {
				//执行重载
       	      	table.reload('userReload');
			}
        });
    }
    ,edit: function(){ //修改
    	var checkStatus = table.checkStatus('userReload')
        ,data = checkStatus.data;
    	if (data.length <= 0) {
    		layer.msg('请选择一条记录', {icon: 5});
    	} else if (data.length > 0 && data.length <= 1) {
    		layer.open({
    			id:"editUser",
    			type: 2,
    			title: "编辑用户",
    			shadeClose: false,
    			shade: [0.3, '#000'],
    			maxmin: true, //开启最大化最小化按钮
    			area: ['100%', '100%'],
    			content: rootPath+"/userAction_find.action?userId="+data[0].userId,
    			end : function() {
    				//执行重载
	       	      	table.reload('userReload');
    			}
    		});
    	} else {
    		layer.msg('只能选择一条记录', {icon: 5});
    	}
    }
    ,deleteBatch: function(){ //批量删除
    	var checkStatus = table.checkStatus('userReload')
        ,data = checkStatus.data;
    	if (data.length > 0) {
    		var array = new Array();
            layui.each(data, function(index, obj) {
          	  array.push(obj.userId);
            });
            var userIds = array.join(",");
            layer.confirm('确认要删除吗？', {
            	skin: 'layui-layer-molv',
        		btn: ['确认','取消'] //按钮
        	}, function(){
    			//layer.msg('删除：'+ userIds);
    			$.ajax({
    	            url: rootPath+"/userAction_deleteBatch.action?modelIds="+userIds,
    	            type: "post",
    	            data: data.field,
    	            success: function (resDate) {
    	            	if (resDate.res === 1) {
    	            		layer.msg(resDate.resMsg, {icon: 1});
    	    			} else if (resDate.res === 0) {
    	    				layer.msg(resDate.resMsg, {icon: 5});
    	    			} else {
    						if (resDate.indexOf("<h1>权限不足</h1>") != -1) {
    							layer.msg("权限不足！", {icon: 5});
    						}
    	    			}
    	            	//执行重载
    	       	      	table.reload('userReload');
    	            },
    	            error: function () {
    	                alert("系统繁忙！");
    	            }
    	        });
        	});
    	} else {
    		layer.msg('至少选择一条记录', {icon: 5});
    	}
    }
  };  
  //绑定click点击事件
  $('.demoTable .layui-btn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
});

layui.use(['form'], function(){
    var form = layui.form;
    //监听新增提交
    form.on('submit(addUserSubmit)', function(data){
    	var url=$(this).attr("data-url");
    	//获取选择的角色
    	var obj=document.getElementsByName('roleIds');
        //遍历选中的角色
    	var array = new Array();
    	for (var i = 0; i < obj.length; i++) {
    		//如果选中，将value添加到数组中
    		if(obj[i].checked) {
    			array.push(obj[i].value);
    		}
    	}
    	var roleIds = array.join(",");
    	//为资源隐藏域赋值
		data.field.roleIds = roleIds;
		//console.info(data);
        $.ajax({
            url: url,
            type: "post",
            data: data.field,
            success: function (resDate) {
            	$t.closeWindow();
    			if (resDate.res === 1) {
    				Msg.success(resDate.resMsg);
    			} else if (resDate.res === 0) {
    				Msg.error(resDate.resMsg);
    			} else {
					if (resDate.indexOf("<h1>权限不足</h1>") != -1) {
						Msg.error("权限不足！");
					}
    			}
            },
            error: function () {
                alert("系统繁忙！");
            }
        });
        return false;
    });
    //监听修改提交
    form.on('submit(editUserSubmit)', function(data){
    	var url=$(this).attr("data-url");
    	//获取选择的角色
    	var obj=document.getElementsByName('roleIds');
        //遍历选中的角色
    	var array = new Array();
    	for (var i = 0; i < obj.length; i++) {
    		//如果选中，将value添加到数组中
    		if(obj[i].checked) {
    			array.push(obj[i].value);
    		}
    	}
    	var roleIds = array.join(",");
    	//为资源隐藏域赋值
		data.field.roleIds = roleIds;
		//console.info(data);
    	$.ajax({
    		url: url,
    		type: "post",
    		data: data.field,
    		success: function (resDate) {
    			$t.closeWindow();
    			if (resDate.res === 1) {
    				Msg.success(resDate.resMsg);
    			} else if (resDate.res === 0) {
    				Msg.error(resDate.resMsg);
    			} else {
					if (resDate.indexOf("<h1>权限不足</h1>") != -1) {
						Msg.error("权限不足！");
					}
    			}
    		},
    		error: function () {
    			alert("系统繁忙！");
    		}
    	});
    	return false;
    });

});
layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  //常规用法
	  laydate.render({
	    elem: '#birthday'
	  });
});