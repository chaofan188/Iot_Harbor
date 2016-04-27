<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%-- 
    <base href="<%=basePath%>">
    
    <title>My JSP 'veInsert.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script> --%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8>">
<title>全部信息-管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link rel="stylesheet" type="text/css" href="../admin/skin/system.css">
<script type="text/javaScript" src="../images/js/newjs/jquery.min.js"></script>
<script type="text/javaScript"
	src="../images/js/newjs/jquery.easyui.min.js"></script>


<script type="text/javascript"
	src="../images/js/newjs/datagrid_adapter.js"></script>
<script type="text/javascript" src="../images/js/newjs/typeSelecter.js"></script>
<!--<script type="text/javaScript" src="images/js/css.js"></script>
<script type="text/javaScript" src="data/config.js"></script>
<script type="text/javaScript" src="images/js/common.js"></script>
<script type="text/javaScript" src="images/js/admin.js"></script>
<script type="text/javascript" src="images/js/validator.js"></script>
<script type="text/javascript" src="images/js/form.js"></script>-->
<link rel="stylesheet" type="text/css"
	href="../images/easyui/easyui.css">
<link rel="stylesheet" type="text/css"
	href="../images/js/newjs/easyui-balthasar.css">
<script type="text/javascript" src="../js/jquery-1.7.2.js"></script>
<style>
.btn {
	background-color: #99d3fb;
	color: black;
	height: 20px;
	width: 58px;
	border: 0;
	cursor: pointer;
	line-height: 20px;
}
</style>
<script type="text/javascript">
	/* function Close()
			{
				window.parent.location.href="/IoT_Harbor/harbor_login.jsp";
			} */
	</script>
</head>

