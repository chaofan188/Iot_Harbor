<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<script type="text/javascript">
		var ws=null;
		function startServer(){
		
			var url = "ws://192.168.12.51:8080/IoT_Harbor/websocketInterface";
			
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
				//alert("receive message:"+event.data);
				document.getElementById("response").value=event.data;
			};
			ws.onclose=function(){
				alert("closed!");
			};
		}
		 function sendMessage(){
			var veid = document.getElementById("veid").value;
			var key = document.getElementById("key").value;
			var control = document.getElementById("selectControl").value;
			var str = '{"veid":"'+veid+'","key":"'+key+'","service":"'+control+'"}';
			if(ws!=null&&str!=""){
				ws.send(str);
			}
		} 
	</script>
  </head>
  
  <body onload="startServer()">
 
   		id:<input type="text" name="veid" id="veid" size="20" value="iot_bj_cetc15:lamp*"/><br/>
   		key:<input type="text" name="key" id="key" size="20" value="11"/>
   		<br/>
   	   <select name="selectControl" id="selectControl">
   			<option value="on">on</option>
   			<option value="off">off</option>
   		</select>
   		<input type="button"  onclick="sendMessage()" value="Send"><br/><br/><br/><br/>
   		<input type="text" name="response" id="response" size="60"><br/> 
 
  </body>
</html>
