// JavaScript Document

function ClearUserForm(isLogin)
{
	var ReturnPGID=-1;
	if(isLogin)
	{
		ReturnPGID=-1;
	}else
	{
		ReturnPGID=-2;
	}
	if(Request.QueryString("session")!=null)
	{
		if(Request.QueryString("returnurl")!=null)
							{
								window.location.href=getpagebyrole(ReturnPGID)+"?returnurl="+(Request.QueryString("returnurl").replace("%26sync%3D1",""));
							}else
							{
								window.location.href=getpagebyrole(ReturnPGID);
							}
	}
}

function LoginForm(formcid)
{
	SubmitFormAJAX(formcid,
				   function(json)
				   {
					    if(json!="")
						{
							try
							{
								var jsonx=JSONString2JSON(json);
								jsession=jsonx.session;
								var RoleId=jsonx.userRole;
								var roleid=parseInt(RoleId);
								//alert(jsession);
								if(Request.QueryString("returnurl")!=null)
								{
									var backurl=unescape(Request.QueryString("returnurl").replace("%26sync%3D1",""));
									if(backurl.indexOf(".html?")>0)
									{
										window.location.href=backurl+"&session="+jsession;
									}
									else
									{
										window.location.href=backurl+"?session="+jsession;
									}
								}
								else
								{
									if(getpagebyrole(roleid).indexOf(".html?")>0)
									{
										window.location.href=getpagebyrole(roleid)+"&sync=1";
									}else
									{
										window.location.href=getpagebyrole(roleid)+"?sync=1";
									}
								}
		//						window.location.href=getpagebyrole(100);
								return true;
							}catch(e)
							{
								var isStringJSON=(json.substr(0,1)=="\"");
								if(isStringJSON)
								{
									eval("var k="+json);
									alert(k);
								}else
								{
									alert(json);
								}
							}
						}
						else
						{
							alert("信息错误,登陆失败！");
							return false;
						}
				   },
				   function()
				   {
						alert("网络通信失败！");
				   });
}

function RegisterForm(formcid)
{
	var p1=$("#pwd").attr("value");
	var p2=$("#pwd2").attr("value");
	if(p1==p2)
	{
		
		SubmitFormAJAX(formcid,
				   function(json)
				   {
					    if(json=="\"success\"")
						{
//							jsession=JSONString2JSON(json).session;

							if(Request.QueryString("returnurl")!=null)
							{
								window.location.href=getpagebyrole(-1)+"?returnurl="+(Request.QueryString("returnurl").replace("%26sync%3D1",""));
							}else
							{
								window.location.href=getpagebyrole(-1);
							}
							return true;
						}
						else
						{
							var isStringJSON=(json.substr(0,1)=="\"");
							if(isStringJSON)
							{
								eval("var k="+json);
								alert(k);
							}else
							{
								alert(json);
							}
							return false;
						}
				   },
				   function()
				   {
						alert("网络通信失败！");
				   });
		
	}else
	{
		alert("两次密码输入不一致！");
	}
}

