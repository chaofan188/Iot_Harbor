<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userName = request.getParameter("userName");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Phpcmsç½ç«ç®¡ç - Powered by Phpcms 2008 sp4</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8>">
<link rel="stylesheet" type="text/css" href="../../images/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="../../images/easyui/icon.css">
<script type="text/javascript" src="../../images/js/newjs/jquery.min.js"></script>
<script language="JavaScript" src="../../images/js/newjs/jquery.easyui.min.js"></script>
<style>
body {
	width:100%;
	background:#ECF7FE url(../../admin/skin/images/bg_body.jpg) repeat-y top left;
	scrollbar-face-color: #E7F5FE; 
	scrollbar-highlight-color: #006699; 
	scrollbar-shadow-color: #006699; 
	scrollbar-3dlight-color: #E7F5FE; 
	scrollbar-arrow-color: #006699; 
	scrollbar-track-color: #E7F5FE; 
	scrollbar-darkshadow-color: #E7F5FE; 
	scrollbar-base-color: #E7F5FE;
	}
</style>

</head>
<body scroll="no">
		<div style="width:220px;margin-left:-10px;margin-top:-5px;text-align:left;">
			<script>
			   function buildSiteMapItem(node)
			   {
				   return "<a href=\"#\" onclick=\"return false;\">"+node.text+"</a>"
			   }
			   function setSiteMapBar(node)
			   {
					var NavBarContent=node.text;
				    var getParent=$('#tt').tree('getParent', node.target);
					while(getParent!=null)
					{
						NavBarContent=buildSiteMapItem(getParent)+NavBarContent;
						getParent=$('#tt').tree('getParent', getParent.target);
					}
					$(window.parent.document).contents().find("#position").html("<strong>地址导航：</strong>"+NavBarContent);
			   }
			   function doTreeMenu(node)
			   {
				    var isLeaf=$('#tt').tree('isLeaf', node.target);
					if(isLeaf)
					{
						setSiteMapBar(node);
						window.parent.frames["right"].location=node.attributes.url+"?userName=<%=userName %>";
					}
			   }
			</script>
			<ul id="tt" class="easyui-tree" data-options="url:'MenuJson.json',onClick:doTreeMenu,method:'get',animate:true"></ul>
		</div>
</body>
</html>
