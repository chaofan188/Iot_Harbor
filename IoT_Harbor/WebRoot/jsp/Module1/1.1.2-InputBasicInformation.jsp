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
<script type="text/javascript" src="../../images/js/newjs/FormEngine.js"></script>
<!-- <script type="text/javascript" src="../../js/FileSaver.js"></script> 
<script type="text/javascript" src="../../js/Blob.js"></script>  -->
<script type="application/ecmascript" async=""
	src="../../js/FileSaver.js" /></script>
<script type="application/ecmascript" async="" src="../../js/Blob.js" /></script>
<script type="text/javascript"
	src="../../images/js/newjs/templateEngine.js"></script>

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
		<table cellpadding="0" cellspacing="1" class="table_form">
			<caption>填写设备基本信息</caption>
			<tr>
				<td width="151">设备名称:</td>
				<td><input id="device_name" name="device_name"
					value="<%=device_name%>" style="width:300px" /></td>

			</tr>

			<tr>
				<td width="151">物体编号:</td>
				<td><input id="device_id" name="device_id" style="width:300px"
					onblur=checkVal() /> <!-- <a class="buttonlink" >条码扫描</a> --><input id="use_veid" name="use_veid" type="checkbox">用做VEID</input></td>
			
			</tr>
			<tr>
				<td width="151">模板ID:</td>
				<td><select id="template_id" name="template_id"
					onChange="template_onChange()" style="width:300px" /></select></td>
			</tr>
			<tr>
				<td width="151">隶属组织:</td>
				<td><input id="belongto" name="belongto" style="width:300px" />
				</td>
			</tr>
			<tr>
				<td width="151">行政区划:</td>
				<td>
					<!--  <label>国家：</label>
         <script>
               FillCountryChange("",0);
             
		 </script> --> <!--  <input class="easyui-combobox" name="country" id="Country" data-options="editable:false,valueField:'countryId',
         																		  textField:'countryName',
                                                                                  panelHeight:'auto',
                                                                                  onChange:function(newValue,oldValue){
                                                                                                   FillCountryChange(newValue,1);
                                                                                                                      }" style="width:100px;"/>
        &nbsp;&nbsp;<label>省份：</label>
        <input class="easyui-combobox" name="province" id="Prov" data-options="editable:false,valueField:'provinceId',
         																		  textField:'provinceName',
                                                                                  panelHeight:'auto',
                                                                                  onChange:function(newValue,oldValue){
                                                                                                   FillCountryChange(newValue,2);
                                                                                                                      }" style="width:100px;"/>
        &nbsp;&nbsp;<label>城市：</label>
        <input class="easyui-combobox" name="city" id="City" data-options="editable:false,valueField:'cityId',
         																		  textField:'cityName',
                                                                                  panelHeight:'auto',
                                                                                  onChange:function(newValue,oldValue){
                                                                                                   FillCountryChange(newValue,3);
                                                                                                                      }" style="width:100px;"/>
                                                                                                                  
                                                                                                                      <br/>     -->
					<label>街道&amp;具体地址：</label> <input name="otherInfo" id="otherInfo"
					value="" style="width:205px;margin-top:10px;" />
				</td>
			</tr>
			<tr>
				<td width="151">公开性:</td>
				<td><select id="public" name="public"><option
							value="public">Public</option>
						<option value="public">Private</option>
				</select></td>
			</tr>
			<!--   <tr>
    <td width="151">要求授权:</td>
    <td><select name="IsShouq"><option>是</option><option>否</option></select></td>
  </tr>
  <tr>
    <td width="151">可见性:</td>
    <td><select name="IsVisible"><option>是</option><option>否</option></select></td>
  </tr> -->
			<tr>
				<td width="151">地理位置:</td>
				<td style="padding:0px;">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td style="width:200px;">
								<table cellpadding="0" cellspacing="0">
									<tr>
										<!--   <td>坐标</td>
                                        <td></td> -->
									</tr>
									<tr>
										<td>经度</td>
										<td><input type="text" name="longitude" id="longitude"
											class="easyui-numberbox" value="100"
											data-options="min:0,precision:2"></input></td>
									</tr>
									<tr>
										<td>纬度</td>
										<td><input type="text" name="latitude" id="latitude"
											class="easyui-numberbox" value="100"
											data-options="min:0,precision:2"></input></td>
									</tr>
									<!--  <tr>
                                        <td>海拔高度</td>
                                        <td><input type="text" name="altitude" id="altitude" class="easyui-numberbox" value="100" data-options="min:0,precision:2"></input></td>
                                      </tr> -->
								</table>
							</td>
							<!--  <td style="width:200px;height:200px;background:#0F0;">地图控件</td> -->
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td width="151">设备描述:</td>
				<td><textarea id="device_description" name="device_description"
						style="width:500px;height:50px;"></textarea></td>
			</tr>
			<!-- <tr>
    <td width="151">设备图片:</td>
    <td style="padding:0px;height:200px;">
    	<iframe src="1.1.2-InputBasicInformationUploadImage.jsp" style="width:500px;height:200px;border:none;"></iframe>
        <input type="hidden" name="device_picture" id="device_picture" value="test" />
    </td>
  </tr> -->
		</table>
		<!--Form1End-->

	</form>
	<div id="extention1"></div>
	<div id="extention_attribute"></div>
	<!-- 属性展示区域 -->

	<!--Form1-->
	<table cellpadding="0" cellspacing="0" style="width:100%">

		<tr>
			<td width="100%" style="padding:10px;text-align:center;"><a
				class="buttonlink" id="dowload_button" onclick="saveFile();">保存PE生成信息</a>
				<a class="buttonlink" href="#" onclick="GoNext();return false;">确定</a>
		</tr>
	</table>
	<!--Form1End-->

