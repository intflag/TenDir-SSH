<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<html>
<head>
    <title>Title</title>
    <script src="../../statics/libs/jquery-1.7.2.js"></script>
    <link rel="stylesheet" href="../../statics/css/font-awesome.min.css">
    <!--layui ztree样式-->
    <link rel="stylesheet" href="../../statics/plugins/layui/css/layui.css" media="all">
    <script src="../../statics/plugins/layer/layer.js"></script>
    <script src="../../statics/plugins/layui/layui.js"></script>
    <script src="../../statics/plugins/ztree/jquery.ztree.all.min.js"></script>
    <link rel="stylesheet" href="../../statics/plugins/ztree/css/metroStyle/metroStyle.css">
    <!--js组件-->
    <script src="../../common/js/pageGrid.js"></script>
    <script src="../../common/js/selectTool.js"></script>
    <script src="../../common/js/radioTool.js"></script>
    <script src="../../common/js/checkboxTool.js"></script>
    <script src="../../common/js/treeTool.js"></script>
    <script src="../../common/js/labelTool.js"></script>
    <script src="../../common/js/linkSelectTool.js"></script>
    <script src="../../common/js/uploadTool.js"></script>
    <script src="../../common/js/HuploadTool.js"></script>
    <script src="../../common/js/tplTool.js"></script>
    <script src="../../common/js/editGrid.js"></script>
    <!--全局-->
    <script src="../../common/js/whole/cyLayer.js"></script>
    <script src="../../common/js/whole/common.js"></script>
    <script src="../../common/js/whole/setting.js"></script>
    <script src="../../common/js/whole/utils.js"></script>
    <script src="../../common/js/whole/monitor.js"></script>
    <!--样式-->
    <link rel="stylesheet" href="../../common/css/cyStyle.css">
    <link rel="stylesheet" href="../../common/css/cyType.css">
</head>
<body>
<div class="layui-field-box">
    <form class="layui-form" action="">


        <div class="layui-form-item">
            <label class="layui-form-label">姓名<span class="label_span">:</span></label>
            <div class="layui-input-normal">
                <input type="text" name="bucket" maxlength="50" value=""
                       placeholder="请输入姓名" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别<span class="span_must">*</span></label>
            <div cyType="radioTool" cyProps="enumName:'SexEnum',disabled:'0',filter:'sex'" name="sex1"
                 value="1" class="layui-input-inline"></div>

        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">城市等级<span class="span_must">*</span></label>
            <div cyType="checkboxTool" cyProps="codeName:'areaLevel',disabled:'1',filter:'demo',allBtn:'true'"
                 name="sex2" value="1,3" class="layui-input-inline"></div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">爱好<span class="span_must">*</span></label>
            <div cyType="selectTool" cyProps="url:'/frontframe/json/like.json',search:'true',filter:'demo'"
                 name="sex" value="0" class="layui-input-inline"></div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">省份<span class="span_must">*</span></label>
            <div cyType="selectTool" cyProps="enumName:'ProvinceEnum',multiple:'true'"
                 value="1111330000,1111210000" name="area" class="layui-input-normal"></div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">详细地区<span class="span_must">*</span></label>
            <div cyType="linkSelectTool" cyProps="url:'/area/normalList/',topId:'0000000000',name:'parentAreaId[]'"
                 value="1111111111,2111110000,1111110000" class="layui-input-inline"></div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">所属菜单<span class="span_must">*</span></label>
            <div class="layui-input-inline">
                <input value="0" id="demo" cyType="treeTool" cyProps="url:'/sys/menu/select',name:'parentId'"
                       placeholder="请选择上级菜单" class="layui-input"/>

            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">上传照片<span class="span_must">*</span></label>
            <div cyType="uploadTool"  class="layui-input-normal"
                 cyProps="url:'/getData/upload/',value:'/statics/img/timg.jpg',name:'file',btnName:'上传图片',multiple:'false'"></div>

        </div>


        <div class="layui-form-item">
            <label class="layui-form-label">备注<span class="label_span">:</span></label>
            <div class="layui-input-normal">
                <textarea placeholder="备注" class="layui-textarea"></textarea>
            </div>
        </div>

        <input type="hidden" name="id" value="${model.id}">
        <div class="page-footer">
            <div class="btn-list">
                <div class="btnlist">
                    <button class="layui-btn" lay-submit="" lay-filter="submit" data-url="/sysoss/update"><i class="fa fa-floppy-o">&nbsp;</i>保存</button>
                    <button class="layui-btn" onclick="$t.closeWindow()"><i class="fa fa-undo">&nbsp;</i>返回</button>
                </div>
            </div>
        </div>
    </form>
</div>
<script>
    layui.use(['form'], function(){
        var form = layui.form;
        //监听提交
        form.on('submit(submit)', function(data){
            closeWindow();
            Msg.success("添加成功");
            return false;
        });

    });
</script>
</body>
</html>

