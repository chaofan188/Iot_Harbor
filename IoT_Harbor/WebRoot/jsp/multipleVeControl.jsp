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
<link rel="stylesheet" type="text/css" href="admin/skin/system.css" >
<script type="text/javaScript" src="images/js/newjs/jquery.min.js"></script>
<script type="text/javaScript" src="images/js/newjs/jquery.easyui.min.js"></script>


<script type="text/javascript" src="images/js/newjs/datagrid_adapter.js"></script>
<script type="text/javascript" src="images/js/newjs/typeSelecter.js"></script>
<!--<script type="text/javaScript" src="images/js/css.js"></script>
<script type="text/javaScript" src="data/config.js"></script>
<script type="text/javaScript" src="images/js/common.js"></script>
<script type="text/javaScript" src="images/js/admin.js"></script>
<script type="text/javascript" src="images/js/validator.js"></script>
<script type="text/javascript" src="images/js/form.js"></script>-->
<link rel="stylesheet" type="text/css" href="images/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="images/js/newjs/easyui-balthasar.css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<style>
.btn{ background-color:#99d3fb; color:black;height:20px; width:58px; border:0; cursor:pointer;  line-height:20px;}
</style>
	<script type="text/javascript">
	/* function Close()
			{
				window.parent.location.href="/IoT_Harbor/harbor_login.jsp";
			} */
	</script>
	<script type="text/javascript">
		var ws=null;
		function doClient(){
		
		}
		function startServer(){
			var url = "ws://192.168.12.40:8080/IoT_Harbor/websocketInterface";
			
			if("WebSocket" in window){
			
				ws=new WebSocket(url);
			}else if("MozWebSocket" in window){
			
				ws=new MozWebSocket(url);
				
				//alert(ws.toString());
			}else{
				alert("unsupported");
				return;
			}
			ws.open = function(){
				alert("opened!");
			};
			ws.onmessage=function(event){
				document.getElementById("response").value=event.data;
			};
			ws.onclose=function(){
				alert("closed!");
			};
		}
		 function sendMessage(){
			var veId = document.getElementById("veId").value;
			var key = document.getElementById("key").value;		
			var serviceId = document.getElementById("serviceId").value;
			var param = document.getElementById("param").value;
			alert(param);
			//var str = '{"veId":"'+veId+'","serviceId":"'+serviceId+'","key":"'+key+'","serviceName":"getValueControl","switcher":"'+switcher+'","refreshTime":"'+refreshTime+'","maxValue":"'+maxValue+'","minValue":"'+minValue+'"}';
	        var str = '{"veId":"'+veId+'","serviceId":"'+serviceId+'","key":"'+key+'","is_atom":"1","param":{'+param+'}}';
	        alert(str);
			if(ws!=null&&str!=""){
				ws.send(str);
			}
		} 
	</script>
  </head>
  
  <body  onload="doClient();startServer()">
 
 	<form id="Mainform" name="Mainform" >
		<table cellpadding="0" cellspacing="1" class="table_form" id="table_form">
  			<caption>
 			 复合VE使用
  			</caption>

   			<tr>
   		    	<td width="151"><br>veID:</td>
   		    	<td><input type="text" name="veId" id="veId" value="102150605112127192168121511009"  style="width:500px"/></td>
   			</tr>
   			<tr>
   				<td width="151">serviceID:</td>
   				<td><input type="text" name="serviceId" id="serviceId" value="105150327143545192168121531004"  style="width:500px"></td>
   			</tr>
 			<tr>
 				<td width="151">key:</td>
 				<td><input type="text" name="key" id="key"  value="11" style="width:500px"/></td>
 			</tr>
 			<tr>
 				<td width="151">param:</td>
 				<td><input type="text" name="param" id="param" placeholder="键值对形式，以逗号分隔，字符串加双引号"  value="" style="width:500px"/></td>
 			</tr>
 			<tr>
 				<td width="151"></td>
 				<td> 					
   					<input style="float:right" type="button"  onclick="sendMessage()" value="Send"  class="btn">   
 				</td>
 			</tr>
 			<tr></tr>
   			<tr>
   				<td width="151">数据</td>
   				<td>
   					<input type="text" name="response" id="response" style="width:500px">
   				</td>
   			</tr>
		</table>
	</form>
  </body>
</html>
