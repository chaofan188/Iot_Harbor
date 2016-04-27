<%@page import="com.cetc.iot.harbormanage.service.UserInfo"%>
<%@page import="com.cetc.iot.database.model.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userName = request.getParameter("userName");
UserInfo userInfo = new UserInfo();
User user = userInfo.getUserInfo(userName);
String email = null ;
String realname = null;
String tel = null;
String address = null;
String registertime = null;
if (user != null){
email = user.getEmail();
realname = user.getUserRealName();
tel = user.getTel();
address = user.getAddress();
registertime = user.getRegisterTime();
if (email==null||"null".equalsIgnoreCase(email)){
email = "";
}
if (realname==null||"null".equalsIgnoreCase(realname)){
realname = "";
}
if (tel==null||"null".equalsIgnoreCase(tel)){
tel = "";
}
if (address==null||"null".equalsIgnoreCase(address)){
address = "";
}
if (registertime==null||"null".equalsIgnoreCase(registertime)){
registertime = "";
}
}else {
	
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
	function Close()
			{
				window.parent.location.href="/IoT_Harbor/harbor_login.jsp";
			}
	</script>

</head>
<body>
<!--Form1-->
<form name="form1" id="MainForm" action="/IoT_Harbor/userInfoChange.html">
<table cellpadding="0" cellspacing="1" class="table_form">
  <caption>
  基本信息修改
  </caption>
  <tr>
    <td width="151" >用户名:</td>
    <td><input name="username" id="username" type="text" style="width:300px" value="<%=userName %>" readonly="readonly"></td>
  </tr>
  <tr>
    <td width="151">安全邮箱:</td>
    <td><input name="email" id="objectNo" type="text" style="width:300px" value="<%=email %>"/> <!-- <a class="buttonlink" >发送验证邮件</a></td> -->
  </tr>
  <tr>
    <td width="151">姓名:</td>
    <td><input name="realname" id="Orgv" type="text" style="width:300px" value="<%=realname %>"/></td>
  </tr>
  <tr>
    <td width="151">电话:</td>
    <td><input name="tel" id="Orgv" type="text" style="width:300px"  value="<%=tel %>"/></td>
  </tr>
  <tr>
    <td width="151">工作单位:</td>
    <td><input name="address" id="Orgv" type="text" style="width:300px" value="<%=address %>"/></td>
  </tr>
  <tr>
    <td width="151">注册时间:</td>
    <td><input name="registertime" id="Orgv" type="text" style="width:300px" value="<%=registertime %>" readonly="readonly"/></td>
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
