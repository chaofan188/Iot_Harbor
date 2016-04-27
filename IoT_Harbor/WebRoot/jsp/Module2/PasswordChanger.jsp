<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userName = request.getParameter("userName");
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
</head>
<body>
<!--Form1-->
<form name="form1" id="MainForm" action="/IoT_Harbor/passwordChange.html">
<table cellpadding="0" cellspacing="1" class="table_form">
  <caption>
  密码修改
  </caption>
  <tr>
    <td width="151">用户名:</td>
    <td><input name="username"  id="userName" type="text" style="width:300px" value="<%=userName %>" readonly="readonly"/></td>
  </tr>
  <tr>
    <td width="151">新密码:</td>
    <td><input name="password" id="password" type="password" style="width:300px"/></td>
  </tr>
  <tr>
    <td width="151">重复密码:</td>
    <td><input name="re-password" id="re-password" type="password" style="width:300px"/></td>
  </tr>
  
</table>
<!--Form1End-->

<!--Form1-->
<table cellpadding="0" cellspacing="0" style="width:100%">
          <tr>
            <td width="100%" style="padding:10px;text-align:center;">
	            		<a class="buttonlink" href="#" onclick='document.getElementById("MainForm").submit();return false;'>修&nbsp;&nbsp;改</a> 
	            		<a class="buttonlink" href="#" onclick='document.getElementById("MainForm").reset();return false;'>清&nbsp;&nbsp;空</a>
          </tr>
</table>
<!--Form1End-->
</form>
</body>
</html>
