<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
    <title>Hupload上传控件</title>
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
<div class="layui-input-inline">
    <button class="layui-btn" onclick="submitCode()">提交代码</button>
</div>
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">编写代码</li>
        <li id="showResult">查看效果</li>
        <li>参数详解</li>

    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <code style="margin-top: 4px; display: block;">使用示例</code>
            <textarea class="layui-textarea" rows="20">
                <!--点击文件名可下载文件-->
                <div cyType="HuploadTool" cyProps="name:'file',url:'/getData/uploads/',uploadId:'',btnName:'上传文件',uploadBtn:'true',deleteBtn:'true'"></div>
           </textarea>

        </div>
        <div class="layui-tab-item " id="result">
                <div cyType="HuploadTool" cyProps="name:'file',url:'/getData/uploads/',uploadId:'',btnName:'上传文件',uploadBtn:'true',deleteBtn:'true'"></div>
        </div>
        <div class="layui-tab-item">
            <table class="layui-table">
                <thead>
                <tr>
                    <th>组件参数</th>
                    <th>描述</th>
                    <th>默认值</th>
                    <th>代码示例</th>
                    <th>备注</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>cyType</td>
                    <td>控件类型</td>
                    <td><span style="color:red">必填</span></td>
                    <td>cyType="HuploadTool"</td>
                    <td><span style="color:red">该控件需配合表sys_file使用</span></td>
                </tr>
                <tr>
                    <td>cyProps:uploadId<td>
                    <%--<td>控件唯一标识</td>--%>
                    <td>为空则自动生成uuid</td>
                    <td>cyProps="uploadId:''"  ||  cyProps="uploadId:'\${model.file}'" </td>
                    <td>提交表单时只保存uploadId,上传的图片自动与uploadId关联</td>
                </tr>
                <tr>
                    <td>cyProps:url</td>
                    <td>上传地址</td>
                    <td>/getData/uploads/</td>
                    <td>cyProps="url:'/getData/uploadFile/'"</td>
                    <td></td>
                </tr>
                <tr>
                    <td>cyProps:name</td>
                    <td>用于表单提交</td>
                    <td>null</td>
                    <td>cyProps="name:'file'"</td>
                    <td class="ft-red">name字段的值为uploadId的值</td>
                </tr>
                <tr>
                    <td>cyProps:btnName</td>
                    <td>按钮名称</td>
                    <td>上传文件</td>
                    <td>cyProps="btnName:'上传文件'"</td>
                    <td></td>
                </tr>
                <tr>
                    <td>cyProps:uploadBtn</td>
                    <td>是否显示上传按钮</td>
                    <td>false</td>
                    <td>cyProps="uploadBtn:'false'"</td>
                    <td></td>
                </tr>
                <tr>
                    <td>cyProps:deleteBtn</td>
                    <td>是否显示删除按钮</td>
                    <td>false</td>
                    <td>cyProps="deleteBtn:'false'"</td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</div>
<script>

    layui.use('upload', function () {
        var upload = layui.upload;
    });

    layui.use('element', function () {
    });
    function submitCode() {
        var code = $("textarea").val();
        $("#result").html(code);
        var uploads = $("#result").find("[cyType='HuploadTool']");
        for (var i = 0; i < uploads.length; i++) {
            $(uploads[i]).Hupload();
        }
        Msg.success("代码提交成功,请查看效果!");
        Tips.info("点此查看效果",$("#showResult"));
    }
</script>
</body>
</html>
