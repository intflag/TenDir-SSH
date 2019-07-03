layui.use('table', function(){
  var table = layui.table;
  
  table.render({
    elem: '#smsTable'
    ,page:true
    ,height:400
    ,url:rootPath+'/qcloudSmsAction_pageQuery.action'
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
	  ,{field:'appId', title: 'AppId',fixed: 'left', align: 'center'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
      ,{field:'appKey', title: 'AppKey',fixed: 'left', align: 'center'}
      ,{field:'templetId', title: '短信模板ID',fixed: 'left', align: 'center'}
      ,{field:'address', title: '收件人', align: 'center'} //单元格内容水平居中
      ,{field:'flag', title: '状态', align: 'center',templet: '#checkFlag', unresize: true, sort: true}
      //,{field:'sort', title: '排序', align: 'center', sort: true} //单元格内容水平居中
      ,{field:'cdateStr', title: '操作时间', sort: true, align: 'center'}
      ,{field:'description', title: '操作描述', align: 'center'}
      ,{field:'content', title: '发送内容', align: 'center'}
    ]]
    ,id : 'smsReload'
  });
  
  //监听表格复选框选择
  table.on('checkbox(smsT)', function(obj){
    //console.log(obj)
  });
  
  var $ = layui.$, active = {
    searchBtn: function() {
    	var keyWord = $('#keyWord');
        
        //执行重载
        table.reload('smsReload', {
          page: {
            curr: 1 //重新从第 1 页开始
          }
          ,where: {
        	  keyWord: keyWord.val()
          }
        });
    }
  	,sendBtn: function() {
  		var appId = $("#appId").val();
  		var appKey = $("#appKey").val();
  		var templetId = $("#templetId").val();
  		var content = $("#content").val();
  		var address = $("#address").val();
  		$.ajax({
            url: rootPath+"/qcloudSmsAction_send.action",
            type: "post",
            data: {appId:appId,appKey:appKey,templetId:templetId,content:content,address:address},
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
       	      	table.reload('smsReload');
            },
            error: function () {
                alert("系统繁忙！");
            }
        });
  	}
    ,deleteBatch: function(){ //批量删除
    	var checkStatus = table.checkStatus('smsReload')
        ,data = checkStatus.data;
    	if (data.length > 0) {
    		var array = new Array();
            layui.each(data, function(index, obj) {
          	  array.push(obj.smsId);
            });
            var smsIds = array.join(",");
            layer.confirm('确认要删除吗？', {
            	skin: 'layui-layer-molv',
        		btn: ['确认','取消'] //按钮
        	}, function(){
    			//layer.msg('删除：'+ smsIds);
    			$.ajax({
    	            url: rootPath+"/qcloudSmsAction_deleteBatch.action?modelIds="+smsIds,
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
    	       	      	table.reload('smsReload');
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