<body>
	 <form id="form1" name="form1" action="/IoT_Harbor/registerVEWithMobile.html"
		method="GET">


		<!-- 		<table width="800" border="0" id="ddd"
			style="margin:0 auto;margin-top:0px;margin-bottom:20px;"> -->

		<table cellpadding="0" cellspacing="1" class="table_form"
			id="table_form">
			<caption>VE注册</caption>
			<tr>
				<td width="151"><br>VE名称：</td>
				<td><input name="veName" id="veName" type="text" style="width:500px;" />&nbsp;</td>
			</tr>
			<tr>
				<td width="151">ve key：</td>
				<td><input name="vePassword" id="veKey" type="text"
					style="width:500px;" />&nbsp;</td>
			</tr>
			<tr>
				<td width="151">VE所有权：</td>
				<td><select name="privacy" id="privacy">
						<option value="001">公有</option>
						<option value="002">私有</option>
				</select></td>
			</tr>
			<tr>
				<td width="151">ve模板ID：</td>
				<td><input name="templateId" id="tplId" type="text"
					style="width:500px;" value="000014" />&nbsp;</td>
			</tr>
			<tr>
				<td width="151"></td>
				<td><input type="button" id="getPEList" value="获取模板列表"
					class="btn" style="width:100px" /></td>
			</tr>
			<tr>
				<td width="151">ve描述：</td>
				<td><input name="description" id="description" type="text"
					style="width:500px;" />&nbsp;</td>
			</tr>
			<tr style="display:none">
				<td><input name="veInfo" id="veInfo" type="text"
					style="width:500px;" />&nbsp;</td>
			</tr>
			<!-- <tr>
				<td width="151">ve所在国家：</td>
				<td><select name="country" id="country" style="width:100px">
						<option value="0">0</option>
						<option value="1">1</option>
				</select>&nbsp;</td>
			</tr>
			<tr>
				<td width="151">ve所在省份：</td>
				<td><select name="province" id="province" style="width:100px">
						<option value="0">0</option>
						<option value="1">1</option>
				</select>&nbsp;</td>
			</tr>
			<tr>
				<td width="151">ve所在城市：</td>
				<td><select name="city" id="city" style="width:100px">
						<option value="0">0</option>
						<option value="1">1</option>
				</select>&nbsp;</td>
			</tr>
			<tr>
				<td width="151">ve经度：</td>
				<td><input name="longitude" id="longitude" type="text"
					style="width:500px;" value="0.0" />&nbsp;</td>
			</tr>
			<tr>
				<td width="151">ve维度：</td>
				<td><input name="latitude" id="latitude" type="text"
					style="width:500px;" value="0.0" />&nbsp;</td>
			</tr>
			<tr>
				<td width="151">ve海拔：</td>
				<td><input name="altitude" id="altitude" type="text"
					style="width:500px;" value="0.0" />&nbsp;</td>
			</tr>
			<tr>
				<td width="151">ve其他信息：</td>
				<td><input name="otherInfo" id="otherInfo" type="text"
					style="width:500px;" />&nbsp;</td>
			</tr> -->

		</table>
		<table cellpadding="0" cellspacing="0" style="width:100%">
		<tr><td id="pe_content"></td></tr>
		</table>
		<table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td width="100%" style="padding:10px;text-align:center;">
					<a class="buttonlink" href="#" onclick="submit()">VE注册</a>

					<a class="buttonlink" href="#"
					onclick='document.getElementById("form1").reset();return false;'>清&nbsp;空</a>
			</tr>
		</table>

	</form>
	<div id="ve_template_services" style="display:none">
		<!-- 提示标题 -->
		<div id="service_template" >
			<div class="row">
				<div class="col-sm-12">
					<label class="col-sm-3 control-label no-padding-right"
						for="form-input-readonly" style="padding-top: 12px;">
						服务列表： </label>
				</div>
			</div>
		</div>
		<!--   服务列表模板 -->
		<div id="service_list_template">
			<div class="row">
				<div class="col-sm-12">
					<label class="col-sm-3 control-label no-padding-right"
						for="form-input-readonly" style="padding-top: 12px;">
						实现$service_description服务需要包含以下设备模板的设备： </label>
				</div>
			</div>
		</div>
		<!--   每个服务下的设备模板列表模板 -->
		<div id="pe_model_list_template">
			<div class="row">
				<div class="col-sm-12">
					<label class="control-label no-padding-right"
						style="padding-top: 12px;">设备模板$pe_template_id下的可选设备列表如下:<span>$bind_type并且选择数量在$bind_min和$bind_max之间</span></label>
				</div>
			</div>
		</div>
		<!--  每个pe模板下的可选设备列表模板 -->
		<div id="pe_list_template">
			<div class="row">
				<div class="col-xs-12 col-sm-6">
					<div class="control-group">
						<div class="checkbox pelist">
							<label><input name="checkbox_pe" type="checkbox"
								serviceId="$service_id" identifyId="$identify_id" peId="$pe_id">
								<span class="lbl">$pe_name</span> </label>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script>
  $("#getPEList").click(function(){
    // var tplId =$("#tplId").val;
    var tplId = document.getElementById("tplId").value;
     $.ajaxSetup({
     	async: false
     });
      $.get("../queryVeTemplateServiceList.html",{ve_template_id:tplId,rows:100,page:0},function(data){
		var content="";
		 for (var i = 0; i < data.length; i++) {
              content+=$("#service_list_template").html()
              		.replace("$service_description",data[i].service_description);
              $.get("../queryPETemplatesByVeTemplateService.html",{ve_template_id:tplId,ve_template_service_id:data[i].service_id,rows:100,page:0},function(pemodellist){
              	 for(var j = 0; j < pemodellist.length; j++) {
              		content+=$("#pe_model_list_template").html()                      	
              				.replace("$pe_template_id",pemodellist[j].pe_template_id)
              				.replace("$bind_type",pemodellist[j].bind_type)
              				.replace("$bind_max",pemodellist[j].bind_max)
              				.replace("$bind_min",pemodellist[j].bind_min);
              		
              	
              		 $.get("../getPEByPEModelID.html",{template_id:pemodellist[j].pe_template_id},function(pelist){
              	 		for(var k=0; k<pelist.length; k++){             	 			
                       			content+=$("#pe_list_template").html()                      		
                       					.replace("$service_id",data[i].service_id)
                       					.replace("$identify_id",pemodellist[j].identify_id)
                       					.replace("$pe_id",pelist[k].pe_id)
                       					.replace("$pe_name",pelist[k].pe_name);
                       		}
              	 	},"json");
              	 }
              },"json");
	    }
	    //alert(content);
	    $("#pe_content").html(content);
     },"json");
  });
   //$("#submit").click(
  		function submit() {
            var obj = new Array();         
            var pe_list= $('input[name="checkbox_pe"]:checked');
            pe_list.each(function(index, device) {
            	 var deviceObj = new Object();
            	deviceObj.pe_id = $(device).attr("peId");
            	  deviceObj.service_id = $(device).attr("serviceId");
                  deviceObj.identify_id = $(device).attr("identifyId");
                  obj[index] = deviceObj;
            });
          
            var peInfoList = JSON.stringify(obj);
            //alert(peInfoList);
            $("#veInfo").val(peInfoList);
            document.getElementById("form1").submit();
          }
            /* $.ajax({
                type: "get",
                url: "/IoT_Harbor/registerVEWithMobile.html",
                async:"true",
                data: {                   
                    templateId: tplId,
                    veInfo: peInfoList,
                    veName: $("#veName").val(),
                    vePassword: $("#veKey").val(),
                    privacy : $("#privacy").val(),
                    description: $("#description").val(),
                },
                dataType: "text",
                success: function(result) {
                        alert(result);
                    
                }
            }); */
 // });
</script>
