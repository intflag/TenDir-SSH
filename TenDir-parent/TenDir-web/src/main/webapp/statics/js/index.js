/**
 * Created by chenyi on 2018/1/23.
 */
//全屏
function fullScreen() {
	var docElm = document.documentElement;
	//W3C  
	if(docElm.requestFullscreen) {
		docElm.requestFullscreen();
	}
	//FireFox  
	else if(docElm.mozRequestFullScreen) {
		docElm.mozRequestFullScreen();
	}
	//Chrome等  
	else if(docElm.webkitRequestFullScreen) {
		docElm.webkitRequestFullScreen();
	}
	//IE11
	else if(elem.msRequestFullscreen) {
		elem.msRequestFullscreen();
	}
	layer.msg('按Esc即可退出全屏');
}
// 判断是否显示锁屏
if(window.sessionStorage.getItem("isLock") == "true"){
    lockPage();
}
//锁屏
function lockScreen(){
    window.sessionStorage.setItem("isLock",true);
    lockPage();
}
function lockPage() {
	var rootPath = getRootPath();
    layer.open({
        title : false,
        area: ['1980', '1080'],
        type : 1,
        content : '<video class="video-player" preload="auto" autoplay="autoplay" loop="loop" data-height="1080" data-width="1980px" height="1080" width="1980px"> ' +
        '<source src="'+rootPath+'/statics/login/login.mp4" type="video/mp4"> </video>' +
        '<div class="lock-content"><div class="admin-header-lock" id="lock-box">'+
        '<div class="admin-header-lock-img"><img src="'+rootPath+'/statics/images/index/head.jpg"/></div>'+
        '<div class="admin-header-lock-name" id="lockUserName">TenDir快速开发框架</div>'+
        '<div class="input_btn">'+
        '<input type="password" class="admin-header-lock-input layui-input" autocomplete="off" placeholder="请输入密码解锁.." name="lockPwd" id="lockPwd" />'+
        '<button class="layui-btn" id="unlock" style="background-color: #42bdf1">解锁</button>'+
        '</div>'+
        '</div></div>',
        closeBtn : 0,
        shade : 0.9
    })
    $(".admin-header-lock-input").focus();
}

function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    if (webName == "") {
        return window.location.protocol + '//' + window.location.host;
    }
    else {
        return window.location.protocol + '//' + window.location.host + '/' + webName;
    }
}

// 解锁
$("body").on("click","#unlock",function(){
	var rootPath = getRootPath();
	var password = $(this).siblings(".admin-header-lock-input").val();
    if($(this).siblings(".admin-header-lock-input").val() == ''){
        Tips.tips("请输入解锁密码123456！",$("#lockPwd"),1,'#4fcef1');
        $(this).siblings(".admin-header-lock-input").focus();
    }else{
        //验证密码是否正确
    	$.ajax({
			type:"post",
			url: rootPath+"/userAction_unlock.action?password="+password,
			success:function(R){
				if (R.res === 1) {
					window.sessionStorage.setItem("isLock",false);
		            $(this).siblings(".admin-header-lock-input").val('');
		            layer.closeAll("page");
				} else {
					Tips.tips("密码错误，请重新输入！",$("#lockPwd"),1,'#4fcef1');
		            $(this).siblings(".admin-header-lock-input").val('').focus();
				}
			},
			error:function(){
				layer.alert('请求超时！', {
        			icon: 5,
        			skin: 'layui-layer-molv' //样式类名
    			});
			}
		});
    }
});
$(document).on('keydown', function() {
    if(event.keyCode == 13) {
        $("#unlock").click();
    }
});
function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    if (webName == "") {
        return window.location.protocol + '//' + window.location.host;
    }
    else {
        return window.location.protocol + '//' + window.location.host + '/' + webName;
    }
}


//打赏作者
function reward() {
	var rootPath = getRootPath();
    layer.open({
        title: '',
        type: 1,
        area: ['600px', '450px'], //宽高
        content: '<img src="'+rootPath+'/statics/img/cy/reward.jpg" width="600" height="450">'
    });
}

