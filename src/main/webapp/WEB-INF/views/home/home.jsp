<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta id="i18n_pagename" content="ui">

  <title class="i18n" i18nKey="title" >管理系统</title>

  <script src="/static/js/import-iframe.js"></script>
  <script type="text/javascript">
  $(document).ready(function(){
      system_business.initMenu("parentId=-1");
      system_business.version();
  }); 

  </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <header class="main-header">
    <!-- Logo -->
    <a href="#" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <!-- <span class="logo-mini"><b>E</b>PS</span> -->
      <!-- logo for regular state and mobile devices -->
      <!-- <span class="logo-lg"><b>EPS</b> Server</span> -->
      <span >管理系统</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
     

      <div class="navbar-custom-menu" style="float:left">
        <ul class="nav navbar-nav" id="menuRoot">
        </ul>
      </div>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">


            <a href="#" class="dropdown-toggle" data-toggle="dropdown" onclick = "system_business.logOut()">
            <i class="fa fa-plug"></i>
              <span class="hidden-xs" >退出</span>
            </a>
            
          </li>
          <!-- Control Sidebar Toggle Button -->
          
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar" id="sidebarmenu">
      <!-- sidebar menu: : style can be found in sidebar.less -->
       <ul class="sidebar-menu" id="menu1" data-widget="tree">
        <li class="header i18n" i18nKey="mainNavigation" ></li>

         
<li><a href="business/index" target="main_content"><i class="fa fa-circle-o"></i> <span>首页</span></a></li>


<li><a href="business/userInfo" target="main_content"><i class="fa fa-circle-o"></i> <span >用户管理</span></a></li>
<li><a href="business/newsCategory" target="main_content"><i class="fa fa-circle-o"></i> <span >新闻分类管理</span></a></li>
<li><a href="business/newsComment" target="main_content"><i class="fa fa-circle-o"></i> <span >新闻评论管理</span></a></li>
<li><a href="business/scoreRules" target="main_content"><i class="fa fa-circle-o"></i> <span >信誉积分管理</span></a></li>
<li><a href="business/newsManage" target="main_content"><i class="fa fa-circle-o"></i> <span >新闻管理</span></a></li>

<!-- <li><a href="business/goods" target="main_content"><i class="fa fa-circle-o"></i> <span>商品加工管理</span></a></li>

<li><a href="business/sellers" target="main_content"><i class="fa fa-circle-o"></i> <span >销售货物管理</span></a></li>
 -->
<!--  <li><a href="business/orderRefund/orderRefund.html" target="main_content"><i class="fa fa-circle-o"></i> <span >Refund</span></a></li>

<li><a href="business/tradeInfo/tradeInfo.html" target="main_content"><i class="fa fa-circle-o"></i> <span >TradeInfo</span></a></li>
<li id = "reconciliationRecord" style="display: none;"><a href="business/reconciliationRecord/reconciliationRecord.html" target="main_content"><i class="fa fa-circle-o"></i> <span >reconciliationRecord</span></a></li>
<li id = "userInfo" style="display: none;"><a href="business/userInfo/userInfo.html" target="main_content"><i class="fa fa-circle-o"></i> <span >resource.userInfo</span></a></li>
 -->
　　
　　

      </ul>

    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <iframe id="main_content" name="main_content" frameborder=no src="business/index" style="background:#ecf0f5"></iframe>
  </div>

  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b class="i18n" i18nKey="resource.versionnumber" ></b>: <span id="sipVersion"></span> 
      &nbsp;&nbsp;&nbsp;&nbsp;
      <b class="i18n" i18nKey="resource.publishedate" ></b>: <span id="sipBuildTime"></span>
    </div>
    <strong>guanlixitong.com</strong>
  </footer>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Create the tabs -->
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
      <!-- Home tab content -->
      <div class="tab-pane" id="control-sidebar-home-tab">
      </div>
    </div>
  </aside>
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>

<!--模式对话框-->
<div class="modal fade" id="cfmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title i18n" id="myModalLabel" i18nKey="action.confirm" >Confirm</h4>
      </div>
      <div class="modal-body" id="cfm_text"></div>
      <div class="modal-footer">
        <button type="button" class="btn btn-outline pull-left i18n" data-dismiss="modal" i18nKey="resource.close" >Close</button>
        <button type="button" class="btn btn-outline i18n" id="cfmClick" i18nKey="resource.confirm" >Confirm</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal -->
</div>

<div class="modal fade" id="altModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title i18n" id="altModalLabel" i18nKey="action.confirm" >Confirm</h4>
      </div>
      <div class="modal-body" id="alt_text"></div>
      <div class="modal-footer">
        <button type="button" class="btn btn-outline i18n" data-dismiss="modal" i18nKey="action.close" >Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal -->
</div>

</body>
</html>