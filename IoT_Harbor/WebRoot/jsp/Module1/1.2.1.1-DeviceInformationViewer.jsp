<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String device_name = request.getParameter("device_name");
String belongto = request.getParameter("belongto");
String device_picture = request.getParameter("device_picture");
String pe_lifecycle = request.getParameter("pe_lifecycle");
String pe_state = request.getParameter("pe_state");
String longitude = request.getParameter("longitude");
String latitude = request.getParameter("latitude");
String altitude= request.getParameter("altitude");
String device_identify = request.getParameter("device_identify");
String device_description = request.getParameter("device_description");
if(device_name == null){
   device_name = "";
}
if(belongto == null){
   belongto = "";
}
if(device_picture == null){
   device_picture = "";
}
if(pe_lifecycle == null){
   pe_lifecycle = "";
}
if(pe_state == null){
   pe_state = "";
}
if(longitude == null){
   longitude = "";
}
if(latitude == null){
   latitude = "";
}
if(altitude == null){
   altitude = "";
}
if(device_identify==null){
  device_identify="";
}
if(device_description==null){
device_description ="";
}  
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8>">
<title>全部信息-管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link rel="stylesheet" type="text/css" href="../../admin/skin/system.css" >
<script type="text/javaScript" src="../../images/js/newjs/jquery.min.js"></script>
<script type="text/javaScript" src="../../images/js/newjs/jquery.easyui.min.js"></script>


<script type="text/javascript" src="../../images/js/newjs/datagrid_adapter.js"></script>
<script type="text/javascript" src="../../images/js/newjs/typeSelecter.js"></script>
<script type="text/javascript" src="../../images/js/newjs/FormEngine.js"></script>
<!--<script type="text/javaScript" src="images/js/css.js"></script>
<script type="text/javaScript" src="data/config.js"></script>
<script type="text/javaScript" src="images/js/common.js"></script>
<script type="text/javaScript" src="images/js/admin.js"></script>
<script type="text/javascript" src="images/js/validator.js"></script>
<script type="text/javascript" src="images/js/form.js"></script>-->
<link rel="stylesheet" type="text/css" href="../../images/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="../../images/js/newjs/easyui-balthasar.css">
</head>
<body>
<!--Form1-->
<form name="form1" id="form1" method="POST" action="">
<table cellpadding="0" cellspacing="1" class="table_form">
  <caption>
	设备基本信息
  </caption>
  <tr>
    <td width="151">名称:</td>
     <td><input id="device_name" name="device_name" style="width:300px" value="<%=device_name %>"/></td>
   
  </tr>
  <tr>
    <td width="151">类型:</td>
     <td><input id="type" name="type" style="width:300px" value="${type}"/></td>
    
  </tr>
  <tr>
    <td width="151">型号:</td>
     <td><input id="model" name="model" style="width:300px" value="${model}"/></td>
  </tr>
  <tr>
    <td width="151">物体编号:</td>
     <td><input id="device_identify" name="device_identify" style="width:300px" value="<%=device_identify %>"/></td>
   <!--  <td> <a class="buttonlink" >查看条码</a></td> -->
  </tr>
  <tr>
    <td width="151">设备图片:</td>
    <td style="padding:0px;height:200px;"><img src="${picture_url}" width="264" height="195"/></td>
  </tr>
  <tr>
    <td width="151">隶属组织:</td>
     <td><input id="belongto" name="belongto" style="width:300px" value="<%=belongto %>"/></td>
   
  </tr>
  <tr>
    <td width="151">行政区划:</td>
     <td><input id="OtherAddress" name="OtherAddress" style="width:300px" value="${OtherAddress}"/></td>
  </tr>
  <tr>
    <td width="151">地理位置:</td>
    <td style="padding:0px;">
        <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td style="width:200px;">
                            <table cellpadding="0" cellspacing="0">
                                      <tr>
                                        <td>坐标</td>
                                        <td><input id="point" name="point" style="width:300px" value="${point}"/></td>
                                      </tr>
                                      <tr>
                                        <td>经度</td>
                                        <td><input id="longitude" name="longitude" style="width:300px" value="<%= longitude %>"/></td>
                                      </tr>
                                      <tr>
                                        <td>纬度</td>

                                         <td><input id="latitude" name="latitude" style="width:300px" value="<%=latitude%>"/></td>
                                      </tr>
                                      <tr>
                                        <td>海拔高度</td>
                                        <td><input id="altitude" name="altitude" style="width:300px" value="<%=altitude%>"/></td>
                                      </tr>
                            </table>
                    </td>
                    <td style="width:200px;height:200px;background:#0F0;">地图控件</td>
                  </tr>
        </table>
    </td>
  </tr>
  <tr>
    <td width="151">设备描述:</td>
    <td><input id="device_description" name="device_description" style="width:300px" value="<%=device_description%>"/></td>
    <td></td>                                                                                  
  </tr>
  <tr>
    <td width="151">设备状态:</td>
     <td><input id="device_status" name="device_status" style="width:300px" value="<%=pe_state%>"/></td>
  </tr>
  <tr>
    <td width="151">生命周期:</td>
     <td><input id="lifecycle" name="lifecycle" style="width:300px" value="<%=pe_lifecycle%>"/></td>
  </tr>
