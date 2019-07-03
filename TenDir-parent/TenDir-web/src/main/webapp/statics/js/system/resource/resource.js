layui.use('table', function(){
  var table = layui.table;
  
  table.render({
    elem: '#resourceTable'
    ,page:true
    ,height:400
    ,url:rootPath+'/resourceAction_pageQuery.action'
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
      ,{field:'resname', title: '菜单名称', fixed: 'left', align: 'center'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
      ,{field:'resurl', title: '菜单URL', fixed: 'left', align: 'center'}
      ,{field:'parentName',templet: '#checkPRes', title: '父级菜单', align: 'center'}
      ,{field:'rescode', title: '授权标识', align: 'center'}
      ,{field:'type', title: '类型', sort: true, align: 'center',templet: '#checkType', unresize: true} //单元格内容水平居中
      ,{field:'icon', title: '图标', align: 'center',templet: '#checkIcon', unresize: true} //单元格内容水平居中
      ,{field:'description', title: '描述', align: 'center'} //单元格内容水平居中
      ,{field:'sort', title: '排序', align: 'center', sort: true} //单元格内容水平居中
      ,{field:'flag', title: '状态',templet: '#checkFlag', unresize: true, sort: true, align: 'center'}
      ,{field:'cdateStr', title: '创建时间', sort: true, align: 'center'}
      ,{field:'mdateStr', title: '修改时间', sort: true, align: 'center'}
    ]]
    ,id : 'resourceReload'
  });
  
  //监听表格复选框选择
  table.on('checkbox(resourceT)', function(obj){
    console.log(obj)
  });
  
  var $ = layui.$, active = {
    searchBtn: function() {
    	var keyWord = $('#keyWord');
        
        //执行重载
        table.reload('resourceReload', {
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
            id:"addResource",
            type: 2,
            title: "新增菜单",
            shadeClose: false,
            shade: [0.3, '#000'],
            maxmin: true, //开启最大化最小化按钮
            area: ['100%', '100%'],
            content: rootPath+"/page_system_resource_add.action",
            end : function() {
				//执行重载
       	      	table.reload('resourceReload');
			}
        });
    }
    ,edit: function(){ //修改
    	var checkStatus = table.checkStatus('resourceReload')
        ,data = checkStatus.data;
    	if (data.length <= 0) {
    		layer.msg('请选择一条记录', {icon: 5});
    	} else if (data.length > 0 && data.length <= 1) {
    		layer.open({
    			id:"editResource",
    			type: 2,
    			title: "编辑菜单",
    			shadeClose: false,
    			shade: [0.3, '#000'],
    			maxmin: true, //开启最大化最小化按钮
    			area: ['100%', '100%'],
    			content: rootPath+"/resourceAction_find.action?resourceId="+data[0].resourceId,
    			end : function() {
    				//执行重载
	       	      	table.reload('resourceReload');
    			}
    		});
    	} else {
    		layer.msg('只能选择一条记录', {icon: 5});
    	}
    }
    ,deleteBatch: function(){ //批量删除
    	var checkStatus = table.checkStatus('resourceReload')
        ,data = checkStatus.data;
    	if (data.length > 0) {
    		var array = new Array();
            layui.each(data, function(index, obj) {
          	  array.push(obj.resourceId);
            });
            var resourceIds = array.join(",");
            layer.confirm('确认要删除吗？', {
            	skin: 'layui-layer-molv',
        		btn: ['确认','取消'] //按钮
        	}, function(){
    			//layer.msg('删除：'+ resourceIds);
    			$.ajax({
    	            url: rootPath+"/resourceAction_deleteBatch.action?modelIds="+resourceIds,
    	            type: "post",
    	            data: data.field,
    	            success: function (resDate) {
    	            	if (resDate.res === 1) {
    	            		layer.msg(resDate.resMsg, {icon: 1});
    	    			} else if (resDate.res === 0) {
    	    				layer.alert(resDate.resMsg+'，请先删除关联的角色！', {
	        					icon: 5,
	        					skin: 'layui-layer-molv' //样式类名
	        				});
    	    			} else {
    						if (resDate.indexOf("<h1>权限不足</h1>") != -1) {
    							layer.msg("权限不足！", {icon: 5});
    						}
    	    			}
    	            	//执行重载
    	       	      	table.reload('resourceReload');
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
    //监听单选框
    form.on('radio(radioType)', function(data){
	  if (data.value == '0') {
		  $("#resurlID").css({"display":"none"});
		  //$("#rescodeID").css({"display":"none"});
	  } else {
		  $("#resurlID").css({"display":"block"});
	  }
	});  
    //监听新增提交                   
    form.on('submit(addResourceSubmit)', function(data){
    	var url=$(this).attr("data-url");
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
    form.on('submit(editResourceSubmit)', function(data){
    	var url=$(this).attr("data-url");
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