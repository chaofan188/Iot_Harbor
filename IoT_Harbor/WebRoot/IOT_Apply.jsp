<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="css/easyui/easyui.css">
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<title>TNS/IOT 管理系统 DEMO</title>
</head>
<style>
body
{
	margin:0px;
	padding:0px;
}
</style>
<body>
<div id="background" style="height:100%;width:100%;">
	<div id="topbg" style="min-height:684px;">
    <table height="100%" width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
      	<td height="95" style="background:url(images/B_Title.png) repeat-x bottom;"><div style="width:1300px;margin:0 auto;"><img src="images/B_Title1.png" style="margin-left:0px;"/></div></td>
      </tr>
      <tr>
      	<td height="31" style="background:url(images/B_Nav.png) repeat-x center;text-align:right;color:#FFF;font-size:12px;padding-right:20px;">
        <div style="width:1300px;margin:0 auto;">
        欢迎aaa用户, 今天是
		
		<script>
		var myDate=new Date();
		var sYear=myDate.getFullYear();
		var sMonth=myDate.getMonth()+1;
		var sDate=myDate.getDate();
		document.write(sYear+"年"+sMonth+"月"+sDate+"日");
		var WeekEnd=myDate.getDay();
		switch(WeekEnd)
		{
			case 0:document.write("&nbsp;星期日");break;
			case 1:document.write("&nbsp;星期一");break;
			case 2:document.write("&nbsp;星期二");break;
			case 3:document.write("&nbsp;星期三");break;
			case 4:document.write("&nbsp;星期四");break;
			case 5:document.write("&nbsp;星期五");break;
			case 6:document.write("&nbsp;星期六");break;
		}
        </script> 
        
        &nbsp;&nbsp;&nbsp;&nbsp;
        <a href="#" style="color:#FFF; text-decoration:none;">退出系统</a></div>
        </td>
      </tr>
      <tr>
        <td valign="top" style="background:url(images/B_Body_BG.png) top repeat-x;">
       
                <table width="1300" border="0" cellspacing="0" cellpadding="0" style="margin:0 auto;">
                  <tr>
                    <td width="211" height="34">&nbsp;</td>
                    <td width="1072">&nbsp;</td>
                  </tr>
                  <tr>
                    <td valign="top">
                                <table width="185" border="0" cellspacing="0" cellpadding="0" style="font-weight:bold;font-size:12px;">
                                  <tr>
                                    <td height=42 style="background:url(images/B_L_Top.png) bottom no-repeat; line-height:30px;" valign="bottom"> &nbsp;&nbsp;&nbsp;&nbsp;<font color="#00CCFF">→</font>导航栏 </td>
                                  </tr>
                                  <tr>
                                    <td style="background:url(images/B_L_Body.png) repeat-y;">
                                    
                               	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                          <tr>
                                            <td height="60" style="padding-left:20px;padding-right:20px;background:url(images/B_L_ItemSpliter.png) bottom no-repeat;"><a>服务域名注册申请</a></td>
                                          </tr>
                                          <tr>
                                            <td height="44" style="padding-left:20px;padding-right:20px;background:url(images/B_L_ItemSpliter.png) bottom no-repeat;"><a href="DomainManage/list.jsp">注册信息修改</a></td>
                                          </tr>
                                          <tr>
                                            <td height="44" style="padding-left:20px;padding-right:20px;background:url(images/B_L_ItemSpliter.png) bottom no-repeat;"><a href="IOT_ViewStatus.jsp">查看审批状态</a></td>
                                          </tr>
                                          <tr>
                                            <td height="44" style="padding-left:20px;padding-right:20px;background:url(images/B_L_ItemSpliter.png) bottom no-repeat;"><a href="IOT_BindIOT.jsp">服务域名绑定</a></td>
                                          </tr>
                                          <tr>
                                            <td height="44" style="padding-left:20px;padding-right:20px;background:url(images/B_L_ItemSpliter.png) bottom no-repeat;"><a href="IOT_Delete.jsp">服务域名注销</a></td>
                                          </tr>
                                        </table>

                                    
                                    
                                    </td>
                                  </tr>
                                  <tr>
                                    <td height=56 style="background:url(images/B_L_Bottom.png) top no-repeat;">&nbsp;</td>
                                  </tr>
                                </table>
                    </td>
                    <td valign="top">
                            <table width="100%" height="95" border="0" cellpadding="0" cellspacing="0">
                              <tr>
                                <td height="60" style="border-bottom:1px dotted;"><p style="display:block; height:32px; line-height:32px; font-weight:bold; font-size:12px; padding-left:20px; background:#cdcec8"><font color="#FF0000" style=" font-family: 'Wingdings 3';"></font>&nbsp;服务域名注册申请</p></td>
                              </tr>
                              <tr>
                                <td>
                                
                                <form id="form1" action="bindAddress.jsp" method="POST">
                                    <input type="hidden" name="userID" id="userID" value="5"/>
                                <table width="100%" border="0" cellspacing="0" cellpadding="0" style="font-size:12px;">
                                  <tr>
                                    <td height="42" style="padding-left:30px;padding-right:30px;">
                                    <div style="min-width:730px;">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                      <tr>
                                        <td width="150" height="42">&nbsp;&nbsp;申请服务域名：</td>
                                        <td width="100"><input class="easyui-validatebox" type="text" name="domainRegName" id="domainRegName" data-options="required:false" style="width:220px"></input></td>
                                        <td width="80">前缀：</td>
                                        <td width="100"><input id="domainID" name="domainID" class="easyui-combobox" data-options="valueField:'domainName',textField:'domainName',panelHeight:'90%'" style="width:220px"></input>
										</td>
                                        <td width="80">有效期：</td>
                                        <td width="100"><input name="avaliableTime" id="avaliableTime" class="easyui-datebox" style="width:220px"></input></td>
                                      </tr>
                                      <tr>
                                        <td width="80" height="42">&nbsp;&nbsp;服务域名类型：</td>
                                        <td>
                                        	<script>
												function changetype(Value)
												{
													if(Value!="0" && Value!=0)
													{
														$("#mcard_original").css("visibility","visible");
														$("#mcard_original").css("position","relative");
														$("#mcard_private").css("visibility","hidden");
														$("#mcard_private").css("position","absolute");
													}else
													{
														$("#mcard_private").css("visibility","visible");
														$("#mcard_private").css("position","relative");
														$("#mcard_original").css("visibility","hidden");
														$("#mcard_original").css("position","absolute");
													}
												}
											</script>
                                            <select id="type" name="type" class="easyui-combobox" data-options="onChange:function(newValue,oldValue){changetype(newValue);}" style="width:220px;">
                                                <option value=0>个人</option> 
                                                <option value=1>组织</option>
                                            </select>
                                        </td>
                                        <td width="70">&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td width="78">&nbsp;</td>
                                        <td >&nbsp;</td>
                                      </tr>
                                     </table>
                                     </div>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td align="left" valign="top" style="padding-left:30px;padding-right:30px;">
                                  <div id="mcard_private" style="min-width:730px;visibility:visible; position:relative;">
                                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                          <tr>
                                            <td width="120" height="42">&nbsp;&nbsp;工作单位：</td>
                                            <td width="255"><input class="easyui-validatebox" type="text" name="perWorkPlace" id="perWorkPlace" data-options="required:false" style="width:220px"></input></td>
                                            <td width="311"></td>
                                            <td width="362"></td>
                                           </tr>
                                           <tr>
                                            <td width="83" height="42">&nbsp;&nbsp;姓名：</td>
                                            <td width="255"><input class="easyui-validatebox" type="text" name="perUserName" id="perUserName" data-options="required:false" style="width:220px"></input></td>
                                            <td></td>
                                            <td></td>
                                           </tr><tr>
                                            <td width="83" height="42">&nbsp;&nbsp;身份证号：</td>
                                            <td width="255"><input class="easyui-validatebox" type="text" name="perIndentiID" id="perIndentiID" data-options="required:false" style="width:220px"></input></td>
                                            <td></td>
                                            <td></td>
                                           </tr><tr>
                                          </tr>
                                         </table>
                                    </div>
                                  <div id="mcard_original" style="min-width:730px; visibility:hidden; position:absolute;">
                                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                          <tr>
                                            <td width="82" height="42">&nbsp;&nbsp;机构名称：</td>
                                            <td width="257"><input class="easyui-validatebox" type="text" name="departName" id="departName" data-options="required:false" style="width:220px"></input></td>
                                            <td width="83">机构法人：</td>
                                            <td width="305"><input class="easyui-validatebox" type="text" name="departPeople" id="departPeople" data-options="required:false" style="width:220px"></input></td>
                                            <td width="97">&nbsp;</td>
                                            <td width="187">&nbsp;</td>
                                          </tr>
                                          <tr>
                                            <td width="82" height="42">&nbsp;&nbsp;机构代码：</td>
                                            <td width="257"><input class="easyui-validatebox" type="text" name="departCode" id="departCode" data-options="required:false" style="width:220px"></input></td>
                                            <td width="83">所属地区：</td>
                                            <td width="305"><input class="easyui-validatebox" type="text" name="departDistrict" id="departDistrict" data-options="required:false" style="width:220px"></input></td>
                                            <td width="97">&nbsp;</td>
                                            <td width="187">&nbsp;</td>
                                          </tr>
                                          <tr>
                                            <td width="82" height="42">&nbsp;&nbsp;单位性质：</td>
                                            <td width="257"><input class="easyui-validatebox" type="text" name="departType" id="departType" data-options="required:false" style="width:220px"></input></td>
                                            <td width="83">&nbsp;</td>
                                            <td width="305">&nbsp;</td>
                                            <td width="97">&nbsp;</td>
                                            <td width="187">&nbsp;</td>
                                          </tr>
                                         </table>
                                    </div>
                                    </td>
                                  </tr>                                  
                                  <tr>
                                    <td  style="padding-left:30px;padding-right:30px;">
                                    <div style="min-width:730px;">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                      <tr>
                                        <td width="120" height="42">&nbsp;&nbsp;电话：</td>
                                        <td width="582"><input class="easyui-validatebox" type="text" name="phone" id="phone" data-options="required:false" style="width:220px"></input></td>
                                        <td width="18">&nbsp;</td>
                                        <td width="25">&nbsp;</td>
                                        <td width="103">&nbsp;</td>
                                        <td width="223">&nbsp;</td>
                                      </tr>
                                      <tr>
                                        <td width="120" height="42">&nbsp;&nbsp;邮编：</td>
                                        <td><input class="easyui-validatebox" type="text" name="postCode" id="postCode" data-options="required:false" style="width:220px"></input></td>
                                        <td width="18">&nbsp;</td>
                                        <td width="25">&nbsp;</td>
                                        <td width="103">&nbsp;</td>
                                        <td width="223">&nbsp;</td>
                                      </tr>
                                      <tr>
                                        <td width="120" height="42">&nbsp;&nbsp;电子信箱：</td>
                                        <td><input class="easyui-validatebox" type="text" name="email" id="email" data-options="required:false" style="width:220px"></input></td>
                                        <td width="18">&nbsp;</td>
                                        <td width="25">&nbsp;</td>
                                        <td width="103">&nbsp;</td>
                                        <td width="223">&nbsp;</td>
                                      </tr>
                                      <tr>
                                        <td width="120" height="42">&nbsp;&nbsp;联系地址：</td>
                                        <td width="582"><input class="easyui-validatebox" type="text" name="address" id="address" data-options="required:false" style="width:100%"></input></td>
                                        <td width="18">&nbsp;</td>
                                        <td width="25">&nbsp;</td>
                                        <td width="103">&nbsp;</td>
                                        <td width="223">&nbsp;</td>
                                      </tr>
                                     </table>
                                     </div>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td height=64 align="center" valign="bottom" style="padding-left:30px;padding-right:30px;">
                                    
                                    
                                         <table border="0" cellspacing="0" cellpadding="0">
                                          <tr>
                                            <td width=100>
                                            <script>
											var userId=5;
											function filliotbox(url,data)
											{
													$.get(url,data, function(jsonobj){
													if(jsonobj!=undefined)
													{
														$("#domainID").combobox("loadData",jsonobj["DomainPool"]);
													}
													},"json");
											}
											
											var SubmitURL='';
											function formonsubmit()
											{																								
												var postdata=$("#form1").serialize();
												$.ajax({type:"POST",									
														url:SubmitURL,									
														data:postdata,
														success:function(json)									
														{									
																if(json=="success")									
																{									
																//	alert("注册成功！");									
																	callback();									
																	return true;									
																}									
																else									
																{									
																//	alert(json);									
																	return false;									
																}									
														},									
														error:function()									
														{									
															//alert("网络通信失败！");									
															return false;									
														}									
													   });		
											}
											</script>
                                            <a class="ButtonBlue" href="#" onclick="formonsubmit();return false;" style="text-decoration:none;"><table  height="24" border="0" cellspacing="0" cellpadding="0"><tr><td width=3 style="background:url(images/M_Btm_Blue_L.png) right no-repeat;"></td><td style="background:url(images/M_Btm_Blue_B.png) center repeat-x; color:#EEE; font-size:13px; text-align: justify;padding-left:10px;padding-right:10px;font-weight:bold;">确&nbsp;&nbsp;认</td><td width=3 style="background:url(images/M_Btm_Blue_R.png) left no-repeat;"></td></tr></table></a>
                                            </td>
                                            <td>
                                            <a class="ButtonGreen" href="#" onclick="$('#form1').form('clear');return false;" style="text-decoration:none;"><table  height="24" border="0" cellspacing="0" cellpadding="0"><tr><td width=3 style="background:url(images/M_Btm_Green_L.png) right no-repeat;"></td><td style="background:url(images/M_Btm_Green_B.png) center repeat-x; color:#EEE; font-size:13px; text-align: justify;padding-left:10px;padding-right:10px;font-weight:bold;">重&nbsp;&nbsp;填</td><td width=3 style="background:url(images/M_Btm_Green_R.png) left no-repeat;"></td></tr></table></a>

                                            </td>
                                          </tr>
                                        </table>
                                    
                                    </td>
                                  </tr>
                                </table>
                                </form>
                                <br/><br/></td>
                              </tr>
                            </table>
                    </td>
                    <td width=21>&nbsp;</td>
                  </tr>
                </table>
        
        </td>
      </tr>
    </table>

    

    
    </div>
