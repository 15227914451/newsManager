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
    <h2>商品加工管理</h2>
   
    <div id="searchtool" style="padding: 5px">
        <span>商品名称:</span><input name="goodsName" type="text" id="goodsName" />
        <a href="javascript:FindData()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">
            查询</a>
     
     </div>
     <form>
            <table id="dataList">
            </table>
 
    </form>
  
    
    <div id="dlg" class="easyui-dialog" style="width:400px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
        <form id="fm" method="post" novalidate style="margin:0;padding:20px 50px">
            <h3>商品信息</h3>
            <div style="margin-bottom:10px">
                <input name="goodsName" class="easyui-textbox" required="true" label="商品名称:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="officeName" class="easyui-textbox" required="true" label="所属公司:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="manufactureTime" class="easyui-datebox" required="true" label="生产日期:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="qualityPeriod" class="easyui-textbox" required="true"  label="保质期:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="goodsDescribe" class="easyui-textbox" required="true" label="商品描述:" style="width:100%">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveProduct()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    
    <div id="editdlg" class="easyui-dialog" style="width:400px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
        <form id="editfm" method="post" novalidate style="margin:0;padding:20px 50px">
            <h3>商品信息</h3>
            <div style="margin-bottom:10px">
                <input name="id" class="easyui-textbox" required="true" label="商品id:" style="width:100%" readonly="readonly">
            </div>
            <div style="margin-bottom:10px">
                <input name="goodsName" class="easyui-textbox" required="true" label="商品名称:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="officeName" class="easyui-textbox" required="true" label="所属公司:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="manufactureTime" class="easyui-datebox" required="true" label="生产日期:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="qualityPeriod" class="easyui-textbox" required="true" label="保质期:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="goodsDescribe" class="easyui-textbox" required="true" label="商品描述:" style="width:100%">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="updateProduct()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    
    
    <div id="selectdlg" class="easyui-dialog" title="关联产品" style="width:800px;height:auto;padding:10px"
			data-options="
				iconCls: 'icon-save',
				toolbar: '#dlg-toolbar',
				buttons: '#dlg-buttons'
			">
		<table id = "selectProduct">
		
		</table>
	</div>
	<div id="dlg-toolbar" style="padding:2px 0">
		<table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td style="text-align:right;padding-right:2px">
					<input class="easyui-searchbox" data-options="prompt:'请输入产品名称'" style="width:150px"></input>
				</td>
			</tr>
		</table>
	</div>
	<div id="dlg-buttons">
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:alert('save')">保存</a> -->
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#selectdlg').dialog('close')">关闭</a>
	</div>
	
	
	
	  <div id="lookProduct" class="easyui-dialog" title="已关联产品" style="width:800px;height:auto;padding:10px"
			>
		<table id = "lookProduct">
		
		</table>
	</div>
	
	
	
	
    <script type="text/javascript">
    $(function () {
    	$('#selectdlg').dialog('close');
    	$('#lookProduct').dialog('close');
        $('#dataList').datagrid({
            title: '商品列表',
            iconCls: 'icon-save',
            width: window.screen.width * 0.8,
            height: window.screen.height * 0.5,
            fit: false,
            nowrap: false,
            striped: true,
            url: '/goods/list',
            sortName: 'id',
            sortOrder: 'id',
            singleSelect: 'true',
            remoteSort: false,
            idField: 'id',
             /* frozenColumns: [[
               { field: 'ck', checkbox: true },
                { title: '订单号', field: 'order_no', width: 100},
                { title: '订单状态', field: 'order_status', width: 80}
            
            ]], */ 
            columns: [[
               
                { title: '商品id', field: 'id', width: 100},
                { title: '商品名称', field: 'goodsName', width: 100},
                {title: '所属公司', field: 'officeName', width: 100 },
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
                    deleteProduct(row);
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
                	   editProduct(row);
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
    function formatOper(val,row,index){
    	var html ="";
    	if(row.state == 0){
    		html = '<a href="#" onclick="lookProduct('+row.id+')">查看关联</a>  <a href="#" onclick="relationProduct('+row.id+')">关联产品</a>';
    		
    	}else{
    		html = '<a href="#" onclick="relationProduct('+row.id+')">关联产品</a>';
    	}
    	return html;
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
    function newProduct(){
        $('#dlg').dialog('open').dialog('center').dialog('setTitle','新增商品');
        $('#fm').form('clear');
        url = '/product/save';
    }
    function editProduct(row){
       
        if (row){
            $('#editdlg').dialog('open').dialog('center').dialog('setTitle','编辑商品');
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
	                 url: "/goods/update" ,//url
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
             url: "/goods/delete" ,//url
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
    function relationProduct(id){
    	
    	$('#selectdlg').dialog('open').dialog('center');
    	$('#dlg-toolbar').searchbox({
    		   width : 500,
    		   searcher : function (value, name) {
    			   $('#selectProduct').datagrid('load', {
    		        	productName: value
    		        });
    		   },
    		   disabled: false
    		  });

    	
    	 $('#selectProduct').datagrid({
             title: '产品列表',
             iconCls: 'icon-save',
             width: 765,
             height:400,
             fit: false,
             nowrap: false,
             striped: true,
             url: '/product/listProduct?goodsId=' + id,
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
                 { title: '产品id', field: 'id', width: 100},
                 { title: '产品名称', field: 'productName', width: 100},
                 {title: '添加人', field: 'userName', width: 100 }

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
                 text: '关联',
                 iconCls: 'icon-add',
                 handler: function () {
                     $('#btnsave').linkbutton('enable');
                     var rows = $('#selectProduct').datagrid('getSelections'); //首先获得你所选择的行。
                     var ids = [];
                     for (var i = 0; i < rows.length; i++) {
	                     ids.push(rows[i].id)
                     }
                     addProductIds(id,ids);
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
    function addProductIds(id,ids){
    	
	    if(!ids){
	    	alert("选择产品。");
	    	return;
	    }
	     var params = {};
	   	 params["id"] = id;
	   	 params["productIds"] = ids;
	   	 $.ajax({
	            type: "POST",//方法类型
	            contentType : "application/json",
	            dataType: "json",//预期服务器返回的数据类型
	            url: "/goods/addProductIds",//url
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
    	
    	$('#lookProduct').dialog('open').dialog('center');
    	
    	
    	 $('#lookProduct').datagrid({
             title: '已关联产品列表',
             iconCls: 'icon-save',
             width: 765,
             height:400,
             fit: false,
             nowrap: false,
             striped: true,
             url: '/product/listProductAlread?goodsId=' + id,
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
             toolbar: [{
                 id: 'btnadd',
                 text: '删除',
                 iconCls: 'icon-cut',
                 handler: function () {
                     $('#btnsave').linkbutton('enable');
                     var rows = $('#lookProduct').datagrid('getSelections'); //首先获得你所选择的行。
                     var ids = [];
                     for (var i = 0; i < rows.length; i++) {
	                     ids.push(rows[i].id)
                     }
                     delateProductIds(id,ids);
                 }
             }]
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
    function delateProductIds(id,ids){
    	 if(!ids){
 	    	alert("选择删除的产品。");
 	    	return;
 	    }
 	     var params = {};
 	   	 params["id"] = id;
 	   	 params["productIds"] = ids;
 	   	 $.ajax({
 	            type: "POST",//方法类型
 	            contentType : "application/json",
 	            dataType: "json",//预期服务器返回的数据类型
 	            url: "/goods/deleteProductIds",//url
 	            data: JSON.stringify(params),
 	            success: function (result) {
 	                console.log(result);//打印服务端返回的数据(调试用)
 	                if (result.code == "S000001") { 
 	               	 $('#lookProduct').datagrid('reload');
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
</body>
</html>