<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8>" />
<title>系统登录</title>
<script language="javascript" src="../../data/config.js"></script>
<script language="javascript" src="../../images/js/jquery.min.js"></script>
<script language="javascript" src="../../images/js/common.js"></script>
<script language="JavaScript" src="../../images/js/login.js"></script>

<style type="text/css">
<!--
*{margin:0; padding:0;}
body {font-family: Arial, Helvetica, sans-serif,"宋体"; font-size: 12px;line-height: 210%;font-weight: normal;color: #333333;text-decoration: none;background: #0cf url(../../images/03.jpg) repeat-x 0 0 ;}
li{ list-style:none;}
input {	font-family:"宋体";	font-size:12px;	border:1px solid #dcdcdc;height:18px;line-height:18px; padding-left:2px;}
#main{ background:url(../../images/01.jpg) no-repeat 300px 0; width:930px; min-height:600px; height:600px; overflow:hidden; margin:0 auto; position:relative;}
#login_box{	width:278px; height:138px; background:url(../../images/02.jpg) no-repeat 0 0;	position:absolute; top:228px; left:380px; padding-left:50px; padding-top:50px;line-height:138px;}
#login_box ul li{ line-height:32px; height:32px;}
.btn{ background:url(../../images/05.gif) no-repeat 0 0; height:20px; width:58px; border:0; cursor:pointer; color:#fff; line-height:20px;}
-->
</style>

  </head>
  
  <body onload="javascript:document.myform.username.focus();">
<div id="main">
  <div id="login_box">
    <ul>
    <form name="myform" method="post" action="/IoT_Harbor/userRegister.html">
      <li>用户名：<input name="username" type="text" size="18" value="" maxlength="20"> <a href="../../harbor_login.jsp">已有账户？</a></li>
	  <li>密　码：<input name="password" type="password" size="18" value="" maxlength="32"></li>
	  <li>重　复：<input name="re-password" type="password" size="18" value="" maxlength="32"></li>
      <li style=" padding-left:48px;">
	    <input type="submit" name="dosubmit" value=" 注册 " class="btn"> 
	    <input type="reset" name="reset" value=" 清除 " class="btn">
      </li>
    </form>
    </ul>
  </div>
</div>
</body>
</html>
