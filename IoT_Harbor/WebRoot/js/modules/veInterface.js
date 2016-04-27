// JavaScript Document
function queryVE(params,callback)
{
	$.ajax({type:"POST",
				   	url:"/appPlatform/queryVE.html",
					data:params+((plusstring=='')?(''):('&'+plusstring)),
					dataType:"json",
					success:function(json)
				   	{
							callback(json);
					},
					error:function()
					{
						callback(null);
					}
				   });
}

function deleteVE(veID,peID,callback)
{
	$.ajax({type:"POST",
				   	url:"/appPlatform/deleteVE.html",
					data:"peId="+peID+"&veId="+veID+((plusstring=='')?(''):('&'+plusstring)),
					success:function(json)
				   	{
							if(json=="success")
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

function getAccess(veID,callback)
{
	$.ajax({type:"POST",
				   	url:"/appPlatform/getAccess.html",
					data:"veId="+veID+((plusstring=='')?(''):('&'+plusstring)),
					dataType:"json",
					success:function(json)
				   	{
							callback(json);
					},
					error:function()
					{
						callback(null);
					}
				   });
}

function stopAccess(veID,callback)
{
	$.ajax({type:"POST",
				   	url:"/appPlatform/stopAccess.html",
					data:"sessionId="+veID+((plusstring=='')?(''):('&'+plusstring)),
					success:function(json)
				   	{
							if(json=="success")
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

function stopAllAccess(callback)
{
	$.ajax({type:"POST",
				   	url:"/appPlatform/stopAllAccess.html",
					data:((plusstring=='')?(''):('&'+plusstring)),
					success:function(json)
				   	{
							if(json=="success")
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

function activateVE(veID,callback)
{
	$.ajax({type:"POST",
				   	url:"/appPlatform/activateVe.html",
					data:"veId="+veID+((plusstring=='')?(''):('&'+plusstring)),
					success:function(json)
				   	{
							if(json=="success")
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

function activateAllVE(callback)
{
	$.ajax({type:"POST",
				   	url:"/appPlatform/activateAllVe.html",
					data:((plusstring=='')?(''):('&'+plusstring)),
					success:function(json)
				   	{
							if(json=="success")
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

function freezeVE(veID,callback)
{
	$.ajax({type:"POST",
				   	url:"/appPlatform/freezeVe.html",
					data:"veId="+veID+((plusstring=='')?(''):('&'+plusstring)),
					success:function(json)
				   	{
							if(json=="success")
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

function freezeAllVE(callback)
{
	$.ajax({type:"POST",
				   	url:"/appPlatform/freezeAllVe.html",
					data:((plusstring=='')?(''):('&'+plusstring)),
					success:function(json)
				   	{
							if(json=="success")
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