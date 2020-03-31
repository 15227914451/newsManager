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
</head>
<body>
    <h2>积分管理</h2>
   
    <div id="searchtool" style="padding: 5px">
        <span>用户名:</span><input name="userName" type="text" id="userName" />
        <span>手机号:</span><input name="mobile" type="text" id="mobile" />
        <span>用户id:</span><input name="id" type="text" id="id" />
        <a href="javascript:FindData()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">
            查询</a>
     
     </div>
     <form>
            <table id="dataList">
            </table>
 
    </form>
    
    <div id="editdlg" class="easyui-dialog" style="width:400px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
        <form id="editfm" method="post" novalidate style="margin:0;padding:20px 50px">
            <h3>编辑积分</h3>
            <div style="margin-bottom:10px">
                <input name="id" class="easyui-textbox" required="true" label="用户id:" style="width:100%" readonly="readonly">
            </div>
            <div style="margin-bottom:10px">
                <input name="userName" class="easyui-textbox" required="true" label="用户名:" style="width:100%" readonly="readonly">
            </div>
            <div style="margin-bottom:10px">
                <input name="score" class="easyui-textbox" required="true" label="积分:" style="width:100%">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="updateScore()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    
    
    
    <div id="scoledlg" class="easyui-dialog" style="width:600px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
        <form id="editScolefm" method="post" novalidate style="margin:0;padding:20px 50px">
            <h3>积分规则</h3>
            <div style="margin-bottom:10px">
                <input name="orignalScore" class="easyui-textbox" required="true" label="初始积分:" style="width:80%" >
            </div>
            <div style="margin-bottom:10px">
                <input name="deductionScore" class="easyui-textbox" required="true" label="扣除积分:" style="width:80%">
            </div>
            <div style="margin-bottom:10px">
                <input name="days" class="easyui-textbox" required="true" label="开始时间:" style="width:80%">
            </div>
            <div style="margin-bottom:10px">
                <input name="scoles" class="easyui-textbox" required="true" label="自增积分:" style="width:80%">
            </div>
            <div style="margin-bottom:10px">
                <input name="upgrade" class="easyui-textbox" required="true" label="升级条件:" style="width:80%">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="updateScore()" style="width:90px">保存</a>
    </div>
    <script type="text/javascript">
    $(function () {
        $('#dataList').datagrid({
            title: '用户列表',
            iconCls: 'icon-save',
            width: window.screen.width * 0.8,
            height: window.screen.height * 0.5,
            fit: false,
            nowrap: false,
            striped: true,
            url: '/userInfo/list',
            sortName: 'id',
            sortOrder: 'id',
            singleSelect: 'true',
            remoteSort: false,
            idField: 'id', 
            columns: [[
               
                { title: '用户id', field: 'id', width: 200},
                { title: '用户手机号', field: 'mobile', width: 200},
                {title: '用户名', field: 'userName', width: 200 },
                {title: '积分', field: 'score', width: 200 },
                {title: '等级', field: 'grade', width: 200,formatter:formatGrade},
                {title: '状态', field: 'status', width: 200,formatter:formatStatus },
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
                id: 'btnedit',
                text: '编辑积分',
                iconCls: 'icon-edit',
                handler: function () {
                    $('#btnsave').linkbutton('enable');
                    var row = $('#dataList').datagrid('getSelected');
                    if (row == null){
                       alert("选择编辑的数据。")
                       return;
                    }
                   if(row){
                	   editScore(row);
                   }
                }
            },
            {
                id: 'btnedit',
                text: '积分规则',
                iconCls: 'icon-edit',
                handler: function () {
                    $('#btnsave').linkbutton('enable');
                    editRules();
                    
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
    function formatOper(val,row,index){
    	var html ="";
    	if(row.score > 150 && row.grade == 0){
    		html = '<a href="#" onclick="audience('+row.id+')">审核</a>';
    	}
    
    	return html;
   }
    function formatGrade(val,row,index){
    	var html ="";
    	if(row.grade == 0){
    		html = '普通用户';
    	}else{
    		html = '发布者';
    	}
    
    	return html;
   }
    function formatStatus(val,row,index){
    	var html ="";
    	if(row.status == 0){
    		html = '禁言';
    	}else{
    		html = '未禁言';
    	}
    
    	return html;
   }
    function audience(id){
    	 $('#dlg').dialog('open').dialog('center').dialog('setTitle','审核');
    	 $('#userInfoId').val(id);
    }
    function saveAudience(){
    	 var formObject = {};
        formObject["userInfoId"] = $("#userInfoId").val();
        formObject["audience"] = $('input:radio:checked').val();
    	 $.ajax({
                 type: "POST",//方法类型
                 contentType : "application/json",
                 dataType: "json",//预期服务器返回的数据类型
                 url: "/userInfo/audience" ,//url
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
  
    function editScore(row){
       
        if (row){
            $('#editdlg').dialog('open').dialog('center').dialog('setTitle','修改积分');
            $('#editfm').form('load',row);
            url = "/goods/update";
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
        	goodsName: $('#goodsName').val()
        }
     );
    }
    function saveProduct(){
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
	                 url: "/goods/save" ,//url
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
    function updateScore(){
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
	                 url: "/userInfo/update" ,//url
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
    function deleteProduct(row){
    	 if(!confirm('确认删除吗？')){
    		 return;
    	 }
    	 var params = {};
    	 params["id"] = row.id;
    	 $.ajax({
             type: "POST",//方法类型
             contentType : "application/json",
             dataType: "json",//预期服务器返回的数据类型
             url: "/sellers/delete" ,//url
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
    function addGoods(){
    	
    	$('#selectdlg').dialog('open').dialog('center');
    	$('#dlg-toolbar').searchbox({
    		   width : 500,
    		   searcher : function (value, name) {
    			   $('#selectProduct').datagrid('load', {
    		        	goodsName: value
    		        });
    		   },
    		   disabled: false
    		  });

    	
    	 $('#selectProduct').datagrid({
             title: '商品列表',
             iconCls: 'icon-save',
             width: 765,
             height:400,
             fit: false,
             nowrap: false,
             striped: true,
             url: '/goods/list?state=1',
             sortName: 'id',
             sortOrder: 'id',
             singleSelect: false,
             remoteSort: false,
             idField: 'id',
              /* frozenColumns: [[
                { field: 'ck', checkbox: true },
                 { title: '订单号', field: 'order_no', width: 100},
                 { title: '订单状态', field: 'order_status', width: 80}
             
             ]], */ 
             columns: [[
            	 {field:'ck',checkbox:true},
                 { title: '商品id', field: 'id', width: 100},
                 { title: '商品名称', field: 'goodsName', width: 100},
                 {title: '生产日期', field: 'manufactureTime', width: 150 },
                 {title: '保质期', field: 'qualityPeriod', width: 150 },
                 {title: '负责人', field: 'userName', width: 100 },
                 {title: '描述', field: 'goodsDescribe', width: 200 },
                 {title: '操作', field: '_operate', width: 200 ,formatter:formatOper}

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
             onLoadSuccess:function(data){   
            	 if(data){
		           	$.each(data.rows, function(index, item){
		           		if(data.rows.checked){
		           			
	            	 		$('#selectProduct').datagrid('checkRow', index);
		           		}
	            	});
            	}
            },            
             pagination: true,
             rownumbers: true,
             toolbar: [{
                 id: 'btnadd',
                 text: '添加',
                 iconCls: 'icon-add',
                 handler: function () {
                     $('#btnsave').linkbutton('enable');
                     var rows = $('#selectProduct').datagrid('getSelections'); //首先获得你所选择的行。
                     var ids = [];
                     for (var i = 0; i < rows.length; i++) {
	                     ids.push(rows[i].id)
                     }
                     addProductIds(ids);
                 }
             }]
         });
         var p = $('#selectProduct').datagrid('getPager');
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
    function formatSelect(val,row,index){
    	return "<input type=\"checkbox\"  name=\"selectProductId\"   value=\"" + row.id + "\" >";
    }
    function addProductIds(ids){
    	
	    if(!ids){
	    	alert("选择产品。");
	    	return;
	    }
	     var params = {};
	   	 params["ids"] = ids;
	   	 $.ajax({
	            type: "POST",//方法类型
	            contentType : "application/json",
	            dataType: "json",//预期服务器返回的数据类型
	            url: "/sellers/addsellers",//url
	            data: JSON.stringify(params),
	            success: function (result) {
	                console.log(result);//打印服务端返回的数据(调试用)
	                if (result.code == "S000001") {
	                 $('#selectdlg').dialog('close'); 
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
    function lookProduct(id){
    	
    	$('#dlg').dialog('open').dialog('setTitle', '商品信息');
    	var params = {};
	   	 params["goodsId"] = id;
    	$.ajax({
	            type: "POST",//方法类型
	            contentType : "application/json",
	            dataType: "json",//预期服务器返回的数据类型
	            url: "/sellers/getGoods",//url
	            data: JSON.stringify(params),
	            success: function (result) {
	                console.log(result);//打印服务端返回的数据(调试用)
	                if (result.code == "S000001") { 
	                	$('#fm').form('load',result.goodsInfo);
	                }else{
	               	 alert(result.message)
	                }
	            },
	            error : function() {
	                alert("系统错误。");
	            }
	        });
    	
    	 $('#lookProduct').datagrid({
             title: '已关联产品列表',
             iconCls: 'icon-save',
             width: 800,
             height:400,
             fit: false,
             nowrap: false,
             striped: true,
             url: '/sellers/listProduct?sellersId=' + id,
             sortName: 'id',
             sortOrder: 'id',
             singleSelect: false,
             remoteSort: false,
             idField: 'id',
              /* frozenColumns: [[
                { field: 'ck', checkbox: true },
                 { title: '订单号', field: 'order_no', width: 100},
                 { title: '订单状态', field: 'order_status', width: 80}
             
             ]], */ 
             columns: [[
            	 { title: '产品id', field: 'id', width: 100},
                 { title: '产品名称', field: 'productName', width: 100},
                 {title: '产品产地', field: 'originPlace', width: 100 },
                 {title: '种植时间', field: 'plantingTime', width: 150 },
                 {title: '收获时间', field: 'harvestTime', width: 150 },
                 {title: '生产者', field: 'userName', width: 100 },
                 {title: '描述', field: 'productDescribe', width: 200 }

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
             
         });
         var p = $('#lookProduct').datagrid('getPager');
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
    
    
    
    
    function editRules(){
 	   	 $.ajax({
 	            type: "POST",//方法类型
 	            contentType : "application/json",
 	            dataType: "json",//预期服务器返回的数据类型
 	            url: "/scoreRules/getRules",//url
 	            success: function (result) {
 	                console.log(result);//打印服务端返回的数据(调试用)
 	                if (result.code == "S000001") { 
 	                	scoledlg
 	                $('#scoledlg').dialog('open').dialog('center').dialog('setTitle','修改积分规则');
 	               	$('#editScolefm').form('load',result.data);
 	                }else{
 	               	 alert(result.message)
 	                }
 	            },
 	            error : function() {
 	                alert("系统错误。");
 	            }
 	        });
    	
    }
    function updateScore(){
    	var validate = $("#editScolefm").form("validate");
    	if(validate){
	    	 var formObject = {};
	         var formArray =$("#editScolefm").serializeArray();
	         $.each(formArray,function(i,item){
	             formObject[item.name] = item.value;
	         });
	    	 $.ajax({
	                 type: "POST",//方法类型
	                 contentType : "application/json",
	                 dataType: "json",//预期服务器返回的数据类型
	                 url: "/scoreRules/update" ,//url
	                 data: JSON.stringify(formObject),
	                 success: function (result) {
	                     console.log(result);//打印服务端返回的数据(调试用)
	                     if (result.code == "S000001") {
	                    	 $('#scoledlg').dialog('close');
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
    
    
    </script>
</body>
</html>