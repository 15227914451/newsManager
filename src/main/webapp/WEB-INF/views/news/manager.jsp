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
 <link rel="stylesheet" type="text/css" href="/static/js/jquery-easyui-1.9.4/themes/default/easyui.css">
<!--  <link rel="stylesheet" type="text/css" href="/static/js/jquery-easyui-1.9.4/themes/icon.css">
 <link rel="stylesheet" type="text/css" href="/static/js/jquery-easyui-1.9.4/themes/color.css">
 <link rel="stylesheet" type="text/css" href="/static/js/jquery-easyui-1.9.4/demo/demo.css"> -->
 <script type="text/javascript" src="/static/js/jquery-3.2.1.min.js"></script>
 <script type="text/javascript" src="/static/js/jquery-easyui-1.9.4/jquery.easyui.min.js"></script>
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
    <div class="row">
      <div class="col-sm-2">
        <div class="hidden-xs list-group side-bar">
          <a onclick="getMessage('message')" class="list-group-item active" id="message">个人信息</a>
          <a onclick="getMessage('comment')" class="list-group-item" id="comment">评论的新闻</a>
          <a onclick="getMessage('collection')" class="list-group-item" id="collection">收藏的新闻</a>
          <a onclick="getMessage('invitation')" class="list-group-item" id ="invitation">帖子中心</a>
        </div>
      </div>
      <div class="col-sm-8">
      <div class="news-list" id=contntMessage >
	      <h3>用户信息</h3>
	     <div  style="width:100%">
	        <form id ="userInfo" method="post" novalidate style="margin:0;padding:20px 50px">
	        	<table >
	        		<tr>
	        			<td style="width:300px">
				            <div style="margin-bottom:10px">
				                <input name="id" class="easyui-textbox" required="true" label="用户d:"  style="width:100%" readonly="readonly" >
				            </div>
			            </td>
			            <td style="width:300px">
				            <div style="margin-bottom:10px ">
				                <input name="userName" class="easyui-textbox"  label="用户昵称:"  style="width:100%">
				            </div>
			            </td>
			         </tr>
			          <tr>
	        			<td style="width:300px">
				            <div style="margin-bottom:10px">
				                <input name="mobile" class="easyui-textbox" required="true" label="用户手机号:" style="width:100%"  readonly="readonly">
				            </div>
			            </td>
			            <td style="width:300px">
			            	<div style="margin-bottom:10px">
				                <input name="grade" class="easyui-textbox" required="true" label="等级:" style="width:100%" eadonly="readonly">
				            </div>
				            
			            </td>
			         </tr>
			         <tr>
	        			<td style="width:300px">
				            <div style="margin-bottom:10px ">
				                <input name="state" class="easyui-textbox" required="true" label="禁言状态" style="width:100%"  readonly="readonly">
				            </div>
			            </td>
			            <td style="width:300px">
				            <div style="margin-bottom:10px ">
				                <input name="audienceReason" class="easyui-textbox"  label="禁言理由:" style="width:100%"   readonly="readonly">
				            </div>
			            </td>
			         </tr>
			         <tr>
	        			<td style="width:300px">
				            <div style="margin-bottom:10px ">
				                <input name="score" class="easyui-textbox" required="true" label="积分" style="width:100%"  readonly="readonly">
				            </div>
			            </td>
			            <td style="width:300px">
				            <div style="margin-bottom:10px ">
				                <input name="createTime" class="easyui-textbox" required="true" label="创建时间:" style="width:100%"  readonly="readonly">
				            </div>
			            </td>
			         </tr>
			        
	        	</table>
	        </form>
	      </div>
	     <div id="saveButton"  style="display:none">
	        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="save()" style="width:90px">保存</a>
	    </div>
    </div>
     <!-- 评论 -->
     <div class="news-list" id="commentNews" >
	   <form>
	           <table id="contntCenterList">
	           </table>
	 
	   </form>
     </div>
     <!-- 收藏 -->
     <div class="news-list" id="collectionNews" >
	   <form>
	           <table id="newsCollectionList">
	           </table>
	 
	   </form>
     </div>
     <!-- 帖子 -->
     <div class="news-list" id="invitationNews" >
	   <form>
	           <table id="newsInvitationList">
	           </table>
	 
	   </form>
     </div>
    <!--  弹窗 -->
     <div id="dlg" class="easyui-dialog" style="width:400px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
        <form id="fm" method="post" novalidate style="margin:0;padding:20px 50px">
            <h3>发布帖子</h3>
            <input name="id" style="display:none">
            <div style="margin-bottom:10px">
                <input name="content" class="easyui-textbox" style="width:250px;height:100px" data-options="multiline:true" required="true" label="帖子内容:" style="width:100%">
            </div>
	        <div id="invitationButton" style="display:none">
		        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveInvitation()" style="width:90px">发布</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
	    	</div>
        </form>
    </div>
     
	</div>
   </div>
  </div>
  
  <div class="footer">
   		京公网安备
  </div>
