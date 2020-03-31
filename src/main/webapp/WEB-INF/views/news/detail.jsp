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
          <li class="active"><a href="#">首页</a></li>
          
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
    <div class="col-md-8">
      <h1 class="news-title">${news.newsTitle}</h1>
      <div class="news-status">${news.clickRate}查看¸发布时间:${news.createTime}
      
      </div>
      <div class="news-content">
        <blockquote>
          ${news.newsContent}
      </div>
    </div>
    <div class="col-md-4">
      <div class="side-bar-card">
        <div class="card-title">ç¸å³æ¨è</div>
        <div class="card-body">
          <div class="list">
            <div class="item clearfix">
              <div class="col-xs-5 no-padding-h">
                <img src="https://dummyimage.com/1000x700/666/ccc">
              </div>
              <div class="col-xs-7">
                <div class="title">Quam dolorem, accusamus autem suscipit dolor esse.</div>
                <div class="desc">15kéè¯» 1kè¯è®º</div>
              </div>
            </div>
            <div class="item clearfix">
              <div class="col-xs-5 no-padding-h">
                <img src="https://dummyimage.com/1000x700/666/ccc">
              </div>
              <div class="col-xs-7">
                <div class="title">Quam dolorem, accusamus autem suscipit dolor esse.</div>
                <div class="desc">15kéè¯» 1kè¯è®º</div>
              </div>
            </div>
            <div class="item clearfix">
              <div class="col-xs-5 no-padding-h">
                <img src="https://dummyimage.com/1000x700/666/ccc">
              </div>
              <div class="col-xs-7">
                <div class="title">Quam dolorem, accusamus autem suscipit dolor esse.</div>
                <div class="desc">15kéè¯» 1kè¯è®º</div>
              </div>
            </div>
            <div class="item clearfix">
              <div class="col-xs-5 no-padding-h">
                <img src="https://dummyimage.com/1000x700/666/ccc">
              </div>
              <div class="col-xs-7">
                <div class="title">Quam dolorem, accusamus autem suscipit dolor esse.</div>
                <div class="desc">15kéè¯» 1kè¯è®º</div>
              </div>
            </div>
            <div class="item clearfix">
              <div class="col-xs-5 no-padding-h">
                <img src="https://dummyimage.com/1000x700/666/ccc">
              </div>
              <div class="col-xs-7">
                <div class="title">Quam dolorem, accusamus autem suscipit dolor esse.</div>
                <div class="desc">15kéè¯» 1kè¯è®º</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="side-bar-card">
        <div class="card-title">24å°æ¶ç­é»</div>
        <div class="card-body">
          <div class="list">
            <div class="item">
              <div class="title">Lorem ipsum dolor sit amet, consectetur adipisicing </div>
              <div class="desc">15kéè¯» 1kè¯è®º</div>
            </div>
            <div class="item">
              <div class="title">Lorem ipsum dolor sit amet, consectetur adipisicing </div>
              <div class="desc">15kéè¯» 1kè¯è®º</div>
            </div>
            <div class="item">
              <div class="title">Lorem ipsum dolor sit amet, consectetur adipisicing </div>
              <div class="desc">15kéè¯» 1kè¯è®º</div>
            </div>
            <div class="item">
              <div class="title">Lorem ipsum dolor sit amet, consectetur adipisicing </div>
              <div class="desc">15kéè¯» 1kè¯è®º</div>
            </div>
            <div class="item">
              <div class="title">Lorem ipsum dolor sit amet, consectetur adipisicing </div>
              <div class="desc">15kéè¯» 1kè¯è®º</div>
            </div>
            <div class="item">
              <div class="title">Lorem ipsum dolor sit amet, consectetur adipisicing </div>
              <div class="desc">15kéè¯» 1kè¯è®º</div>
            </div>
            <div class="item">
              <div class="title">Lorem ipsum dolor sit amet, consectetur adipisicing </div>
              <div class="desc">15kéè¯» 1kè¯è®º</div>
            </div>
            <div class="item">
              <div class="title">Lorem ipsum dolor sit amet, consectetur adipisicing </div>
              <div class="desc">15kéè¯» 1kè¯è®º</div>
            </div>
            <div class="item">
              <div class="title">Lorem ipsum dolor sit amet, consectetur adipisicing </div>
              <div class="desc">15kéè¯» 1kè¯è®º</div>
            </div>
            <div class="item">
              <div class="title">Lorem ipsum dolor sit amet, consectetur adipisicing </div>
              <div class="desc">15kéè¯» 1kè¯è®º</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  
</body>
</html>
