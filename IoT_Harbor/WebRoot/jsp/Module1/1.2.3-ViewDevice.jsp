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
<form name="form1" id="form1" method="POST" action="">
<!--Form1-->
<table cellpadding="0" cellspacing="1" class="table_form">
          <caption>
            查询条件
          </caption>
          <tr>
            <td width="203">设备类别:</td>
            <td width="954" style="padding:0px;">
				<!--InnerForm1Layer1-->
                <table width="940" cellpadding="0" cellspacing="0" style="width:900px">
                    <tr>
                        <td width="310">
                        	<input class="easyui-combobox" name="TypeLevel1" id="TypeLevel1" data-options="editable:false,valueField:'classId',textField:'className',panelHeight:'auto'
                    										,onChange:function(newValue,oldValue){FillChange(newValue,1);$('#TypeLevel1Value').val(newValue);}" style="width:300px;"/>
							<script>
                                FillChange("",0);
                            </script>
						</td>
                        <td width="310">
                            <input class="easyui-combobox" name="TypeLevel2" id="TypeLevel2" data-options="editable:false,valueField:'classId',textField:'className',panelHeight:'auto'
                    										,onChange:function(newValue,oldValue){FillChange(newValue,2);$('#TypeLevel2Value').val(newValue);}" style="width:300px;"/>

                        </td>
                        <td width="318">
	                        <input class="easyui-combobox" name="TypeLevel3" id="TypeLevel3" data-options="editable:false,valueField:'classId',textField:'className',panelHeight:'auto'
                    										,onChange:function(newValue,oldValue){FillChange(newValue,3);$('#TypeLevel3Value').val(newValue);}" style="width:300px;"/>
                        </td>
                     </tr>
                     
                            <input name="TypeLevel1" value="" type="hidden" id="TypeLevel1Value" />
                            <input name="TypeLevel2" value="" type="hidden" id="TypeLevel2Value" />
                            <input name="TypeLevel3" value="" type="hidden" id="TypeLevel3Value" />
                </table>
				<!--InnerForm1Layer1End-->
            </td>
            <td width="443">
            </td>
          </tr>
          <tr>
            <td>设备名称：</td>
            <td><input name="deviceName" style="width:920px"/></td>
            <td width="443" style="text-align:left;">
            </td>
          </tr>
       <tr>
            <td width="151">设备状态:</td>
            <td><select name=devicestate><option value="0">在线</option><option value="1">故障</option ><option value="2">离线</option ></select></td>
            <td width="443" style="text-align:left;">
            </td>
          </tr>
          <tr>
            <td width="151">生命周期:</td>
            <td><select name=devicelifecycle><option value="0">正常</option><option value="1">待激活</option ><option value="2">冻结</option ></select></td>
            
          </tr>
          <tr>
            <td>设备所属：</td>
            <td><input name="belongto" style="width:920px"/></td>
            <td width="443" style="text-align:left;">
                <a class="buttonlink" onclick="searchgener();return false;">查询</a>
            </td>
          </tr>
        </table>
<!--Form1End-->
</form>

<!--Form1-->
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
			function Linkformatter(value,row,index)
			{
				//var pid=GetGridValue("#dg",index,2);//获取列明
				return "<a href='B_R_1_1_2_1.html' target='_blank'>"+value+"</a>";
			}
			function Eventformatter(value,row,index)
			{
				var pid=GetGridValue("#localdeviceView",index,1);//获取列明
				return "<a href='' class='buttonlink' onclick='Event_Shenqing(\""+pid+"\");return false;' target='_blank'>申请</a>";
				//return "";
			}
			///*   */
			</script>
			<script>
		
			function Event_Shenqing(ID)
			{
				alert(ID);
				$.ajax({type:"GET",
				   	url:"/IoT_Harbor/viewdevice_shenqing.html",
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
						//alert("NetError");
					}
				   });
			}
			</script>
<table cellpadding="0" cellspacing="1" class="table_form">
          <caption>
            本地设备列表
  		  </caption>
          <tr>
            <td style="padding:0px;">
            <!-- DATAGRID -->
            <table id="localdeviceView" class="easyui-datagrid" style="height:300px;"
                        data-options="rownumbers:true,singleSelect:true,url:'/IoT_Harbor/viewdevice.html',method:'get'">
			         <thead>
                            <tr style="background:#CCC">
                            <th data-options="checkbox:true">全选</th>
                            <th data-options="field:'ID',width:50,align:'center',formatter:Linkformatter">ID</th>
                            <th data-options="field:'devicename',width:200,align:'center'">设备名称</th>
                            
                            <th data-options="field:'status',width:100,align:'center'">当前状态</th>
                  <!--           <th data-options="field:'enrolltime',width:100,align:'center'">注册时间</th>
                            <th data-options="field:'updatetime',width:100,align:'center'">修改时间</th> -->
                            <th data-options="field:'lifecycle',width:100,align:'center'">生命周期</th>
                            <th data-options="field:'belongto',width:100,align:'center'">设备所属</th>
                            <th data-options="field:'visable',width:100,align:'center'">可见性</th>
                            <th data-options="field:'cc',width:300,align:'center',formatter:Eventformatter,height:50">操作</th> 
                              
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
          	<td width="5%" style="text-align:right">
            	备注：
            </td>
            <td width="95%">
	            		1.普通用户只能看见自己注册的设备或公开性为public的设备。管理员可以看见所有设备，并只有管理员可以使用修改、删除、激活和冻结
            </td>
          </tr>
          <tr>
          	<td width="5%" style="text-align:right">
            </td>
            <td width="95%">
	            		2.申请授权需关联VE
            </td>
          </tr>
</table>
<!--Form1End-->

</body>
</html>