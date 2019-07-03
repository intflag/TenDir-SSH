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

<form class="layui-form" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">姓名:</label>
        <div class="layui-input-inline">
            <input type="text" name="nameVague" placeholder="请输入姓名" class="layui-input">
        </div>
        <label class="layui-form-label">年龄:</label>
        <div class="layui-input-inline">
            <input type="text" name="age" placeholder="请输入年龄" class="layui-input">
        </div>

        <label class="layui-form-label">性别:</label>
        <div class="layui-input-inline">
            <input type="text" name="gender" placeholder="请输入性别" class="layui-input">
        </div>

        <div class="layui-input-normal">
            <button class="layui-btn layui-btn-green" lay-submit="" lay-filter="moreSearch">
                <i class="fa fa-chevron-down">&nbsp;</i>更多
            </button>
            <button class="layui-btn search-btn" table-id="studentTable" lay-submit="" lay-filter="search">
                <i class="fa fa-search">&nbsp;</i>查询
            </button>
            <button type="reset" class="layui-btn layui-btn-primary"><i class="fa fa-refresh">&nbsp;</i>重置</button>
        </div>
    </div>

    <div class="layui-form-item more-search">
        <label class="layui-form-label">地址:</label>
        <div class="layui-input-inline">
            <input type="text" name="addressVague" placeholder="请输入地址" class="layui-input">
        </div>

        <label class="layui-form-label">出生日期:</label>
        <div class="layui-input-inline">
            <input type="text" name="birthday" placeholder="请输入出生日期" class="layui-input">
        </div>

    </div>
</form>


              </pre>
        </div>

        <div class="layui-tab-item" id="result">

            <form class="layui-form" action="">
                <div class="layui-form-item">
                    <label class="layui-form-label">姓名:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="nameVague" placeholder="请输入姓名" class="layui-input">
                    </div>
                    <label class="layui-form-label">年龄:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="age" placeholder="请输入年龄" class="layui-input">
                    </div>

                    <label class="layui-form-label">性别:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="gender" placeholder="请输入性别" class="layui-input">
                    </div>

                    <div class="layui-input-normal">
                        <button class="layui-btn layui-btn-green" lay-submit="" lay-filter="moreSearch">
                            <i class="fa fa-chevron-down">&nbsp;</i>更多
                        </button>
                        <button class="layui-btn search-btn" table-id="studentTable" lay-submit="" lay-filter="search">
                            <i class="fa fa-search">&nbsp;</i>查询
                        </button>
                        <button type="reset" class="layui-btn layui-btn-primary"><i class="fa fa-refresh">&nbsp;</i>重置</button>
                    </div>
                </div>

                <div class="layui-form-item more-search">
                    <label class="layui-form-label">地址:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="addressVague" placeholder="请输入地址" class="layui-input">
                    </div>

                    <label class="layui-form-label">出生日期:</label>
                    <div class="layui-input-inline">
                        <input id="birthday" type="text" readonly="readonly" name="birthday" placeholder="请输入出生日期" class="layui-input">
                    </div>

                </div>
            </form>

        </div>
        <div class="layui-tab-item">
            <table class="layui-table">
                <thead>
                <tr>
                    <th>属性</th>
                    <th>描述</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>table-id="studentTable" </td>
                    <td>对应列表页table的id,查询时与该表格关联</td>
                </tr>
                <tr>
                    <td>table-id="studentTable" </td>
                    <td>对应列表页table的id,查询时与该表格关联</td>
                </tr>
                <tr>
                    <td>class="layui-form-item more-search"</td>
                    <td>请将更多搜索条件放入此容器中</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    layui.use('code', function(){ //加载code模块
        layui.code({encode: true}); //引用code方法
    });
    layui.use('element', function () {
    });
    layui.use('laydate', function(){
    	  var laydate = layui.laydate;
    	  //常规用法
    	  laydate.render({
    	    elem: '#birthday'
    	  });
    });

</script>

</body>
</html>
