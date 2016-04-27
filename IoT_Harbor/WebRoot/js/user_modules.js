//UserEvent
function LoadUserMsg(usermsg,callback)
{
	var username=usermsg.userName;
	var session=usermsg.session;
	var userid=usermsg.userId;
	var RoleId=usermsg.userRole;
	var roleid=parseInt(RoleId);
	var rolename=getNameofRole(roleid);
	
	try{
		$("#usern").html(username);
		$("#userbutton").html("【"+rolename+"】|<a href='#' onclick='LoginOut();return false;' style='text-decoration:none;'>注销</a>");
		$("#avatar").attr("src",pageroot+"images/public/defuser.jpg");
	}catch(e){}
	
	try{$("#userId").attr("value",""+userid.toString());}catch(e){}
	try{$("#userName").attr("value",username);}catch(e){}

	callback();
	RoleControl(roleid);
}

function LoadUserDefault()
{
	//PublicEvent
	try{
		$("#usern").html("用户未登陆");
		$("#userbutton").html("<a href='"+getpagebyrole(-1)+"' style='text-decoration:none;'>登陆</a>|<a href='"+getpagebyrole(-2)+"' style='text-decoration:none;'>注册</a>");
		$("#avatar").attr("src",pageroot+"images/avatar.jpg");
		//alert(UserDebug);
		/*
		return;
		*/
		if(!UserDebug){
						var marvurl=window.location.href;
						if(marvurl.indexOf("session=")>0)
						{
							if(Request.QueryString("session")!=null)
							{
								if(marvurl.indexOf("session="+Request.QueryString("session"))>0)
								{
									marvurl=marvurl.replace("&session="+Request.QueryString("session"),"");
									marvurl=marvurl.replace("?session="+Request.QueryString("session"),"");
								}
							}
						}
						$("#usern").html("用户未登陆<script>window.location.href='"+getpagebyrole(-1)+"?returnurl="+escape(marvurl)+"'</script>");
						}
	}catch(e){}
	//RoleControl(1);
}

function RoleControl(roleid)
{
	if(PageRole!=roleid)
	{
		window.location.href=getpagebyrole(100);
	}
}