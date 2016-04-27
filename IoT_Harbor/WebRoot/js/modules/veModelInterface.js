// JavaScript Document

function getActivePEPageContent(veID,callback)
{
					$.ajax({type:"POST",
				   	url:"/appPlatform/queryVeModel.html",
					data:"tplId="+veID+((plusstring=='')?(''):('&'+plusstring)),
					dataType:"json",
					success:function(json)
				   	{
							if(json!=undefined && json!=null)
							{
								var M=(JSONString2JSON(json)["rows"][0]);
								callback(M);
							}
					},
					error:function()
					{
					}
				   });
}

function getVEModelList_Developer(Params,callback)
{
					if(Params=="")
					{
						Params=((plusstring=='')?(''):('&'+plusstring));
					}
					if(Params.indexOf("session")<=0)
					{
						Params=Params+((plusstring=='')?(''):('&'+plusstring));
					}
					$.ajax({type:"POST",
				   	url:"/appPlatform/getVeModelList.html",
					data:Params,
					dataType:"json",
					success:function(json)
				   	{
							if(json!=undefined && json!=null)
							{
								json=json.replace(/"tpl_state":"001"/g,"\"tpl_state\":\"正常\",\"tpl_stateColor\":\"#0F0;\"");
								json=json.replace(/"tpl_state":"002"/g,"\"tpl_state\":\"未审核\",\"tpl_stateColor\":\"#000;\"");
								json=json.replace(/"tpl_state":"003"/g,"\"tpl_state\":\"异常\",\"tpl_stateColor\":\"#F00;\"");
								json=json.replace(/"tpl_state":"004"/g,"\"tpl_state\":\"未通过\",\"tpl_stateColor\":\"#F00;\"");
								json=json.replace(/"tpl_state":"005"/g,"\"tpl_state\":\"冻结\",\"tpl_stateColor\":\"#00F;\"");
								var M=(JSONString2JSON(json));
								callback(M);
							}
					},
					error:function()
					{
					}
				   });
}

function deleteVEModel(veID,callback)
{
	$.ajax({type:"POST",
				   	url:"/appPlatform/deleteVeModel.html",
					data:"tplId="+veID+((plusstring=='')?(''):('&'+plusstring)),
					success:function(json)
				   	{
							if(json=="success" || json=="\"012\"" || json=="012")
							{
								callback("成功！");
							}else
							{
								var isStringJSON=(json.substr(0,1)=="\"");
								if(isStringJSON)
								{
									eval("var jptmp="+json);
									callback(jptmp);
								}else
								{
									callback(json);
								}
							}
					},
					error:function()
					{
						callback("网络失败！");
					}
				   });
}

function ConfirmVEModel(veID,callback)
{
	$.ajax({type:"POST",
				   	url:"/appPlatform/updateVeModelState.html ",
					data:"state=001&tplId="+veID+((plusstring=='')?(''):('&'+plusstring)),
					success:function(json)
				   	{
							if(json=="success" || json=="\"012\"" || json=="012")
							{
								callback("成功！");
							}else
							{
								var isStringJSON=(json.substr(0,1)=="\"");
								if(isStringJSON)
								{
									eval("var jptmp="+json);
									callback(jptmp);
								}else
								{
									callback(json);
								}
							}
					},
					error:function()
					{
						callback("网络失败！");
					}
				   });
}


function RefushVEModel(veID,callback)
{
	$.ajax({type:"POST",
				   	url:"/appPlatform/updateVeModelState.html ",
					data:"state=004&tplId="+veID+((plusstring=='')?(''):('&'+plusstring)),
					success:function(json)
				   	{
							if(json=="success" || json=="\"012\"" || json=="012")
							{
								callback("成功！");
							}else
							{
								var isStringJSON=(json.substr(0,1)=="\"");
								if(isStringJSON)
								{
									eval("var jptmp="+json);
									callback(jptmp);
								}else
								{
									callback(json);
								}
							}
					},
					error:function()
					{
						callback("网络失败！");
					}
				   });
}

function FreezeVEModel(veID,callback)
{
	$.ajax({type:"POST",
				   	url:"/appPlatform/updateVeModelState.html ",
					data:"state=005&tplId="+veID+((plusstring=='')?(''):('&'+plusstring)),
					success:function(json)
				   	{
							if(json=="success" || json=="\"012\"" || json=="012")
							{
								callback("成功！");
							}else
							{
								var isStringJSON=(json.substr(0,1)=="\"");
								if(isStringJSON)
								{
									eval("var jptmp="+json);
									callback(jptmp);
								}else
								{
									callback(json);
								}
							}
					},
					error:function()
					{
						callback("网络失败！");
					}
				   });
}


function ActiveVEModel(veID,callback)
{
	$.ajax({type:"POST",
				   	url:"/appPlatform/updateVeModelState.html ",
					data:"state=001&tplId="+veID+((plusstring=='')?(''):('&'+plusstring)),
					success:function(json)
				   	{
							if(json=="success" || json=="\"012\"" || json=="012")
							{
								callback("成功！");
							}else
							{
								var isStringJSON=(json.substr(0,1)=="\"");
								if(isStringJSON)
								{
									eval("var jptmp="+json);
									callback(jptmp);
								}else
								{
									callback(json);
								}
							}
					},
					error:function()
					{
						callback("网络失败！");
					}
				   });
}
