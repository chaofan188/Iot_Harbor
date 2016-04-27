<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userName = request.getParameter("userName");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<title>IOT大型公共服务网</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8>">
<link rel="stylesheet" type="text/css" href="../../admin/skin/system.css"/>
<script type="text/javascript" src="../../images/js/jquery.min.js"></script>
<script type="text/javascript" src="../../images/js/css.js"></script>
<style>
body {
	width:100%;
	background:#ECF7FE url(../../admin/skin/images/bg_body.jpg) repeat-y top left;
	scrollbar-face-color: #E7F5FE; 
	scrollbar-highlight-color: #006699; 
	scrollbar-shadow-color: #006699; 
	scrollbar-3dlight-color: #E7F5FE; 
	scrollbar-arrow-color: #006699; 
	scrollbar-track-color: #E7F5FE; 
	scrollbar-darkshadow-color: #E7F5FE; 
	scrollbar-base-color: #E7F5FE;
	}
</style>

</head>
  
  <body scroll="no">
<!--head-->
<div id="header">
  <div class="logo">
	<a href="http://192.168.22.201/" target="_blank">
		<img src="../../admin/skin/images/logo.jpg" width="258" height="56" border="0"/>
	</a>
  </div>
  <p id="info_bar"> 用户名：
	<strong class="font_arial white">
		<%=userName %>    
	</strong>，角色： 用户     | 
	<a href="/IoT_Harbor/logout.html?userName=<%=userName %>" class="white">退出登录</a> | 
	<a href="/IoT_Harbor/jsp/Module2/index.jsp?userName=<%=userName %>" class="white" >网站首页</a>  <!-- target="_blank" -->
  </p>
  <div id="menu" style="display:none;">
    <ul>
      <li><a href="javascript:click_topmenu(2);" id="menu_2" class="menu" alt="我的面板"><span>我的面板</span></a></li>
	  <li><a href="javascript:click_topmenu(3);" id="menu_3" class="menu" alt="系统设置"><span>系统设置</span></a></li>
	  <li><a href="javascript:click_topmenu(4);" id="menu_4" class="menu" alt="内容管理"><span>内容管理</span></a></li>
	  <li><a href="javascript:click_topmenu(5);" id="menu_5" class="menu" alt="模块管理"><span>模块管理</span></a></li>
	  <li><a href="javascript:click_topmenu(6);" id="menu_6" class="menu" alt="会员管理"><span>会员管理</span></a></li>
	  <li><a href="javascript:click_topmenu(7);" id="menu_7" class="menu" alt="模板风格"><span>模板风格</span></a></li>    
	 </ul>
  </div>
</div>
<!--headend-->
<!--left-->
<div id="admin_left">
  <div id="inner" class="inner">
    <h4><span id="menu_name">我的面板</span></h4>
	<div id="tree_box" class="p_r" style="top:10px; left:6px;" >	
	      <iframe name="lefttree" id="lefttree" frameborder="0" src="after-menu.jsp?userName=<%=userName %>" style="margin-top:30px;height:100%;width:213px;z-index:111;background-color:#ffffff"></iframe>
	</div>
  </div>
</div>
<!--leftend-->
<!--right-->
<div id="admin_right">
  <div id="inner" class="inner">
    <!--地图-->
    <div id="position"><!--<strong>后台首页：</strong><a href="#" onclick="return false;" id="menu_top">我的面板</a>--></div>
    <!--内容IFRAME-->
    <div>
      <iframe name="right" id="right" frameborder="0" src="welcome.jsp?userName=<%=userName %>" style="height:100%;width:100%;z-index:111;background-color:#ffffff"></iframe>
    </div>
  </div>
</div>
<!--rightend-->

<script language="JavaScript" src="../../images/js/newjs/menu2.js"></script>


</body>
</html>
