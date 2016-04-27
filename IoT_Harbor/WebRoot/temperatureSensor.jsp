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
			var url = "ws://60.170.60.178:8087/IoT_Harbor/websocketInterface";
			
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
			var switcher = document.getElementById("switcher").value;
			
			var obj = document.getElementsByName("refreshTime");
			var refreshTime=5;
			//var maxValue = document.getElementById("maxValue").value;
			//var minValue = document.getElementById("minValue").value;
			var serviceId = document.getElementById("serviceId").value;
			for(var i=0;i<obj.length;i++){
				if(obj[i].checked){
					refreshTime = obj[i].value;
				}
			}
			//var str = '{"veId":"'+veId+'","serviceId":"'+serviceId+'","key":"'+key+'","serviceName":"getValueControl","switcher":"'+switcher+'","refreshTime":"'+refreshTime+'","maxValue":"'+maxValue+'","minValue":"'+minValue+'"}';
	        var str = '{"veId":"'+veId+'","serviceId":"'+serviceId+'","key":"'+key+'","is_atom":"1","param":{"control":"'+switcher+'","time":'+refreshTime+'}}';
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
 			 数据类VE使用
  			</caption>

   			<tr>
   		    	<td width="151"><br>veID:</td>
   		    	<td><input type="text" name="veId" id="veId" value="1021509291937571921681201aaaaa1010"  style="width:500px"/></td>
   			</tr>
   			<tr>
   				<td width="151">serviceID:</td>
   				<td><input type="text" name="serviceId" id="serviceId" value="10515092919375719216812011002"  style="width:500px"></td>
   			</tr>
 			<tr>
 				<td width="151">key:</td>
 				<td><input type="text" name="key" id="key"  value="11" style="width:500px"/></td>
 			</tr>
 			<tr>
 				<td width="151"></td>
 				<td>
 					<select name="switcher" id="switcher" style="width:60px">
   						<option value="on" >on</option>
   						<option value="off">off</option>
   					</select>
   					5s<input type="radio" name="refreshTime" id="refreshTime" value="5" />
   					10s<input type="radio" name="refreshTime" id="refreshTime" value="10" checked="checked"/>
   					&nbsp;&nbsp;
   					<input type="button"  onclick="sendMessage()" value="Send"  class="btn">   
 				</td>
 			</tr>
 			<!-- <tr>
 				<td width="151"></td>
 				<td>
 					maxValue:<input type="text" name="maxValue" id="maxValue" size="20" />
   					minValue:<input type="text" name="minValue" id="minValue" size="20" />
 				</td>
 			</tr> -->
   			<tr>
   				<td width="151">数据</td>
   				<td>
   					<input type="text" name="response" id="response" style="width:500px">
   				</td>
   			</tr>
   		 

   		
   		
 
  </body>
</html>
