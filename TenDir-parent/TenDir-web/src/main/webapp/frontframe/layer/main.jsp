<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<html>
<head>
 <script src="${pageContext.request.contextPath}/statics/libs/jquery-1.7.2.js"></script>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/font-awesome.min.css">
 <!--layui ztree样式-->
 <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/plugins/layui/css/layui.css" media="all">
 <script src="${pageContext.request.contextPath}/statics/plugins/layer/layer.js"></script>
 <script src="${pageContext.request.contextPath}/statics/plugins/layui/layui.js"></script>
 <script src="${pageContext.request.contextPath}/statics/plugins/ztree/jquery.ztree.all.min.js"></script>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/plugins/ztree/css/metroStyle/metroStyle.css">
 <!--js组件-->
 <script src="${pageContext.request.contextPath}/common/js/pageGrid.js"></script>
 <script src="${pageContext.request.contextPath}/common/js/selectTool.js"></script>
 <script src="${pageContext.request.contextPath}/common/js/radioTool.js"></script>
 <script src="${pageContext.request.contextPath}/common/js/checkboxTool.js"></script>
 <script src="${pageContext.request.contextPath}/common/js/treeTool.js"></script>
 <script src="${pageContext.request.contextPath}/common/js/labelTool.js"></script>
 <script src="${pageContext.request.contextPath}/common/js/linkSelectTool.js"></script>
 <script src="${pageContext.request.contextPath}/common/js/uploadTool.js"></script>
 <script src="${pageContext.request.contextPath}/common/js/HuploadTool.js"></script>
 <script src="${pageContext.request.contextPath}/common/js/tplTool.js"></script>
 <script src="${pageContext.request.contextPath}/common/js/editGrid.js"></script>
 <!--全局-->
 <script src="${pageContext.request.contextPath}/common/js/whole/cyLayer.js"></script>
 <script src="${pageContext.request.contextPath}/common/js/whole/common.js"></script>
 <script src="${pageContext.request.contextPath}/common/js/whole/setting.js"></script>
 <script src="${pageContext.request.contextPath}/common/js/whole/utils.js"></script>
 <script src="${pageContext.request.contextPath}/common/js/whole/monitor.js"></script>
 <!--样式-->
 <link rel="stylesheet" href="${pageContext.request.contextPath}/common/css/cyStyle.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/common/css/cyType.css">

 <script type="text/javascript" src="${pageContext.request.contextPath}/frontframe/demo/js/list.js"></script>
 <!-- 引入 ECharts 文件 -->
 <script src="${pageContext.request.contextPath}/statics/plugins/echarts/echarts.js"></script>
</head>
<body>
 <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
 <div id="main" style="width: 900px;height:600px;"></div>
 <button class="layui-btn" onclick="Alert.alert($(Page.getElement('#test1','body')).text())">获取某个弹窗页面中的某个元素</button>
 <button class="layui-btn" onclick="closeWindow()">关闭窗口</button>
</body>
<script type="text/javascript">

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据

   var option = {
       title: {
           text: '登陆统计'
       },
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期七'],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'当前数量',
                type:'bar',
                barWidth: '60%',
                data:[8459,4845,5486,8974,6584,1536,3654]
            }
        ]
    };
    
   var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

    //关闭
    function closeWindow() {
    	parent.layer.close(index);
    }

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</html>