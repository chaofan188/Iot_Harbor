<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8>">
<title>全部信息-管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link rel="stylesheet" type="text/css"
	href="../../admin/skin/system.css">
<script type="text/javaScript" src="../../images/js/newjs/jquery.min.js"></script>
<script type="text/javaScript"
	src="../../images/js/newjs/jquery.easyui.min.js"></script>


<script type="text/javascript"
	src="../../images/js/newjs/datagrid_adapter.js"></script>
<script type="text/javascript"
	src="../../images/js/newjs/typeSelecter.js"></script>
<!--<script type="text/javaScript" src="images/js/css.js"></script>
<script type="text/javaScript" src="data/config.js"></script>
<script type="text/javaScript" src="images/js/common.js"></script>
<script type="text/javaScript" src="images/js/admin.js"></script>
<script type="text/javascript" src="images/js/validator.js"></script>
<script type="text/javascript" src="images/js/form.js"></script>-->
<link rel="stylesheet" type="text/css"
	href="../../images/easyui/easyui.css">
<link rel="stylesheet" type="text/css"
	href="../../images/js/newjs/easyui-balthasar.css">
</head>
<body>

	<script>

					function Linkformatter(value,row,index)
					{
						//var pid=GetGridValue("#dg",index,2);//获取列明
						return "<a href='B_R_1_1_2_1.html' target='_blank'>"+value+"</a>";

					}
					function Linkformatter1(value,row,index)
					{
						//var pid=GetGridValue("#dg",index,2);//获取列明
						return "<a href='B_R_1_1_2_1.html' target='_blank'>"+value+"</a>";

					}
				   	function Eventformatter(value,row,index)
			        {
				        var pid=GetGridValue("#localdeviceView1",index,1);//获取列明
				        return "<a href='' class='buttonlink' onclick='Event_Query(\""+pid+"\");return false;' target='_blank'>查看</a><a href='' class='buttonlink' onclick='Event_Jujue(\""+pid+"\");return false;' target='_blank'>拒绝</a>";
				
			        }
					function Eventformatter1(value,row,index)
			        {
				        var pid=GetGridValue("#localdeviceView2",index,1);//获取列明
				        return "<a href='' class='buttonlink' onclick='Event_Query(\""+pid+"\");return false;' target='_blank'>查看</a><a href='' class='buttonlink' onclick='Event_Author(\""+pid+"\");return false;' target='_blank'>授权</a>";
				
			        }
			        function Eventformatter3(value,row,index)
			        {
				        var pid=GetGridValue("#localdeviceView3",index,1);//获取列明
				        return "<a href='' class='buttonlink' onclick='Event_Query(\""+pid+"\");return false;' target='_blank'>查看</a><a href='' class='buttonlink' onclick='Unview_Event_Jujue(\""+pid+"\");return false;' target='_blank'>拒绝</a><a href='' class='buttonlink' onclick='Unview_Event_Author(\""+pid+"\");return false;' target='_blank'>授权</a>";
				
			        }
			        
			        function Unview_Event_Jujue(ID)
			       {
				
				     $.ajax({type:"GET",
				   	         url:"/IoT_Harbor/unviewprovidemanager_jujue.html",
					         data:"ID="+ID,
					         success:function(json)
				         	 {
							   if(json=="success")
							   {
							 //	alert("成功！");
							   }else
							   { 
							//	alert('Failure');
							   }
					          },
					          error:function()
					          {
						   //     alert("NetError");
					           }
				                });
			        }
			        function Unview_Event_Author(ID)
			       {
				
				     $.ajax({type:"GET",
				   	         url:"/IoT_Harbor/unviewprovidemanager_author.html",
					         data:"ID="+ID,
					         success:function(json)
				         	 {
							   if(json=="success")
							   {
							 //	alert("成功！");
							   }else
							   { 
							//	alert('Failure');
							   }
					          },
					          error:function()
					          {
						      //  alert("NetError");
					           }
				                });
			        }
					function Event_Jujue(ID)
			       {
				
				     $.ajax({type:"GET",
				   	         url:"/IoT_Harbor/providemanager_jujue.html",
					         data:"ID="+ID,
					         success:function(json)
				         	 {
							   if(json=="success")
							   {
							 //	alert("成功！");
							   }else
							   { 
							//	alert('Failure');
							   }
					          },
					          error:function()
					          {
						   //     alert("NetError");
					           }
				                });
			        }
			        function Event_Author(ID)
			       {
				
				     $.ajax({type:"GET",
				   	         url:"/IoT_Harbor/providemanager_author.html",
					         data:"ID="+ID,
					         success:function(json)
				         	 {
							   if(json=="success")
							   {
							 //	alert("成功！");
							   }else
							   { 
							//	alert('Failure');
							   }
					          },
					          error:function()
					          {
						      //  alert("NetError");
					           }
				                });
			        }
					function Event_Query(ID)
			        {
			
			            window.location.href="/IoT_Harbor/providemanagerquery.html?ID="+ID;
	                } 
					</script>
	<!--Form1-->
