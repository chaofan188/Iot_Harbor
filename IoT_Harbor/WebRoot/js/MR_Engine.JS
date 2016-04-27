// JavaScript Document
							var KListNore="";
							function LoadTemplate(ListCID)
							{
								if(KListNore=="")
								{
									KListNore=$(ListCID).html().replace(/%7B/g,"{").replace(/%7D/g,"}");
								}
								$(ListCID).html("");
							}
							
							function loadMRList(ListCID,json)
							{
								var NewJson=FormatResponseJSON(json);
								var Def=JSONString2JSON("{\"page\":1,\"json\":[],\"maxpage\":1,\"callback\":\"\"}");
								Def.json=NewJson;
								loadMRListData(ListCID,"",Def);
							}
							function loadMRListEx(ListCID,PageCID,CallBackFunctionName,CurPage,PerCountOfPage,json)
							{
								var NewJson=FormatResponseJSON(json);
								var Def=JSONString2JSON("{\"page\":"+CurPage+",\"json\":[],\"maxpage\":"+CurPage+",\"callback\":\"\"}");
								Def.json=ForntSplitPageEx(CurPage,PerCountOfPage,NewJson);
								Def.callback=CallBackFunctionName;
								var total=NewJson.total;
								var MaxPageID=Math.floor(total/PerCountOfPage);
								if((total%PerCountOfPage)>0)
								{
									MaxPageID=MaxPageID+1;
								}
								Def.maxpage=MaxPageID;
								loadMRListData(ListCID,PageCID,Def);
							}
							/*
							function loadMRList(ListCID,json)
							{
								return loadMRListEx(ListCID,"",-1,function(pageid,callback){return callback(json);});
//								return loadMRListData(ListCID,"",0,json);
							}
							function loadMRListEx(ListCID,PagerCID,PagerRequester)
							{
								//alert(PagerRequester);
								return loadMRListEvents(ListCID,PagerCID,0,PagerRequester);
							}
							var MRLoaderExRequester=undefined;
							
							function loadMRListEvents(ListCID,PagerCID,CurPage,PagerRequester)
							{
									MRLoaderExRequester=PagerRequester;
									PagerRequester(CurPage,function(json)
														{
															return loadMRListData(ListCID,PagerCID,CurPage,json);
														});
							}
							*/
							function loadMRListData(ListCID,PagerCID,ParentJson)
							{
								try
								{
									var json=ParentJson.json;
									var CurPage=ParentJson.page;
									var MaxPage=ParentJson.maxpage;
									var cbName=ParentJson.callback;
									LoadTemplate(ListCID);
									var NoteRows=json["rows"];
									var i=0;
									for(i=0;i<NoteRows.length;i++)
									{
										var MRItem=KListNore;
										var obj=NoteRows[i];
										for(var key in obj)
										{
											var searchKey="{"+key+"}";
											while(MRItem.indexOf(searchKey)>0)
											{
												MRItem=MRItem.replace(searchKey,obj[key]);
											}
										}
										$(ListCID).append(MRItem);
									}
									if(NoteRows.length>0)
									{
										$(ListCID).css("visibility","visible");
									}
									if(!PagerCID=="")
									{
										var MaxACG=3;
										var UPP="<a href='#' onclick=\""+cbName+"("+(CurPage-1)+");return false;\">上一页</a>";
										var DOWNP="<a href='#' onclick=\""+cbName+"("+(CurPage+1)+");return false;\">下一页</a>";
										if(CurPage==1){UPP="";};
										if(MaxPage==CurPage){DOWNP="";};
										
										var html="";
										
										var LK=1;
										var RK=MaxPage;
										if(MaxPage>MaxACG)
										{
											var LK=Math.floor(MaxACG/2);
											var RK=MaxACG-LK;
											if(LK<0)
											{
												LK=1;
												RK=MaxPage;
											}else if(RK>MaxACG)
											{
												RK=MaxPage;
												LK=MaxPage-MaxACG;
											}
										}
										
										for(var i=LK;i<=RK;i++)
										{
											if(CurPage!=i)
											{
												html=html+"<a href='#' onclick=\""+cbName+"("+i+");return false;\">"+i+"</a>&nbsp;";
											}else
											{
												html=html+"<a>"+i+"</a>&nbsp;";
											}
										}
										if(UPP!=""){html=UPP+"&nbsp;|&nbsp;"+html;}
										if(DOWNP!=""){html=html+"&nbsp;|&nbsp;"+DOWNP;}
										$(PagerCID).html(html);
									}
								}catch(e){;}
								/*
									var NoteRows=json["rows"];
									var i=0;
									for(i=0;i<NoteRows.length;i++)
									{
										var RowObj=NoteRows[i];
										var RegTime=RowObj["tpl_enroll_time"];
										var TPID=RowObj["tpl_ID"];
										var TPDESC=RowObj["tpl_description"];
										var KFZ=RowObj["tpl_developer_id"];
										var TPName=RowObj["tpl_name"];
										var TPStatus=RowObj["ve_status"];
										
										var MRItem=KListNore;
										
										MRItem=MRItem.replace(/{MR_SUBMITTIME}/g,RegTime);
										MRItem=MRItem.replace(/{MR_PRODUCTOR}/g,KFZ);
										MRItem=MRItem.replace(/{MR_NAME}/g,TPName);
										MRItem=MRItem.replace(/{MR_INDEX}/g,TPID);
										MRItem=MRItem.replace(/{MR_DESCRIPTION}/g,TPDESC);
										MRItem=MRItem.replace(/{MR_STATUS}/g,TPStatus);
										
										
										$("#MRList").append(MRItem);
										
									}
								*/
							}