<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Basic CRUD Application - jQuery EasyUI CRUD Demo</title>
    <link rel="stylesheet" type="text/css" href="/static/js/jquery-easyui-1.9.4/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/js/jquery-easyui-1.9.4/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/static/js/jquery-easyui-1.9.4/themes/color.css">
    <link rel="stylesheet" type="text/css" href="/static/js/jquery-easyui-1.9.4/demo/demo.css">
    <script type="text/javascript" src="/static/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/static/js/jquery-easyui-1.9.4/jquery.easyui.min.js"></script>
    
    <link rel="stylesheet" href="/static/js/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="/static/js/kindeditor/plugins/code/prettify.css" />
	<script  type="text/javascript" src="/static/js/kindeditor/kindeditor-min.js"></script>
	<!-- <script src="/static/js/kindeditor/kindeditor-all.js"></script> -->
	<!-- <script src="/static/js/ckeditor/ckeditor.js"></script>
	<script src="/static/js/ckeditor/samples/js/sample.js"></script>
	<link rel="stylesheet" href="/static/js/ckeditor/samples/css/samples.css">
	<link rel="stylesheet" href="/static/js/ckeditor/samples/toolbarconfigurator/lib/codemirror/neo.css"> -->
</head>
<body>

<!--  <div class="adjoined-bottom">
										<div class="grid-container">
											<div class="grid-width-100">
												<div id="editor" name="editor">
													
												</div>
												<textarea name="editor1" id="editor1" rows="10" cols="80"></textarea>

												
											</div>
										</div>
									</div> -->
    <h2>新闻管理</h2>
    <!-- <div>
     <form id="searchForm1" class="searchForm" name="searchForm1">
                                       <table>
                                           <tbody>
		                                           <tr>
		                                               <td><label  for="id">商品名称</label></td>
		                                               <td><input name="name" class="ml-10 mr-10" type="text"></td>
		                                               <td><label  for="createTime">创建时间</label></td>
		                                               <td><input name="createTimeStart" dataPickerName="createTime" class="ml-10 mr-10 dataPickerItem dataPickerInput" type="text">-<input name="createTimeEnd" class="ml-10 mr-10 dataPickerInput" type="text"></td>
		                                               <td><label  for="operateTime">修改时间</label></td>
		                                               <td><input name="operateTimeStart" dataPickerName="operateTime" class="ml-10 mr-10 dataPickerItem dataPickerInput" type="text">-<input name="operateTimeEnd" class="ml-10 mr-10 dataPickerInput" type="text"></td>
		                                           </tr>
		                                           <tr style="height:10px;"></tr>
		                                      
                                           </tbody>
                                       </table>
                                   </form>
    </div> -->
    <div id="searchtool" style="padding: 5px">
        <span>新闻名称:</span><input name="productName" type="text" id="newsName" />
        <a href="javascript:FindData()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">
            查询</a>
     
     </div>
     <form>
            <table id="dataList">
            </table>
 
    </form>
   <!--  <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
    </div> -->
    
    <div id="dlg" class="easyui-dialog" style="width:800px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
        <form id="fm" method="post" novalidate style="margin:0;padding:20px 50px">
            <table>
            	<input type="hidden" name="id" id= "id" value=""> 
              	<tr>
              		<td>
			            <div style="margin-bottom:10px">
			                <input name="newsTitle" class="easyui-textbox" required="true" label="新闻名称:" style="width:100%">
			            </div>
			        </td>
			        <td>
			            <div style="margin-bottom:10px">
			                <input name="newsAuthor" class="easyui-textbox" required="true" label="新闻作者:" style="width:100%">
			            </div>
			         </td>
			        
			    </tr>
			  <tr>
			        <td>
			            <div style="margin-bottom:10px">
			                <input name="newsCategory" id ="newsCategory" class="easyui-combobox" required="true" label="新闻分类:"style="width:100%">
			            </div>
			         </td>
			     </tr>
			     <tr>
			        <h6>新闻内容</h6>
			        <div style="margin-bottom:10px;width: 500px;">
        					<textarea name="content" id="content"></textarea>
   					 </div> 
   					<!--  <div class="adjoined-bottom">
										<div class="grid-container">
											<div class="grid-width-100">
												<div id="editor" name="editor">
													
												</div>
												<textarea name="editor1" id="editor1" rows="10" cols="80"></textarea>

												
											</div>
										</div>
									</div> -->
		         </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveNews()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    
    
    <script type="text/javascript">
    $(function () {
    	 //var CKEDITOR_BASEPATH = '/Scripts/ckeditor/';
    	 //initSample();
    	 initKindEditor();
    	//initSample();
        $('#dataList').datagrid({
            title: '新闻列表',
            iconCls: 'icon-save',
            width: window.screen.width * 0.8,
            height: window.screen.height * 0.5,
            fit: false,
            nowrap: false,
            striped: true,
            url: '/newsManager/list',
            sortName: 'id',
            sortOrder: 'id',
            singleSelect: 'true',
            remoteSort: false,
            idField: 'id',
            columns: [[
               
                { title: '新闻id', field: 'id', width: 100},
                { title: '新闻名称', field: 'newsTitle', width: 100},
                {title: '作者', field: 'newsAuthor', width: 150 },
                {title: '所属分类', field: 'category', width: 100 },
                {title: '创建时间', field: 'createTime', width: 150 },
                {title: '修改时间', field: 'updateTime', width: 150 },
                {title: '点击量', field: 'clickRate', width: 150 }
               

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
            toolbar: [{
                id: 'btnadd',
                text: '添加',
                iconCls: 'icon-add',
                handler: function () {
                    $('#btnsave').linkbutton('enable');
                    
                    newProduct();
                }
            }, {
                id: 'btncut',
                text: '删除',
                iconCls: 'icon-cut',
                handler: function () {
                    $('#btnsave').linkbutton('enable');
                    var row = $('#dataList').datagrid('getSelected');
                    deleteNews(row);
                }
            }, {
                id: 'btnedit',
                text: '编辑',
                iconCls: 'icon-edit',
                handler: function () {
                    $('#btnsave').linkbutton('enable');
                    var row = $('#dataList').datagrid('getSelected');
                    if (row == null){
                       alert("选择编辑的数据。")
                       return;
                    }
                   if(row){
                	   editNews(row);
                   }
                }
            }
            ]
        });
        var p = $('#dataList').datagrid('getPager');
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

    });

    function createColumnMenu() {
        var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo('body');
        var fields = $('#dataList').datagrid('getColumnFields');
        for (var i = 0; i < fields.length; i++) {
            $('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
        }
        tmenu.menu({
            onClick: function (item) {
                if (item.iconCls == 'icon-ok') {
                    $('#dataList').datagrid('hideColumn', item.text);
                    tmenu.menu('setIcon', {
                        target: item.target,
                        iconCls: 'icon-empty'
                    });
                } else {
                    $('#dataList').datagrid('showColumn', item.text);
                    tmenu.menu('setIcon', {
                        target: item.target,
                        iconCls: 'icon-ok'
                    });
                }
            }
        });
    }
    var url;
    var kind;
    function newProduct(){
        $('#dlg').dialog('open').dialog('center').dialog('setTitle','新增商品');
        $('#id').val('');
        $('#newsCategory').combobox({
            url:'/category/allList',
            valueField:'id',
            textField:'text'
        });
        /* $.ajax({
            type: "POST",//方法类型
            contentType : "application/json",
            dataType: "json",//预期服务器返回的数据类型
            url: "/category/allList" ,//url
            success: function (result) {
                console.log(result);//打印服务端返回的数据(调试用)
                if (result.code == "S000001") {
                	var html = "";
                	for(j = 0; j < result.data.length; j++) {
                		 console.log(result.data[j].id);
                		 html += "<option value ="+result.data[j].id+">"+result.data[j].categoryName+"</option><br>";
                	$('#newsCategory').combobox('setValue',result.data[j].id);
                	$('#newsCategory').combobox('setText',result.data[j].categoryName); 
                	}
                	$('#newsCategory').append(html);
                }else{
               	 alert(result.message)
                }
            },
            error : function() {
                alert("系统错误。");
            }
        }); */
        
        $('#fm').form('clear');
        url = '/product/save';
    }
    function editNews(row){
        
        if (row){
            $('#dlg').dialog('open').dialog('center').dialog('setTitle','编辑新闻');
            $('#fm').form('load',row);
            url = "/product/update";
            $('#newsCategory').combobox({
                url:'/category/allList',
                valueField:'id',
                textField:'text',
                onLoadSuccess:function(){
                		$('#newsCategory').combobox('select',row.category);//菜单项可以text或者id
                }
            });
            kind.html(row.content);
        }
    }
        
    function resize() {
        $('#dataList').datagrid('resize', {
            width: 700,
            height: 400
        });
    }
    function showDate(val) {
        if (val != null) {
            val = val.replace("\/Date(", "");
            val = val.replace(")/", "");
            dt = new Date(Number(val));
            return dt.toLocaleString();
        } else {
            return "";
        }
    }
    function getProduct(val) {
        var start = val.indexOf("产品属性");
        if (start > 8) {
            start = start - 8;
        }
        return val.substr(start);
    }

    function FindData() {
        $('#dataList').datagrid('load', {
        	newsName: $('#newsName').val()
        }
     );
    }
    function saveNews(){
    	var validate = $("#fm").form("validate");
    	var content =$("#content").val();
    	if(content == ""){
    		alert("新闻内容不能为空");
    		return false;
    	}
    	if(validate){
	    	 var formObject = {};
	         var formArray =$("#fm").serializeArray();
	        
	         
	         $.each(formArray,function(i,item){
	             formObject[item.name] = item.value;
	         });
	         formObject["content"] = content;
	    	 $.ajax({
	                 type: "POST",//方法类型
	                 contentType : "application/json",
	                 dataType: "json",//预期服务器返回的数据类型
	                 url: "/newsManager/save" ,//url
	                 data: JSON.stringify(formObject),
	                 success: function (result) {
	                     console.log(result);//打印服务端返回的数据(调试用)
	                     if (result.code == "S000001") {
	                    	 $('#dlg').dialog('close');
	                    	 $('#dataList').datagrid('reload');
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
    function updateProduct(){
    	var validate = $("#editfm").form("validate");
    	if(validate){
	    	 var formObject = {};
	         var formArray =$("#editfm").serializeArray();
	         $.each(formArray,function(i,item){
	             formObject[item.name] = item.value;
	         });
	    	 $.ajax({
	                 type: "POST",//方法类型
	                 contentType : "application/json",
	                 dataType: "json",//预期服务器返回的数据类型
	                 url: "/product/update" ,//url
	                 data: JSON.stringify(formObject),
	                 success: function (result) {
	                     console.log(result);//打印服务端返回的数据(调试用)
	                     if (result.code == "S000001") {
	                    	 $('#editdlg').dialog('close');
	                    	 $('#dataList').datagrid('reload');
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
    function deleteNews(row){
    	 if(!confirm('确认删除吗？')){
    		 return;
    	 }
    	 var params = {};
    	 params["id"] = row.id;
    	 $.ajax({
             type: "POST",//方法类型
             contentType : "application/json",
             dataType: "json",//预期服务器返回的数据类型
             url: "/newsManager/delete" ,//url
             data: JSON.stringify(params),
             success: function (result) {
                 console.log(result);//打印服务端返回的数据(调试用)
                 if (result.code == "S000001") {
                	 $('#dataList').datagrid('reload');
                 }else{
                	 alert(result.message)
                 }
             },
             error : function() {
                 alert("系统错误。");
             }
         });
    }
    
    function initKindEditor() {
        var a = 'kind';
        kind = KindEditor.create('#content', {
            width: '100%',       // 文本框宽度(可以百分比或像素)
            height: '100px',     // 文本框高度(只能像素)
            minWidth: 200,       // 最小宽度（数字）
            minHeight: 100,      // 最小高度（数字）
            uploadJson: '/newsManager/upload_img/',
            /* extraFileUploadParams: {
                'csrfmiddlewaretoken': '{{ csrf_token }}'
            },
            afterUpload: function(){this.sync();}, //图片上传后，将上传内容同步到textarea中
            allowPreviewEmoticons: true, */
            allowFileManager: true,
            afterUpload: function(){
            	this.sync();
            }, //图片上传后，将上传内容同步到textarea中
           
            afterBlur: function(){this.sync();}   ////失去焦点时，将上传内容同步到textarea中
        
            
			
		});
    }
   
    
    </script>
</body>
</html>