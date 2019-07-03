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
      ,{field:'username', title: '登录名',fixed: 'left'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
      ,{field:'nickname', title: '昵称',fixed: 'left'}
      ,{field:'gender', title: '性别',templet: '#switchTpl', unresize: true}
      ,{field:'email', title: '邮箱', align: 'center'} //单元格内容水平居中
      ,{field:'telephone', title: '电话', align: 'right'} //单元格内容水平居中
      ,{field:'flag', title: '状态', align: 'right',templet: '#checkboxTpl', unresize: true}
      ,{field:'cdateStr', title: '创建时间', sort: true, align: 'right'}
      ,{field:'mdateStr', title: '修改时间', sort: true, align: 'right'}
      ,{fixed: 'right', title: '操作' ,width:178, align:'center', toolbar: '#barDemo'}
    ]]
    ,id : 'userReload'
  });
  
  //监听表格复选框选择
  table.on('checkbox(demo)', function(obj){
    console.log(obj)
  });
  //监听工具条
  table.on('tool(demo)', function(obj){
    var data = obj.data;
    if(obj.event === 'detail'){
      layer.msg('ID：'+ data.userId + ' 的查看操作');
    } else if(obj.event === 'del'){
      layer.confirm('真的删除行么', function(index){
        obj.del();
        layer.close(index);
      });
    } else if(obj.event === 'edit'){
	   layer.open({
           id:"editUser",
           type: 2,
           title: "编辑用户",
           shadeClose: false,
           shade: [0.3, '#000'],
           maxmin: true, //开启最大化最小化按钮
           area: ['50%', '80%'],
           content: rootPath+"/userAction_find.action?userId="+data.userId,
           end : function() {
        	 //执行重载
       	      table.reload('userReload');
           }
       });
 		
    }
  });
  
  var $ = layui.$, active = {
    getCheckData: function(){ //获取选中数据
      var checkStatus = table.checkStatus('userReload')
      ,data = checkStatus.data;
      layer.alert(JSON.stringify(data));
    }
    ,getCheckLength: function(){ //获取选中数目
      var checkStatus = table.checkStatus('userReload')
      ,data = checkStatus.data;
      layer.msg('选中了：'+ data.length + ' 个');
    }
    ,isAll: function(){ //验证是否全选
      var checkStatus = table.checkStatus('userReload');
      layer.msg(checkStatus.isAll ? '全选': '未全选')
    }
  };
  
  $('.demoTable .layui-btn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
});

layui.use(['form'], function(){
    var form = layui.form;
    //监听提交
    form.on('submit(editUserSubmit)', function(data){
    	var url=$(this).attr("data-url");
        $.ajax({
            url: url,
            type: "post",
            data: data.field,
            success: function (res) {
            	if (res == '1') {
            		$t.closeWindow();
                    Msg.success("修改成功");
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