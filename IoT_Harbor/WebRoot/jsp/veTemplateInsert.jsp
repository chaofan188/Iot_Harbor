<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8>">
<title>全部信息-管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link rel="stylesheet" type="text/css" href="../admin/skin/system.css" >
<script type="text/javaScript" src="../images/js/newjs/jquery.min.js"></script>
<script type="text/javaScript" src="../images/js/newjs/jquery.easyui.min.js"></script>


<script type="text/javascript" src="../images/js/newjs/datagrid_adapter.js"></script>
<script type="text/javascript" src="../images/js/newjs/typeSelecter.js"></script>
<!--<script type="text/javaScript" src="images/js/css.js"></script>
<script type="text/javaScript" src="data/config.js"></script>
<script type="text/javaScript" src="images/js/common.js"></script>
<script type="text/javaScript" src="images/js/admin.js"></script>
<script type="text/javascript" src="images/js/validator.js"></script>
<script type="text/javascript" src="images/js/form.js"></script>-->
<link rel="stylesheet" type="text/css" href="../images/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="../images/js/newjs/easyui-balthasar.css">
<script type="text/javascript" src="../js/jquery-1.7.2.js"></script>
<style>
.btn{ background-color:#99d3fb; color:black;height:20px; width:58px; border:0; cursor:pointer;  line-height:20px;}
</style>
	<script type="text/javascript">
	/* function Close()
			{
				window.parent.location.href="/IoT_Harbor/harbor_login.jsp";
			} */
	</script>

</head>

<body>
<form id="form" name="form" action="/IoT_Harbor/addVeModel.html"
		method="POST" enctype="multipart/form-data">
<table cellpadding="0" cellspacing="1" class="table_form">
  <caption>
  VE模板导入
  </caption>
 
			<tr>
				<td width="150">VE模板别名：</td>
				<td><input name="tplAlias" type="text"
					style="width:500px;" />&nbsp;</td>
			</tr>
			<tr>
				<td width="150">VE模板类名：</td>
				<td><input name="className" type="text"
					style="width:500px;" />&nbsp;</td>
			</tr>
			<tr>
				<td>VE模板公开性：</td>
				<td><select name="openness">
						<option value="0">公开</option>
						<option value="1">私有</option>
				</select></td>
			</tr>
			<tr>
				<td>VE模板描述文件：</td>
				<td><input name="tplFile" id="tplFile" type="file"
					style="width:500px;" />&nbsp;</td>
			</tr>
			<!-- <tr>
				<td height="27">VE模板服务包名：</td>
				<td><input name="className" id="className" style="width:500px;"
					width=500 />&nbsp;</td>
			</tr> -->
			<tr>
				<td>VE模板服务文件：</td>
				<td><input name="classFile" id="tplClass" type="file"
					style="width:500px;"/>&nbsp;</td>
			</tr>
			<!-- <tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="上传模板"
					style="margin-left:450px;" />
				</td>
			</tr> -->
	 <!-- 	</table>

	</form>  -->
  
</table>
<table cellpadding="0" cellspacing="0" style="width:100%">
          <tr>
            <td width="100%" style="padding:10px;text-align:center;">
	            		<a class="buttonlink" href="#" onclick='document.getElementById("form").submit();return false;'>上&nbsp;&nbsp;传</a>
	            		<a class="buttonlink" href="#" onclick='document.getElementById("form").reset();return false;'>清&nbsp;&nbsp;空</a>
          </tr>
</table>
</form>
</body>
</html>