$(document).ready(function () {
    //默认显示菜单
	var rootPath = getRootPath();
    createMenu(rootPath+"/resourceAction_findMenu.action");
    setMainHeight();
});
$(window).resize(function () {
    setMainHeight();
});
//设置主内容高度
function setMainHeight() {
    var height = $(parent.window).height();
    $("#main").css("height", height - 154 + "px");
}
//生成菜单
function createMenu(url) {
    $("#menuSearch").val("");
    $.getJSON(url, function (r) {
        //设置菜单缓存
    	console.info(r);
        $t.setStorageItem("menuList", r.obj);
        //显示菜单
        setMenu(r.obj);

    });
}
//显示菜单
function setMenu(menuList) {
    $(".layui-nav-tree").html("");
    for (var i = 0; i < menuList.length; i++) {
        var _li;
        console.info(menuList[i].type === "0");
        if (menuList[i].type === "0") {
            _li = ['<li class="layui-nav-item">',
                '<a class="" href="javascript:;" title="' + menuList[i].resname + '" >',
                '<i class="' + menuList[i].icon + '"></i>' + menuList[i].resname + '</a>',
                '</li>'].join("");
            //是否有下级菜单
            if (menuList[i].children) {
                var $li = $(_li);
                $li.find("a").after('<dl class="layui-nav-child">');
                for (var j = 0; j < menuList[i].children.length; j++) {
                    $li.find(".layui-nav-child").append(' <dd><a class="cy-page" href="javascript:;" data-url="' + menuList[i].children[j].resurl + '" title="' + menuList[i].children[j].resname + '">' + menuList[i].children[j].resname + '</a></dd>');
                }
            }
            _li = $li.prop("outerHTML");
        }
        if (menuList[i].type === "1") {
            _li = '<li class="layui-nav-item"><a class="layui-nav-item cy-page" href="javascript:;" data-url="' + menuList[i].resurl + '" title="' + menuList[i].resname + '"><i class="' + menuList[i].icon + '"></i> ' + menuList[i].resname + '</a></li>';
        }
        $(".layui-nav-tree").append(_li);
    }
    console.log($(".layui-nav-tree").html());

    layui.use('element', function () {
        var element = layui.element;
        element.render();
    });
}

//左侧菜单收起与显示
$(".toggle-collapse").click(function () {
    var width = $(window).width();
    if ($(this).hasClass("toggle-show")) {
        $(this).removeClass("toggle-show").animate({left: '200px'});
        $(".layui-body,.layui-footer").css("width", parseInt(width) - 200 + "px").animate({left: '200px'});
        $(".layui-side").animate({left: '0px'}).fadeIn("slow");
    } else {
        $(this).addClass("toggle-show").animate({left: '0px'});
        $(".layui-body,.layui-footer").css("width", parseInt(width) + "px").animate({left: '0px'});
        $(".layui-side").animate({left: '-200px'});
    }

});


//菜单搜索
$(" .menu-search-clear").click(function () {
    $("#menuSearch").val("");
    $(".menu-search-clear").hide()
    //显示默认菜单
    setMenu($t.getStorageItem("menuList"))
});

$("#menuSearch").keyup(function () {
    if ($("#menuSearch").val() == "") {
        $(".menu-search-clear").hide();
        //显示默认菜单
        setMenu($t.getStorageItem("menuList"))
    } else {
        $(".menu-search-clear").show();
        var menuList = $t.getStorageItem("menuList");
        //显示搜索结果菜单
        var k = $("#menuSearch").val().trim("");
        if (k == "") return;
        var arr = [];
        var patt = new RegExp(k);
        for (var i = 0; i < menuList.length; i++) {
            if (menuList[i].type === 1) {
                if (patt.test(menuList[i].name) || patt.test(menuList[i].url)) {
                    arr.push({name: menuList[i].name, url: menuList[i].url, icon: menuList[i].icon});
                }
            }
            if (menuList[i].list) {
                for (var j = 0; j < menuList[i].list.length; j++) {
                    if (menuList[i].list[j].type === 1) {
                        if (patt.test(menuList[i].list[j].name) || patt.test(menuList[i].list[j].url)) {
                            arr.push({
                                name: menuList[i].list[j].name,
                                url: menuList[i].list[j].url,
                                icon: menuList[i].list[j].icon
                            });
                        }
                    }

                }
            }
        }
        $(".layui-nav-tree").html("");
        if (arr.length > 0) {
            //渲染查询后的表格
            for (var i = 0; i < arr.length; i++) {
                $('.layui-nav-tree').append(
                    ['<li class="layui-nav-item">',
                        '<a class="layui-nav-item cy-page" href="javascript:;" ',
                        'data-url="' + arr[i].url + '" title="' + arr[i].name + '">',
                        '<i class="fa fa-pencil"></i> ' + arr[i].name + '</a></li>'].join(""));
            }
            layui.use('element', function () {
                var element = layui.element;
                element.render();

            });
        }

    }
});

function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    if (webName == "") {
        return window.location.protocol + '//' + window.location.host;
    }
    else {
        return window.location.protocol + '//' + window.location.host + '/' + webName;
    }
}