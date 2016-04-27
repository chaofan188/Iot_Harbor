<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	String pemodel = request.getParameter("pemodel");

	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8>">
<title>PE模板注册</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link rel="stylesheet" type="text/css"
	href="../../admin/skin/system.css">
<script type="text/javaScript" src="../../images/js/newjs/jquery.min.js"></script>
<script type="text/javaScript"
	src="../../images/js/newjs/jquery.easyui.min.js"></script>


<script type="text/javascript"
	src="../../images/js/newjs/datagrid_adapter.js"></script>
<script type="text/javascript"
	src="../../images/js/newjs/typeSelecter.js"></script>
<script type="text/javascript" src="../../images/js/newjs/FormEngine.js"></script>
<script type="text/javascript" src="../../images/js/newjs/templateEngine.js"></script>
<!--<script type="text/javaScript" src="images/js/css.js"></script>
<script type="text/javaScript" src="data/config.js"></script>
<script type="text/javaScript" src="images/js/common.js"></script>
<script type="text/javaScript" src="images/js/admin.js"></script>
<script type="text/javascript" src="images/js/validator.js"></script>
<script type="text/javascript" src="images/js/form.js"></script>-->
<link rel="stylesheet" type="text/css"
	href="../../images/easyui/easyui.css">
<link rel="stylesheet" type="text/css"
	href="../../images/js/newjs/easyui-balthasar.css">
