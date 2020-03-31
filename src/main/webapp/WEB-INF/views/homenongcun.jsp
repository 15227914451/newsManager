<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>admin</title>
</head>
<style type="text/css">
    *{
        margin: 0;
        padding: 0;
    }
    body{
        width: 100%;
 
    }
    a{
     text-decoration: none;
      color: #f5fff5;
    }
    .contain{
        margin-left: 1%;
        width: 98%;
        border-left-width: 5px;
        border-right-width: 5px;
        border-top-width: 0px;
        height: 830px;
    }
    /*顶部*/
    .top{
        padding-top: 10px;
        width: 100%;
        height: 40px;
    }
    .system{
        font-size: 30px;
        font-family: 华文新魏;
        float: left;
        color: #0ca0ff;
    }
    .admin{
        float: right;
        margin-right: 2%;
        color: #37080a;
    }
 
    /*中间内容区*/
    .content{
        height: 820px;
        background-color: #daffea;
        width: 100%;
    }
    .menu{
        width: 12%;
        float: left;
        height: 800px;
        background-color: #31372d;
    }
    .headimg_name{
        margin-top: 10px;
        text-align: center;
        font-size: 20px;
        color: #f5fff5;
    }
    .headimg{
        margin-top: 100px;
        margin-left: 25%;
        border-radius: 50px;
    }
    .address{
        margin-top: 5px;
        text-align: center;
        font-size: 10px;
        color: rgba(255, 252, 229, 0.67);
    }
    .exit{
        padding-top: 2px;
        margin-top: 10px;
        display: block;
        height: 25px;
        width: 40%;
        margin-left: 30%;
        margin-right: 30%;
        text-align: center;
        font-size: 15px;
        border-radius: 15px;
        color: #f5fff5;
        border-style: solid;
        border-width: 1px;
        border-color: #75ffdf;
    }
    .exit:hover{
        color: #75ffdf;
    }
    .menu_ul{
        margin-top: 50px;
        color: #f5fff5;
    }
    img{
        border-radius: 50px;
    }
    li{
        display: block;
        list-style: none;
        height: 40px;
        border-top-width: 1px;
        border-bottom-width: 0px;
        border-left-width: 0px;
        border-right-width: 0px;
        border-style: solid;
        padding-top: 15px;
    }
    .lastli{
        border-bottom-width: 1px;
    }
    li:hover{
        color: #75ffdf;
        border-left-width: 1px;
    }
    
    
    /*右侧内容部分*/
    #right_content{
        width: 88%;
        float: left;
        height: 840px;
    }
    </style>
<body>
  <div class="contain">
      <div class="top"><div class="system">农村特色产品销售系统</div><div class="admin"><strong>欢迎您！${currentUser }</strong><a href="/RuralSales/adminexit">退出登录</a></div></div>
      <div class="content">
          <div class="menu">
              <!-- <div class="exit"><a href="/RuralSales/adminexit">退出登录</a></div> -->
              <div class="menu_ul">
                  <ul>
                      <li οnclick="goodstype()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商品类型管理</li>
                      <li οnclick="shop()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商户管理</li>
                      <li οnclick="user()" class="lastli">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;消费者管理</li>
                      <li οnclick="user()" class="lastli">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;购物车管理</li>
                      <li οnclick="user()" class="lastli">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订单管理</li>
                  </ul>
              </div>
          </div>
          <div id="right_content">
 
          </div>
      </div>
  </div>
  <script type="text/javascript">
//创建访问对象
  function createXMLHTTPObject(){
      var XMLHttpFactories=[
       function () {
           return new XMLHttpRequest();
       },
       function () {
           return new ActiveXObject("Msxml2.XMLHTTP");
       },
       function(){
          return new ActiveXObject("Msxml3.XMLHTTP");
       },
       function () {
           return new ActiveXObject("Microsoft.XMLHTTP");
       }
      ];
      var xmlhttp=false;
      for(var i=0;i<XMLHttpFactories.length;i++) {
      	try{
      		xmlhttp=XMLHttpFactories[i]();
      	}catch (e) {
				continue;
			}
      	break;
      }
      return xmlhttp;       //返回是对象实例
  }
  function goodstype() {
      var x=createXMLHTTPObject();
      var url="/RuralSales/querytype";
      x.open("GET",url,true);
      x.onreadystatechange=function () {
          if(x.readyState==4&&x.status==200){
              var o=document.getElementById("right_content");
           
              o.innerHTML=x.responseText;
          }
      }
      x.send(null);
  }
  function shop() {
	  var x=createXMLHTTPObject();
      var url="/RuralSales/queryshop";
      x.open("GET",url,true);
      x.onreadystatechange=function () {
          if(x.readyState==4&&x.status==200){
              var o=document.getElementById("right_content");
              o.innerHTML=x.responseText;
          }
      }
      x.send(null);
  }
  function user() {
	  var x=createXMLHTTPObject();
      var url="/RuralSales/queryuser";
      x.open("GET",url,true);
      x.onreadystatechange=function () {
          if(x.readyState==4&&x.status==200){
              var o=document.getElementById("right_content");
              o.innerHTML=x.responseText;
          }
      }
      x.send(null);
}
  </script>
</body>
</html>