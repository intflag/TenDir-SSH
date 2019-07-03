<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
  <c:set var="rootPath" value="${pageContext.request.contextPath}"/>

  <script>
      var rootPath = '${rootPath}';
  </script>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="demo">
  <meta name="keywords" content="demo">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp,no-store" />
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Expires" content="0">
  <link rel="apple-touch-icon-precomposed" href="../dist/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="demo" />
  <link rel="stylesheet" href="${rootPath}/statics/amazeui/dist/amazeui.min.css"/>
  <link rel="stylesheet" href="${rootPath}/statics/css/appCount.css"/>
</head>
<body>

  <header class="am-topbar am-header">
    <div class="am-topbar-brand">
        <strong>TenDir-十方SSH快速开发框架</strong> <small>应用统计</small>
    </div>

    <div data-module="topbarcollapse" class="am-collapse am-topbar-collapse">
      <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right">
        <li><a data-type="fisrt" href="javascript:void(0);"><span class="am-icon-asterisk"></span>基础场景统计</a></li>
        <li><a data-type="second" href="javascript:void(0);"><span class="am-icon-history"></span>访问分布统计</a></li>
        <%-- <li><a  href="javascript:void(0);"><span class="am-icon-angellist"></span>应用性能</a></li> --%>
      </ul>
    </div>
  </header>

  <div class="am-content">
    <div data-module="firstlevel" class="am-animation-slide-left">
      <div class="am-cf am-padding">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">基础场景统计</strong></div>
      </div>
      <!-- <div class="am-container"> -->
          <div class="am-g">
            <div class="am-u-md-6">
              <div class="am-panel am-panel-default">
                  <div class="am-panel-hd">
                    <h4 class="am-panel-title" data-am-collapse="{ target: '#base'}">
                      系统完成度 - 基础
                    </h4>
                  </div>
                  <div id="base" class="am-panel-collapse am-collapse am-in">
                    <div class="am-panel-bd">
                       <div id="basic-main-content" render="true" ></div>
                    </div>
                  </div>
               </div>
            </div>

            <div class="am-u-md-6">
              <div class="am-panel am-panel-default">
                  <div class="am-panel-hd">
                    <h4 class="am-panel-title" data-am-collapse="{ target: '#type'}">
                      来源统计 - 分类
                    </h4>
                  </div>
                  <div id="type" class="am-panel-collapse am-collapse am-in">
                    <div class="am-panel-bd">
                       <div id="type-main-content" render="true" ></div>
                    </div>
                  </div>
               </div>
            </div>
          </div>

          <div class="am-g">
            <div class="am-u-md-6">
              <div class="am-panel am-panel-default">
                  <div class="am-panel-hd">
                    <h4 class="am-panel-title" data-am-collapse="{ target: '#statistics'}">
                      场景xxx - 统计
                    </h4>
                  </div>
                  <div id="statistics" class="am-panel-collapse am-collapse am-in">
                    <div class="am-panel-bd">
                       <div id="statistics-main-content" render="true" ></div>
                    </div>
                  </div>
               </div>
            </div>

            <div class="am-u-md-6">
              <div class="am-panel am-panel-default">
                  <div class="am-panel-hd">
                    <h4 class="am-panel-title" data-am-collapse="{ target: '#expend'}">
                      场景xxx - 扩展
                    </h4>
                  </div>
                  <div id="expend" class="am-panel-collapse am-collapse am-in">
                    <div class="am-panel-bd">
                       <div id="expend-main-content" render="true" ></div>
                    </div>
                  </div>
               </div>
            </div>
          </div>
      <hr/>
      <div class="am-g">
        <div class="am-u-md-12">
          <div class="am-panel am-panel-default">
              <div class="am-panel-hd">
                <h4 class="am-panel-title" data-am-collapse="{ target: '#combo'}">
                  场景xxx - 组合报表
                </h4>
              </div>
              <div id="combo" class="am-panel-collapse am-collapse am-in">
                <div class="am-panel-bd">
                   <div id="combo-main-content"  render="true"></div>
                   <hr />
                   <div id="combo-main-echarts"  render="true" ></div>
                </div>
              </div>
           </div>
        </div>
      </div>
    </div>
    <!--am-hide -->
    <div data-module="secondlevel" class="am-animation-slide-right am-hide">
      <div class="am-cf am-padding">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">细节报表 - </strong><small>场景xxx</small></div>
      </div>

      <div class="am-g">
        <div class="am-u-md-12">
          <div class="am-panel am-panel-default">
              <div class="am-panel-hd">
                <h4 class="am-panel-title" data-am-collapse="{ target: '#detail'}">
                  细节xxxx
                </h4>
              </div>
              <div id="detail" class="am-panel-collapse am-collapse am-in">
                <div class="am-panel-bd">
                   <div id="detail-main-content"  render="true"></div>
                   <hr />
                   <div id="detail-main-echarts"  render="true" ></div>
                </div>
              </div>
           </div>
        </div>
      </div>

    </div>
  </div>

  <script src="${rootPath}/statics/amazeui/dist/jquery.min.js"></script>
  <script src="${rootPath}/statics/amazeui/dist/amazeui.min.js"></script>
  <script src="${rootPath}/statics/amazeui/dist/echarts.min.js"></script>
  <script src="${rootPath}/statics/amazeui/dist/t3.min.js"></script>
  <script src="${rootPath}/statics/js/count/appCount/appCount.js"></script>
  <script type="text/javascript">
    Box.Application.init();
  </script>
</body>
</html>