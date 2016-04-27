// JavaScript Document
						var editIndex = undefined;
						var curEdit= undefined;
						var keep=false;
						function endEditing(cid){			
							if(curEdit!=undefined)
							{
								if(cid!=curEdit)
								{
									CloseEdit(curEdit,editIndex);
								}
							}
							curEdit=cid;
							if (editIndex == undefined){return true}				
							if ($(cid).datagrid('validateRow', editIndex)){					
								$(cid).datagrid('endEdit', editIndex);				
								editIndex = undefined;				
								return true;				
							} else {				
								return false;				
							}				
						}
						
						function ShutdownEdit(cid)
						{
							if(!endEditing(cid))
							{
								CloseEdit(cid,editIndex);
							}
						}
				
						function append(cid){				
							if (endEditing(cid)){				
								$(cid).datagrid('appendRow',{});				
								editIndex = $(cid).datagrid('getRows').length-1;				
								$(cid).datagrid('selectRow', editIndex)				
										.datagrid('beginEdit', editIndex);	
							}
							keep=false;
						}
				
						function removeit(cid){				
							if (editIndex == undefined){return}				
							$(cid).datagrid('cancelEdit', editIndex)				
									.datagrid('deleteRow', editIndex);				
							editIndex = undefined;	
							keep=false;
						}
												
						function removebyid(cid,index){		
							if (index == undefined){return}			
							$(cid).datagrid('deleteRow', index);				
							editIndex = undefined;	
							keep=false;
						}
						
						function submitgrid(cid)
						{
							$(cid).acceptChanges();
						}
						function resetgrid(cid)
						{
							$(cid).rejectChanges();
						}
						
						function onClickRowWithEdit(cid,index){
							if (editIndex != index){
								if (endEditing(cid)){
									$(cid).datagrid('selectRow', index)
											.datagrid('beginEdit', index);
									editIndex = index;
								} else {
									$(cid).datagrid('selectRow', editIndex);
								}
							}
							else
							{
								CloseEdit(cid,index);
							}
						}
						
						function onClickRow(cid,index){
							if(keep)
							{
								keep=false;
								return;
							}
							if (editIndex != index){
								if (endEditing(cid)){
									$(cid).datagrid('selectRow', index)
								} else {
									
									$(cid).datagrid('selectRow', editIndex)
											.datagrid('endEdit', editIndex);
									editIndex = undefined;
								}
							}
							else
							{
								CloseEdit(cid,index);
							}
						}
						
						function CloseEdit(cid,index)
						{
							if (editIndex == index){
								if (!endEditing(cid)){									
									$(cid).datagrid('selectRow', editIndex)
											.datagrid('endEdit', editIndex);
									editIndex = undefined;
								}
							}
						}
						
						function SetEdit(cid,index)
						{
							if (editIndex != index){
								if (endEditing(cid)){
									$(cid).datagrid('selectRow', index)
											.datagrid('beginEdit', index);
									editIndex = index;
								} else {
									$(cid).datagrid('selectRow', editIndex);
								}
							}
							else
							{
									$(cid).datagrid('selectRow', index)
											.datagrid('beginEdit', index);
									editIndex = index;
							}
							keep=true;
						}
						
						//DATA FITTER
						
						function SetGridValue(gridtableid,rowid,columnid,valuestring)
						{
							if(curEdit==gridtableid && editIndex==rowid)
							{
								CloseEdit(gridtableid,rowid);
							}
							var columns= $(gridtableid).datagrid("options").columns;
							var rows=$(gridtableid).datagrid("getRows");
							rows[rowid][columns[0][columnid].field]=valuestring;
							$(gridtableid).datagrid("refreshRow",rowid);
						}
						function GetGridValue(gridtableid,rowid,columnid)
						{
							if(curEdit==gridtableid && editIndex==rowid)
							{
								CloseEdit(gridtableid,rowid);
							}
							var columns= $(gridtableid).datagrid("options").columns;
							var rows=$(gridtableid).datagrid("getRows");
							return rows[rowid][columns[0][columnid].field];
						}
						
						
						
						function changeCard(CardGlobalClassName,CardPrivateId)
						{
										$("."+CardGlobalClassName).css("visibility","hidden");
										$("."+CardGlobalClassName).css("position","absolute");
										$("#"+CardPrivateId).css("visibility","visible");
										$("#"+CardPrivateId).css("position","relative");
						}
						
						function fillcombobox(url,data,comboId)
						{
							try
							{
								$.get(url,data, function(jsonobj){
								if(jsonobj!=undefined)
								{
									var AppData=jsonobj["rows"][0];
									$("#"+comboId).combobox("loadData",jsonobj["rows"]);
								}
								},"json");
							}catch(e){};
						}
						
						function fillcombobox(url,data,comboId,callback)
						{
							try
							{
								$.get(url,data, function(jsonobj){
								if(jsonobj!=undefined)
								{
									var AppData=jsonobj["rows"][0];
									$("#"+comboId).combobox("loadData",jsonobj["rows"]);
									callback();
								}
								},"json");
							}catch(e){};
						}
						
						function bindEndEditEvent()
						{
							$(".datagrid-view").bind('click',function(){
													try
													{
														var v1=curEdit;
														var v2=editIndex;
														ShutdownEdit(curEdit);
														$(v1).datagrid('selectRow', v2)
													}catch(e){};
												 });
						}
						
						function ReloadDataGrid(cid)
						{
							$(cid).datagrid('reload'); 
						}
						
						function getSelections(cid,ColumnName){
							var ss = [];
							var rows = $(cid).datagrid('getSelections');
							for(var i=0; i<rows.length; i++){
								var row = rows[i];
								ss.push(row[ColumnName]);//.itemid+":"+row.productid+":"+row.attr1+'</span>');
							}
							return ss;
						}
						
						function getSelectionsJSONString(cid,ColumnName)
						{
							var ss = "";
							var rows = $(cid).datagrid('getSelections');
							ss=ss+("{\"total\":"+rows.length+",\"rows\":[");
							for(var i=0; i<rows.length; i++){
								var row = rows[i];
								if(i>0)
								{
									ss=ss+",";
								}
								ss=ss+("{\""+ColumnName+"\":\""+row[ColumnName]+"\"}");//.itemid+":"+row.productid+":"+row.attr1+'</span>');
							}
							ss=ss+("]}");
							return ss;
						}
						
						function getSelectionsParam(cid,ColumnName)
						{
							var ss = "";
							var rows = $(cid).datagrid('getSelections');
							for(var i=0; i<rows.length; i++){
								var row = rows[i];
								if(i>0)
								{
									ss=ss+",";
								}
								ss=ss+row[ColumnName];//.itemid+":"+row.productid+":"+row.attr1+'</span>');
							}
							return ss;
						}
						
						function getSelectionsCount(cid)
						{
							var rows = $(cid).datagrid('getSelections');
							return rows.length;
						}
						
						function getSelectionsJSON(cid,ColumnName)
						{
							eval("var tmp="+getSelectionsJSONString(cid,ColumnName)+";");
							return tmp;
						}