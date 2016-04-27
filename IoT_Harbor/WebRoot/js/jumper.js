var pageroot="/NewIndex/"
function BuildJump(location,session)
{
	if(FrontServerHost!=undefined && FrontServerHost!="")
	{
		pageroot="http://"+FrontServerHost+pageroot;
		FrontServerHost="";
	}
	var plusstring="session="+session;
	var l=location;
		if(l.indexOf("session=")<=0)
		{
			if(l.indexOf("?")>0)
			{
				l=l+"&"+plusstring;
			}else
			{
				l=l+"?"+plusstring;
			}
		}
		alert(pageroot+l);
	window.location.href=pageroot+l;
}
function DoJump()
{
	if(FrontServerHost!=undefined && FrontServerHost!="")
	{
		pageroot="http://"+FrontServerHost+pageroot;
		FrontServerHost="";
	}
	var roleid=-3;
	if(roleid<-2)
	{
		if(Request.QueryString("roleid")!=null)
		{
			roleid=parseInt(Request.QueryString("roleid"));
		}
	}
	if(Request.QueryString("location")!=null)
	{
		if(jsession!="" && plusstring=="")
		{
			plusstring="session="+jsession;
		}
		var l=Request.QueryString("location");
		if(l.indexOf("session=")<=0)
		{
			if(l.indexOf("?")>0)
			{
				l=l+"&"+plusstring;
			}else
			{
				l=l+"?"+plusstring;
			}
		}
		window.location.href=pageroot+l;
	}
	else
	{
		window.location.href=getpagebyrole(roleid);
	}
}