<!-- 	<table cellpadding="0" cellspacing="1" class="table_form">
		<caption>待处理授权请求</caption>
		<tr>
			<td style="padding:0px;">
				DATAGRID
				<table id="localdeviceView" class="easyui-datagrid"
					style="height:200px;"
					data-options="rownumbers:true,singleSelect:true,url:'/IoT_Harbor/provideManager.html',method:'post'">
						     <thead>
                            <tr style="background:#CCC">
                                <th data-options="checkbox:true">全选</th>
                              <th data-options="field:'productid1',width:50,align:'center',formatter:Linkformatter">ID</th>
                                <th data-options="field:'devicename1',width:200,align:'center'">设备名称</th>
                              <th data-options="field:'status1',width:100,align:'center'">当前状态</th>
                              <th data-options="field:'lifecycle1',width:100,align:'center'">注册时间</th>
                              <th data-options="field:'belong1',width:100,align:'center'">最后一次修改</th>
                              <th data-options="field:'lifecycle1',width:100,align:'center'">生命周期</th>
                              <th data-options="field:'visable1',width:100,align:'center'">设备所属</th>
                              <th data-options="field:'author1',width:100,align:'center'">设备所属</th>
                              <th data-options="field:'ve1',width:100,align:'center'">关联VE</th>
                              <th data-options="field:'cc',width:300,align:'center',formatter:Eventformatter,height:50">操作</th> 
                            </tr>
                    </thead>
				</table> DATAGRIDEND</td>
		</tr>
	</table> -->
	<!--Form1End-->
	<table cellpadding="0" cellspacing="1" class="table_form">
		<caption>未处理的请求</caption>
		<tr>
			<td style="padding:0px;">
				<!-- DATAGRID -->
				<table id="localdeviceView3" class="easyui-datagrid"
					style="height:200px;"
					data-options="rownumbers:true,singleSelect:true,url:'/IoT_Harbor/unauthor.html',method:'post'">
					
					     <thead>
                            <tr style="background:#CCC">
                                <th data-options="checkbox:true">全选</th>
                              <th data-options="field:'productid',width:50,align:'center',formatter:Linkformatter">ID</th>
                                <th data-options="field:'devicename',width:200,align:'center'">设备名称</th>
                              <th data-options="field:'status',width:100,align:'center'">当前状态</th>
                              <th data-options="field:'lifecycle',width:100,align:'center'">生命周期</th>
                                <th data-options="field:'visable',width:100,align:'center'">可见性</th>
                                <th data-options="field:'belong',width:100,align:'center'">设备所属</th>
                                
                              <th data-options="field:'applyuser',width:100,align:'center'">申请用户</th>
                     
                              <th data-options="field:'ve',width:100,align:'center'">关联VE</th>
                              <th data-options="field:'cc1',width:300,align:'center',formatter:Eventformatter3,height:50">操作</th> 
                            </tr>
                    </thead>
				</table> <!-- DATAGRIDEND --></td>
		</tr>
	</table>
	<!--Form1-->
