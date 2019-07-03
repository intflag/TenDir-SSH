<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
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
    <script src="../../common/js/starTool.js"></script>
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
    <button class="layui-btn layui-btn-disabled">
    <!--onclick="submitCode()"-->
    提交代码</button>
</div>
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">代码</li>
        <li id="showResult">效果</li>
        <li>参数详解</li>

    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <code style="margin-top: 4px; display: block;">星级插件使用示例</code>
            <textarea class="layui-textarea" rows="20" class="layui-code">
               <form class="layui-form " action="">


                <!--默认-->
                <div cyType="starTool" cyProps="" class="layui-input-inline"></div><br><br>

                   <!--常用配置-->
                <div cyType="starTool" cyType="starTool" cyProps="name:'star',value:3.5,disable:true"

                     class="layui-input-inline"></div><br><br>

                   <!--全部配置-->
                <div cyType="starTool" cyType="starTool"
                     cyProps="name:'star',count:10,value:3.5,height:15,width:15,margin:5,disable:false,cursor:'default'"
                     class="layui-input-inline"></div><br><br>


               </form>
           </textarea>

        </div>
        <div class="layui-tab-item" id="result">
            <form class="layui-form " action="">

                <!--默认-->
                <div cyType="starTool" cyProps="" class="layui-input-inline"></div>
                <br><br>

                <!--常用配置-->
                <div cyType="starTool" cyType="starTool" cyProps="name:'star',value:3.5,disable:true"

                     class="layui-input-inline"></div>
                <br><br>

                <!--全部配置-->
                <div cyType="starTool" cyType="starTool"
                     cyProps="name:'star',count:10,value:3.5,height:15,width:15,margin:5,disable:false,cursor:'default'"

                     class="layui-input-inline"></div>
                <br><br>


            </form>
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
                    <td>cyType="radioTool"</td>
                </tr>
                <tr>
                    <td>cyProps:name</td>
                    <td>字段</td>
                    <td>star</td>
                    <td>cyProps="name:'star'" </td>
                </tr>
                <tr>
                    <td>cyProps:count</td>
                    <td>星星总个数</td>
                    <td>5</td>
                    <td>cyProps="count:10"</td>
                </tr>
                <tr>
                    <td>cyProps:value</td>
                    <td>默认选择个数</td>
                    <td>0</td>
                    <td>cyProps="value:1.5"</td>
                </tr>
                <tr>
                    <td>cyProps:height</td>
                    <td>高度</td>
                    <td>23</td>
                    <td>cyProps="height:15"</td>
                </tr>
                <tr>
                    <td>cyProps:width</td>
                    <td>宽度</td>
                    <td>23</td>
                    <td>cyProps="width:15"</td>
                </tr>
                <tr>
                    <td>cyProps:margin</td>
                    <td>星星之间的间距</td>
                    <td>5</td>
                    <td>cyProps="width:15"</td>
                </tr>
                <tr>
                    <td>cyProps:disable</td>
                    <td>是否禁选</td>
                    <td>false</td>
                    <td>cyProps="disable:true"</td>
                </tr>
                <tr>
                    <td>cyProps:cursor</td>
                    <td>鼠标经过手势样式</td>
                    <td>pointer</td>
                    <td>cyProps="cursor:default"</td>
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
        var starTools = $("#result").find("[cyType='starTool']");

        for (var i = 0; i < starTools.length; i++) {
            $(starTools[i]).starTool();
        }

        Msg.success("代码提交成功,请查看效果!");
        Tips.info("点此查看效果", $("#showResult"));
    }

    //    layui.use('code', function () { //加载code模块
    //        layui.code({encode: true}); //引用code方法
    //    });
</script>
</body>
</html>
