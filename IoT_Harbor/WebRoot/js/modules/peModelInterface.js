// JavaScript Document  LPY

function deleteModelInfo(peModelId,callback)
{
					$.ajax({type:"POST",
				   	url:"/PSDLProject/deleteModelInfo.action",
					data:"pemodelID="+peModelId,
					success:function(json)
				   	{
							if(json=="success")
							{
								callback("成功！");
							}else
							{
								callback(json);
							}
					},
					error:function()
					{
						callback("NetError");
					}
				   });
}

function AcceptModelInfo(peModelId,callback)
{
					$.ajax({type:"POST",
				   	url:"/PSDLProject/checkModel.action",
					data:"status=1&pemodelID="+peModelId,
					success:function(json)
				   	{
							if(json=="success")
							{
								callback("成功！");
							}else
							{
								callback(json);
							}
					},
					error:function()
					{
						callback("NetError");
					}
				   });
}

function RefushModelInfo(peModelId,callback)
{
					$.ajax({type:"POST",
				   	url:"/PSDLProject/checkModel.action",
					data:"status=2&pemodelID="+peModelId,
					success:function(json)
				   	{
							if(json=="success")
							{
								callback("成功！");
							}else
							{
								callback(json);
							}
					},
					error:function()
					{
						callback("NetError");
					}
				   });
}

function disfrockModelInfo(peModelName,callbackSuccess)
{
					$.ajax({type:"POST",
				   	url:"/PSDLProject/disfrockModelInfo.action",
					data:"pemodelName="+peModelName,
					success:function(json)
				   	{
							if(json=="success")
							{
								callbackSuccess();
							}
					},
					error:function()
					{
					}
				   });
}

function getPEInfo(peID,callback)
{
					$.ajax({type:"POST",
				   	url:"/PSDLProject/getPEInfo.action",
					data:"peID="+peID,
					datatype:"json",
					success:function(json)
				   	{
							if(json!=undefined && json!=null)
							{
								callback(json);
							}
					},
					error:function()
					{
					}
				   });
}

function deletePEInfo(peID,status,callbackSuccess)
{
					$.ajax({type:"POST",
				   	url:"/PSDLProject/deletePEInfo.action",
					data:"peID="+peID+"&status="+status,
					success:function(json)
				   	{
							if(json=="success")
							{
								callbackSuccess();
							}
					},
					error:function()
					{
					}
				   });
}

function disfrockPEInfo(peID,callbackSuccess)
{
					$.ajax({type:"POST",
				   	url:"/PSDLProject/disfrockPEInfo.action",
					data:"peID="+peID,
					success:function(json)
				   	{
							if(json=="success")
							{
								callbackSuccess();
							}
					},
					error:function()
					{
					}
				   });
}

function getActivePEPageContent(peID,callback)
{
					$.ajax({type:"POST",
				   	url:"/PSDLProject/activePE.action",
					data:"peID="+peID,
					dataType:"json",
					success:function(json)
				   	{
							if(json!=undefined && json!=null)
							{
								callback(json);
							}
					},
					error:function()
					{
					}
				   });
}

function checkName(name,callback)
{
					$.ajax({type:"POST",
				   	url:"/PSDLProject/checkName.action",
					data:"Name="+name,
					success:function(json)
				   	{
							if(json=="success")
							{
								callback(true);
							}else
							{
								callback(false);
							}
					},
					error:function()
					{
						alert('网络超时！');
					}
				   });
}



function getPEModelList_Developer(Params,pageid,rows,callback)
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
				   	url:"/PSDLProject/getHarborModelList.action",
					data:Params+"&page="+pageid+"&rows="+rows,
					dataType:"text",
					success:function(json)
				   	{
							if(json!=undefined && json!=null)
							{
								json=json.replace(/"status":"0"/g,"\"status\":\"未审核\",\"peStatusColor\":\"#00F;\"");
								
								json=json.replace(/"status":"1"/g,"\"status\":\"已审核\",\"peStatusColor\":\"#0F0;\"");
								
								json=json.replace(/"status":"2"/g,"\"status\":\"已拒绝\",\"peStatusColor\":\"#F00;\"");
								
								var M=(JSONString2JSON(json));
								callback(M,pageid);
							}
					},
					error:function()
					{
					}
				   });
}