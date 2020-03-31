<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--  <link rel="stylesheet" href="/static/css/main.css">
 <script src="/static/js/jquery-3.2.1.min.js"></script>
 <link rel="stylesheet" href="/static/css/bootstrap.min.css">
 <script src="/static/js/bootstrap.min.js"></script> -->
 <link rel="stylesheet" href="/static/css/bootstrap.min.css">
 <link rel="stylesheet" href="/static/css/main.css">
  <script src="/static/js/import-iframe.js"></script>
 <script src="/static/js/jquery-3.2.1.min.js"></script>
</head>
<!-- <body> -->
	    <!-- <nav class="navbar navbar-default">
		  <div class="container-fluid">
		    Brand and toggle get grouped for better mobile display
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="index.html"></a>
		    </div>
		
		    Collect the nav links, forms, and other content for toggling
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav">
		        <li class="active" style="padding-bottom: 15px;"><a href="index.html">首页<span class="sr-only">(current)</span></a></li>
		        <li><a href="#">国内</a></li>
		          <li><a href="#">数读</a></li>
		          <li><a href="#">社会</a></li>
		      </ul>
		      <ul class="nav navbar-nav navbar-right">
		        <li><a href="login.html">登陆</a></li>
		        <li><a href="signup.html">注册</a></li>
		      </ul>
		    </div>/.navbar-collapse
		  </div>/.container-fluid
		</nav> -->
<body >
  <div class="container container-small login" style="width:300px">
    <h1>登录
    </h1>
    <form >
      <div class="form-group">
        <label>手机</label>
        <input type="text" name="mobile" id="mobile" class="form-control username-text">
      </div>
      
      <div class="form-group">
        <label>密码</label>
        <input type="password" name="password" id="password" class="form-control username-text">
      </div>
      <div class="form-group">
      		<button  type="button" name="Submit" onclick = "login()">登录</button>
      </div>
</body>
<script type="text/javascript">
function login(){
	var mobile = $("#mobile").val();
	var password = $("#password").val();
	
	if(mobile =="" || mobile == null){
			alert("输入手机号")	;
			return false;
	}
	if(password == "" || password == null){
			alert("输入密码")	;
			return false;
	}
	 var params = {};
	 params["mobile"] = mobile;
	 params["password"] = password;
	 $.ajax({
        type: "POST",//方法类型
        contentType : "application/json",
        dataType: "json",//预期服务器返回的数据类型
        url: "/news/loginByMobile" ,//url
        data: JSON.stringify(params),
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.code == "S000001") {
            	window.location.href="/news/manager";
            }else{
           	 alert(result.message)
            }
        },
        error : function() {
            alert("系统错误。");
        }
    });
}

</script>
</html>