layui.use(['form','code'], function(){
    var form = layui.form;
    //监听新增提交
    form.on('submit(addGeneratorSubmit)', function(data){
    	var url=$(this).attr("data-url");
    	console.log(data);
        $.ajax({
            url: url,
            type: "post",
            data: data.field,
            success: function (resDate) {
            	$t.closeWindow();
    			if (resDate.res === 1) {
    				var contentStr = '<pre class="layui-code" lay-title="" lay-height="" lay-skin="" lay-encode="">'+resDate.obj+'</pre>';
    				layer.open({
					  type: 1
					  ,title: "提示："+resDate.resMsg+"，请复制下面代码到struts.xml中"
					  ,closeBtn: false
					  ,area: '600px;'
					  ,shade: 0.3
					  ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
					  ,resize: false
					  ,btn: ['我已复制']
					  ,btnAlign: 'r'
					  ,moveType: 1 //拖拽模式，0或者1
					  ,content: contentStr
					});
    				//Msg.success(resDate.resMsg);
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
    //监听下拉
    form.on('select(selectTableName)', function(data){
    	var url=$(this).attr("data-url");
    	var tableName=data.value;
		if (tableName.length > 0) {
			$.ajax({
				url: rootPath+"/codeGeneratorAction_findFieldByTableName.action",
	            type: "post",
			    data: {tableName:tableName},
			    success: function (resDate) {
			        if (resDate.res == 1) {
			        	var data = resDate.obj;
			        	$("#pkColumn").html("");
			        	$("#pkColumn").append('<option value=""></option>');
			        	for(var i=data.length-1;i>=0;i--){
							$("#pkColumn").append('<option selected="" value='+data[i].columnName+'>'+data[i].columnName+'</option>');
						}
			        	form.render('select');//重新渲染
			        } else {
			        	layer.msg(resDate.resMsg, {icon: 5});
			        }
			    }
			});
		}
    	return false;
    });

});