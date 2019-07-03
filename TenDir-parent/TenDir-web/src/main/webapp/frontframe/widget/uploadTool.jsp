<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <title>Hupload附件上传</title>
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
        <li class="layui-this">代码</li>
        <li>效果</li>
        <li>参数详解</li>

    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <code style="margin-top: 4px; display: block;">使用示例</code>
            <textarea class="layui-textarea" rows="20">

                    上传多张图片
                    <div cyType="uploadTool"
                         cyProps="url:'/getData/upload/',value:'statics/img/cy/weixin.jpg,../../statics/img/cy/zhifubao.jpg' ,name:'files[]',btnName:'上传多张图片',multiple:'true',type:'img'"></div>


                    上传单张图片
                    <div cyType="uploadTool"
                         cyProps="url:'/getData/upload/',value:'../../statics/img/timg.jpg',name:'file',btnName:'上传图片',multiple:'false'"></div>

            </textarea>

        </div>
        <div class="layui-tab-item " id="result">


            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
                <legend>上传多张图片</legend>
            </fieldset>


            <div cyType="uploadTool"
                 cyProps="url:'/getData/upload/',value:'${pageContext.request.contextPath}/statics/img/cy/weixin.jpg,${pageContext.request.contextPath}/statics/img/cy/zhifubao.jpg' ,name:'files[]',btnName:'上传多张图片',multiple:'true',type:'img'"></div>


            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
                <legend>上传单张图片</legend>
            </fieldset>


            <div cyType="uploadTool"
                 cyProps="url:'/getData/upload/',value:'${pageContext.request.contextPath}/statics/img/timg.jpg',name:'file',btnName:'上传图片',multiple:'false'"></div>


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
                    <td>cyType="uploadTool"</td>
                    <td></td>
                </tr>
                <tr>
                    <td>cyProps:url</td>
                    <td>上传地址</td>
                    <td>/getData/upload/</td>
                    <td>cyProps="url:'/getData/upload/'"</td>
                    <td></td>
                </tr>
                <tr>
                    <td>value</td>
                    <td>默认显示图片(多图以逗号隔开)</td>
                    <td>null</td>
                    <td>cyProps="value:'/static/cy.img,/static/test.img'"||cyProps="value:'\${model.imgUrl}'"</td>
                    <td></td>
                </tr>
                <tr>
                    <td>cyProps:name</td>
                    <td>用于表单提交</td>
                    <td>null</td>
                    <td>cyProps="name:'files[]'"</td>
                    <td>如果是多个文件后台用数组接收</td>
                </tr>
                <tr>
                    <td>cyProps:btnName</td>
                    <td>按钮名称</td>
                    <td>上传图片</td>
                    <td>cyProps="btnName:'上传文件'"</td>
                    <td></td>
                </tr>
                <tr>
                    <td>cyProps:multiple</td>
                    <td>是否可上传多个文件</td>
                    <td>false</td>
                    <td>cyProps="multiple:'true'"</td>
                    <td></td>
                </tr>
                <tr>
                    <td>cyProps:type</td>
                    <td>限制文件类型</td>
                    <td>img</td>
                    <td>cyProps="type:'video'" | cyProps="type:'audio'"</td>
                    <td>img:图片 , video:视频 ,audio:音频</td>
                </tr>
                <tr>
                    <td>cyProps:size</td>
                    <td>限制文件大小(KB)</td>
                    <td>1000</td>
                    <td>cyProps="size:'5000'"</td>
                    <td></td>
                </tr>
                <tr>
                    <td>cyProps:exts</td>
                    <td>允许上传的文件后缀</td>
                    <td>null</td>
                    <td>cyProps="exts:'zip|rar|7z'"</td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</div>
<script>

    layui.use('element', function () {
    });
    function submitCode() {
        var code = $("textarea").val();
        $("#result").html(code);
        var uploads = $("#result").find("[cyType='uploadTool']");
        for (var i = 0; i < uploads.length; i++) {
            $(uploads[i]).uploadTool();
        }
        layui.use('form', function () {
            var form = layui.form;
            form.render('select');
        });
        Msg.success("代码提交成功,请查看效果!");
        Tips.info("点此查看效果", $("#showResult"));
    }
</script>
</body>
</html>
