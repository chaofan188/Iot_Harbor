// JavaScript Document

function ChangeUserModel(userID,userState,callback)
{
	//alert("userState="+userState+"&userId="+userID+((plusstring=='')?(''):('&'+plusstring)));
	$.ajax({type:"POST",
				   	url:"/appPlatform/updateUserState.html",
					data:"userState="+userState+"&userId="+userID+((plusstring=='')?(''):('&'+plusstring)),
					success:function(json)
				   	{
						
							var isStringJSON=(json.substr(0,1)=="\"");
							if(isStringJSON)
							{
								eval("var jptmp="+json);
								json=jptmp;
							}
							if(json=="success")
							{
								callback("成功！");
							}else
							{
								callback(jptmp);
							}
					},
					error:function()
					{
						callback("网络失败！");
					}
				   });
}

function ChangeUser2NoRealName(userID,callback)
{
	return ChangeUserModel(userID,1,callback);
}
function ChangeUser2RealName(userID,callback)
{
	return ChangeUserModel(userID,0,callback);
}
function ChangeUser2Freeze(userID,callback)
{
	return ChangeUserModel(userID,2,callback);
}


function getUserModelData(userID,callback)
{
					$.ajax({type:"POST",
				   	url:"/appPlatform/getUserList.html",
					data:"page=1&rows=1&userId="+userID+((plusstring=='')?(''):('&'+plusstring)),
					success:function(json)
				   	{
							json=json.replace(/"null"/g,"\"\"");
							json=JSONString2JSON(json);
							if(json!=undefined && json!=null)
							{
								callback(json["rows"][0]);
							}
					},
					error:function()
					{
					}
				   });
}