/**
 * Created by 陈熠 on 2017/6/21
 * email   :  228112142@qq.com
 */
(function ($) {
    var cyProps = {};
    
    /* 入口函数 */
    $.fn.treeTool = function () {
    	var zNodes =[
			{ name:"父节点1 - 展开", open:true,
				children: [
					{ name:"父节点11 - 折叠",
						children: [
							{ name:"叶子节点111"},
							{ name:"叶子节点112"},
							{ name:"叶子节点113"},
							{ name:"叶子节点114"}
						]},
					{ name:"父节点12 - 折叠",
						children: [
							{ name:"叶子节点121"},
							{ name:"叶子节点122"},
							{ name:"叶子节点123"},
							{ name:"叶子节点124"}
						]},
					{ name:"父节点13 - 没有子节点", isParent:true}
				]},
			{ name:"父节点2 - 折叠",
				children: [
					{ name:"父节点21 - 展开", open:true,
						children: [
							{ name:"叶子节点211"},
							{ name:"叶子节点212"},
							{ name:"叶子节点213"},
							{ name:"叶子节点214"}
						]},
					{ name:"父节点22 - 折叠",
						children: [
							{ name:"叶子节点221"},
							{ name:"叶子节点222"},
							{ name:"叶子节点223"},
							{ name:"叶子节点224"}
						]},
					{ name:"父节点23 - 折叠",
						children: [
							{ name:"叶子节点231"},
							{ name:"叶子节点232"},
							{ name:"叶子节点233"},
							{ name:"叶子节点234"}
						]}
				]},
			{ name:"父节点3 - 没有子节点", isParent:true}

		];
        //参数数据
        cyProps = $(this).attr("cyProps");
        if (!cyProps) {
            return
        }
        cyProps = cyProps ? cyProps : "";
        //将表格参数转为json
        cyProps = eval("({" + cyProps + "})");
        //添加默认样式
        $(this).attr("readonly", "readonly");
        $(this).attr("style", "padding-right: 30px;");
        //为该组件添加清空按钮
        $(this).after('<i class="layui-icon  clear-btn" onclick="clearTreeData(this)">&#x1006;</i>');
        //获取控件id
        var _id = $(this).attr("id")||"id_"+new Date().getTime();
        //获取下拉树默认值的id
        var _value = $(this).attr("value") || "";
        //下拉树显示的值
        var valueName="";
        //加载下拉树数据
        ztree = $.fn.zTree.init($("#zTree"), setting, zNodes);
        var node = ztree.getNodeByParam("id", _value);
        if (node != null) {
            //获取下拉树要显示的值
            valueName=node.name;
            // 选中下拉树默认节点
            ztree.selectNode(node);
            $(this).val(node.name);
        }
        if(_value!=null&&_value!=""){
            $(this).attr("valueId",_value);
            $(this).val(valueName);
        }

        $("#" + _id + "_id").remove();
        //$("#treeLayer").remove();
        $(".layui-layer-molv").remove();

        $(this).after('<input value="' + _value + '"  style="display: none" id="' + _id + '_id"  name="' + cyProps.name + '"  class="layui-input">' +
            '<div id="treeLayer" style="display: none;padding:10px;"> ' +
            '<ul id="zTree" class="ztree"></ul> ' +
            '</div>');
    };


})(jQuery);
var setting = {
    data: {
        simpleData: {
            enable: false
        },
        key: {
            url: "nourl"
        }
    }
};
var ztree;
/**菜单列表*/
function openZtree(obj) {
    var _id = $(obj).attr("id");
    var _value=$("#" + _id + "_id").val();
    $("#" + _id + "_id").remove();
    $(".layui-layer-molv").remove();
    $("#treeLayer").remove();
    var cyProps = $(obj).attr("cyProps");
    if (!cyProps) {
        return
    }
    cyProps = cyProps ? cyProps : "";
    //将表格参数转为json
    cyProps = eval("({" + cyProps + "})");

    $(obj).after('<input  id="' + _id + '_id"  style="display: none" name="' + cyProps.name + '" value="' + _value + '" class="layui-input">' +
        '<div id="treeLayer" style="display: none;padding:10px;"> ' +
        '<ul id="zTree" class="ztree"></ul> ' +
        '</div>');
    var zNodes =[
		{ name:"父节点1 - 展开", open:true,
			children: [
				{ name:"父节点11 - 折叠",
					children: [
						{ name:"叶子节点111"},
						{ name:"叶子节点112"},
						{ name:"叶子节点113"},
						{ name:"叶子节点114"}
					]},
				{ name:"父节点12 - 折叠",
					children: [
						{ name:"叶子节点121"},
						{ name:"叶子节点122"},
						{ name:"叶子节点123"},
						{ name:"叶子节点124"}
					]},
				{ name:"父节点13 - 没有子节点", isParent:true}
			]},
		{ name:"父节点2 - 折叠",
			children: [
				{ name:"父节点21 - 展开", open:true,
					children: [
						{ name:"叶子节点211"},
						{ name:"叶子节点212"},
						{ name:"叶子节点213"},
						{ name:"叶子节点214"}
					]},
				{ name:"父节点22 - 折叠",
					children: [
						{ name:"叶子节点221"},
						{ name:"叶子节点222"},
						{ name:"叶子节点223"},
						{ name:"叶子节点224"}
					]},
				{ name:"父节点23 - 折叠",
					children: [
						{ name:"叶子节点231"},
						{ name:"叶子节点232"},
						{ name:"叶子节点233"},
						{ name:"叶子节点234"}
					]}
			]},
		{ name:"父节点3 - 没有子节点", isParent:true}

	];
    ztree = $.fn.zTree.init($("#zTree"), setting, zNodes);
    var node = ztree.getNodeByParam("id", _value);
    if (node != null) {
        ztree.selectNode(node);
        $(obj).val(node.name);
    }
    layer.open({
        type: 1,
        offset: '50px',
        skin: 'layui-layer-molv',
        title: "请选择",
        area: ['300px', '400px'],
        shade: 0,
        shadeClose: false,
        content: jQuery("#treeLayer"),
        btn: ['确定', '取消'],
        btn1: function (index) {
            var node = ztree.getSelectedNodes();
            if (node.length > 0) {
                $("#" + _id + "_id").val(node[0].id);
                $("#" + _id).val(node[0].name);
               // $(obj).attr("valueId", node[0].id);
            }
            //选择上级菜单
            layer.close(index);
        }
    });
}
/**清空下拉树数据**/
function clearTreeData(obj){
        //重置显示的值
    $(obj).prev().prev().prev().val("");
    $(obj).prev().prev().val("");
}
$(document).ready(function () {
    $("[cyType='treeTool']").click(function () {
        var obj = $(this);
        openZtree(obj);
    });
    //下拉树查询
    var treeTools = $("[cyType='treeTool']");
    for (var i = 0; i < treeTools.length; i++) {
        $(treeTools[i]).treeTool();
    }


});
