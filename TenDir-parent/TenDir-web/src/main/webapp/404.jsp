<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>404</title>
<!--<meta http-equiv="refresh" content="3;url=http://localhost:8085/index.html">-->
<!-- content="3，即3秒后返回主页，可根据需要修改或者删除这段代码 -->
<link href="${pageContext.request.contextPath}/statics/css/error.css" rel="stylesheet" type="text/css" />
</head>
<body>

<!-- 代码 开始 -->
<div id="container"><img class="png" src="${pageContext.request.contextPath}/statics/img/404/404.png" /> <img class="png msg" src="${pageContext.request.contextPath}/statics/img/404/404_msg.png" />
  <p><a href="${pageContext.request.contextPath}/index.jsp" target="_blank"><img class="png" src="${pageContext.request.contextPath}/statics/img/404/404_to_index.png" /></a> </p>
</div>
<div id="cloud" class="png"></div>
<!-- 代码 结束 -->

</body>
</html>