// JavaScript Document
function queryPeById(peid,callback)
{
						EngineString2JSON("POST","/appPlatform/queryPeById.html","peId="+peid+"&page=1&rows=1"+((plusstring=='')?(''):('&'+plusstring)),function(json){
																																						  //SUCCESS
																																						  	var json0=json["rows"][0];//["peName"]);
																																								callback(json0);
																																						  },function(){
																																							  
																																							  
																																							  });
						/*$.ajax({type:"POST",
						url:"/appPlatform/queryPeById.html",
						data:"peId="+peid+"&page=1&rows=1"+((plusstring=='')?(''):('&'+plusstring)),
						type:'json',
						success:function(json)
						{
								if(json!=undefined)
								{
									callback(json);
								}
								else
								{
									alert("数据格式错误！");
									return false;
								}
						},
						error:function()
						{
							alert("网络通信失败！");
							return false;
						}
					   });*/
}


function updateState(peid,approvalstate,callback)
{
						$.ajax({type:"POST",
						url:"/appPlatform/updateState.html",
						data:"peId="+peid+"&approvalState="+approvalstate+((plusstring=='')?(''):('&'+plusstring)),
						success:function(json)
						{
								if(FormatString(json)=="success")
								{
									alert("数据操作成功！");
									callback();
									return true;
								}
								else
								{
									alert("数据格式错误！");
									return false;
								}
						},
						error:function()
						{
							alert("网络通信失败！");
							return false;
						}
					   });
}
function updateStateToUncheck(peid,callback)
{
	return updateState(peid,0,callback);//未审核
}
function updateStateToUnactive(peid,callback)
{
	return updateState(peid,1,callback);//未激活
}
function updateStateToActived(peid,callback)
{
	return updateState(peid,2,callback);//已激活
}
function updateStateToUnAccept(peid,callback)
{
	return updateState(peid,3,callback);//未通过
}

function LoadFormData(peid)
{
	if(peid!=null && peid!=undefined)
	{
		queryPeById(peid,function(json){
									/*fillcombobox('/appPlatform/getProvinceList.html','countryId='+json["country"],'Prov',function(){});
									fillcombobox('/appPlatform/getCityList.html','provinceId='+json["province"],'City',function(){});
									
									fillcombobox('/appPlatform/subClassList.html','classId='+json["middleTypeID"],'TypeLevel3',function(){});
									fillcombobox('/appPlatform/subClassList.html','classId='+json["bigTypeID"],'TypeLevel2',function(){});
										*/		 
									//$('#form1').form('load',json);
									Json2Form("#form1",json);
								  });
	}
}