<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    		<script type="text/javascript" src="js/frontevents.js"></script>
    		<script type="text/javascript" src="js/user.js"></script>
    		<script type="text/javascript" src="js/BitchJSON.js"></script>
    		<script type="text/javascript" src="js/FormEngine.js"></script>
    		<script type="text/javascript" src="js/modules/userinterface.js"></script>
    		<script type="text/javascript" src="/user_debug.js"></script>
    		<script>var pageroot="./"</script>
    <link rel="stylesheet" type="text/css" href="./css/defcss.css"/>
    <style>
		body
		{
			margin:0px; padding:0px;
		}
	</style>
<title>结果</title>

  </head>
  
  <body>
   <table width="827" border="0" cellspacing="0" cellpadding="0" style="margin:0 auto;">
  <tr>
    <td>&nbsp;</td>
    <td width=755 height=97 valign="top"><a href="#" onclick="redirectbyrole(100);"><img src="images/loginui/reglogo.png" style="border:none;"/></a></td>
    <td>&nbsp;</td>
  </tr>
<!--   <tr>
    <td>&nbsp;</td>
    <td width=755 height=35 valign="top" style="background:url(images/loginui/regl.png) no-repeat;">&nbsp;</td>
    <td>&nbsp;</td>
  </tr> -->
  <tr>
    <td width=36 style="background:url(images/loginui/l-bar.png) bottom no-repeat;">&nbsp;</td>
    <td width=755 height=360 style="font-size:12px;font-family:'微软雅黑', '黑体';line-height:32px; background:url(images/loginui/regbg.png)">
          
          
          result1:${state }
                    
       
    </td>
    <td width=36 style="background:url(images/loginui/r-bar.png) bottom no-repeat;">&nbsp;</td>
  </tr>
  <tr>
    <td style="border-top:1px solid #f5f5f5;color:#666666;">&nbsp;</td>
    <td height=75 align="center" valign="top" style="border-top:1px solid #f5f5f5;color:#666666;font-size:12px;padding-top:10px;">中国电子科技集团公司 第十五研究所</td>
    <td style="border-top:1px solid #f5f5f5;color:#666666;">&nbsp;</td>
  </tr>
</table>
<script>
ClearUserForm(false);
</script>
  </body>
</html>
