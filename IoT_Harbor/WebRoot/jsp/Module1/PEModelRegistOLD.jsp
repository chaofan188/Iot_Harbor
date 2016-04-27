<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String device_name = request.getParameter("device_name");
	String template_id = request.getParameter("ID");
	String peid = request.getParameter("peid");
	if (device_name == null) {
		device_name = "";
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8>">
<title>PE模板注册</title>
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
<script type="text/javascript" src="../../images/js/newjs/FormEngine.js"></script>
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

	<!--Form1-->
	<form action="" method="post" name="form1" id="form1">
		<input name="count" id="count" type="hidden" value="">
		<table cellpadding="0" cellspacing="1" class="table_form"
			style="border-bottom:none;">
			<caption>PE模板注册</caption>
			<table>
				<div id="Area1">
					<table cellpadding="0" cellspacing="1" class="table_form"
						style="border-top:none;border-bottom:none;">
						<tr>
							<td width="">模板ID:</td>
							<td><input id="Model_ID" name="Model_ID" value=""
								style="width:300px" /></td>
						</tr>
						<tr>
							<td width="">设备型号:</td>
							<td><input id="Model" name="Model" value=""
								style="width:300px" /></td>
						</tr>
						<tr>
							<td width="">模板名称:</td>
							<td><input id="Name" name="Name" value=""
								style="width:300px" /></td>
						</tr>
						<tr>
							<td width="">设备类型:</td>
							<td><input id="Type" name="Type" value=""
								style="width:300px" /></td>
						</tr>
						<tr>
							<td width="">图片路径:</td>
							<td><input id="Pic_url" name="Pic_url" value=""
								style="width:300px" /></td>
						</tr>
						<tr>
							<td width="">设备描述:</td>
							<td><input id="Description" name="Description" value=""
								style="width:300px" /></td>
						</tr>
					</table>
				</div>

				<table cellpadding="0" cellspacing="1" class="table_form"
					style="border-top:none;">
					<tr>
						<td width="" style="text-align:left;"><a class="buttonlink"
							onclick="add();">增加接口</a>
						</td>
						<td style="text-align:left;"></td>
						<td width="443" style="text-align:left;"></td>
					</tr>

				</table>

				<div id="Area2"></div>
				<div id="Area_submit">
					<table cellpadding="0" cellspacing="1" class="table_form"
						style="border-top:none;">
						<tr>

							<td width="28%" style="text-align:left;"><a
								class="buttonlink" onclick="searchgener();">提交</a>
							</td>
						</tr>
					</table>
				</div>
				<!-- <table cellpadding="0" cellspacing="1" class="table_form" style="border-top:none;">
  <tr>
     <td width="" style="text-align:left;">
      <a class="buttonlink" onclick="add1();return false;">添加元素</a>
     </td>
     <td style="text-align:left;"></td>
     <td width="443" style="text-align:left;"></td>
  </tr>
  
</table> -->
				<!--Form1End-->

				</form>
				<script>
					var num = 0;
					var m = 1;
					var Area1Temp = "";
					Area1Temp = Area1Temp + "<div id=\"interface_{ERX}\">";

					Area1Temp = Area1Temp
							+ "<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;border-bottom:none;\">";
					Area1Temp = Area1Temp + "<tr>";
					Area1Temp = Area1Temp + "<td width=\"\">第{ERX}个接口</td>";
					Area1Temp = Area1Temp + "</tr>";
					Area1Temp = Area1Temp + "<tr>";

					Area1Temp = Area1Temp
							+ " <td width=\"\">接口标识Interface_id_{ERX}:<input  name=\"Interface_id_{ERX}\"+num  value=\"\" style=\"width:300px\"/></td>";
					/*        Area1Temp=Area1Temp+" <td width=\"443\" style=\"text-align:left;\"></td>"; */
					Area1Temp = Area1Temp + "</tr>";
					/*   Area1Temp=Area1Temp+"<tr>"; */

					Area1Temp = Area1Temp + "<tr>";

					/* Area1Temp = Area1Temp
							+ "<td width=\"\">is_control_{ERX}:<select name=\"is_control_{ERX}\";><option   >请选择</option ><option value=\"true\"  >true</option > <option value=\"false\"  >false</option></select></td>";
					Area1Temp = Area1Temp + "</tr>";

					Area1Temp = Area1Temp + "<tr>";
					Area1Temp = Area1Temp
							+ "<td width=\"\">is_transparent_{ERX}:<select name=\"is_transparent_{ERX}\";><option   >请选择</option ><option value=\"true\"  >true</option > <option value=\"false\"  >false</option></select></td>";
					Area1Temp = Area1Temp + "</tr>";

					Area1Temp = Area1Temp + "<tr>";
					Area1Temp = Area1Temp
							+ "<td width=\"\">need_response_{ERX}:<select name=\"need_response_{ERX}\";><option   >请选择</option ><option value=\"true\"  >true</option > <option value=\"false\"  >false</option></select></td>";
					Area1Temp = Area1Temp + "</tr>";

					Area1Temp = Area1Temp + "<tr>";
					Area1Temp = Area1Temp
							+ "<td width=\"\">is_dir_down_{ERX}:<select name=\"is_dir_down_{ERX}\";><option   >请选择</option ><option value=\"true\"  >true</option > <option value=\"false\"  >false</option></select></td>";
					Area1Temp = Area1Temp + "</tr>"; */

					Area1Temp = Area1Temp
							+ "<input name=\"paramm_{ERX}\" id=\"paramm_{ERX}\" type=\"hidden\" value=\"\">";

					Area1Temp = Area1Temp + "</table>";
					Area1Temp = Area1Temp + "</div>";

					Area1Temp = Area1Temp + "<div id=\"return_{ERX}\">";
					Area1Temp = Area1Temp
							+ "<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;\">";
					Area1Temp = Area1Temp + "<tr>";
					Area1Temp = Area1Temp
							+ " <td width=\"\">返回值编码return_encode_{ERX}:<input  name=\"return_encode_{ERX}\"+num  value=\"\" style=\"width:300px\"/></td>";
					Area1Temp = Area1Temp
							+ " <td width=\"443\" style=\"text-align:left;\"></td>";
					Area1Temp = Area1Temp + "</tr>";
					Area1Temp = Area1Temp + "<tr>";
					Area1Temp = Area1Temp
							+ " <td width=\"\">返回值描述return_interface_description_{ERX}:<input  name=\"return_interface_description_{ERX}\"+num  value=\"\" style=\"width:300px\"/></td>";
					Area1Temp = Area1Temp
							+ " <td width=\"443\" style=\"text-align:left;\"></td>";
					Area1Temp = Area1Temp + "</tr>";
					Area1Temp = Area1Temp + "<tr>";
					Area1Temp = Area1Temp
							+ " <td width=\"\">返回参数return_key_{ERX}:<input  name=\"return_key_{ERX}\"+num  value=\"\" style=\"width:300px\"/></td>";
					Area1Temp = Area1Temp
							+ " <td width=\"443\" style=\"text-align:left;\"></td>";
					Area1Temp = Area1Temp + "</tr>";
					Area1Temp = Area1Temp + "<tr>";
					Area1Temp = Area1Temp
							+ "<td>返回的参数类型:<select name=return_param_type_{ERX}  onchange=\"match_return(this.options[this.options.selectedIndex].value,this.name)\";><option>请选择</option ><option value=\"enum\"  >enum</option > <option value=\"Int\"  >Int</option><option value=\"Float\"  >Float</option ><option value=\"String\"  >String</option ><option value=\"Boolean\"  >Boolean</option ></select></td>";
					Area1Temp = Area1Temp + "</tr>";
					Area1Temp = Area1Temp + "</table>";
					Area1Temp = Area1Temp + "</div>";

					Area1Temp = Area1Temp
							+ "<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;\">";
					Area1Temp = Area1Temp + "<tr>";
					Area1Temp = Area1Temp
							+ "<td width=\"\" style=\"text-align:left;\">";
					Area1Temp = Area1Temp
							+ " <a class=\"buttonlink\" onclick=\"param('interface_{ERX}')\">添加接口参数</a>";
					Area1Temp = Area1Temp + " </td>";
					Area1Temp = Area1Temp
							+ "<td style=\"text-align:left;\"></td>";
					Area1Temp = Area1Temp
							+ "<td width=\"443\" style=\"text-align:left;\"></td>";
					Area1Temp = Area1Temp + "</tr>";
					Area1Temp = Area1Temp + "</table> ";
					/*    Area1Temp=Area1Temp+"<br><br><br><br><br><br>"; */

					/* Area1Temp=Area1Temp+"</div>"; */

					function add() {

						$("#Area2").append(Area1Temp.replace(/{ERX}/g, num));
						num++;
						$("#count").val(num);
						m = 0;
					}
					function param(name) {
						var index = name.substring(10);
						var temp = "";

						temp = temp + "<div id=\"paramlist_{a}_{w}\">"

						temp = temp
								+ "<table name=\"fornext_{a}_{w}\" cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;\">";

						temp = temp + "<tr>";
						temp = temp
								+ "<td width=\"\" style=\"width:300px\">第{a}个接口的第{w}个参数</td>";
						temp = temp + "</tr>";

						temp = temp + "<tr>";
						temp = temp
								+ " <td width=\"\">参数编码encode_{a}_{w}:<input  name=\"encode_{a}_{w}\"+num  value=\"\" style=\"width:300px\"/></td>";
						temp = temp
								+ " <td width=\"443\" style=\"text-align:left;\"></td>";
						temp = temp + "</tr>";

						temp = temp + "<tr>";
						temp = temp
								+ " <td width=\"\">参数描述interface_description_{a}_{w}:<input  name=\"interface_description_{a}_{w}\"+num  value=\"\" style=\"width:300px\"/></td>";
						temp = temp
								+ " <td width=\"443\" style=\"text-align:left;\"></td>";
						temp = temp + "</tr>";

						temp = temp + "<tr>";
						temp = temp
								+ " <td width=\"\">参数名称key_{a}_{w}:<input  name=\"key_{a}_{w}\"+num  value=\"\" style=\"width:300px\"/></td>";
						temp = temp
								+ " <td width=\"443\" style=\"text-align:left;\"></td>";
						temp = temp + "</tr>";

						temp = temp + "<tr>";
						temp = temp
								+ "<td>参数类型:<select name=param_type_{a}_{w}  onchange=\"match(this.options[this.options.selectedIndex].value,this.name)\";><option>请选择</option ><option value=\"enum\"  >enum</option > <option value=\"Int\"  >Int</option><option value=\"Float\"  >Float</option ><option value=\"String\"  >String</option ><option value=\"Boolean\"  >Boolean</option ></select></td>";
						temp = temp
								+ "<td width=\"443\" style=\"text-align:left;\"></td>";
						temp = temp + "</tr>";

						temp = temp + "</table> ";

						temp = temp + "</div>";

						$("#interface_" + index).append(
								temp.replace(/{w}/g, m).replace(/{a}/g, index));

						m++;

						$("#paramm_" + index).val(m);

					}
					function match_return(sequence, name) {

						var index = name.substring(18);

						var tempindex = index.substring(2);

						if (sequence == "enum") {

							var temp = "";
							temp = temp
									+ "<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;border-bottom:none;\">";
							temp = temp + "<tr>";
							temp = temp
									+ " <td width=\"100\">枚举型return_enum_{w}:</td>";
							temp = temp
									+ " <td width=\"200\">枚举选项return_option_{w}:<input  name=\"return_option_{w}\"+num  value=\"\" style=\"width:300px\"/></td>";
							temp = temp
									+ " <td width=\"443\" style=\"text-align:left;\"></td>";
							temp = temp + "</tr>";
							temp = temp + "</table>"

							$("#return_" + index).append(
									temp.replace(/{w}/g, index));

						}
						if (sequence == "Int") {
							var temp = "";
							temp = temp
									+ "<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;border-bottom:none;\">";
							temp = temp + "<tr>";
							temp = temp
									+ " <td width=\"100\">整形return_Int_{w}:</td>";
							temp = temp
									+ " <td width=\"200\">单位return_unit_{w}:<input  name=\"return_unit_{w}\"+num  value=\"\" style=\"width:100px\"/></td>";
							temp = temp
									+ " <td width=\"200\">最小值return_min_val_{w}<input name=\"return_min_val_{w}\"+num  value=\"\" style=\"width:100px\"/></td>";
							temp = temp
									+ " <td width=\"200\">最大值return_max_val_{w}<input name=\"return_max_val_{w}\"+num  value=\"\" style=\"width:100px\"/></td>";
							temp = temp
									+ " <td width=\"443\" style=\"text-align:left;\"></td>";
							temp = temp + "</tr>";
							temp = temp + "</table>";

							$("#return_" + index).append(
									temp.replace(/{w}/g, index));

						}
						if (sequence == "Float") {
							var temp = "";
							temp = temp
									+ "<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;border-bottom:none;\">";
							temp = temp + "<tr>";
							temp = temp
									+ " <td width=\"100\">浮点型return_Float_{w}</td>";
							temp = temp
									+ " <td width=\"200\">单位return_unit_{w}:<input  name=\"return_unit_{w}\"+num  value=\"\" style=\"width:100px\"/></td>";
							temp = temp
									+ " <td width=\"200\">最小值return_min_val_{w}<input name=\"return_min_val_{w}\"+num  value=\"\" style=\"width:100px\"/></td>";
							temp = temp
									+ " <td width=\"200\">最大值return_max_val_{w}<input name=\"return_max_val_{w}\"+num  value=\"\" style=\"width:100px\"/></td>";
							temp = temp
									+ " <td width=\"443\" style=\"text-align:left;\"></td>";
							temp = temp + "</tr>";
							temp = temp + "</table>";

							$("#return_" + index).append(
									temp.replace(/{w}/g, index));

						}
						if (sequence == "String") {
							var temp = "";
							temp = temp
									+ "<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;border-bottom:none;\">";
							temp = temp + "<tr>"

							temp = temp
									+ " <td width=\"100\">字符串型return_String_{w}:<input  name=\"return_String_{w}\"+num  value=\"\" style=\"width:300px\"/></td>";
							temp = temp
									+ " <td width=\"443\" style=\"text-align:left;\"></td>";
							temp = temp + "</tr>";
							temp = temp + "</table>";

							$("#return_" + index).append(
									temp.replace(/{w}/g, index));

						}
						if (sequence == "Boolean") {
							var temp = "";
							temp = temp
									+ "<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;border-bottom:none;\">";
							temp = temp + "<tr>"

							temp = temp
									+ " <td width=\"100\">布尔型return_Boolean_{w}:<select name=\"return_Boolean_{w}\";><option   >请选择</option ><option value=\"true\"  >true</option > <option value=\"false\"  >false</option></select>";
							temp = temp
									+ " <td width=\"443\" style=\"text-align:left;\"></td>";
							temp = temp + "</tr>";
							temp = temp + "</table>";

							$("#return_" + index).append(
									temp.replace(/{w}/g, index));
						}
					}

					function match(sequence, name) {

						var index = name.substring(11);

						var tempindex = index.substring(2);

						if (sequence == "enum") {

							var temp = "";
							temp = temp
									+ "<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;border-bottom:none;\">";
							temp = temp + "<tr>";
							temp = temp + " <td width=\"100\">枚举型enum_{w}:</td>";
							temp = temp
									+ " <td width=\"100\">枚举选项option_{w}:<input  name=\"option_{w}\"+num  value=\"\" style=\"width:300px\"/></td>";
							temp = temp
									+ " <td width=\"443\" style=\"text-align:left;\"></td>";
							temp = temp + "</tr>";
							temp = temp + "</table>"

							$("#paramlist_" + index).append(
									temp.replace(/{w}/g, index));

						}
						if (sequence == "Int") {
							var temp = "";
							temp = temp
									+ "<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;border-bottom:none;\">";
							temp = temp + "<tr>";
							temp = temp + " <td width=\"100\">整形Int_{w}:</td>";
							temp = temp
									+ " <td width=\"100\">单位unit_{w}:<input  name=\"unit_{w}\"+num  value=\"\" style=\"width:100px\"/></td>";
							temp = temp
									+ " <td width=\"100\">最小值min_val_{w}<input name=\"min_val_{w}\"+num  value=\"\" style=\"width:100px\"/></td>";
							temp = temp
									+ " <td width=\"100\">最大值max_val_{w}<input name=\"max_val_{w}\"+num  value=\"\" style=\"width:100px\"/></td>";
							temp = temp
									+ " <td width=\"443\" style=\"text-align:left;\"></td>";
							temp = temp + "</tr>";
							temp = temp + "</table>";

							$("#paramlist_" + index).append(
									temp.replace(/{w}/g, index));

						}
						if (sequence == "Float") {
							var temp = "";
							temp = temp
									+ "<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;border-bottom:none;\">";
							temp = temp + "<tr>";
							temp = temp + " <td width=\"100\">浮点型Float_{w}</td>";
							temp = temp
									+ " <td width=\"100\">单位unit_{w}:<input  name=\"unit_{w}\"+num  value=\"\" style=\"width:100px\"/></td>";
							temp = temp
									+ " <td width=\"100\">最小值min_val_{w}<input name=\"min_val_{w}\"+num  value=\"\" style=\"width:100px\"/></td>";
							temp = temp
									+ " <td width=\"100\">最大值max_val_{w}<input name=\"max_val_{w}\"+num  value=\"\" style=\"width:100px\"/></td>";
							temp = temp
									+ " <td width=\"443\" style=\"text-align:left;\"></td>";
							temp = temp + "</tr>";
							temp = temp + "</table>";

							$("#paramlist_" + index).append(
									temp.replace(/{w}/g, index));

						}
						if (sequence == "String") {
							var temp = "";
							temp = temp
									+ "<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;border-bottom:none;\">";
							temp = temp + "<tr>"
							temp = temp
									+ " <td width=\"100\">字符串型String_{w}:<input  name=\"String_{w}\"+num  value=\"\" style=\"width:300px\"/></td>";
							temp = temp
									+ " <td width=\"443\" style=\"text-align:left;\"></td>";
							temp = temp + "</tr>";
							temp = temp + "</table>";

							$("#paramlist_" + index).append(
									temp.replace(/{w}/g, index));

						}
						if (sequence == "Boolean") {
							var temp = "";
							temp = temp
									+ "<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;border-bottom:none;\">";
							temp = temp + "<tr>"

							temp = temp
									+ " <td width=\"100\">布尔型Boolean_{w}:<select name=\"Boolean_{w}\";><option   >请选择</option ><option value=\"true\"  >true</option > <option value=\"false\"  >false</option></select>";

							temp = temp
									+ " <td width=\"443\" style=\"text-align:left;\"></td>";
							temp = temp + "</tr>";
							temp = temp + "</table>";

							$("#paramlist_" + index).append(
									temp.replace(/{w}/g, index));

						}
					}

					function searchgener() {

						var jStr = Form2QueryString("#form1");
						alert(jStr);
						alert("生成文件成功！111");
						window.location.href = "http://localhost:8080/IoT_Harbor/registpemodel.html?"
								+ jStr;
					}
				</script>
				<!--Form1-->
				<table cellpadding="0" cellspacing="0" style="width:100%">


				</table>
				<!--Form1End-->
</body>
</html>