// JavaScript Document

//FORNAJAXFilter
						function EngineString2JSON(method,url,param,success,error)
						{
							$.ajax({type:method,
									url:url,
									data:param,
									success:function(json)
									{
											var TmpJSON=JSONString2JSON(json);
											if(TmpJSON==null)
											{
												success(JSONString2JSON("{\"total\":0,\"rows\":[]}"));
											}
											else
											{
												success(JSONString2JSON(json));
											}
									},
									error:error
						   	});
						}
						
						function EngineString2JSONForGridView(method,url,param,success,error)
						{
							EngineString2JSON(method,url,param,function(json)
							{
								success(ForntSplitPage(param,json));
							},error);
						}
						
						function ForntSplitPage(requestparam,responsejson)
						{
							return ForntSplitPageEx(requestparam.page,requestparam.rows,responsejson);
						}
						function ForntSplitPageEx(page,rows,responsejson)
						{
							if(page==undefined)return responsejson;
							if(rows==undefined)return responsejson;
							var total=responsejson.total;
							var rowsL=responsejson.rows;
							if(total==undefined)return responsejson;
							if(rowsL==undefined)return responsejson;
							var Def=JSONString2JSON("{\"total\":"+total+",\"rows\":[]}");
							if(typeof(rows)=="string")
							{
								rows=parseInt(rows);
							}
							if(typeof(page)=="string")
							{
								page=parseInt(page);
							}
							if(rowsL.length>rows)
							{
								Def.rows=rowsL.slice(rows*(page-1),rows*(page));
							}else
							{
								Def.rows=rowsL;
							}
							return Def;
						}
						
						function FormatResponseJSON(responsejson)
						{
							var KR=false;
							var KT=false;
							var KK=false;
							var total=responsejson.total;
							var rowsL=responsejson.rows;
							if(rowsL!=null && rowsL!=undefined)
							{
								KR=true;
							}
							if(total!=null && total!=undefined)
							{
								KT=true;
							}
							try
							{
								var rl=responsejson.length;
								if(rl!=null && rl!=undefined)
								{
									KK=true;
								}
							}catch(e){;}
							if(KR && !KT)
							{
								var Def=JSONString2JSON("{\"total\":"+rowsL.length+",\"rows\":[]}");
								Def.rows=rowsL;
								return Def;
							}
							if(!KR)
							{
								if(KK)
								{
									var Def=JSONString2JSON("{\"total\":"+responsejson.length+",\"rows\":[]}");
									Def.rows=responsejson;
									return Def;
								}
							}
							if(KR && KT)
							{
								if(typeof(total)=="string")
								{
									responsejson.total=parseInt(total);
								}
								return responsejson;
							}
							return null;
						}
						
						function JSONString2JSON(json)
						{
							try
							{
											var isStringJSON=(json.substr(0,1)=="\"");
											var cmdstr="var jtmp='"+json+"';"
											if(isStringJSON)
											{
												cmdstr="var jtmp="+json+";";
											}
											eval(cmdstr);
											return jQuery.parseJSON(jtmp);
							}catch(e){
								return null;
							}
						}
						
						function FormatString(jsonstring)
						{
								var isStringJSON=(jsonstring.substr(0,1)=="\"");
								if(isStringJSON)
								{
									eval("var jptmp="+jsonstring);
									return jptmp;
								}else
								{
									return jsonstring;
								}
						}