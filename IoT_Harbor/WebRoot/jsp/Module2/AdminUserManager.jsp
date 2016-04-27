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
				   	function Eventformatter(value,row,index)
			        {
				        var pid=GetGridValue("#localdeviceView1",index,0);//获取列明
				        return "<a href='' class='buttonlink' onclick='Event_Open(\""+pid+"\");return false;' target='_blank'>启用</a><a href='' class='buttonlink' onclick='Event_Close(\""+pid+"\");return false;' target='_blank'>冻结</a>";
				
			        }
					function Event_Open(ID)
			       {
						window.location.href="/IoT_Harbor/adminManageOpen.html?userName="+ID;
			        }
			        function Event_Close(ID)
			       {
						window.location.href="/IoT_Harbor/adminManageClose.html?userName="+ID;
			       } 
					</script>
	
<table cellpadding="0" cellspacing="1" class="table_form">
		<caption>用户信息</caption>
		<tr>
			<td style="padding:0px;">
				<!-- DATAGRID -->
				<table id="localdeviceView1" class="easyui-datagrid"
					style="height:200px;"
					data-options="rownumbers:true,singleSelect:true,url:'/IoT_Harbor/queryAllUser.html',method:'post'">
					
					     <thead>
                            <tr style="background:#CCC">
                              <!-- <th data-options="checkbox:true">全选</th> -->
                              <th data-options="field:'userName',width:100,align:'center',formatter:Linkformatter">用户名</th>
                              <th data-options="field:'userRealName',width:100,align:'center'">姓名</th>
                              <th data-options="field:'registerTime',width:200,align:'center'">注册时间</th>
                              <th data-options="field:'email',width:100,align:'center'">邮箱</th>
                              <th data-options="field:'address',width:100,align:'center'">工作单位</th>
                              <th data-options="field:'tel',width:100,align:'center'">电话</th>
                              <th data-options="field:'state',width:100,align:'center'">状态</th>
                              <th data-options="field:'cc',width:200,align:'center',formatter:Eventformatter,height:50">操作</th> 
                            </tr>
                    </thead>
				</table> <!-- DATAGRIDEND --></td>
		</tr>
	</table>

</body>
</html>