<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String device_name = request.getParameter("device_name");
String template_id = request.getParameter("ID");
String peid = request.getParameter("peid");
if(device_name==null){
   device_name="";
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
<form action="" method="post" name="form1" id="form1">
<table cellpadding="0" cellspacing="1" class="table_form">
  <caption>
	填写设备基本信息
  </caption>
  <tr>
   <input name="peid" value="<%=peid %>" type="hidden" id="TypeLevel1Value" />
    <td width="151">名称:</td>
    <td><input id="device_name" name="device_name"  style="width:300px"/></td>
  </tr>
  <tr>
    <td width="151">物体编号(PEID禁止修改):</td>
    <td><input id="device_identifier" readonly="readonly" name="device_identifier" value="<%=peid %>" style="width:300px"/> <!-- <a class="buttonlink" >条码扫描</a> --></td>

  </tr>
  <tr>
    <td width="151">隶属组织:</td>
    <td><input id="belongto" name="belongto" style="width:500px"/></td>
  </tr>
  <tr>
    <td width="151">行政区划:</td>
    <td>
         <label>国家：</label>
         <script>
               FillCountryChange("",0);
             
		 </script>
         <input class="easyui-combobox" name="country" id="Country" data-options="editable:false,valueField:'countryId',
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
                                                                                                                      
                                                                                                                      <br/>
         <label>街道&amp;具体地址：</label>
    	 <input name="OtherAddress" id="OtherAddress" value="" style="width:405px;margin-top:10px;"/>
    
    </td>
  </tr>
    <tr>
    <td width="151">公开性:</td>
    <td><select id="public" name="public"><option value="public">Public</option><option value="public">Private</option></select></td>
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
                                        <td>坐标</td>
                                        <td></td>
                                      </tr>
                                      <tr>
                                        <td>经度</td>
                                        <td><input type="text" name="longitude" id="longitude"class="easyui-numberbox" value="" data-options="min:0,precision:2"></input></td>
                                      </tr>
                                      <tr>
                                        <td>纬度</td>
                                        <td><input type="text" name="latitude" id="latitude" class="easyui-numberbox" value="" data-options="min:0,precision:2"></input></td>
                                      </tr>
                                      <tr>
                                        <td>海拔高度</td>
                                        <td><input type="text" name="altitude" id="altitude" class="easyui-numberbox" value="" data-options="min:0,precision:2"></input></td>
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
    <td><textarea id="device_description" name="device_description" style="width:500px;height:50px;"></textarea></td>
  </tr>
  <tr>
    <td width="151">设备图片:</td>
    <td style="padding:0px;height:200px;">
    	<iframe src="1.1.2-InputBasicInformationUploadImage.jsp" style="width:500px;height:200px;border:none;"></iframe>
        <input type="hidden" name="device_picture" id="device_picture" value="test" />
    </td>
  </tr>
</table>
<!--Form1End-->

</form>
 <script>
		
			
		   function GoNext()
			{
			window.location.href="http://localhost:8080/IoT_Harbor/jsp/Module1/1.2.1-MyDevice.jsp";
			}
	
			/* function GoPre()
			{
				window.location.href="http://localhost:8080/IoT_Harbor/jsp/Module1/1.1.1-SelectTemplate.jsp";
			} */
</script>
<!--Form1-->
<table cellpadding="0" cellspacing="0" style="width:100%">
 
          <tr>
            <td width="100%" style="padding:10px;text-align:center;">
              
	            <a class="buttonlink" href="#" onclick="GoNext();return false;" >完成</a>
          </tr>
</table>
<!--Form1End-->

</body>
</html>