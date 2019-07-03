<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>

    <meta charset="utf-8">
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
    <!-- 全局js -->
    <script src="../../statics/libs/jquery.min.js"></script>
    <!-- Web Uploader -->
    <script type="text/javascript">
    // 添加全局站点信息
    var BASE_URL = '../../statics/plugins/webuploader';
    </script>
    <script src="../../statics/plugins/webuploader/webuploader.js"></script>

    <script src="../../statics/plugins/webuploader/webuploader-demo.js"></script>
    <link rel="stylesheet" type="text/css" href="../../statics/plugins/webuploader/webuploader.css">
    <link rel="stylesheet" type="text/css" href="../../statics/plugins/webuploader/webuploader-demo.css">


</head>

<body>
<fieldset class="layui-elem-field site-demo-button" style="margin-top: 30px;">
    <legend>百度上传控件</legend>
    <div id="uploader" class="wu-example">
        <div class="queueList">
            <div id="dndArea" class="placeholder">
                <div id="filePicker"></div>
                <p>或将照片拖到这里，单次最多可选300张</p>
            </div>
        </div>
        <div class="statusBar" style="display:none;">
            <div class="progress">
                <span class="text">0%</span>
                <span class="percentage"></span>
            </div>
            <div class="info"></div>
            <div class="btns">
                <div id="filePicker2"></div>
                <div class="uploadBtn">开始上传</div>
            </div>
        </div>
    </div>
</fieldset>
<pre class="layui-code" >
   <!-- 全局js -->
    <script src="/statics/libs/jquery.min.js"></script>
    <!-- Web Uploader -->
    <script type="text/javascript">
    // 添加全局站点信息
    var BASE_URL = '/statics/plugins/webuploader';
    </script>
    <script src="/statics/plugins/webuploader/webuploader.js"></script>

    <script src="/statics/plugins/webuploader/webuploader-demo.js"></script>
    <link rel="stylesheet" type="text/css" href="/statics/plugins/webuploader/webuploader.css">
    <link rel="stylesheet" type="text/css" href="/statics/plugins/webuploader/webuploader-demo.css">

     <div class="statusBar" style="display:none;">
            <div class="progress">
                <span class="text">0%</span>
                <span class="percentage"></span>
            </div>
            <div class="info"></div>
            <div class="btns">
                <div id="filePicker2"></div>
                <div class="uploadBtn">开始上传</div>
            </div>
        </div>
</pre>

</body>
<script>
    layui.use('code', function(){ //加载code模块
        layui.code({encode: true}); //引用code方法
    });

</script>
</html>
