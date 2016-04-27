//UserEvent

function setRole(roleid)
{
	var roleId=roleid;
	if(typeof(roleid)=="string")
	{
		try
		{
			roleId=parseInt(roleid);
		}catch(e){
		}
	}
	clearRoles();
	switch(roleId)
	{
		case 0:$("#R1").attr("href",getpagebyrole(0));return;
		case 1:$("#R2").attr("href",getpagebyrole(1));return;
		case 2:$("#R2").attr("href",getpagebyrole(2));return;
		case 3:$("#R3").attr("href",getpagebyrole(3));return;
		case 4:$("#R4").attr("href",getpagebyrole(4));return;
		case 5:$("#R5").attr("href",getpagebyrole(5));return;
	}
}
function clearRoles()
{
		$("#R1").attr("href","login.html");
		$("#R2").attr("href","login.html");
		$("#R3").attr("href","login.html");
		$("#R4").attr("href","login.html");
		$("#R5").attr("href","login.html");
}

function LoadUserMsg(usermsg,callback)
{
		var username=usermsg.userName;
		var session=usermsg.session;
		var userId=usermsg.userId;
		var RoleId=usermsg.userRole;
		/*if(session=="")
		{
			LoadUserDefault();
			return;
		}*/
		callback();
		$("#IndexLoginArea").html("<a style='color:#FFF;text-decoration:none;'>欢迎你！&nbsp;"+username+"</a> | <a href='#' onclick='LoginOut();return false;' style='color:#FFF;text-decoration:none;'>注销</a>");
		setRole(RoleId);
}

function LoadUserDefault()
{
	//IndexEvent
	try{
		$("#IndexLoginArea").html("<a href='"+getpagebyrole(-1)+"' style='color:#FFF;text-decoration:none;'>登陆</a> | <a href='"+getpagebyrole(-2)+"' style='color:#FFF;text-decoration:none;'>注册</a>");
		clearRoles();
		if(UserDebug)
		{
			var k=prompt("请输入开发者角色id\r\n0：生产者\r\n1：VE开发者\r\n2：应用开发者\r\n3：接入者\r\n4：运营商\r\n5：使用者\r\n其他：跳转入港名申请管理系统","1");
			var PK=(parseInt(k));
			if(PK<0 || PK>5)
			{
				window.location.href='../IOT/';
				return;
			}
		    setRole(PK);
			/*
			$("#R1").attr("href",getpagebyrole(0));
			$("#R3").attr("href",getpagebyrole(3));
			$("#R4").attr("href",getpagebyrole(4));
			$("#R5").attr("href",getpagebyrole(5));
			*/
		}
	}catch(e){}
}

