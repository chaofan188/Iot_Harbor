//UserEvent
var UserDebug=true;
var jsession="";
var plusstring="";
var jsessquery=Request.QueryString("session");
var sync="";
var syncquery=Request.QueryString("session");
if(jsessquery!=null)
{
	jsession=jsessquery;
}
if(syncquery!=null)
{
	sync=syncquery;
}
function LoadUser(nextstep)
{
	ExeJSESSION();
	if(jsession=="")
	{
		//空模式
		LoadUserDefault();
		nextstep();
	}
	else
	{
		//有SESSION，验证
		$.ajax({type:"POST",
			url:"/appPlatform/checkOut.html ",
			data:"session="+jsession+"&ret="+(new Date()).toString(),
			success:function(json)
			{
				var flg=true;
				var json2;
				if(json=="")
				{
					flg=false;
				}else
				{
					json2=JSONString2JSON(json);
					if(json2==undefined || json2==null)
					{
						flg=false;
					}
				}
				
				if(flg)
				{
					RegistrySession(json2,nextstep);//如果解析成功，判定SESSION
				}
				else
				{
					LoadUserDefault(); //否则加载默认信息
					nextstep();
				}
				return;
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				if(XMLHttpRequest.status==0 && errorThrown=="")
				{
					//302 EVETNS
					jsession="";
					plusstring="";
					ExeJSESSION();
					LoadUserDefault();
					nextstep();
				}else
				{
					alert("发生请求错误！ HTTP "+ XMLHttpRequest.status + " : "+errorThrown);
					LoadUserDefault(); //否则加载默认信
					nextstep();
				}
			}
		});
	}
}

function JumpToIndex()
{
	var IndexConfirm=false;
	try
	{
		IndexConfirm=isIndex;
	}catch(e){;}
	if(!IndexConfirm)
	{
		window.location.href=getpagebyrole(100);
	}
}
function ClearSession(sessionbedelete)
{		
		if(jsession=="")
		{
			JumpToIndex();
		}else
		{
			RegistrySessionToLYP(sessionbedelete,true,function(result){
															   JumpToIndex();
														   });
		}
		jsession="";
		plusstring="";
		ExeJSESSION();
		window.location.href=getpagebyrole(100);
		//LoadUserDefault();
}

function UpdateSession(usersession,nextstep)
{
		jsession=usersession;
		plusstring="";
		RegistrySessionToLYP(usersession,false,function(){});
}

function RegistrySession(userjson,nextstep)
{
	var usersession=userjson.session;
	if(usersession=="")//认证失败
	{
		ClearSession();
	}
	else
	{
		if(jsession!=usersession || sync!="")//更新JSESSSION
		{
			UpdateSession(usersession,nextstep);
		}
		//FIX
		LoadUserMsg(userjson,nextstep);
		ExeJSESSION();
	}
}
function RegistrySessionToLYP(session,isDelete,callback)
{
		var DelS="";
		if(isDelete)
		{
			DelS="&delete=1";
		//DEBUG
			return;
		}
//		return;
		//REAL EVENTS
		$.ajax({type:"POST",
				url:"/PSDLProject/userInfo.action",
				data:"session="+jsession+DelS+"&ret="+(new Date()).toString(),
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
					callback(false);
				}
		});
		//DEBUG
		callback();
}

function LoginOut()
{
	//DEBUG
	ClearSession();
	return;
	//REAL
	$.ajax({type:"POST",
				url:"//appPlatform/logout.html",
				data:"session="+jsession+"&ret="+(new Date()).toString(),
				success:function(json)
				{
					if(json=="success")
					{
						ClearSession();
					}else
					{
						alert("注销失败！");
					}
				},
				error:function()
				{
						alert("通信失败！");
				}
		});
}



function CalcJsePlusString(hh)
{
			if(hh.toLowerCase().indexOf("javascript:")!=-1 || hh.indexOf("%7B")>-1)
			{
				return "";
			}
			//alert(hh);
			if(hh!="#" && hh!=undefined && hh!=null && hh!="")
			{
				if(hh.indexOf("session=")==-1)
				{
					var c1="";
					var c2="&";
					if(hh.indexOf("?")==-1)
					{
						c2="?";
					}
					if(hh.length>7 && c2=="?")
					{
						if(hh.substr(0,7).toLowerCase()=="http://")
						{
							var ott=hh.substr(7);
							if(ott.indexOf("/")==-1)
							{
								c1="/";
							}
						};
					}
					return c1+c2+plusstring;
				}else
				{
					return "";
				}
			}else
			{
				return "";
			}
}
function ExeJSESSION()
{
	if(jsession!="" || true)
	{
		plusstring="session="+jsession;
		$("a").each(function(i){
			var hh=this.href;
			//alert(this.hasClass("nosession"));
			var TN=CalcJsePlusString(hh);
			if(TN!="")
			{
			 	this.href=this.href+TN;
			}
		});
		$("form").each(function(i){
			//var hh=this.action;
			//this.action=this.action+CalcJsePlusString(hh);
				//this.append("<input type='hidden' name='session' value='"+jsession+"'/>");
				var formHTML=this.innerHTML.toLowerCase();
				var k=formHTML.indexOf("name=session")+formHTML.indexOf("name=\"session\"");
				if(k<0)
				{
					$("<input type='hidden' name='session' class='sessionbox'>", {
						  type: "hidden",
						  name:"session",
						  class:"sessionbox"
						}).appendTo(this);
				}
		});
		if(jsession!="" || true)
		{
			$(".sessionbox").each(function(i)
										   {
											   this.value=jsession;
										   });
		}
	}
}
function LoadUserSession()
{
	if(jsession!="" && plusstring=="")
	{
		plusstring="session="+jsession;
	}
}

function getpagebyrole(roleid)
{
	
	if(jsession!="" && plusstring=="")
	{
		plusstring="session="+jsession;
	}
	
	//0:R1 1:R2-ve 2:R2-app 3-R3 4-R4 5-R5 -1:login -2:reg other index
	switch(roleid)
	{
		case -2:return pageroot+'reg.html';
		case -1:return pageroot+'login.html';
		case 0:return pageroot+'Modules/R1_2.2.1/2.2.1.1.html'+((plusstring=="")?(""):("?"+plusstring));
		case 1:return pageroot+'Modules/R2_2.2.2/2.2.2.4.html'+((plusstring=="")?(""):("?"+plusstring));
		case 2:return pageroot+'Modules/R2_2.3.1/2.3.1.4.html'+((plusstring=="")?(""):("?"+plusstring));
		case 3:return pageroot+'Modules/R3_2.2.3/2.2.3.1.html'+((plusstring=="")?(""):("?"+plusstring));
		case 4:return pageroot+'Modules/R4_2.2.4/B_R_2_2/B_R_2_2_3.html'+((plusstring=="")?(""):("?"+plusstring));
		case 5:return pageroot+'Modules/R5_2.3.2/2.3.2.1.html'+((plusstring=="")?(""):("?"+plusstring));
		default:return pageroot+((plusstring=="")?(""):("?"+plusstring));
	}
}
function getNameofRole(roleid)
{
	switch(roleid)
	{
		case 0:return "物体生产商";
		case 1:return "VE开发者";
		case 2:return "应用开发者";
		case 3:return "物体接入者";
		case 4:return "运营商";
		case 5:return "应用用户";
		default:return "未知角色";
	}
}

function redirectbyrole(roleid)
{
	window.location.href=getpagebyrole(roleid);
}