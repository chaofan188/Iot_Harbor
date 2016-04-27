
<%@page import="com.cetc.iot.harbormanage.service.UserInfo"%>
<%@page import="com.cetc.iot.database.model.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userName = request.getParameter("userName");
UserInfo userInfo = new UserInfo();
User user = userInfo.getUserInfo(userName);
String userState = null;
if (user != null){
userState = user.getState();
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8>">
<title>全部信息-管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link rel="stylesheet" type="text/css" href="../../admin/skin/system.css" >
<script type="text/javaScript" src="../../images/js/newjs/jquery.min.js"></script>
<script type="text/javaScript" src="../../images/js/newjs/jquery.easyui.min.js"></script>


<script type="text/javascript" src="../../images/js/newjs/datagrid_adapter.js"></script>
<script type="text/javascript" src="../../images/js/newjs/typeSelecter.js"></script>
<!--<script type="text/javaScript" src="images/js/css.js"></script>
<script type="text/javaScript" src="data/config.js"></script>
<script type="text/javaScript" src="images/js/common.js"></script>
<script type="text/javaScript" src="images/js/admin.js"></script>
<script type="text/javascript" src="images/js/validator.js"></script>
<script type="text/javascript" src="images/js/form.js"></script>-->
<link rel="stylesheet" type="text/css" href="../../images/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="../../images/js/newjs/easyui-balthasar.css">
<script type="text/javascript" src="../../js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	function Open()
			{
				window.location.href="/IoT_Harbor/openUser.html?userName=<%=userName %>";
			}
	function Close()
			{
				window.location.href="/IoT_Harbor/closeUser.html?userName=<%=userName %>";
			}
	function Test()
	        {
				window.location.href="/IoT_Harbor/testString.html?userName=<%=userName %>";	
			}
	</script>
	
</head>
<body>
<!--Form1-->
<table cellpadding="0" cellspacing="1" class="table_form">
  <caption>
  用户管理
  </caption>
  <tr>
    <td style="padding:10px;text-align:center;">当前用户状态： <span style="color:#096;font-weight:bold;"><%=userState %></span></td>
  </tr>
  <tr>
    <td style="padding:10px;text-align:center;">
	            	<!-- <td><input id="open" type="button" value='启用'/></td>
	            		<td><input id="close" type="button" value='冻结'/></td> -->
	            		<!-- <input  id="open" type="button" value='启用'/>
	            		<input  id="close" type="button" value='冻结'/> -->
	            		<!-- <a class="buttonlink" href="#">注&nbsp;&nbsp;销</a> -->
	            		<a class="buttonlink" href="#" onclick="Open();return false;" >启&nbsp;&nbsp;用</a>
	            		<a class="buttonlink" href="#" onclick="Close();return false;" >冻&nbsp;&nbsp;结</a>
	            		<!-- <a class="buttonlink" href="#" onclick="Test();return false;" >测&nbsp;&nbsp;试</a> -->
  </tr>
  
</table>
<!--Form1End-->

</body>
</html>
