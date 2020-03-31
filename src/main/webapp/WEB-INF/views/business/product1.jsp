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
    <h2>农产品管理</h2>
    <div>
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
    </div>
    <table id="dg" title="农产品列表" class="easyui-datagrid" style="width:1040px;height:600px"
            url="/business/list"
            toolbar="#toolbar" pagination="true"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="productName" width="50">产品名称</th>
                <th field="originPlace" width="50">产地</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:400px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
        <form id="fm" method="post" novalidate style="margin:0;padding:20px 50px">
            <h3>User Information</h3>
            <div style="margin-bottom:10px">
                <input name="firstname" class="easyui-textbox" required="true" label="First Name:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="lastname" class="easyui-textbox" required="true" label="Last Name:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="phone" class="easyui-textbox" required="true" label="Phone:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="email" class="easyui-textbox" required="true" validType="email" label="Email:" style="width:100%">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">Save</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>
    </div>
    <script type="text/javascript">
        var url;
        function newUser(){
            $('#dlg').dialog('open').dialog('center').dialog('setTitle','新增商品');
            $('#fm').form('clear');
            url = 'save_user.php';
        }
        function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('center').dialog('setTitle','编辑商品');
                $('#fm').form('load',row);
                url = 'update_user.php?id='+row.id;
            }
        }
        function saveUser(){
            $('#fm').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                        $('#dlg').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                }
            });
        }
        function destroyUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
                    if (r){
                        $.post('destroy_user.php',{id:row.id},function(result){
                            if (result.success){
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: result.errorMsg
                                });
                            }
                        },'json');
                    }
                });
            }
        }
       //初始化表
       
       function loadData(pageNumber, pageSize)
{
	var vEmployType = $('#cbbType').combobox('getValue');
	
	$.ajax({
		type : "post",
		url : "EmployServlet?act=load",
		data : {
			"employType" : vEmployType,
			"pageNumber": pageNumber,
			"pageSize": pageSize
		},
		dataType : "json",
		// async: false, //同步请求
		error : function(event, request, settings) {
			alert("请求失败。" + event);
		},
		success : function(data) {
			if (data.packStatus == 200) {

                $("#grdMain").datagrid({
                    data: data.retDat
                });
                initDataGrid(pageNumber, pageSize); // 不知道为什么每次都要重新绑定当前页面和相关事件回调

			} else if (data.packStatus == 301) {
				alert("应用超时，请求重新登录。");
				top.location = 'login.jsp';
			} else {
				alert(data.retMsg);
			}
		}
	});	
}

function initDataGrid(pageNumber, pageSize)
{
	if (!pageNumber) pageNumber = 1;
	if (!pageSize) pageSize = 10;
	
	var vGridPage = $("#grdMain").datagrid("getPager");
	$(vGridPage).pagination({
		pageNumber: pageNumber, // 不设置当前页会变成1
		pageSize: pageSize,     // 不设置页面记录数会变成10
		onSelectPage: function(pageNumber, pageSize){
			loadData(pageNumber, pageSize);  // 不设置onSelectPage刷新数据之后便失效。
	});
	
}
        
    </script>
</body>
</html>