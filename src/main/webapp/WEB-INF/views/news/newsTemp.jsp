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
</head>
<body>
  
      <div class="col-sm-10">
        <div class="news-list" id="newsList">
          	<c:forEach items="${dataPage.data}" var="news" varStatus="vs">
	          <div class="news-list-item clearfix">
	            <div class="col-xs-7">
	              <a href="#" class="title">${news.newsTitle}</a>
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
      
      
      
     
</body>
</html>
