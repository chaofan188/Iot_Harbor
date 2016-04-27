<%@page import="com.cetc.iot.harbormanage.service.UserInfo"%>
<%@page import="com.cetc.iot.database.model.User"%>
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
  <h1><%=userName %>,欢迎进入物联港管理平台！<h1>
</body>
</html>
