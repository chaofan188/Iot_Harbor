<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String QF = request.getParameter("peid");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8>">
<title>全部信息-管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link rel="stylesheet" type="text/css" href="../../admin/skin/system.css" >
<script type="text/javaScript" src="../../images/js/newjs/jquery.min.js"></script>
<script type="text/javaScript" src="../../images/js/newjs/jquery.easyui.min.js"></script>


<script type="text/javascript" src="../../images/js/newjs/datagrid_adapter.js"></script>
<script type="text/javascript" src="../../images/js/newjs/typeSelecter.js"></script>
<script type="text/javascript" src="../../images/js/newjs/FormEngine.js"></script>
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
<table cellpadding="0" cellspacing="0" class="table_form">
  <caption>
	激活设备
  </caption>
   <script>
		
		   var peid = "<%=QF %>";
		   function active()
			{
			 //   alert(peid);
				window.location.href="http://localhost:8080/IoT_Harbor/active.html?peid="+peid;
			}
	
			function unactive()
			{
			   
				window.location.href="http://localhost:8080/IoT_Harbor/unactive.html?peid="+peid;
			}
</script>
  <tr>
    <td>设备激活后才能使用</td>
  </tr>
  <tr>
    <td>设备PEID：XXXXXXXXX</td>
  </tr>
  <tr>
    <td>

	 <a class="buttonlink" href="#" onclick="active();return false;" >确认激活</a>
	 <a class="buttonlink" href="#" onclick="unactive();return false;" >取消激活</a>
    </td>
  </tr>
  <tr>
    <td>请将此PE-ID导入设备或网关，并与平台地址XX进行通信。 <a href="#">什么是PE-ID？</a></td>
  </tr>
</table>
<!--Form1End-->


</body>
</html>