</body>
<script type="text/javascript">
		$(function () {
			$(".news-list").hide();
			$("#contntMessage").show();
			$("#saveButton").show();
			 var params = {};
	    	   params["param"] = "message";
	    		 $.ajax({
	    	        type: "POST",//方法类型
	    	        contentType : "application/json",
	    	        dataType: "json",//预期服务器返回的数据类型
	    	        url: "/news/getUserMessage" ,//url
	    	        data: JSON.stringify(params),
	    	        success: function (result) {
	    	            console.log(result);//打印服务端返回的数据(调试用)
	    	            if (result.code == "S000001") {
	    	 				 $('#userInfo').form('load',result.result);
	    	            }else{
	    	           	 alert(result.message)
	    	            }
	    	        },
	    	        error : function() {
	    	            alert("系统错误。");
	    	        }
	    	    });
			
	    		 
	    
			
		});
	 function save(){
		 var validate = $("#userInfo").form("validate");
	    	if(validate){
		    	 var formObject = {};
		         var formArray =$("#userInfo").serializeArray();
		         $.each(formArray,function(i,item){
		             formObject[item.name] = item.value;
		         });
		    	 $.ajax({
		                 type: "POST",//方法类型
		                 contentType : "application/json",
		                 dataType: "json",//预期服务器返回的数据类型
		                 url: "/news/update" ,//url
		                 data: JSON.stringify(formObject),
		                 success: function (result) {
		                     console.log(result);//打印服务端返回的数据(调试用)
		                     if (result.code == "S000001") {
		                    	 alert("修改成功");
		                     }else{
		                    	 alert(result.message)
		                     }
		                 },
		                 error : function() {
		                     alert("系统错误。");
		                 }
		             });
		       }
	    	
			
	}
       function getMessage(str){
    	   $("#saveButton").hide();
    	   $("#invitationButton").hide();
    	 if("message" == str){
    	   $("#saveButton").show();
    	   var params = {};
    	   params["param"] = str;
    		 $.ajax({
    	        type: "POST",//方法类型
    	        contentType : "application/json",
    	        dataType: "json",//预期服务器返回的数据类型
    	        url: "/news/getUserMessage" ,//url
    	        data: JSON.stringify(params),
    	        success: function (result) {
    	            console.log(result);//打印服务端返回的数据(调试用)
    	            if (result.code == "S000001") {
    	               $(".list-group-item").removeClass("active");
    	          	   $("#"+str).addClass("active");
    	          	   $(".news-list").hide();
    	 			   $("#contntMessage").show();
    	 			   if("message" == str){
    	 				 $('#userInfo').form('load',result.result);
    	 			   }
    	            }else{
    	           	 alert(result.message)
    	            }
    	        },
    	        error : function() {
    	            alert("系统错误。");
    	        }
    	    });
    	  }else if("comment" == str){
    		  conment();
    		  $(".news-list").hide();
			  $("#commentNews").show();
			  $(".list-group-item").removeClass("active");
         	  $("#"+str).addClass("active");
    		  
    	  }else if("collection" == str){
    		  collection();
    		  $(".news-list").hide();
			  $("#collectionNews").show();
			  $(".list-group-item").removeClass("active");
         	  $("#"+str).addClass("active");
    	  }else if("invitation" == str){
    		  $("#invitationButton").show();
    		  invitation();
    		  $(".news-list").hide();
			  $("#invitationNews").show();
			  $(".list-group-item").removeClass("active");
         	  $("#"+str).addClass("active");
    		  
    	  }
       }
       function conment(){
    	   //评论列表
  	     $('#contntCenterList').datagrid({
              title: '评论列表',
              iconCls: 'icon-save',
              width: window.screen.width * 0.7,
              height: window.screen.height * 0.5,
              fit: false,
              nowrap: false,
              striped: true,
              url: '/news/contentList',
              sortName: 'id',
              sortOrder: 'id',
              singleSelect: 'true',
              remoteSort: false,
              idField: 'id', 
              columns: [[
                 
                  { title: '评论id', field: 'id', width: 50},
                  { title: '新闻id', field: 'newsId', width: 50},
                  {title: '新闻标题', field: 'title', width: 300 },
                  {title: '评论内容', field: 'comment', width: 300 },
                  {title: '举报状态', field: 'report', width: 50,formatter:formatStatus},
                  {title: '评论时间', field: 'createTime', width: 50 }

              ]],
              onHeaderContextMenu: function (e, field) {
                  e.preventDefault();
                  if (!$('#tmenu').length) {
                      createColumnMenu();
                  }
                  $('#tmenu').menu('show', {
                      left: e.pageX,
                      top: e.pageY
                  });
              },
              pagination: true,
              rownumbers: true,
              toolbar: [ {
                  id: 'btncut',
                  text: '删除',
                  iconCls: 'icon-cut',
                  handler: function () {
                      $('#btnsave').linkbutton('enable');
                      var row = $('#contntCenterList').datagrid('getSelected');
                      deleteComment(row);
                  }
              }, {
                  id: 'btnedit',
                  text: '查看新闻',
                  iconCls: 'icon-edit',
                  handler: function () {
                      $('#btnsave').linkbutton('enable');
                      var row = $('#dataList').datagrid('getSelected');
                      if (row == null){
                         alert("选择编辑的数据。")
                         return;
                      }
                     if(row){
                  	   editProduct(row);
                     }
                  }
              }
              ]
          });
          var p = $('#contntCenterList').datagrid('getPager');
          if (p) {
              $(p).pagination({
                  pageSize: 10, //每页显示的记录条数，默认为10   
                  pageList: [5, 10, 15], //可以设置每页记录条数的列表   
                  beforePageText: '第', //页数文本框前显示的汉字   
                  afterPageText: '页    共 {pages} 页',
                  displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
                  onBeforeRefresh: function (pageNumber, pageSize) {
                      alert('pageNumber:' + pageNumber + ',pageSize:' + pageSize);
                      alert('before refresh');
                  }
              });
          }
       }
       function deleteComment(row){
    	   if(!confirm('确认删除吗？')){
      		 return;
      	 }
      	 var params = {};
      	 params["id"] = row.id;
      	 $.ajax({
               type: "POST",//方法类型
               contentType : "application/json",
               dataType: "json",//预期服务器返回的数据类型
               url: "/news/deleteConment" ,//url
               data: JSON.stringify(params),
               success: function (result) {
                   console.log(result);//打印服务端返回的数据(调试用)
                   if (result.code == "S000001") {
                  	 $('#contntCenterList').datagrid('reload');
                   }else{
                  	 alert(result.message)
                   }
               },
               error : function() {
                   alert("系统错误。");
               }
           });
       }
       function deleteCollection(row){
    	   if(!confirm('确认删除吗？')){
      		 return;
      	 }
      	 var params = {};
      	 params["id"] = row.id;
      	 $.ajax({
               type: "POST",//方法类型
               contentType : "application/json",
               dataType: "json",//预期服务器返回的数据类型
               url: "/news/deleteCollection" ,//url
               data: JSON.stringify(params),
               success: function (result) {
                   console.log(result);//打印服务端返回的数据(调试用)
                   if (result.code == "S000001") {
                  	 $('#newsCollectionList').datagrid('reload');
                   }else{
                  	 alert(result.message)
                   }
               },
               error : function() {
                   alert("系统错误。");
               }
           });
       }
       function formatStatus(val,row,index){
       	var html ="";
       	if(row.report == 0){
       		html = '未举报';
       	}else{
       		html = '被举报';
       	}
       
       	return html;
      }
       
       function collection(){
    	   //收藏列表
  	     $('#newsCollectionList').datagrid({
              title: '收藏列表',
              iconCls: 'icon-save',
              width: window.screen.width * 0.7,
              height: window.screen.height * 0.5,
              fit: false,
              nowrap: false,
              striped: true,
              url: '/news/collectionList',
              sortName: 'id',
              sortOrder: 'id',
              singleSelect: 'true',
              remoteSort: false,
              idField: 'id', 
              columns: [[
                 
                  { title: '收藏id', field: 'id', width: 100},
                  { title: '新闻id', field: 'newsId', width: 100},
                  {title: '新闻标题', field: 'title', width: 100 },
                  {title: '收藏时间', field: 'createTime', width: 100 }

              ]],
              onHeaderContextMenu: function (e, field) {
                  e.preventDefault();
                  if (!$('#tmenu').length) {
                      createColumnMenu();
                  }
                  $('#tmenu').menu('show', {
                      left: e.pageX,
                      top: e.pageY
                  });
              },
              pagination: true,
              rownumbers: true,
              toolbar: [ {
                  id: 'btncut',
                  text: '删除',
                  iconCls: 'icon-cut',
                  handler: function () {
                      $('#btnsave').linkbutton('enable');
                      var row = $('#newsCollectionList').datagrid('getSelected');
                      deleteCollection(row);
                  }
              }, {
                  id: 'btnedit',
                  text: '查看新闻',
                  iconCls: 'icon-edit',
                  handler: function () {
                      $('#btnsave').linkbutton('enable');
                      var row = $('#dataList').datagrid('getSelected');
                      if (row == null){
                         alert("选择编辑的数据。")
                         return;
                      }
                     if(row){
                  	   editProduct(row);
                     }
                  }
              }
              ]
          });
          var p = $('#newsCollectionList').datagrid('getPager');
          if (p) {
              $(p).pagination({
                  pageSize: 10, //每页显示的记录条数，默认为10   
                  pageList: [5, 10, 15], //可以设置每页记录条数的列表   
                  beforePageText: '第', //页数文本框前显示的汉字   
                  afterPageText: '页    共 {pages} 页',
                  displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
                  onBeforeRefresh: function (pageNumber, pageSize) {
                      alert('pageNumber:' + pageNumber + ',pageSize:' + pageSize);
                      alert('before refresh');
                  }
              });
          }
       }
       
       function invitation(){
    	   //帖子中心
  	     $('#newsInvitationList').datagrid({
              title: '帖子中心',
              iconCls: 'icon-save',
              width: window.screen.width * 0.7,
              height: window.screen.height * 0.5,
              fit: false,
              nowrap: false,
              striped: true,
              url: '/news/invitationList',
              sortName: 'id',
              sortOrder: 'id',
              singleSelect: 'true',
              remoteSort: false,
              idField: 'id', 
              columns: [[
                 
                  { title: '帖子id', field: 'id', width: 50},
                  {title: '帖子内容', field: 'content', width: 300 },
                  {title: '创建时间', field: 'createTime', width: 50 }

              ]],
              onHeaderContextMenu: function (e, field) {
                  e.preventDefault();
                  if (!$('#tmenu').length) {
                      createColumnMenu();
                  }
                  $('#tmenu').menu('show', {
                      left: e.pageX,
                      top: e.pageY
                  });
              },
              pagination: true,
              rownumbers: true,
              toolbar: [ {
                  id: 'btnadd',
                  text: '发布帖子',
                  iconCls: 'icon-add',
                  handler: function () {
                      $('#btnsave').linkbutton('enable');
                      
                      newInvitation();
                  }
              },{
                  id: 'btncut',
                  text: '删除',
                  iconCls: 'icon-cut',
                  handler: function () {
                      $('#btnsave').linkbutton('enable');
                      var row = $('#newsInvitationList').datagrid('getSelected');
                      deleteInvitation(row);
                  }
              }, {
                  id: 'btnedit',
                  text: '编辑',
                  iconCls: 'icon-edit',
                  handler: function () {
                      $('#btnsave').linkbutton('enable');
                      var row = $('#newsInvitationList').datagrid('getSelected');
                      if (row == null){
                         alert("选择编辑的数据。")
                         return;
                      }
                     if(row){
                  	   editInvitation(row);
                     }
                  }
              }
              ]
          });
          var p = $('#newsInvitationList').datagrid('getPager');
          if (p) {
              $(p).pagination({
                  pageSize: 10, //每页显示的记录条数，默认为10   
                  pageList: [5, 10, 15], //可以设置每页记录条数的列表   
                  beforePageText: '第', //页数文本框前显示的汉字   
                  afterPageText: '页    共 {pages} 页',
                  displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
                  onBeforeRefresh: function (pageNumber, pageSize) {
                      alert('pageNumber:' + pageNumber + ',pageSize:' + pageSize);
                      alert('before refresh');
                  }
              });
          }
       }
       function newInvitation(){
    	   
    	   $('#dlg').dialog('open').dialog('center').dialog('setTitle','发布帖子');
           $('#fm').form('clear');
           url = '/product/save';
    	   
       }
       function saveInvitation(){
    	var validate = $("#fm").form("validate");
       	if(validate){
   	    	 var formObject = {};
   	         var formArray =$("#fm").serializeArray();
   	         $.each(formArray,function(i,item){
   	             formObject[item.name] = item.value;
   	         });
   	    	 $.ajax({
   	                 type: "POST",//方法类型
   	                 contentType : "application/json",
   	                 dataType: "json",//预期服务器返回的数据类型
   	                 url: "/news/saveInvitation" ,//url
   	                 data: JSON.stringify(formObject),
   	                 success: function (result) {
   	                     console.log(result);//打印服务端返回的数据(调试用)
   	                     if (result.code == "S000001") {
   	                    	 $('#dlg').dialog('close');
   	                    	 $('#newsInvitationList').datagrid('reload');
   	                     }else{
   	                    	 alert(result.message)
   	                     }
   	                 },
   	                 error : function() {
   	                     alert("系统错误。");
   	                 }
   	             });
   	       }
    	   
       }
       
       function  editInvitation(row){
           
           if (row){
               $('#dlg').dialog('open').dialog('center').dialog('setTitle','编辑帖子');
               $('#fm').form('load',row);
               url = "/product/update";
           }
       }
       function deleteInvitation(row){
    	   if(!confirm('确认删除吗？')){
        		 return;
        	 }
        	 var params = {};
        	 params["id"] = row.id;
        	 $.ajax({
                 type: "POST",//方法类型
                 contentType : "application/json",
                 dataType: "json",//预期服务器返回的数据类型
                 url: "/news/deleteInvitation" ,//url
                 data: JSON.stringify(params),
                 success: function (result) {
                     console.log(result);//打印服务端返回的数据(调试用)
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
       }
</script>
<!--

//-->
</script>
</html>
