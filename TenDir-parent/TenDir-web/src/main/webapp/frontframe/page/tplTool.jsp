<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">代码</li>
        <li>效果</li>
        <li>参数详解</li>

    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
              <pre class="layui-code">

<div cyType="tplTool" cyProps="url:'${pageContext.request.contextPath}/frontframe/json/student.json'">

    <script type="text/html" >
        <table class="layui-table">

            {{# d.page.list.forEach(function(item){ }}
            <tr>
                <td>{{ item.name }}</td>
                <td>{{ item.age }}</td>
                <td>{{ item.sfzh || '' }}</td>
            </tr>
            {{# }); }}
            {{# if(d.page.list.length === 0){ }}
            <tr>
                <td colspan="3" class="center">暂无数据</td>
            </tr>
            {{# } }}
        </table>
    </script>

</div>


              </pre>
        </div>

        <div class="layui-tab-item" id="result">
            <div cyType="tplTool" cyProps="url:'${pageContext.request.contextPath}/frontframe/json/student.json'">

                <script type="text/html" >
                    <table class="layui-table">

                        {{# d.page.list.forEach(function(item){ }}
                        <tr>
                            <td>{{ item.name }}</td>
                            <td>{{ item.age }}</td>
                            <td>{{ item.sfzh || '' }}</td>
                        </tr>
                        {{# }); }}
                        {{# if(d.page.list.length === 0){ }}
                        <tr>
                            <td colspan="3" class="center">暂无数据</td>
                        </tr>
                        {{# } }}
                    </table>
                </script>

            </div>
        </div>
        <div class="layui-tab-item">
            <table class="layui-table">
                <thead>
                <tr>
                    <th>组件参数</th>
                    <th>描述</th>
                    <th>默认值</th>
                    <th>代码示例</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>cyType</td>
                    <td>控件类型</td>
                    <td><span style="color:red">必填</span></td>
                    <td>cyType="tplTool"</td>
                </tr>
                <tr>
                    <td>cyProps:url</td>
                    <td>数据源</td>
                    <td><span style="color:red">必填</span></td>
                    <td>cyProps="url:'${pageContext.request.contextPath}/frontframe/json/student.json'"</td>
                </tr>
                <tr>
                    <td colspan="4" class="center">未完待续...</td>

                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<a class="click-span ft-20" href="http://www.layui.com/doc/modules/laytpl.html#syntax" target="_blank"> &gt;&gt;&gt; laytpl入口</a>
<script>
    layui.use('code', function(){ //加载code模块
        layui.code({encode: true}); //引用code方法
    });
    layui.use('element', function () {
    });

</script>

</body>
</html>