</head>

      <script type="text/javascript">  
     $(document).ready(function () { 
         var json = $("#pemodel").val();
       
             ShowData(json);
       });  
          
           function ShowData(json){         
              
              json = eval('(' + json + ')');
              var tempHolder;
              tempHolder = new Template($("#main").html());
              var s = tempHolder.render(tempHolder);
            
              $("#Area0").append(s); 
             
     
              var obj = eval(json);
         
              //给pemodel的基本属性赋值
              var model = obj.model;
              var model_id = obj.model_id;
              var name = obj.name;
              var pic_url = obj.pic_url;
              var description = obj.description;
           
              $("#Model").val(model);
              $("#Model_ID").val(model_id);
              $("#Name").val(name);
              $("#Pic_url").val(pic_url);
              $("#Description").val(description); 
              
              //获取interfaces数组
              var interfaces = obj.interfaces;
              var interfaces_length = eval(interfaces).length;
         
              
              
              var attribute = obj.attribute;
              var attribute_length = eval(attribute).length;
                 //遍历属性并赋值    
              if(attribute_length>0){
               
                 for(var i=0;i<attribute_length;i++){
                  
                   add_attribute();
                   var attribute_description = attribute[i].description;
                   var attribute_key = attribute[i].key;
                   var attribute_option = attribute[i].option;
                   var attribute_type = attribute[i].type;
                   var attribute_unit = attribute[i].unit;
                   var attribute_value_max = attribute[i].value_max;
                   var attribute_value_min = attribute[i].value_min;
                    
                   if(attribute_type=="Boolean"){
                   
                       $("#attribute_type_"+(i+1)).val("Boolean");
                       $("#button_attribute_"+(i+1)).trigger("click");
                   }
                   else if(attribute_type=="enum"){
                   
                       $("#attribute_type_"+(i+1)).val("enum");
                       $("#button_attribute_"+(i+1)).trigger("click");
                       $("#attribute_option_"+(i+1)+"_0").val(attribute_option);
                   }
                   else if(attribute_type=="Int"){
                           
                       $("#attribute_type_"+(i+1)).val("Int");
                       $("#button_attribute_"+(i+1)).trigger("click");
                       $("#attribute_unit_"+(i+1)+"_0").val(attribute_unit);
                       $("#attribute_min_val_"+(i+1)+"_0").val(attribute_value_min);
                       $("#attribute_max_val_"+(i+1)+"_0").val(attribute_value_max);
                   }
                   else if(attribute_type=="Float"){
                    
                      $("#attribute_type_"+(i+1)).val("Float");
                      $("#button_attribute_"+(i+1)).trigger("click");
                      $("#attribute_unit_"+(i+1)+"_0").val(attribute_unit);
                      $("#attribute_min_val_"+(i+1)+"_0").val(attribute_value_min);
                      $("#attribute_max_val_"+(i+1)+"_0").val(attribute_value_max); 
                     
                   }
                   else if(attribute_type=="String"){
                    
                      $("#attribute_type_"+(i+1)).val("String");
                      $("#button_attribute_"+(i+1)).trigger("click");
                   } 
                  
                   $("#attribute_key_"+(i+1)).val(attribute_description);
                   $("#attribute_description_"+(i+1)).val(attribute_key); 
                 }
              }
              //便利接口 并赋值  
              if(interfaces_length>0){
                 for(var i=0;i<interfaces_length;i++){
                   //获取interface参数
                   var interfaceID = interfaces[i].interfaceID;
                   var interface_description = interfaces[i].interface_description;
                   var interface_protocol = interfaces[i].interface_protocol;
                   var interface_direction = interfaces[i].interface_direction;
                   var protocol_rule = interfaces[i].protocol_rule;
                   var param = interfaces[i].param;
                   var return_param = interfaces[i].return_param;
                   var param_length = eval(param).length;
                   var return_param_length = eval(return_param).length;
  
                   add();
                  
                   //给interface各参数赋值
                   $("#Interface_description_"+(i+1)).val(interface_description);
                   $("#interface_direction_"+(i+1)).val(interface_direction);
                   $("#Interface_id_"+(i+1)).val(interfaceID);
                   $("#interface_protocol_"+(i+1)).val(interface_protocol);
                  
                 
                   if(interface_protocol=="StandardProtocol"){
                      if(param_length>0) {
                        for(var j=0;j<param_length;j++){
                      
                           var param_description = param[j].description;
                           var param_key = param[j].key;
                           var param_max_val = param[j].max_val;
                           var param_min_val = param[j].min_val;
                           var param_option = param[j].option;
                           var param_Boolean = param[j].pe_Boolean;
                           var param_String = param[j].pe_String;
                           var param_unit = param[j].pe_unit;
                           var param_type = param[j].type;
                        
                           if(param_type=="Boolean"){
                              param1("interface_1");
                              $("#param1_type_"+(i+1)+"_"+(j+1)).val("Boolean");
                              $("#button_param_"+(i+1)+"_"+(j+1)).trigger("click");
                           }
                           else if(param_type=="enum"){
                            
                              
                              param1("interface_1");
                              $("#param1_type_"+(i+1)+"_"+(j+1)).val("enum");
                              $("#button_param_"+(i+1)+"_"+(j+1)).trigger("click");
                              $("#param_option_"+(i+1)+"_"+(j+1)).val(param_option);
                           }
                           else if(param_type=="Int"){
                              param1("interface_1");
                              $("#param1_type_"+(i+1)+"_"+(j+1)).val("Int");
                           
                              $("#button_param_"+(i+1)+"_"+(j+1)).trigger("click");
                              $("#param_unit_"+(i+1)+"_"+(j+1)).val(param_unit);
                              $("#param_min_val_"+(i+1)+"_"+(j+1)).val(param_min_val);
                              $("#param_max_val_"+(i+1)+"_"+(j+1)).val(param_max_val);
                           }
                           else if(param_type=="Float"){
                              param1("interface_1");
                              $("#param1_type_"+(i+1)+"_"+(j+1)).val("Float");
                              $("#button_param_"+(i+1)+"_"+(j+1)).trigger("click");
                              $("#param_unit_"+(i+1)+"_"+(j+1)).val(param_unit);
                              $("#param_min_val_"+(i+1)+"_"+(j+1)).val(param_min_val);
                              $("#param_max_val_"+(i+1)+"_"+(j+1)).val(param_max_val);
                           }
                           else if(param_type=="String"){
                              param1("interface_1");
                              $("#param1_type_"+(i+1)+"_"+(j+1)).val("String");
                              $("#button_param_"+(i+1)+"_"+(j+1)).trigger("click");
                           } 
                           var key = "#key_"+(i+1)+"_"+(j+1);
                           var description = "#interface_description_"+(i+1)+"_"+(j+1);
                          
                           $("#key_"+(i+1)+"_"+(j+1)).val(param_key);
                           $("#interface_description_"+(i+1)+"_"+(j+1)).val(param_description);
                          
                        }
                      }
                      if(return_param_length>0){
                       
                         for(var j=0;j<return_param_length;j++){
                      
                             var return_param_description = return_param[j].description;
                             var return_param_key = return_param[j].key;
                             var return_param_max_val = return_param[j].max_val;
                             var return_param_min_val = return_param[j].min_val;
                             var return_param_option = return_param[j].option;
                             var return_param_Boolean = return_param[j].pe_Boolean;
                             var return_param_String = return_param[j].pe_String;
                             var return_param_unit = return_param[j].pe_unit;
                             var return_param_type = return_param[j].type;
                          
                             if(return_param_type=="Boolean"){
                                return_param1("interface_1");
                                $("#return_param_type_"+(i+1)+"_"+(j+1)).val("Boolean");
                                $("#button_return_"+(i+1)+"_"+(j+1)).trigger("click");
                             }
                             else if(return_param_type=="enum"){
                                
                                return_param1("interface_1");
                                $("#return_param_type_"+(i+1)+"_"+(j+1)).val("enum");
                                $("#button_return_"+(i+1)+"_"+(j+1)).trigger("click");
                                $("#return_option_"+(i+1)+"_"+(j+1)).val(return_param_option);
                             }
                             else if(return_param_type=="Int"){
                                return_param1("interface_1");
                                $("#return_param_type_"+(i+1)+"_"+(j+1)).val("Int");
                                $("#button_return_"+(i+1)+"_"+(j+1)).trigger("click");
                                $("#return_unit_"+(i+1)+"_"+(j+1)).val(return_param_unit);
                                $("#return_min_val_"+(i+1)+"_"+(j+1)).val(return_param_min_val);
                                $("#return_max_val_"+(i+1)+"_"+(j+1)).val(return_param_max_val);
                             }
                             else if(return_param_type=="Float"){
                                return_param1("interface_1");
                                $("#return_param_type_"+(i+1)+"_"+(j+1)).val("Float");
                                $("#button_return_"+(i+1)+"_"+(j+1)).trigger("click");
                               
                                $("#return_unit_"+(i+1)+"_"+(j+1)).val(return_param_unit);
                                $("#return_min_val_"+(i+1)+"_"+(j+1)).val(return_param_min_val);
                                $("#return_max_val_"+(i+1)+"_"+(j+1)).val(return_param_max_val);
                             }
                             else if(return_param_type=="String"){
	                            return_param1("interface_1");
	                            $("#return_param_type_"+(i+1)+"_"+(j+1)).val("String");
	                            $("#button_return_"+(i+1)+"_"+(j+1)).trigger("click");
                             } 
                             $("#return_key_"+(i+1)+"_"+(j+1)).val(param_key);
                             $("#return_interface_description_"+(i+1)+"_"+(j+1)).val(param_description);
                         }
                      
                       }
                   }
                   if(interface_protocol=="onvif"){
                      onchangeProtocol_1("onvif",1);
                   }
                   if(interface_protocol=="http"){
                      onchangeProtocol_1("http",1);
                     
                      $("#http_"+(i+1)).val(protocol_rule);
                   }
                    if(interface_protocol=="ezviz"){
                      onchangeProtocol_1("ezviz",1);
                   }
                 
                } 
               
             }
          }
        

      </script>  
    
    <body >  
    
   <!--  onload="init();" -->
	<table cellpadding="0" cellspacing="1" class="table_form">
  <caption>
    导入已存在设备模板
  </caption>
  <tr>
    <td width="13%">选择模板文件:</td>
    <td width="">
   
    <form name="inputModelForm" id="inputModelForm" method="post" action="../../fileuploadtest.html" enctype="multipart/form-data">
      <span id="inputmodelFalse">未选择任何模板</span>
       <input type="file" id="inputmodel" name="inputmodel" onChange='document.getElementById("inputmodelFalse").innerHTML=document.getElementById("inputmodel").value;' style="visibility:collapse;" />
        
      <!--   <input type="file" name=""/>--> 
 </form> 
    </td>
    <td width="28%"><a class="buttonlink" onClick='document.getElementById("inputmodel").click();'>选择导入模板</a><a class="buttonlink" onclick='document.getElementById("inputModelForm").submit();'>提交本地模板</a>
 </td>
  </tr>
  
