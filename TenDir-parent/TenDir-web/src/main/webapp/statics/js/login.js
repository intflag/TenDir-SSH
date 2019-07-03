layui.config({
    base: "js/"
}).use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;
    //登录按钮事件
    form.on("submit(login)", function (data) {
    	//var rootPath = getRootPath();
        //parent.location.href = rootPath+'/index.jsp';
        // var datas = "username=" + data.field.username + "&password=" + data.field.password + "&captcha=" + data.field.captcha;
        // $.ajax({
        //     type: "POST",
        //     url: "/sys/login",
        //     data: datas,
        //     dataType: "json",
        //     success: function (result) {
        //         if (result.code == 0) {//登录成功
        //             parent.location.href = '/index.html';
        //         } else {
        //             layer.msg(result.msg, {icon: 5});
        //             refreshCode();
        //         }
        //     }
        // });
        return true;
    })
});
function refreshCode(){
    var captcha = document.getElementById("captcha");
    captcha.src = "/captcha.jpg?t=" + new Date().getTime();
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
