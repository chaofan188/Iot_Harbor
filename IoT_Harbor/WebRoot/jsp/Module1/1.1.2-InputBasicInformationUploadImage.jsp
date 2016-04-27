<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String QF = request.getParameter("file");
String QF2 = QF;
if(QF==null || QF=="")
{
	QF="未上传图片";
	QF2="";
} 
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
<!--<script type="text/javaScript" src="images/js/css.js"></script>
<script type="text/javaScript" src="data/config.js"></script>
<script type="text/javaScript" src="images/js/common.js"></script>
<script type="text/javaScript" src="images/js/admin.js"></script>
<script type="text/javascript" src="images/js/validator.js"></script>
<script type="text/javascript" src="images/js/form.js"></script>-->
<link rel="stylesheet" type="text/css" href="../../images/easyui/easyui.css">
</head>
<body>

<script>
	window.onload=function()
	{
		window.parent.document.getElementById("device_picture").value="<%=QF %>";
	}
</script>

<table width="500" border="0" cellspacing="0" cellpadding="0" style="float:left;">
  <tr>
    <td width="200" style="paddding:0px;"><img src="<%=QF2 %>" width="200" height="200"/></td>
    <td>
    
     <form name="inputPictureForm" id="inputPictureForm" method="post" action="../../addpicture.html" enctype="multipart/form-data">
       <span id="inputPictureFalse"><%=QF %></span>
       <input type="file" id="inputPicture" name="inputPicture" onChange='document.getElementById("inputPictureFalse").innerHTML=document.getElementById("inputPicture").value;' style="visibility:collapse;" />
	</form>
    <a class="buttonlink" onClick='document.getElementById("inputPicture").click();return false;'>选择本地图片</a><a class="buttonlink" onclick='document.getElementById("inputPictureForm").submit();return false;'>提交到远程</a>
    
    </td>
  </tr>
</table>


<body>
</body>
</html>
