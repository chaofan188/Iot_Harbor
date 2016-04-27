<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	<form name="form1" id="form1" method="POST" action="/IoT_Harbor/registVe.html">
		<!--Form1-->
		<table cellpadding="0" cellspacing="1" class="table_form">
			<caption>注册信息</caption>
			<tr>
				<td width="203">VEID:</td>
				<td width="954"><input name="veId" style="width:920px" />
				</td>
				<td width="443"></td>
			</tr>
			<tr>
				<td>VE名称：</td>
				<td><input name="veName" style="width:920px" /></td>
				<td width="443" style="text-align:left;"></td>
			</tr>
			<tr>
				<td>VE所有权：</td>
				<td><select name="privacy"><option value="001">公有</option>
						<option value="002">私有</option>
				</select></td>
				<td width="443" style="text-align:left;"></td>
			</tr>
			<tr>
				<td>VE模板ID：</td>
				<td><input name="veTemplateId" id="vetid" style="width:320px" /><a class="buttonlink"
					onclick="searchgener2();return false;">查询</a>
            	</td>
				<td width="443" style="text-align:left;"></td>
			</tr>
			<tr>
				<td>VE描述：</td>
				<td><textarea name="description"
						style="width:920px;height:100px;"></textarea></td>
				
				<td width="443" style="text-align:left;" valign="bottom">
				<input type="hidden" name="creatorId" value="11" />
				<input type="hidden" name="selection_peid" id="selected_peid" value=""/>
				<a class="buttonlink"
					onclick="$('#form1').submit();return false;">提交</a>
				</td>
			</tr>
		</table>
		<!--Form1End-->
	</form>
	<!--Form1-->
	<script>
		function searchgener() {
			var jStr = Form2Json("#form1");
			LoadAjax(jStr);
		}
		function searchgener2() {
			var jStr = {veTemplateId:$("#vetid").val()};
			LoadAjax(jStr);
		}
		function LoadAjax(postjson) {
			if (postjson == null) {
				/*$('#dg').datagrid('load', {   
					//state: '1'  
				});  */
			} else {
				$('#localdeviceView').datagrid('load', postjson);
			}
		}
		function Linkformatter(value, row, index) {
			//var pid=GetGridValue("#dg",index,2);//获取列明
			return "<a href='B_R_1_1_2_1.html' target='_blank'>" + value
					+ "</a>";
		}
		function Eventformatter(value, row, index) {
			var pid = GetGridValue("#localdeviceView", index, 1);//获取列明
			return "<a href='B_R_1_1_2_1.html' class='buttonlink' target='_blank'>修改</a><a href='B_R_1_1_2_1.html' class='buttonlink' target='_blank'>删除</a><a href='B_R_1_1_2_1.html' class='buttonlink' target='_blank'>激活</a><a href='B_R_1_1_2_1.html' class='buttonlink' target='_blank'>冻结</a><a href='B_R_1_1_2_1.html' class='buttonlink' target='_blank'>授权申请</a>";
		}
		
		function getSelections(){
			var ss = [];
			var rows = $('#localdeviceView').datagrid('getSelections');
			for(var i=0; i<rows.length; i++){
				var row = rows[i];
//				ss.push('<span>'+row.pe_id+":"+row.productid+":"+row.attr1+'</span>');
				ss.push(row.pe_id+',')
			}
			return ss;
		}
		function SelectEvent(r1,r2){
				var VA=(getSelections());
				$("#selected_peid").val(VA);
		}
	</script>
	<table cellpadding="0" cellspacing="1" class="table_form">
		<caption>可绑定的PE列表</caption>
		<tr>
			<td style="padding:0px;">
				<!-- DATAGRID -->
				<table id="localdeviceView" class="easyui-datagrid"
					style="height:300px;"
					data-options="rownumbers:true,singleSelect:false,onSelect:SelectEvent,url:'/IoT_Harbor/peForRegistVe.html',method:'post'">
					<thead>
						<tr style="background:#CCC">
							<th data-options="field:'ck',checkbox:true">全选</th>
							<th data-options="field:'pe_id',width:50,align:'center',formatter:Linkformatter">ID</th>
							<th data-options="field:'pe_name',width:200,align:'center'">PE名称</th>
							<th data-options="field:'pe_owner',width:100,align:'center'">PE所有者</th>
							<th data-options="field:'pe_state',width:100,align:'center'">PE状态</th>
							<th data-options="field:'template_id',width:100,align:'center'">PE模板ID</th>
							<!-- <th data-options="field:'aaa',width:200,align:'center',formatter:Eventformatter">操作</th> -->
						</tr>
					</thead>
				</table> <!-- DATAGRIDEND -->
			</td>
		</tr>
	</table>
	<!--Form1End-->


	<!--
	<table cellpadding="0" cellspacing="0" style="width:100%">
		<tr>
			<td width="5%" style="text-align:right">备注：</td>
			<td width="95%">
				1.普通用户只能看见自己注册的设备或公开性为public的设备。管理员可以看见所有设备，并只有管理员可以使用修改、删除、激活和冻结</td>
		</tr>
		<tr>
			<td width="5%" style="text-align:right"></td>
			<td width="95%">2.申请授权需关联VE</td>
		</tr>
	</table>
	-->
</body>
</html>
