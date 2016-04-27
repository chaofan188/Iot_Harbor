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
		function startServer(){
		
			var url = "ws://192.168.12.51:8880/IoT_Harbor/websocketInterface";
			
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
		 /* function sendMessage(){
			var veId = document.getElementById("veId").value;
			var key = document.getElementById("key").value;
			var direction = document.getElementById("direction").value;
			var str = '{"veId":"'+veId+'","key":"'+key+'","serviceName":"car","direction":"'+direction+'"}';
			if(ws!=null&&str!=""){
				ws.send(str);
			}
		}  */
		function sendMessageForward(){
		    var veId = document.getElementById("veId").value;
			var key = document.getElementById("key").value;
			var str = '{"veId":"'+veId+'","key":"'+key+'","serviceName":"directionControl","is_atom":"2","param":{"direction":"forward","movementAmount":"0"}}';
			if(ws!=null&&str!=""){
				ws.send(str);
			}
		}
		function sendMessageForwardTime(){
		    var veId = document.getElementById("veId").value;
			var key = document.getElementById("key").value;
			var timeValue = document.getElementById("time").value;
			var str = '{"veId":"'+veId+'","key":"'+key+'","serviceName":"directionControl","is_atom":"2","param":{"direction":"forward","movementAmount":"'+timeValue+'"}}';
			if(ws!=null&&str!=""){
				ws.send(str);
			}
		}
		function sendMessageBackward(){
		    var veId = document.getElementById("veId").value;
			var key = document.getElementById("key").value;
			var str = '{"veId":"'+veId+'","key":"'+key+'","serviceName":"directionControl","is_atom":"2","param":{"direction":"backward","movementAmount":"0"}}';
			if(ws!=null&&str!=""){
				ws.send(str);
			}
		}
		function sendMessageBackwardTime(){
		    var veId = document.getElementById("veId").value;
			var key = document.getElementById("key").value;
			var timeValue = document.getElementById("time").value;
			var str = '{"veId":"'+veId+'","key":"'+key+'","serviceName":"directionControl","is_atom":"2","param":{"direction":"backward","movementAmount":"'+timeValue+'"}}';
			if(ws!=null&&str!=""){
				ws.send(str);
			}
		}
		function sendMessageStop(){
			var veId = document.getElementById("veId").value;
			var key = document.getElementById("key").value;
			var str = '{"veId":"'+veId+'","key":"'+key+'","serviceName":"carControl","is_atom":"2","param":{"function":"stop","movementAmount":"0"}}';
			if(ws!=null&&str!=""){
				ws.send(str);
			}
		}
		function sendMessageLeft(){
			var veId = document.getElementById("veId").value;
			var key = document.getElementById("key").value;
			var str = '{"veId":"'+veId+'","key":"'+key+'","serviceName":"directionControl","is_atom":"2","param":{"direction":"left","movementAmount":"0"}}';
			if(ws!=null&&str!=""){
				ws.send(str);
			}
		}
		function sendMessageLeftTime(){
		    var veId = document.getElementById("veId").value;
			var key = document.getElementById("key").value;
			var timeValue = document.getElementById("time").value;
			var str = '{"veId":"'+veId+'","key":"'+key+'","serviceName":"directionControl","is_atom":"2","param":{"direction":"left","time":"'+timeValue+'","asdfs":"asdfsValue"}}';
			if(ws!=null&&str!=""){
				ws.send(str);
			}
		}
		function sendMessageRight(){
			var veId = document.getElementById("veId").value;
			var key = document.getElementById("key").value;
			var str = '{"veId":"'+veId+'","key":"'+key+'","serviceName":"directionControl","is_atom":"2","param":{"direction":"right","movementAmount":"0"}}';
			if(ws!=null&&str!=""){
				ws.send(str);
			}
		}
		function sendMessageRightTime(){
		    var veId = document.getElementById("veId").value;
			var key = document.getElementById("key").value;
			var timeValue = document.getElementById("time").value;
			var str = '{"veId":"'+veId+'","key":"'+key+'","serviceName":"directionControl","is_atom":"2","param":{"direction":"right","movementAmount":"'+timeValue+'"}}';
			if(ws!=null&&str!=""){
				ws.send(str);
			}
		}
		function sendMessageIR1(){
			var veId = document.getElementById("veId").value;
			var key = document.getElementById("key").value;
			var str = '{"veId":"'+veId+'","key":"'+key+'","serviceName":"carControl","is_atom":"2","param":{"function":"1"}}';
			if(ws!=null&&str!=""){
				ws.send(str);
			}
		}
		function sendMessageIR2(){
			var veId = document.getElementById("veId").value;
			var key = document.getElementById("key").value;
			var str = '{"veId":"'+veId+'","key":"'+key+'","serviceName":"carControl","is_atom":"2","param":{"function":"2"}}';
			if(ws!=null&&str!=""){
				ws.send(str);
			}
		}
		function sendMessageIR3(){
			var veId = document.getElementById("veId").value;
			var key = document.getElementById("key").value;
			var str = '{"veId":"'+veId+'","key":"'+key+'","serviceName":"carControl","is_atom":"2","param":{"function":"3"}}';
			if(ws!=null&&str!=""){
				ws.send(str);
			}
		}
		function sendMessageIR4(){
			var veId = document.getElementById("veId").value;
			var key = document.getElementById("key").value;
			var str = '{"veId":"'+veId+'","key":"'+key+'","serviceName":"carControl","is_atom":"2","param":{"function":"4"}}';
			if(ws!=null&&str!=""){
				ws.send(str);
			}
		}
		function sendMessageIR5(){
			var veId = document.getElementById("veId").value;
			var key = document.getElementById("key").value;
			var str = '{"veId":"'+veId+'","key":"'+key+'","serviceName":"carControl","is_atom":"2","param":{"function":"5"}}';
			if(ws!=null&&str!=""){
				ws.send(str);
			}
		}
		function sendMessageX(){
			var veId = document.getElementById("veId").value;
			var key = document.getElementById("key").value;
			var xValue = document.getElementById("x").value;
			var str = '{"veId":"'+veId+'","key":"'+key+'","serviceName":"x","is_atom":"2","param":{"movementAmount":"'+xValue+'"}}';
			if(ws!=null&&str!=""){
				ws.send(str);
			}
		}
		function sendMessageY(){
			var veId = document.getElementById("veId").value;
			var key = document.getElementById("key").value;
			var xValue = document.getElementById("y").value;
			var str = '{"veId":"'+veId+'","key":"'+key+'","serviceName":"y","is_atom":"2","param":{"movementAmount":"'+xValue+'"}}';
			if(ws!=null&&str!=""){
				ws.send(str);
			}
		}
		
	</script>
  </head>
  
  <body onload="startServer()">
  
    <form id="Mainform" name="Mainform" >
		
    
<!-- 		<table width="800" border="0" id="ddd"
			style="margin:0 auto;margin-top:0px;margin-bottom:20px;"> -->
			
<table cellpadding="0" cellspacing="1" class="table_form" id="table_form">
  <caption>
  控制类VE使用
  </caption>

   		<tr>
   		    <td width="151"><br>id:</td>
   		    <td><input type="text" name="veId" id="veId" value="102150605112127192168121511006" style="width:500px"/></td>
   		</tr>
   		<tr>
   		    <td width="151">key:</td>
   		    <td><input type="text" name="key" id="key"  value="11" style="width:500px"/></td>
   		</tr>
   		<!-- <select name="selectControl" id="selectControl">
   			<option value="forward">forward</option>
   			<option value="stop">stop</option>
   			<option value="backward">backward</option>
   			<option value="left">left</option>
   			<option value="right">right</option>
   		</select>
   		on<input type="radio" name="control" id="control" value="on"/>
   		off<input type="radio" name="control" id="control" value="off"/>
   		<input type="button"  onclick="sendMessage()" value="Send"><br/><br/><br/><br/>
   		<input type="text" name="response" id="response" size="60"><br/> -->
   		<tr>
   		    <td width="151">控制：</td>
   		    <td>
   		    	<input type="button"  onclick="sendMessageForward()" value="forward" class="btn">
   		    	<input type="button"  onclick="sendMessageBackward()" value="backward" class="btn">
   		    	<input type="button"  onclick="sendMessageLeft()" value="left" class="btn">
   		    	<input type="button"  onclick="sendMessageRight()" value="right" class="btn">
   		    	<input type="button"  onclick="sendMessageStop()" value="stop" class="btn">
   		    </td>
   		</tr>
   		<tr>
   			<td width="151"></td>
   			<td>
   				<input type="button"  onclick="sendMessageIR1()" value="开" class="btn">
   				<input type="button" onclick="sendMessageIR2()" value="关" class="btn">
   				<input type="button" onclick="sendMessageIR3()" value="ir3" class="btn">
   				<input type="button" onclick="sendMessageIR4()" value="ir4" class="btn">
   				<input type="button" onclick="sendMessageIR5()" value="ir5" class="btn">
   			</td>
   		</tr>
   		<tr>
   			<td width="151"></td>
   			<td>
   				<input type="text" name="time" id="time" size="5" value="0.3">
   				<input type="button" onclick="sendMessageForwardTime()" value="forwardTime" class="btn" style="width:100px">
   				<input type="button" onclick="sendMessageBackwardTime()" value="backwardTime" class="btn" style="width:100px">
   				<input type="button" onclick="sendMessageLeftTime()" value="leftTime" class="btn" style="width:100px">
   				<input type="button" onclick="sendMessageRightTime()" value="rightTime" class="btn" style="width:100px">
   			</td>
   		</tr>
		<tr>
			<td width="151"></td>
			<td>
				<input type="text" name="x" id="x" size="5">
				<input type="button"  onclick="sendMessageX()" value="x" class="btn">
				<input type="text" name="y" id="y" size="5">
   				<input type="button"  onclick="sendMessageY()" value="y" class="btn">
			</td>
		</tr>
        <tr>
       		<td width="151"></td>
       		<td>
       			<form id="form1">
       			<div id="VIDEO" style="width:640px;height:480px;"></div>
       			<script>
				function LoadVideo()
				{
					var videoURL="http://192.168.12.177:8080/";
					var resetCGI="http://192.168.12.51:8080/IoT_Harbor/sendResetCommand.html";"http://192.168.12.177/cgi-bin/zfw_robot/resetWebCam";//"http://192.168.12.151:8080/IoT_Harbor/sendResetCommand.html";   
					if((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0))
					{
						var CODE="<object classid=\"clsid:FA274BCC-7F50-484B-ABB3-503D79ED2F8F\" width=\"100%\" height=\"100%\" id=\"Player\"><param name=\"VideoURL\" value=\""+videoURL+"\"><param name=\"ResetCGI\" value=\""+resetCGI+"\"></object>";
						window.document.getElementById("VIDEO").innerHTML=CODE;
					}else
					{
						var CODE="<object clsid=\"{FA274BCC-7F50-484B-ABB3-503D79ED2F8F}\" id=\"Player\" width=\"100%\" height=\"100%\" TYPE=\"application/x-itst-activex\" param_ResetCGI=\""+resetCGI+"\" param_VideoURL=\""+videoURL+"\"></object>"
						window.document.getElementById("VIDEO").innerHTML=CODE;
					}
				}
				</script>
				</form>
       		</td>
       	</tr>
        <tr>
        	<td width="151"></td>
        	<td>
        		<input type="button" value="加载小车摄像头" onclick="LoadVideo();"class="btn" style="width:100px"/>
        	</td>
        </tr>
       
    </table>
    </form>
 
  </body>
</html>
