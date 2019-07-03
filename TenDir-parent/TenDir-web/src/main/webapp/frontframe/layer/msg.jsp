<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<html>
<head>
    <title>msg</title>
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
            <textarea class="layui-textarea" rows="28" class="layui-code">

              类型 默认灰色 1绿色√ 2红色x 3黄色？  4灰色锁  5红色委屈脸  6绿色开心脸  7黄色！

             <button class="layui-btn" onclick="Msg.msg('提示信息',4)">短信息提示 1-7</button> </br></br>

             <button class="layui-btn" onclick="Msg.info('普通信息')">普通信息(灰色)</button> </br></br>

             <button class="layui-btn" onclick="Msg.success('操作成功')">操作成功(灰色)</button> </br></br>

             <button class="layui-btn" onclick="Msg.warn('警告信息')">警告提示</button> </br></br>

             <button class="layui-btn" onclick="Msg.error('错误信息')">错误提示</button> </br></br>

           </textarea>

        </div>

        <div class="layui-tab-item" id="result">
            <button class="layui-btn" onclick="Msg.msg('提示信息',4)">短信息提示 1-7</button> </br></br>

            <button class="layui-btn" onclick="Msg.info('普通信息')">普通信息(灰色)</button> </br></br>

            <button class="layui-btn" onclick="Msg.success('操作成功')">操作成功(灰色)</button> </br></br>

            <button class="layui-btn" onclick="Msg.warn('警告信息')">警告提示</button> </br></br>

            <button class="layui-btn" onclick="Msg.error('错误信息')">错误提示</button> </br></br>
        </div>

        <div class="layui-tab-item">
            <table class="layui-table">
                <thead>
                <tr>
                    <th>类型</th>
                    <th>方法</th>
                    <th>参数</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>自由配置</td>
                    <td>Msg.msg(msg,type)</td>
                    <td>msg:提示信息  type: 不填灰色 1绿色√ 2红色x 3黄色？  4灰色锁  5红色委屈脸  6绿色开心脸  7黄色！</td>
                </tr>
                <tr>
                    <td>普通信息</td>
                    <td>Msg.info(msg)</td>
                    <td>msg:提示信息</td>
                </tr>
                <tr>
                    <td>操作成功</td>
                    <td>Msg.success(msg)</td>
                    <td>msg:提示信息</td>
                </tr>
                <tr>
                    <td>警告提示</td>
                    <td>Msg.warn(msg)</td>
                    <td>msg:提示信息</td>
                </tr>
                <tr>
                    <td>错误提示</td>
                    <td>Msg.error(msg)</td>
                    <td>msg:提示信息</td>
                </tr>
                </tbody>
            </table>

        </div>
    </div>
</div>


</body>
<script>
    layui.use('element', function () {
    });

    function submitCode() {
        var code = $("textarea").val();
        $("#result").html(code);
        Msg.success("代码提交成功,请查看效果!");
        Tips.info("点此查看效果",$("#showResult"));
    }
</script>
</html>
