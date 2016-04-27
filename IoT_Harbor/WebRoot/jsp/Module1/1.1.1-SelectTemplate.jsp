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
<table cellpadding="0" cellspacing="1" class="table_form">
  <caption>
    导入已存在设备模板
  </caption>
  <tr>
    <td width="13%">选择模板文件:</td>
    <td width="">
   
    <form name="inputModelForm" id="inputModelForm" method="post" action="../../addfile.html" enctype="multipart/form-data">
      <span id="inputmodelFalse">未选择任何模板</span>
       <input type="file" id="inputmodel" name="inputmodel" onChange='document.getElementById("inputmodelFalse").innerHTML=document.getElementById("inputmodel").value;' style="visibility:collapse;" />
        
      <!--   <input type="file" name=""/>--> 
 </form> 
    </td>
    <td width="28%"><a class="buttonlink" onClick='document.getElementById("inputmodel").click();'>选择导入模板</a><a class="buttonlink" onclick='document.getElementById("inputModelForm").submit();'>提交本地模板</a>
  </td>
  </tr>
  
</table>
<!--Form1End-->
<!--Form1-->
<table cellpadding="0" cellspacing="1" class="table_form">
<form name="form1" id="form1" method="POST" action="">
  <!--         <caption>
            查询已有设备模板
          </caption> -->
          <!-- <tr>
            <td width="13%">设备类别:</td>
            <td width="59%" style="padding:0px;">
				InnerForm1Layer1
                <table width="940" cellpadding="0" cellspacing="0" style="width:900px">
                    <tr>
                        <td width="310">
                        	<input class="easyui-combobox" id="TypeLevel1" data-options="editable:false,valueField:'classId',textField:'className',panelHeight:'auto'
                    										,onChange:function(newValue,oldValue){FillChange(newValue,1);$('#TypeLevel1Value').val(newValue);}" style="width:300px;"/>
							<script>
                                FillChange("",0);
                            </script>
                            </td>
						</td>
                        <td width="310">
                            <input class="easyui-combobox" id="TypeLevel2" data-options="editable:false,valueField:'classId',textField:'className',panelHeight:'auto'
                    										,onChange:function(newValue,oldValue){FillChange(newValue,2);$('#TypeLevel2Value').val(newValue);}" style="width:300px;"/>

                        </td>
                        <td width="318">
	                        <input class="easyui-combobox" id="TypeLevel3" data-options="editable:false,valueField:'classId',textField:'className',panelHeight:'auto'
                    										,onChange:function(newValue,oldValue){FillChange(newValue,3);$('#TypeLevel3Value').val(newValue);}" style="width:300px;"/>
                        </td>
                     </tr>
                </table>    
                            <input name="TypeLevel1" value="" type="hidden" id="TypeLevel1Value" />
                            <input name="TypeLevel2" value="" type="hidden" id="TypeLevel2Value" />
                            <input name="TypeLevel3" value="" type="hidden" id="TypeLevel3Value" />
				InnerForm1Layer1End
            </td>
            <td width="28%">
            </td>
          </tr> -->
        <!--   <tr>
            <td>设备名称：</td>
            <td><input name="device_name" style="width:920px"/></td> 
            <td width="28%" style="text-align:left;">
                <a class="buttonlink" onclick="searchgener();return false;">查询现有模板</a>
            </td>
          </tr> -->
</form>
        </table>
<!--Form1End-->

<!--Form1-->
<table cellpadding="0" cellspacing="1" class="table_form">
          <caption>
            本地设备模板列表
          </caption>
          <tr>
            <td style="padding:0px;">
            <!-- DATAGRID -->
            <script>
       
			function searchgener()
			{
				var jStr=Form2Json("#form1");
				LoadAjax(jStr);
			}
			function LoadAjax(postjson)
			{
				if(postjson==null)
				{
					/*$('#dg').datagrid('load', {   
						//state: '1'  
					});  */
				}else
				{
					$('#localdeviceView').datagrid('load',postjson);
				}
			}
			var NextID=0;
			function ClickIng(rowIndex,rowData)
			{
						var pid=GetGridValue("#localdeviceView",rowIndex,2);
						NextID=pid;
						//window.frames["innerP"].location="http://localhost:8080/IoT_Harbor/images/js/newjs/datagrid_adapter.js?ID="+pid;
					/* 	window.frames["innerP"].location="http://localhost:8080/IoT_Harbor/jsp/Module1/1.1.2-InputBasicInformation.jsp?ID="+pid; */
			}
			function GoNext(rowIndex,rowData)
			{
			   // alert("1");
			   // var pid=GetGridValue("#localdeviceView",rowIndex,2);
			  //  alert(NextID);
				
			
				window.location.href="1.1.2-InputBasicInformation.jsp?ID="+NextID;
			}
			function test()
			{
				window.location.href="/IoT_Harbor/a.html?ID="+1;
			}
			
			</script>
            <table id="localdeviceView" class="easyui-datagrid" style="height:300px;"
                        data-options="rownumbers:true,singleSelect:true,url:'/IoT_Harbor/getPEModel.html',method:'get',onClickRow:ClickIng">
			        <thead>
                            <tr style="background:#CCC">
                                <th data-options="field:'template_name',width:300,align:'center'">模板名称</th>
                              <th data-options="field:'template_id',width:200,align:'center'">模板ID</th>
                              <th data-options="field:'template_model',width:200,align:'center'">设备型号</th>
                              <th data-options="field:'template_remark',width:400,align:'center'">设备描述</th>	
                            </tr>
                    </thead>
            </table>
            <!-- DATAGRIDEND -->
            </td>
          </tr>
          <tr>
            <td style="padding:0px;height:10px;height:300px;">
            	<iframe src="about:blank" id="innerP" name="innerP" style="width:100%;height:300px;border:none;"></iframe>
            </td>
          </tr>
</table>
<!--Form1End-->


<!--Form1-->
<table cellpadding="0" cellspacing="0" style="width:100%">
          <tr>
            <td width="100%" style="padding:20px;text-align:center;">
						无本地模板和设备模板？<a href="#">点此查询设备模板</a>
                        &nbsp;
                        &nbsp;
                        &nbsp;
                        &nbsp;
                        &nbsp;
                        &nbsp;
                        &nbsp;
	            		<a class="buttonlink" href="#" onclick="GoNext();return false;" >下一步</a>
	            		
          </tr>
</table>
<!--Form1End-->

</body>
</html>