</table>


     <div id="Area0"></div><!-- 接口区域 -->
     

     
 <input name="pemodel" id="pemodel" value='<%=pemodel%>'  type="hidden" /> 
      <script id="main" type="text/plain">  
            <form action="" method="post" name="form1" id="form1">
		<input name="count" id="count" type="hidden" value="">

	    <input name="attribute_count" id="attribute_count" type="hidden" value="">
		<input name="filename" id="filename" type="hidden" value="">
		<table cellpadding="0" cellspacing="1" class="table_form"
			style="border-bottom:none;">
			<caption>PE模板注册</caption>
			<table>
				<div id="Area1">
					<table cellpadding="0" cellspacing="1" class="table_form"
						style="border-top:none;border-bottom:none;">
						<tr>
							<td width="">模板ID:</td>
							<td><input id="Model_ID" name="model_ID" value=""
								  style="width:300px" />
							</td>
						</tr>
						<tr>
							<td width="">模板名称:</td>
							<td><input id="Name" name="name" value=""
								style="width:300px" />
							</td>
						</tr>
						<tr>
							<td width="">设备型号:</td>
							<td><input id="Model" name="model" value=""
								style="width:300px" />
							</td>
						</tr>						
						<tr>
							<td width="">设备描述:</td>
							<td><input id="Description" name="description" value=""
								style="width:300px" />
							</td>
						</tr>
						<tr>
							<td width="">图片路径:</td>
							<td><input id="Pic_url" name="pic_url" value=""
								style="width:300px" />
							</td>
						</tr>						
					</table>
				</div>
                <div>
				 <table cellpadding="0" cellspacing="1" class="table_form"
					style="border-top:none;">
					<tr>
						<td width="" style="text-align:left;"><a class="buttonlink"
							onclick="add();">增加接口</a></td>
						<td style="text-align:left;"></td>
						<td width="443" style="text-align:left;"></td>
					</tr>
					<tr>
						<td width="" style="text-align:left;"><a class="buttonlink"
							onclick="add_attribute();">增加属性</a></td>
						<td style="text-align:left;"></td>
						<td width="443" style="text-align:left;"></td>
					</tr>

				 </table>
                </div>
				<div id="Area2"></div><!-- 接口区域 -->
				<div id="Area3"></div><!-- 属性参数区域 -->
				
				<div id="Area_submit">
					<table cellpadding="0" cellspacing="1" class="table_form"
						style="border-top:none;">
						<tr>

							<td width="28%" style="text-align:left;"><a
								class="buttonlink" onclick="searchgener();">提交</a></td>
						</tr>
					</table>
				</div>
				</form>
         </script> 
         <script>
					var interface_num = 1; //接口个数计数
					var attribute_num = 1; //属性个数计数
					
					var interface_param = new Array(); //接口参数计数
					var interface_return_param = new Array(); //接口返回参数计数
					
					var json;
                    var path = "a";
				    
				    function test() {
				
					
					  $.ajax({     
    								url:'/IoT_Harbor/recoveryPemodel.html',
									type : 'post',
									data : document.getElementById("inputmodel"),
									async : false, //默认为true 异步     
									error : function() {
										
									},
									success : function(data) {
										alert(data);
									}
								});
					}
				    	
					
					function add() {
					  
						var tempHolder;

						var data = {
							"ERX" : interface_num,
							"w" : interface_num
						};
						tempHolder = new Template($("#interface_template").html());
                       
						var s = tempHolder.render(data);
						
					   
						$("#Area2").append(s);
						bind_click(interface_num);

						interface_param[interface_num] = 1;
						interface_return_param[interface_num] = 1;
						interface_num++;
						$("#count").val(interface_num);
						
					}

					function del_interface_post_process(del_interface_num){
						
					}
					
					function add_attribute(){
						var tempHolder;
						
						var data = {"ERX" : attribute_num};
						tempHolder = new Template($("#attribute_template").html());
						
						var s = tempHolder.render(data);
					
						$("#Area3").append(s);
						bind_attribute_button(attribute_num);

						attribute_num++;
						$("#attribute_count").val(attribute_num);					

					}
					
					function param1(name) {
					  
						var index = name.substring(10);
                        var data = {
							"w" : interface_param[index],
							"ERX" : index
						};
						var tempHolder = new Template($("#interface_param_template").html());
                        var s = tempHolder.render(data);
                       
						$("#interface_param_" + index).append(s);
						
						bind_click_param(index, interface_param[index]);
                        $("#paramm_" + index).val(interface_param[index]);
                        interface_param[index]++;
					}
				 
				

					function return_param1(name) {
					
						var index = name.substring(10);
						var data = {
							"a" : interface_return_param[index],
							"ERX" : index
						};
						var tempHolder = new Template($("#return_param_template").html());
                        var s = tempHolder.render(data);
                        $("#interface_return_" + index).append(s);
                     
                        bind_click_return(index, interface_return_param[index]);
						interface_return_param[index]++;
                        $("#return_paramm_" + index).val(interface_return_param[index]);

					}

					function param_template_select(sequence){
						var tempHolder;
					
						if (sequence == "enum") {

							tempHolder = new Template($("#enum_template").html());
						}
						
						if (sequence == "Int") {
						
							tempHolder = new Template($("#int_template").html());
						}
						if (sequence == "Float") {
						
							tempHolder = new Template($("#float_template").html());

						}
						if (sequence == "String") {
						
							tempHolder = new Template($("#string_template").html());

						}
						if (sequence == "Boolean") {
						
						   tempHolder = new Template($("#boolean_template").html());
						}
						return tempHolder;
					}
					
					function match_return(sequence, index, count) {

						var data = {
							"w" : count,
							"ERX" : index,
							"type" : "return",
						};
						var tempHolder = param_template_select(sequence);
						
						var s = tempHolder.render(data);
					
						$("#return_" + index + "_" + count).append(s);
					}

					function match(sequence, index, param_count) {
                     
						var data = {
							"w" : param_count,
							"ERX" : index,
							"type" : "param",
						};
					    var tempHolder = param_template_select(sequence);
						
						var s = tempHolder.render(data);
						
						
                    
						$("#paramlist_" + index + "_" + param_count).append(s);

					}
					function match_attribute(sequence, num){
						var data = {
							"w" : 0,
							"ERX" : num,
							"type" : "attribute",
						};
						
						var tempHolder = param_template_select(sequence);
						
						var s = tempHolder.render(data);
					
						$("#attribute_" + num).append(s);
					}

                  
					function searchgener() {
                      
	
						var str2 = Form2QueryString("#form1");
					/* 	alert(str2); */
						var temp;
						$.ajax({     
    								url:'/IoT_Harbor/generatepemodel.html',
									type : 'post',
									data : str2,
									async : false, //默认为true 异步     
									error : function() {
										
									},
									success : function(data) {
										path = data;
									}
								});
						
						downfile();
						

					}
					function downfile() {
					    var fileName = $("#Name").val();
					   
						window.open("/IoT_Harbor/download.html?name="+path+"&fileName="+fileName);
						
					}
					function bind_click(num) {

						$("#button_del_interface_" + num + "").click(
						     function() {
								element_del("#interface_" + num);
						     }
						 );
					}
					
					function bind_attribute_button(num){
					
						$("#button_del_attribute_" + num + "").click(function() {
							element_del("#attribute_" + num);
						});
						$("#button_attribute_" + num + "").click(function() {
						  
							if ($("#attribute_type_" + num + "_0") != null) {
								$("#attribute_type_" + num +"_0").remove();
							}
						    
							match_attribute($(this).prev().val(), num);
						});
					}

					function element_del(str) {
						$(str).remove();
					}

				  
					function bind_click_param(interface_num, param_num) {
				      
						$("#button_del_param_" + interface_num + "_"+ param_num + "").click(
						    function() {
						    
                              param_del(interface_num, param_num);
						    }
						);
						 $("#button_param_" + interface_num + "_"+ param_num + "").click(
								function() {
								  
								  if ($("#param_type_" + interface_num + "_"+ param_num + "") != null) {
									  
										$("#param_type_" + interface_num+ "_" + param_num + "").remove();
								  }  
								 
							      match($(this).prev().val(), interface_num,param_num);
								}
						 ); 
						
					} 
					
                   
					function bind_click_return(interface_num, return_num) {
					   
						
						$("#button_del_return_" + interface_num + "_"+ return_num + "").click(function() {
                            return_del(interface_num, return_num);
						});
						$("#button_return_" + interface_num + "_"+ return_num + "").click(function() {
						 
							if ($("#return_type_"+ interface_num + "_"+ return_num + "") != null) {
							
							
							  $("#return_type_"+ interface_num+ "_"+ return_num+ "").remove();
							}  
							
						   
							match_return($(this).prev().val(),interface_num, return_num);
						});
					}

					function param_del(interface_num, param_num) {
						$("#paramlist_" + interface_num + "_" + param_num)
								.remove();
					}

					function return_del(interface_num, return_num) {
						$("#return_" + interface_num + "_" + return_num)
								.remove();
					}
					function onchangeProtocol_1(protocol, num){
						
						var data = {
							"ERX" : num,
						};
						
						var tempHolder;
						
						if($("#protocol_content_" + num) != null)
							$("#protocol_content_" + num).remove();
							
						if(protocol == "onvif"){
							tempHolder = new Template($("#onvif_template").html());
						}
						
						if(protocol == "ezviz"){
							tempHolder = new Template($("#ezviz_template").html());
						}
						
						if(protocol == "http"){
							tempHolder = new Template($("#HTTP_template").html());
						}
						
						if(protocol == "StandardProtocol")
							return;
							
						var s = tempHolder.render(data);
						
						$("#protocol_extra_" + num).append(s);
					}
					
					function onchangeProtocol(obj, num){
						
						var data = {
							"ERX" : num,
						};
						
						var tempHolder;
						
						if($("#protocol_content_" + num) != null)
							$("#protocol_content_" + num).remove();
							
						if(obj.value == "onvif"){
							tempHolder = new Template($("#onvif_template").html());
						}
						
						if(obj.value == "ezviz"){
							tempHolder = new Template($("#ezviz_template").html());
						}
						
						if(obj.value == "http"){
							tempHolder = new Template($("#HTTP_template").html());
						}
						
						if(obj.value == "StandardProtocol")
							return;
							
						var s = tempHolder.render(data);
						$("#protocol_extra_" + num).append(s);
						
					}
					
					function fold(str, num){
						$("#" + str + "_" + num + "_content").toggle();
						if($("#button_fold_" + str + "_" + num).text() == "折叠"){
							$("#button_fold_interface_" + str + "_" + num).text("展开");
						} else {
							$("#button_fold_interface_" + str + "_" + num).text("折叠");
						}
						
					}
				</script>
				<!--Form1-->
				<table cellpadding="0" cellspacing="0" style="width:100%">


				</table>

				<!--接口区域模板  -->
				<script id="interface_template" type="text/plain">
				<div id="interface_{ERX}">
					<table cellpadding="0" cellspacing="1" class="table_form"
						style="border-top:none;border-bottom:none;">
						<tr>
							<td width="500">接口{ERX} <a id="button_del_interface_{w}"
								class="buttonlink">删除</a>
								<a id="button_fold_interface_{ERX}"
								class="buttonlink" onclick="fold('interface' , {ERX})">折叠</a>
                            </td>
							
						</tr>
					</table>
				<div id="interface_{ERX}_content">
					<table cellpadding="0" cellspacing="1" class="table_form"
						style="border-top:none;border-bottom:none;">
						
						<tr>
							<td width="">接口标识Interface_id_{ERX}:<input name="Interface_id_{ERX}" id="Interface_id_{ERX}" value="" style="width:300px" />
							</td>
						</tr>
						<tr>

							<td width="">接口描述:<input id="Interface_description_{ERX}" name="Interface_description_{ERX}" value=""

							</td>
						</tr>
						<tr>
							<td>接口方向:<select id="interface_direction_{ERX}" name="interface_direction_{ERX}">
									<option  value="up">Up</option>
									<option  value="down">Down</option>
									<option  value="bidirection">Bidirection</option>
							</select>
							</td>
						</tr>
						<tr>
							<td>接口协议:<select id="interface_protocol_{ERX}" name="interface_protocol_{ERX}" onchange="onchangeProtocol(this, {ERX})">
									<option  value="StandardProtocol">StandardProtocol</option>
									<option  value="onvif">Onvif</option>
                                    <option  value="ezviz">Ezviz</option>
									<option  value="http">HTTP</option>
							</select>
							<div id="protocol_extra_{ERX}">
							</div>	
						</td>
						</tr>
						<tr>
							<input name="paramm_{ERX}" id="paramm_{ERX}" type="hidden"
								value="">
             <input name="return_paramm_{ERX}" id="return_paramm_{ERX}" type="hidden"
								value="">
					</table>
					
					<table cellpadding="0" cellspacing="1" class="table_form"
						style="border-top:none;">
						<tr>
							<td width="" style="text-align:left;"><a class="buttonlink"
								onclick="param1('interface_{ERX}')">添加接口参数</a>
							</td>
							<td width="" style="text-align:left;"><a class="buttonlink"
								onclick="return_param1('interface_{ERX}')">添加返回参数</a>
							</td>
							<td style="text-align:left;"></td>
							<td width="443" style="text-align:left;"></td>
						</tr>
					</table>
					<div id="interface_param_{ERX}" value="interface_param"></div>
					<div id="interface_return_{ERX}"></div>
				</div>
				</div>
				</script>

				<!--接口区域模板End-->
				
				<script id="onvif_template" type="text/plain">
				<div id="protocol_content_{ERX}">
					<tr>
						<td>注意：选择Onvif协议后，下面对本接口参数和返回参数的配置将不再有效，默认为Bidirection接口</td>
					</tr>
				</div>
				</script>
				
				<script id="ezviz_template" type="text/plain">
				<div id="protocol_content_{ERX}">
					<tr>
                		<td>注意：选择Ezviz协议后，下面对本接口参数和返回参数的配置将不再有效，默认为Bidirection接口</td>
					</tr>
				</div>
				</script>
				
				<script id="HTTP_template" type="text/plain">
				<div id="protocol_content_{ERX}">
					<tr>
						<td width="">命令拼接规则:</td>
						<td"><input id="http_{ERX}" name="http_rule_{ERX}" value="" style="width:400px" />
						<sapn>注意：HTTP命令目前仅支持下行接口</span>
						</td>						
					</tr>
					
				</div>
				</script>
				
				<!-- 属性参数区域模板 -->
				<script id="attribute_template" type="text/plain">
				<div id="attribute_{ERX}">
					<table cellpadding="0" cellspacing="1" class="table_form"
						style="border-top:none;border-bottom:none;">
						<tr>
							<td width="500">属性{ERX} <a id="button_del_attribute_{ERX}"
								class="buttonlink">删除</a>
								<a id="button_fold_attribute_{ERX}"
								class="buttonlink" onclick="fold('attribute' ,{ERX})">折叠</a>
                            </td>
							
						</tr>
					</table>
					<div id="attribute_{ERX}_content">
					<table name="attribute_table_{ERX}" cellpadding="0" cellspacing="1"
						class="table_form" style="border-top:none;">
						
						<tr>
							<td width="">属性描述attribute_description_{ERX}:<input name="attribute_description_{ERX}" id="attribute_description_{ERX}" value="" style="width:300px" />
							</td>
							<td width="443" style="text-align:left;"></td>
						</tr>
						<tr>
							<td width="">属性名称attribute_key_{ERX}:<input name="attribute_key_{ERX}" id="attribute_key_{ERX}" value="" style="width:300px" />
							</td>
							<td width="443" style="text-align:left;"></td>
						</tr>
						<tr>
							<td>类型:<select name=attribute_type_{ERX} id=attribute_type_{ERX}>
									<option value="Boolean">Boolean</option>
									<option value="enum">enum</option>
									<option value="Int">Int</option>
									<option value="Float">Float</option>
									<option value="String">String</option>
									
							</select><a class='buttonlink' id='button_attribute_{ERX}'>确定</a>
							</td>
							<td width="443" style="text-align:left;"></td>
						</tr>
				</div>
				</div>
				</script>
				<!-- 属性参数区域模板 End-->
				
				<!-- 返回值区域模板 -->
				<script id="return_param_template" type="text/plain">

				<div id="return_{ERX}_{a}"  style="margin-left: 20pt;">
						<table cellpadding="0" cellspacing="1" class="table_form"
							style="border-top:none;">
							<tr>
							<td width="500">接口{ERX}返回参数{a} <a id="button_del_return_{ERX}_{a}"
								class="buttonlink">删除</a></td>"
							</tr>
							<tr>
								<td width="">返回参数描述return_interface_description_{ERX}_{a}:<input
									name="return_interface_description_{ERX}_{a}"  id="return_interface_description_{ERX}_{a}" value=""
									style="width:300px" /></td>
								<td width="443" style="text-align:left;"></td>
							</tr>
							<tr>
								<td width="">返回参数名称return_key_{ERX}_{a}:<input
									name="return_key_{ERX}_{a}" id="return_key_{ERX}_{a}" value="" style="width:300px" /></td>
								<td width="443" style="text-align:left;"></td>
							</tr>
							<tr>
								<td>返回参数类型:<select class='paramtype'
									name="return_param_type_{ERX}_{a}" id="return_param_type_{ERX}_{a}">
										<option value="Boolean">Boolean</option>
										<option value="enum">enum</option>
										<option value="Int">Int</option>
										<option value="Float">Float</option>
										<option value="String">String</option>
										
								</select><a class='buttonlink' id='button_return_{ERX}_{a}'>确定</a></td>
							</tr>
						</table>
					</div>
				</script>

				<!--返回值区域模板End-->

				<!-- 参数区域模板 -->
				<script id="interface_param_template" type="text/plain">
				<div id="paramlist_{ERX}_{w}"  style="margin-left: 20pt;">
					<table name="fornext_{ERX}_{w}" cellpadding="0" cellspacing="1"
						class="table_form" style="border-top:none;">
						<tr>
							<td width="" style="width:300px">接口{ERX}参数{w}<a id="button_del_param_{ERX}_{w}"
								class="buttonlink">删除</a></td>
						</tr>
						<tr>
							<td width="">参数描述interface_description_{ERX}_{w}:<input name="interface_description_{ERX}_{w}"  id="interface_description_{ERX}_{w}" value=""
								style="width:300px" />
							</td>
							<td width="443" style="text-align:left;"></td>
						</tr>
						<tr>
							<td width="">参数名称key_{ERX}_{w}:<input name="key_{ERX}_{w}" id="key_{ERX}_{w}"
								value="" style="width:300px" />
							</td>
							<td width="443" style="text-align:left;"></td>
						</tr>
						<tr>
							<td>参数类型:<select name=param_type_{ERX}_{w} id=param1_type_{ERX}_{w}>
									<option value="Boolean">Boolean</option>
									<option value="enum">enum</option>
									<option value="Int">Int</option>
									<option value="Float">Float</option>
									<option value="String">String</option>
									
							</select><a class='buttonlink' id='button_param_{ERX}_{w}'>确定</a>
							</td>
							<td width="443" style="text-align:left;"></td>
						</tr>
					</table>
					</script>
				<!-- 参数区域模板End -->

				<script id="enum_template" type="text/plain">
				<table id="{type}_type_{ERX}_{w}" cellpadding="0" cellspacing="1" class="table_form"
					style="border-top:none;border-bottom:none;">
					<tbody>
						<tr>
							<td width="100">枚举型{type}_enum:</td>
							<td width="200">枚举选项{type}_option:<input name="{type}_option_{ERX}_{w}" id="{type}_option_{ERX}_{w}" value="" style="width:300px">
							</td>
							
							<td width="443" style="text-align:left;"></td>
						</tr>
					</tbody>
				</table>
				</script>

				<script id="boolean_template" type="text/plain">
				<table id="{type}_type_{ERX}_{w}"  cellpadding="0" cellspacing="1" class="table_form"
					style="border-top:none;border-bottom:none;">
					<tbody>
						<tr>
							<td width="100">布尔型{type}_Boolean</td>

						
							<td width="443" style="text-align:left;"></td>
						</tr>
					</tbody>
				</table>
				</script>

				<script id="string_template" type="text/plain">
				<table id="{type}_type_{ERX}_{w}"  cellpadding="0" cellspacing="1" class="table_form"
					style="border-top:none;border-bottom:none;">
					<tbody>
						<tr>
							<td width="100">字符串型{type}_String</td>
							
							
							<td width="443" style="text-align:left;"></td>
						</tr>
					</tbody>
				</table>
				</script>

				<script id="float_template" type="text/plain">
				<table id="{type}_type_{ERX}_{w}" cellpadding="0" cellspacing="1" class="table_form"
					style="border-top:none;border-bottom:none;">
					<tbody>
						<tr>
							<td width="100">浮点型{type}_Float</td>
							<td width="200">单位{type}_unit:<input name="{type}_unit_{ERX}_{w}" id="{type}_unit_{ERX}_{w}"
								 value="" style="width:100px">
							</td>
							<td width="200">最小值{type}_min_val<input
								name="{type}_min_val_{ERX}_{w}"  id="{type}_min_val_{ERX}_{w}"  value="" style="width:100px">
							</td>
							<td width="200">最大值{type}_max_val<input
								name="{type}_max_val_{ERX}_{w}"  id="{type}_max_val_{ERX}_{w}"  value="" style="width:100px">
							</td>
							
							<td width="443" style="text-align:left;"></td>
						</tr>
					</tbody>
				</table>
				</script>

				<script id="int_template" type="text/plain">
				<table id="{type}_type_{ERX}_{w}" cellpadding="0" cellspacing="1" class="table_form"
					style="border-top:none;border-bottom:none;">
					<tbody>
						<tr>
							<td width="100">整形{type}_Int:</td>
							<td width="200">单位{type}_unit:<input name="{type}_unit_{ERX}_{w}" id="{type}_unit_{ERX}_{w}"
								 value="" style="width:100px">
							</td>
							<td width="200">最小值{type}_min_val<input
								name="{type}_min_val_{ERX}_{w}"  id="{type}_min_val_{ERX}_{w}" value="" style="width:100px">
							</td>
							<td width="200">最大值{type}_max_val<input
								name="{type}_max_val_{ERX}_{w}"  id="{type}_max_val_{ERX}_{w}" value="" style="width:100px">
							</td>
							
							<td width="443" style="text-align:left;"></td>
						</tr>
					</tbody>
				</table>
				</script>
         
</body>  
  
</html>  