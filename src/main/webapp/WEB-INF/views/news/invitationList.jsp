<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false" %>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Document</title>
   <link rel="stylesheet" href="/static/css/bootstrap.min.css">
 <link rel="stylesheet" href="/static/css/main.css">
  <script type="text/javascript" src="/static/js/jquery-3.2.1.min.js"></script>
</head>
<body>
  <div class="navbar navbar-default">
    <div class="container">
      <div class="navbar-header">
        <a href="index.html" class="navbar-brand"></a>
      </div>
      <label id="toggle-label" class="visible-xs-inline-block" for="toggle-checkbox">MENU</label>
      <input class="hidden" id="toggle-checkbox" type="checkbox">
      <div class="hidden-xs">
        <ul class="nav navbar-nav">
          <li class="active"><a href="/news">首页</a></li>
          
        </ul>
        <ul class="nav navbar-nav navbar-right">
        
         
          <c:if test="${!empty userInfo}">
	          <li><a href="/news/manager">您好：${userInfo.mobile}</a></li>
	      </c:if>
		  <c:if test="${empty userInfo}">
		  	  <li><a href="/news/login">登录</a></li>
	          <li><a href="/news/signup">注册</a></li>
		  </c:if>
        </ul>
      </div>
    </div>
  </div>
  <div class="container">
    <div class="col-md-10">
       <c:forEach items="${invitationList}" var="invitation" varStatus="in">
	      <input type = "hidden" id="conmentId"  value="${invitation.id}">
	      <div class="news-status">${invitation.rate}查看.发布时间:${invitation.createTime}
	      
	      </div>
	      <div class="news-content">
	        <blockquote>
	          <a href = "/news/invitationDetail?id=${invitation.id}">${invitation.invitationContent}</a>
	      </div>
	       <div style="border-top:1px solid #000"></div>
     	</c:forEach>
    </div>
    <div class="col-md-2">
        <div>
          <span>热点新闻</span>&nbsp;&nbsp;&nbsp;&nbsp; <a href="/news">返回首页</a>
        </div>
        <c:forEach items="${listNews}" var="newsHot" varStatus="in">
	        <div class="side-bar-card">
	          <div class="card-body">
	            <div class="list">
	              <div class="item">
	                <div class="title"><a onclick="invitationDetail('${newsHot.id}')">${newsHot.newsTitle}<a></div>
	                <div class="desc"> ${news.newsAuthor}⋅
	                ${newsHot.clickRate}阅读⋅
	                ${newsHot.createTime}</div>
	              </div>
	            </div>
	          </div>
	        </div>
	     </c:forEach>
	  </div>
 </div>
</body>
<script type="text/javascript">
	function contentSubmit(){
		var content = $("#content").val();
		var newsId = $("#newsId").val();
		var params = {};
   	   
		if(content != null && content != ""){
			params["content"] = content;
			params["newsId"] = newsId;
			$.ajax({
                type: "POST",//方法类型
                contentType : "application/json",
                dataType: "json",//预期服务器返回的数据类型
                url: "/news/saveNewsComment" ,//url
                data: JSON.stringify(params),
                success: function (result) {
                   
                    if (result.code == "S000001") {
                   	 $('#newsInvitationList').datagrid('reload');
                    }else{
                   	 alert(result.message)
                    }
                },
                error : function() {
                    alert("系统错误。");
                }
            });
		}else{
			alert("评论内容为空!");
		}
	}
	function report(id){
		var params = {};
		params["id"] = id;
		$.ajax({
            type: "POST",//方法类型
            contentType : "application/json",
            dataType: "json",//预期服务器返回的数据类型
            url: "/news/reportContent" ,//url
            data: JSON.stringify(params),
            success: function (result) {
               
                if (result.code == "S000001") {
               	 alert("举报成功");
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