<table cellpadding="0" cellspacing="1" class="table_form">
		<caption>已处理且通过授权请求</caption>
		<tr>
			<td style="padding:0px;">
				<!-- DATAGRID -->
				<table id="localdeviceView1" class="easyui-datagrid"
					style="height:200px;"
					data-options="rownumbers:true,singleSelect:true,url:'/IoT_Harbor/provideManager.html',method:'post'">
					
					     <thead>
                            <tr style="background:#CCC">
                                <th data-options="checkbox:true">全选</th>
                              <th data-options="field:'productid',width:50,align:'center',formatter:Linkformatter">ID</th>
                                <th data-options="field:'devicename',width:200,align:'center'">设备名称</th>
                              <th data-options="field:'status',width:100,align:'center'">当前状态</th>
                              <th data-options="field:'lifecycle',width:100,align:'center'">生命周期</th>
                                <th data-options="field:'visable',width:100,align:'center'">可见性</th>
                                <th data-options="field:'belong',width:100,align:'center'">设备所属</th>
                                
                              <th data-options="field:'applyuser',width:100,align:'center'">申请用户</th>
                     
                              <th data-options="field:'ve',width:100,align:'center'">关联VE</th>
                              <th data-options="field:'cc',width:300,align:'center',formatter:Eventformatter,height:50">操作</th> 
                            </tr>
                    </thead>
				</table> <!-- DATAGRIDEND --></td>
		</tr>
	</table>
	<!--Form1-->

	<table cellpadding="0" cellspacing="1" class="table_form">
		<caption>已处理且未通过授权请求</caption>
		<tr>
			<td style="padding:0px;">
				<!-- DATAGRID -->
				<table id="localdeviceView2" class="easyui-datagrid"
					style="height:200px;"
					data-options="rownumbers:true,singleSelect:true,url:'/IoT_Harbor/provideManager1.html',method:'post'">
					
				<!-- 	     <thead>
                            <tr style="background:#CCC">
                              <th data-options="checkbox:true">全选</th>
                              <th data-options="field:'productid1',width:50,align:'center',formatter:Linkformatter">ID</th>
                              <th data-options="field:'devicename1',width:200,align:'center'">设备名称</th>
                              <th data-options="field:'status1',width:100,align:'center'">当前状态</th>
                              <th data-options="field:'lifecycle1',width:100,align:'center'">注册时间</th>
                              <th data-options="field:'belong1',width:100,align:'center'">最后一次修改</th>
                              <th data-options="field:'lifecycle1',width:100,align:'center'">生命周期</th>
                              <th data-options="field:'visable1',width:100,align:'center'">设备所属</th>
                              <th data-options="field:'author1',width:100,align:'center'">设备所属</th>
                              <th data-options="field:'ve1',width:100,align:'center'">关联VE</th>
                              <th data-options="field:'cc',width:300,align:'center',formatter:Eventformatter1,height:50">操作</th> 
                            </tr>
                    </thead> -->
                    
					     <thead>
                            <tr style="background:#CCC">
                                <th data-options="checkbox:true">全选</th>
                              <th data-options="field:'productid',width:50,align:'center',formatter:Linkformatter">ID</th>
                                <th data-options="field:'devicename',width:200,align:'center'">设备名称</th>
                              <th data-options="field:'status',width:100,align:'center'">当前状态</th>
                              <th data-options="field:'lifecycle',width:100,align:'center'">生命周期</th>
                                <th data-options="field:'visable',width:100,align:'center'">可见性</th>
                                <th data-options="field:'belong',width:100,align:'center'">设备所属</th>
                                
                              <th data-options="field:'applyuser',width:100,align:'center'">申请用户</th>
                     
                              <th data-options="field:'ve',width:100,align:'center'">关联VE</th>
                              <th data-options="field:'cc1',width:300,align:'center',formatter:Eventformatter1,height:50">操作</th> 
                            </tr>
                    </thead>
				</table> <!-- DATAGRIDEND --></td>
		</tr>
	</table>
	
	
<!-- 	<table cellpadding="0" cellspacing="1" class="table_form">
		<caption>未查看的授权</caption>
		<tr>
			<td style="padding:0px;">
				DATAGRID
				<table id="localdeviceView2" class="easyui-datagrid"
					style="height:200px;"
					data-options="rownumbers:true,singleSelect:true,url:'/IoT_Harbor/unauthor.html',method:'post'">
					
					     <thead>
                            <tr style="background:#CCC">
                              <th data-options="checkbox:true">全选</th>
                              <th data-options="field:'productid1',width:50,align:'center',formatter:Linkformatter">ID</th>
                              <th data-options="field:'devicename1',width:200,align:'center'">设备名称</th>
                              <th data-options="field:'status1',width:100,align:'center'">当前状态</th>
                              <th data-options="field:'lifecycle1',width:100,align:'center'">注册时间</th>
                              <th data-options="field:'belong1',width:100,align:'center'">最后一次修改</th>
                              <th data-options="field:'lifecycle1',width:100,align:'center'">生命周期</th>
                              <th data-options="field:'visable1',width:100,align:'center'">设备所属</th>
                              <th data-options="field:'author1',width:100,align:'center'">设备所属</th>
                              <th data-options="field:'ve1',width:100,align:'center'">关联VE</th>
                              <th data-options="field:'cc',width:300,align:'center',formatter:Eventformatter1,height:50">操作</th> 
                            </tr>
                    </thead>
                    
					     <thead>
                            <tr style="background:#CCC">
                                <th data-options="checkbox:true">全选</th>
                              <th data-options="field:'productid',width:50,align:'center',formatter:Linkformatter">ID</th>
                                <th data-options="field:'devicename',width:200,align:'center'">设备名称</th>
                              <th data-options="field:'status',width:100,align:'center'">当前状态</th>
                              <th data-options="field:'lifecycle',width:100,align:'center'">生命周期</th>
                                <th data-options="field:'visable',width:100,align:'center'">可见性</th>
                                <th data-options="field:'belong',width:100,align:'center'">设备所属</th>
                                
                              <th data-options="field:'applyuser',width:100,align:'center'">申请用户</th>
                     
                              <th data-options="field:'ve',width:100,align:'center'">关联VE</th>
                              <th data-options="field:'cc1',width:300,align:'center',formatter:Eventformatter1,height:50">操作</th> 
                            </tr>
                    </thead>
				</table> DATAGRIDEND</td>
		</tr>
	</table> -->
	<!--Form1End-->

</body>
</html>