<div id="bottombg" style="height:64px;background:#F00;background:url(images/L_bgbottom.png) top repeat-x;text-align:center;padding-top:80px;">
    	<p style="font-size:12px;font-weight:bold;font-family:'微软雅黑';color:#666;">物联服务域名注册申请管理系统</p>
    </div>
</div>


<script>
function LoadData(url,data)
{
/*
msg_stat   申请状态
msg_IOTname   服务域名全称
msg_IOTType   申请类型
msg_OrgName   机构名称
msg_OfficeType  单位性质
msg_Office  工作单位
msg_master  机构法人
msg_OfficeCode"  机构代码
msg_Name    姓名
msg_Area    所属地区
msg_Card    付款账户
msg_IDCard   身份证
msg_Phone    电话
msg_eMail    电子邮件
msg_BindURL   绑定地址
msg_IOTDeadline   服务域名有效期
msg_Address   联系地址
			*/

								//$("#type").combobox("select",1);
							var filltextbox=function(id,jsonObj)
							{
								$("#"+id).val(jsonObj[id]);
							}
							var fillcombobox=function(id,jsonObj)
							{
								$("#"+id).combobox("select",parseInt(jsonObj[id]));
							}
							var filldatebox=function(id,jsonObj)
							{
								$("#"+id).datebox("setValue",jsonObj[id]);
							}
							$.post(url,data, function(jsonobj){
								if(jsonobj!=undefined)
								{
									var TData=jsonobj["info"];
									userId=TData["userID"];
									
									filltextbox("userID",TData);
									filltextbox("domainID",TData);
									fillcombobox("type",TData);
									filltextbox("domainRegName",TData);
									filldatebox("avaliableTime",TData);
									filltextbox("perWorkPlace",TData);
									filltextbox("perUserName",TData);
									filltextbox("perIndentiID",TData);
									filltextbox("departName",TData);
									filltextbox("departType",TData);
									filltextbox("departPeople",TData);
									filltextbox("departCode",TData);
									filltextbox("departDistrict",TData);
									filltextbox("address",TData);
									filltextbox("postCode",TData);
									filltextbox("email",TData);
									filltextbox("phone",TData);
								}
							},"json");

}
$(document).ready(function(){
			if(!$.browser.msie)
			{
				$(window).resize(function(){
											 var WinH=$(window).height()-144;
											 $("#topbg").css("height",WinH+"px");
										  });
				$(window).resize();
			}
			filliotbox('/IOTDomainDemo/getdomainPoolList.action',"userID="+userId);
			//LoadData('/IOTDomainDemo/getUserInfo.action','userId='+userId);
			SubmitURL='/IOTDomainDemo/addDomainRegist.action';
	});
</script>
</body>
</html>
