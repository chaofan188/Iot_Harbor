// JavaScript Document
						function getModelInfo(peModelID,callback)
						{
											$.ajax({type:"POST",
											url:"/PSDLProject/getModelInfo.action",
											data:"peKey=1&pemodelID="+peModelID,
											dataType:"json",
											success:function(json)
											{
													if(json!=undefined && json!=null)
													{
														callback(json);
													}
											},
											error:function()
											{
											}
										   });
						}
						
				  function loadModelDatas(jsondata)
				  {
					  //初始化
					  eval("var json="+jsondata+";");
					  var pe=json;
					  
					  //加载静态属性
					  $('#dg1').datagrid('loadData',json.domainAttributes);
					  
					  //加载动态属性
					  if(json.states==null)
					  {
						  $("#DYNTable").css("display","none");
					  }else if(json.states.length==null || json.states.length==undefined || json.states.length==0)
					  {
						  $("#DYNTable").css("display","none");
					  }else
					  {
						  $("#DYNTable").css("display","inline");
					  }
					  $('#dg2').datagrid('loadData',json.states);

					  //加载接口信息
					  $("#InterfaceTable").css("display","none");
					  if(json.appInterface!=null){
						  $("#InterfaceSMS").html("Application");
					      $("#InterfaceTable").css("display","inline");
						  if(json.appInterface.type=="Web"){
						  				    $("#InterfaceSMS").html("Application ( Web )");
											var content="<table><tr><td>应用接口类型</td><td>"+pe.appInterface.type+"</td></tr><tr><td>Web接口URL</td><td>"+pe.appInterface.webURL+"</td></tr></table>";
											$("#MessageInner").html(content);
						  }else if(json.appInterface.type=="Client"){
						  				    $("#InterfaceSMS").html("Application ( Client )");
											var content="<table><tr><td>应用接口类型</td><td>"+pe.appInterface.type+"</td></tr><tr><td>客户端名称</td><td>"+pe.appInterface.cliantName+
											"</td></tr><tr><td>客户端大小</td><td>"+pe.appInterface.size+"</td></tr><tr><td>运行环境</td><td>"+pe.appInterface.environment+
											"</td></tr><tr><td>下载地址</td><td>"+pe.appInterface.downloadAddress+"</td></tr><tr><td>版本号</td><td>"+pe.appInterface.number+
											"</td></tr><tr><td>运行要求</td><td>"+pe.appInterface.requirment+"</td></tr></table>";
											$("#MessageInner").html(content);
						  }
					  }else if(json.wsdlInterface!=null)
					  {
						  $("#InterfaceSMS").html("WSDL");
					      $("#InterfaceTable").css("display","inline");
						  var content="<table><tr><td>WSDL文件地址</td><td>"+pe.wsdlInterface.url+"</td></tr></table>";
						  $("#MessageInner").html(content);
					  }else if(json.m2mInterface!=null)
					  {
						  $("#InterfaceSMS").html("M2M");
					      $("#InterfaceTable").css("display","inline");
						  $("#MessageInner").html("<table id='m2mdg'></table>");
						  $('#m2mdg').datagrid({
							    Loader:function(a,b,c)
								{
								},
								columns:[[   
									{field:'functionName',title:'函数名',width:300},   
									{field:'effect',title:'函数说明',width:200},   
									{field:'returnType',title:'返回值类型',width:100},
									{field:'returnComment',title:'返回值说明',width:100}  
								]]   
							});  
					  	   $('#m2mdg').datagrid('loadData',json.m2mInterface);

					  }
				  }
						

						function getPeModelMSG(pjson,successcallback,errorcallback){
							$.ajax({
								url : "/PSDLProject/getModelInfo.action",
								type : "post",   		
								dataType : "json",
								data:{
									bigType:pjson["TypeLevel1"],
									middleType:pjson["TypeLevel2"],
									smallType:pjson["TypeLevel3"],
									model:pjson["ObjectType"]
								},
								success : successcallback
							});
						}
						function getMsgSuccess(result)
						{
									var Flag=FormatString(result);
									if(Flag=="error")
									{
										alert("找不到模型信息！请先在线填写该型号的详细信息！");
										errorcallback();
										return;
									}
									
									var pe=eval("("+result+")"); 
									var attrcontent="<table><tr><td>序号</td><td>属性名</td><td>属性值</td><td>单位</td></tr>";
									var statuscontent="<table><tr><td>序号</td><td>属性名</td><td>默认值</td><td>单位</td><td>关联函数</td></tr>";   			
									var domainlist=pe.domainAttributes;
									var statuslist=pe.states;
									var attrindex=0;
									var stateindex=0;
									for(i in domainlist){ 
											var domaininfo=domainlist[i];
											attrindex++;
											attrcontent+="<tr><td>"+attrindex+"</td><td>"+domaininfo.attrName+"</td><td>"+domaininfo.attrValue+"</td><td>"+domaininfo.unit+"</td></tr>";	
											//REOK					
									} 
									attrcontent+="</table>";
									$("#property").html(attrcontent);
									for(i in statuslist){ 	  					
											var statusinfo=statuslist[i];	
											//alert(statusinfo.stateID); 
											stateindex++;					
											statuscontent+="<tr><td>"+stateindex+"</td><td>"+statusinfo.stateName+"</td><td>"+statusinfo.defaultValue+"</td><td>"+statusinfo.unit+"</td><td>"+statusinfo.m2mName+"</td></tr>";
											
									} 
									statuscontent+="</table>";
									$("#status").html(statuscontent);
									//
									if(pe.appInterface!=null){
										$("#interfaceType").val("Application");
										if(pe.appInterface.type=="Web"){
											var content="<table><tr><td>应用接口类型</td><td>"+pe.appInterface.type+"</td></tr><tr><td>Web接口URL</td><td>"+pe.appInterface.webURL+"</td></tr></table>";
											$("#interface").html(content);
										}
										if(pe.appInterface.type=="Client"){
											var content="<table><tr><td>应用接口类型</td><td>"+pe.appInterface.type+"</td></tr><tr><td>客户端名称</td><td>"+pe.appInterface.cliantName+
											"</td></tr><tr><td>客户端大小</td><td>"+pe.appInterface.size+"</td></tr><tr><td>运行环境</td><td>"+pe.appInterface.environment+
											"</td></tr><tr><td>下载地址</td><td>"+pe.appInterface.downloadAddress+"</td></tr><tr><td>版本号</td><td>"+pe.appInterface.number+
											"</td></tr><tr><td>运行要求</td><td>"+pe.appInterface.requirment+"</td></tr></table>";
											$("#interface").html(content);
										}
									}
									if(pe.wsdlInterface!=null){
										$("#interfaceType").val("WSDL");
										var content="<table><tr><td>WSDL文件地址</td><td>"+pe.wsdlInterface.url+"</td></tr></table>";
										$("#interface").html(content);
									}
									if(pe.m2mInterface!=null){
										$("#interfaceType").val("M2M");
										var m2mlist=pe.m2mInterface;
										//alert(m2mlist.lengtd);
										/* var a="asdf";
										alert(a); */
										var interfaceinfo="<table><tr><td>函数名称</td><td>函数说明</td><td>返回值类型</td><td>返回值说明</td></tr>";
										//alert(interfaceinfo);
										//alert(interfaceinfo);
										for(i in m2mlist){
											var m2minfo=m2mlist[i];
											//alert(m2minfo.functionName);
											//alert(m2minfo.effect);	  					
											interfaceinfo+="<tr><td>"+m2minfo.functionName+"</td><td>"+m2minfo.effect+"</td><td>"+m2minfo.returnType+"</td><td>"+m2minfo.returnComment+"</td></tr>";
											//alert(interfaceinfo);
										}
										interfaceinfo+="</table>";
										$("#interface").html(interfaceinfo);	
							}
						}