<!--   <tr>
            <td width="28%" style="text-align:left;">
                <a class="buttonlink" onclick="searchgener();return false;">查询现有模板</a>
            </td>
  </tr> -->
</table>
<!--Form1End-->

 <script>
       function Event_Active()
			{
			
				 var ID = "<%=device_identify %>";
				$.ajax({type:"GET",
				   	url:"/IoT_Harbor/mydevice_active.html",
						data:"ID="+"<%=device_identify %>",
					success:function(json)
				   	{
							if(json=="success")
							{
					//			alert("成功！");
							}else
							{
					///			alert('Failure');
							}
					},
					error:function()
					{
					//	alert("NetError");
					}
				   });
				//    alert("b");
			}
			function Event_Unactive()
			{
				
				$.ajax({type:"GET",
				   	url:"/IoT_Harbor/mydevice_unactive.html",
					data:"ID="+"<%=device_identify %>",
					success:function(json)
				   	{
							if(json=="success")
							{
						////		alert("成功！");
							}else
							{
						//	alert('Failure');
							}
					},
					error:function()
					{
					//	alert("NetError");
					}
				   });
			}
      
			function searchgener()
			{
			    alert("a");
			    var jStr=Form2Json("#form1");
				alert(jStr);
				LoadAjax(jStr);
			}
			function LoadAjax(postjson)
			{
				if(postjson==null)
				{
					
				}else
				{
					$('#localdeviceView').datagrid('load',postjson);
				}
			}
		
			</script>

<form>
<!--Form1-->
<table cellpadding="0" cellspacing="0" style="width:100%">
          <tr>
            <td width="100%" style="padding:0px;text-align:center;">
	            		<a class="buttonlink" href="#" onclick="Event_Active();return false;">激活</a>
	            		<a class="buttonlink" href="#" onclick="Event_Unactive();return false;" >冻结</a>
	            	<!-- 	<a class="buttonlink" >解冻</a> -->
	            		<a class="buttonlink" >查看</a>
          </tr>
</table>
<!--Form1End-->

<!--Form1-->


<table cellpadding="0" cellspacing="1" class="table_form" style="width:98%">
          <caption>
            设备历史状态
          </caption>
          <tr>
            <td style="padding:0px;">
            <!-- DATAGRID -->
            		<script>
					function Linkformatter(value,row,index)
					{
						//var pid=GetGridValue("#dg",index,2);//获取列明
						return "<a href='B_R_1_1_2_1.html' target='_blank'>"+value+"</a>";

					}
					</script>
            <table id="localdeviceView" class="easyui-datagrid" style="height:200px;"
                        data-options="rownumbers:true,singleSelect:true,url:'/IoT_Harbor/historydata.html',method:'post'">
			        <thead>
                            <tr style="background:#CCC">
                                <th data-options="field:'ck',checkbox:true">全选</th>
                                <th data-options="field:'dataid',width:200,align:'center',formatter:Linkformatter">数据点ID</th>
                              <th data-options="field:'time',width:200,align:'center'">时间</th>
                              <th data-options="field:'interfaces',width:200,align:'center'">接口</th>
                              <th data-options="field:'status',width:100,align:'center'">状态</th>
                              <th data-options="field:'operateve',width:100,align:'center'">操作VE</th>
                              <th data-options="field:'content',width:200,align:'center'">内容</th>
                            </tr>
                    </thead>
            </table>
            <!-- DATAGRIDEND -->
            </td>
          </tr>
</table>
<!--Form1End-->

<!--Form1-->
<table cellpadding="0" cellspacing="1" class="table_form">
          <caption>
            设备授权状态
          </caption>
          <tr>
            <td style="padding:0px;">
            <!-- DATAGRID -->
            <table id="localdeviceView1" class="easyui-datagrid" style="height:200px;"
                        data-options="rownumbers:true,singleSelect:true,url:'/IoT_Harbor/devicestatus.html',method:'post'">
			        <thead>
                            <tr style="background:#CCC">
                                <th data-options="field:'ck',checkbox:true">全选</th>
                              <th data-options="field:'deviceid',width:200,align:'center',formatter:Linkformatter">ID</th>
                                <th data-options="field:'itemname',width:200,align:'center'">实体名称</th>
                              <th data-options="field:'type',width:200,align:'center'">类型</th>
                              <th data-options="field:'time',width:100,align:'center'">申请时间</th>
                              <th data-options="field:'status',width:100,align:'center'">状态</th>
                              <th data-options="field:'description',width:200,align:'center'">请求描述</th>
                            </tr>
                    </thead>
            </table>
            <!-- DATAGRIDEND -->
            </td>
          </tr>
</table>
<!--Form1End-->


<!--Form1-->
<table cellpadding="0" cellspacing="0" style="width:100%">
          <tr>
            <td width="100%" style="padding:10px;text-align:center;">
	            		<a class="buttonlink" >关闭</a>
	            		<a class="buttonlink" >修改</a>
          </tr>
</table>
<!--Form1End-->
</body>
</html>