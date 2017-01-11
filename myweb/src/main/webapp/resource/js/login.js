 var local = window.location;
 var contextPath = local.pathname.split("/")[1];
 var basePath = local.protocol + "//" + local.host + "/" + contextPath + "/";
$('#login').on('click',function (){

	console.log(basePath + "user/login.do");
	var userName = $('#emailaddr').val();
	var passwd = $('#passwd').val();
    $.ajax({
        url: basePath + "user/login.do",
        type: 'POST',
        data: {"userName": userName, "passwd": passwd},
        dataType: 'json',
        success: function (data) {
        	alert(data);
            if(data.result == "success"){
                window.location.href = basePath + "main.do";
            }else {
                alert("用户名或密码错误！");
            }
        },
        error: function (data) {//服务器响应失败处理函数
            window.location.href = basePath + "login.do";
        }
    })


});
