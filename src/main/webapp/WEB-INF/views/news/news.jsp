<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
          <li class="active"><a href="/news/">首页</a></li>
         
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
    <div class="row">
      <div class="col-sm-2">
        <div class="hidden-xs list-group side-bar">
        	<a onclick="getNews('category0')" class="list-group-item active">综合</a>
        	<c:forEach items="${categoryList}" var="category" varStatus="vs">
	 				
			          <a onclick="getNews('category${category.id}')" id= "category${category.id}" class="list-group-item">${category.categoryName}</a>
			          
			 </c:forEach>
        </div>
      </div>
      <div class="col-sm-7">
        <div class="news-list" id="newsList">
          	<c:forEach items="${dataPage.data}" var="news" varStatus="vs">
	          <div class="news-list-item clearfix">
	            <div class="col-xs-7">
	              <a onclick="detail('${news.id}')" class="title">${news.newsTitle}</a>
	              <div class="info">
	                <span></span>${news.newsAuthor}</span> ⋅
	                <span>${news.clickRate}点击量</span> ⋅
	                <span>${news.createTime}</span>
	              </div>
	            </div>
	          </div>
	         </c:forEach> 
	       
        <ul class="pagination pagination-lg">
			<c:if test="${dataPage.totalCount > 0}">
		 								<c:choose>
		 									<c:when test="${dataPage.pageNo == 1}">
		 										<a style="text-decoration: none">首页</a>    
		 										<a style="text-decoration: none">上一页</a>    
		 									</c:when>
		 									<c:otherwise>
		 										<a href="javascript:doPage(1);">首页</a>    
		 										<a href="javascript:doPage(${dataPage.upPage});">上一页</a>    
		 									</c:otherwise>
		 								</c:choose>
		 								<c:choose>
		 									<c:when test="${dataPage.pageNo == dataPage.totalPage || dataPage.totalPage == 0}">
		 										<a style="text-decoration: none">下一页</a>    
		 										<a style="text-decoration: none">末页</a>    
		 									</c:when>
		 									<c:otherwise>
		 										<a href="javascript:doPage(${dataPage.nextPage});">下一页</a>    
		 										<a href="javascript:doPage(${dataPage.totalPage});">末页</a>    
		 									</c:otherwise>
		 								</c:choose>
									
									<a style="text-decoration: none">当前第${dataPage.pageNo}页</a>    
									<a style="text-decoration: none">共${dataPage.totalPage}页</a>    
			</c:if>
									<a style="text-decoration: none">共${dataPage.totalCount}条数据</a>    

			</ul>
        
      </div>
      
      
    </div>
   <div class="col-sm-3">
        <div>
          <span>热点帖子</span>&nbsp;&nbsp;&nbsp;&nbsp; <a href="/news/invitationLists">查看更多</a>
        </div>
        <c:forEach items="${listInvitation}" var="invitation" varStatus="in">
	        <div class="side-bar-card">
	          <div class="card-body">
	            <div class="list">
	              <div class="item">
	                <div class="title"><a onclick="invitationDetail('${invitation.id}')">${invitation.invitationContent}<a></div>
	                <div class="desc">${invitation.rate}阅读 </div>
	              </div>
	            </div>
	          </div>
	        </div>
	     </c:forEach>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
	function getNews(id){
	   var params = {};
 	   params["param"] = id;
 		 $.ajax({
 	        type: "POST",//方法类型
 	        contentType : "application/json",
 	        dataType: "html",//预期服务器返回的数据类型
 	        url: "/news/getNewsList" ,//url
 	        data: JSON.stringify(params),
 	        success: function (result) {
 	             $(".list-group-item").removeClass("active");
 	          	 $("#"+id).addClass("active");
 	          	 $("#newsList").html("");
 	          	 $("#newsList").html(result);
 	        },
 	        error : function() {
 	            alert("系统错误。");
 	        }
 	    });
	}
	function detail(id){
		window.location.href= "/news/newsDetail?id="+id;
	}
	function invitationDetail(id){
		window.location.href= "/news/invitationDetail?id="+id;
	}
</script>
</html>
