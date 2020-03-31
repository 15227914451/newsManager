<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="static/js/jquery-3.2.1.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body bgcolor="#F4F4F4">
	<div style="padding:200px 100px 10px 100px">
	    <form id="form1" name="form1" action="home" method = "post">  
	        <p align="center">用户登录</p>  
	        <table width="296" align="center">  
	            <tr>  
	                <td width="98" height="34" align="right">用户名：</td>  
	                <td width="182">
	                	<label>
	                		<input name="loginName" type="text" id="loginName" />
	                	</label>
	                </td>  
	            </tr>  
	  
	            <tr>  
	                <td height="36"  align="right">密码：</td>  
	                <td>
	                	<label>
	                		<input name="password" type="password" id="password" />
	                	</label>
	                </td>  
	            </tr>  
	            <tr>  
	                <td height="35" colspan="2" align="center">
	                	<label>        
	                        <input type="button" name="Submit" onclick="login()"  value="提交" />  
	                	</label> 
	                	<label>
	                		<input type="reset" name="Submit2" value="重置" />  
	                	</label>
	                </td>  
	            </tr>  
	        </table>  
	    </form>  	
	</div>
</body>
<script type="text/javascript">
		if (window != top) 
			top.location.href = location.href; 
        function login() {
        	 $.ajax({
                 method : 'post',
                 url : '/home/login',
                 dateType:'json',
                 data : $('#form1').serializeArray(),
                 success : function(data) {
                	 console.log(data);
                	 var result = JSON.parse(data);
                	 console.log("data.code =" +  result.code);
                     if(result.code == "S000001"){
                    	 window.location.href="/index";
                     }else{
                    	 alert(result.message)
                     }
                 }
             });

        } 
      
     </script>
</html>