</body>
<script>
		var already_query_template = 0;
		<%-- 	function searchgener() {
                       
						var str2 = Form2QueryString("#form1");
						str2+="&ID="+"<%=template_id%>";
						alert(str2);
						var temp;
						$.ajax({     
    								url:'/IoT_Harbor/information.html',
									type : 'post',
									data : str2,
									/* async : false,  */
									error : function() {
										alert('error');
									},
									success : function(data) {
										alert('success');
									}
								});
						

					} --%>
			var pe_result;
			
		   function GoNext()
			{
			    var jStr=$("#form1").serializeArray();
			    var postObj = new Object();
			    
			    for(var element in jStr){
			    	if(jStr[element].value.length == 0){
			    		alert("参数不能为空");
			    		return false;
			    	}
			    	var evalString = "postObj." + jStr[element].name + "='" + jStr[element].value + "';";
			    	eval(evalString);
			    }
			    
			  var onvif_array = new Array();
			  $(".onvif_area").each(function(index, element){
			  	var onvifObj = new Object();
			  	onvifObj.interface_id = $(element).find("#onvif_id").val();
			  	onvifObj.onvif_ip = $(element).find("#onvif_ip").val();
			  	onvifObj.username = $(element).find("#onvif_username").val();
			  	onvifObj.password = $(element).find("#onvif_password").val();
			  	onvif_array[index] = onvifObj;
			  });
			  
			  var ezviz_array = new Array();
			  $(".ezviz_area").each(function(index, element){
			  	var ezvizObj = new Object();
			  	ezvizObj.interface_id = $(element).find("#ezviz_id").val();
			  	ezvizObj.ezviz_key = $(element).find("#ezviz_key").val();
			  	ezvizObj.ezviz_secret = $(element).find("#ezviz_secret").val();
			  	ezvizObj.ezviz_cameraname = $(element).find("#ezviz_cameraname").val();
			  	ezviz_array[index] = ezvizObj;
			  });
			  
			  var http_array = new Array();
			  $(".http_area").each(function(index, element){
			  	var httpObj = new Object();
			  	httpObj.interface_id = $(element).find("#interface_id").val();
			  	httpObj.url = $(element).find("#http_url").val();
			  	
			  	http_array[index] = httpObj;
			  });
			  
			  var attribute_array = new Array();
			  $(".attribute_area").each(function(index, element){
			  	var attributeObj = new Object();
			  	attributeObj.key = $(element).attr("name");
			  	attributeObj.value = $(element).val();
			  	//attributeObj.id = $(element).attr("param_id");
			  	attribute_array[index] = attributeObj;
			  });
			  
			  if(onvif_array.length > 0){
			  		postObj.onvif = onvif_array;
			  }
			  
			  if(ezviz_array.length > 0){
			  	postObj.ezviz = ezviz_array;
			  }
			  
			  if(attribute_array.length > 0){
			  	postObj.attribute = attribute_array;
			  }
			  
			  if(http_array.length > 0){
			  	postObj.http = http_array;
			  }
			  
			  var resultString = JSON.stringify(postObj);
			  alert(resultString);
				$.ajax({
					url:'/IoT_Harbor/registerPE.html',
					type:'post',
					contentType: "application/json",
					dataType: "json",
					data:resultString,
					error:function(){
						alert("register PE error!");
						location.reload();
					},
					success:function(result){
						if(result.result != "success"){
							alert(result.result);
							return false;
						}
						pe_result = result;
						$("#dowload_button").show();
						alert("注册成功");
						blob = null;
					}
				});   
					
			}
	
			var blob = null;
			function saveFile(){
				try {
	    				var isFileSaverSupported = !!new Blob;
					} catch (e) {
						alert("can not save file!");
					}
					
				if(blob == null){
					blob = new Blob([JSON.stringify(pe_result)], {type: "text/plain;charset=utf-8"});
				}
				saveAs(blob, "pe.txt" );
				//window.open(URL.createObjectURL(blob));
			}
			
			function GoPre()
			{
				window.location.href="/IoT_Harbor/jsp/Module1/1.1.1-SelectTemplate.jsp";
			}
			$(function(){
				$("#dowload_button").hide();
				if(!already_query_template){
					$.ajax({
					url:'/IoT_Harbor/getPEModel.html',
					type:'get',
					error:function(){
						alert("Get PEModel list error!");
					},
					success:function(result){
						//alert(result);
						var json = JSON.parse(result);
						var template_element = document.getElementById("template_id");
						var inner="<option value=' '> </option>";
						template_element.innerHTML=inner;
						for(var index in json){
							//console.log(json[index]);
							var objOption = document.createElement("OPTION");
	  						objOption.text = json[index].template_id;
	  						objOption.value = json[index].template_id;
	  						template_element.options.add(objOption); 			
						}
						already_query_template = 1;
					}
					});
				}
			})
			
			function template_onChange(){
				$.ajax({
					url:'/IoT_Harbor/template_check.html?&template_id=' + document.getElementById("template_id").value,
					type:'get',
					error:function(){
						alert("Can not verify PE Model!");
					},
					success:function(result){
						var json = JSON.parse(result);
						//alert(json);
						$("#extention1").empty();
				
						$("#extention_attribute").empty();
						
						//动态显示属性
						if(json.attribute){
							$("#extention_attribute").append("<table id='extention_attribute_content' cellpadding='0' cellspacing='1' class='table_form'></table>");
							$("#extention_attribute_content").append("<tr><td width='151'>PE属性</td><td width='151'>属性描述</td><td>属性值</td></tr>");
							
							for(var index in json.attribute){
								var key = json.attribute[index].pe_template_key;
								var type = json.attribute[index].pe_template_type;
								var description = json.attribute[index].description;
								var template_param_id = json.attribute[index].template_param_id;
								var data = {"key" : key, "description" : description, "param_id": template_param_id};
								var tempHolder = new Template($("#attribute_template").html());
								var s = tempHolder.render(data);
								$("#extention_attribute_content").append(s);
							}
						}
						
						//动态显示接口要求参数
						if(json.protocol){
							$("#extention1").append("<table id='extention2' cellpadding='0' cellspacing='1' class='table_form'></table>");
					      
							for(var element in json.protocol){
								var interface_id = json.protocol[element].interface_id;
								var interface_type = json.protocol[element].interface_type;
								var interface_description = json.protocol[element].interface_id;
					           // alert(interface_type);
								if(interface_type == "onvif"){
								
								    $("#extention2").append("<tr class='onvif_area'><td width='151'>ONVIF摄像头(" + interface_description + ")</td>"
								    + "<td style='display:none'><input name='interface_id' id='onvif_id' value='" + interface_id + "'></td>"
								    + "<td>IP地址：</td>" + "<td><input name='onvif_ip' id='onvif_ip'></td>"
								    + "<td>用户名：</td>" + "<td><input name='onvif_username' id='onvif_username'></td>"
								    + "<td>密码：</td>" + "<td><input name='onvif_password' id='onvif_password'></td>"
								    +"</tr>");
								}
							    else if(interface_type == "ezviz"){
								    
								
                                    $("#extention2").append("<tr class='ezviz_area'><td width='151'>Ezviz摄像头(" + interface_description + ")</td>"
								    + "<td style='display:none'><input name='interface_id' id='ezviz_id' value='" + interface_id + "'></td>"
								    + "<td>萤石云应用Key：</td>" + "<td><input name='ezviz_key' id='ezviz_key'></td>"
								    + "<td>萤石云应用Secret：</td>" + "<td><input name='ezviz_secret' id='ezviz_secret'></td>"
								    + "<td>摄像头名称：</td>" + "<td><input name='ezviz_cameraname' id='ezviz_cameraname'></td>"
								    +"</tr>");
								}
								else if(interface_type == "http"){
									var data = {
										"interface_id" : interface_id,
									};
									var tempHolder = new Template($("#http_template").html());
									var s = tempHolder.render(data);
									$("#extention2").append(s);
								}
								
							}
						}
						
					}
				});
			}
			
			function checkVal(){
				if(!isNumberOr_Letter($("#device_id").val())){
					alert("物体编号只能由数字、字母及下划线组成");
					$("#device_id").val("");
				};
			}
/*
用途：检查输入字符串是否只由英文字母和数字和下划线组成
输入s：字符串
返回：如果通过验证返回true,否则返回false
*/
function isNumberOr_Letter( s ){//判断是否是数字或字母
	var regu = "^[0-9a-zA-Z\_]+$";
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	}else{
		return false;
	}
}
</script>

<script id="attribute_template" type="text/plain">
	<tr>
		<td width='151'>{key}</td>
		<td width='151'>{description}</td>
		<td><input class="attribute_area" name="{key}" param_id = "{param_id}" id="attribute_{key}"></td>
	</tr>
</script>

<script id="http_template" type="text/plain">
	<tr class="http_area">
		<td width='151'>HTTP-URL地址({interface_id})</td>
		<td style="display:none"><input name='interface_id' id='interface_id' value="{interface_id}"></td>
		<td><input style="width:400" id="http_url"></td>
	</tr>
</script>
</html>