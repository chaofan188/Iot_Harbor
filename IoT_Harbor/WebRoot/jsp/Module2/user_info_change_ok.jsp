<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userName = request.getParameter("userName");
String email = request.getParameter("email");
String realname = request.getParameter("realname");
String tel = request.getParameter("tel");
String address = request.getParameter("address");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user_info_change_ok.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    用户信息修改成功！ <br>
    
    <a href="/IoT_Harbor/jsp/Module2/UserInformationChanger.jsp?userName=<%=userName %>&email=<%=email%>&realname=<%=realname %>&tel=<%=tel%>&address=<%=address%>">返回</a>
  </body>
</html>
