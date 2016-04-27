<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String device_name = request.getParameter("device_name");
String template_id = request.getParameter("ID");
String peid = request.getParameter("peid");
if(device_name==null){
   device_name="";
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8>">
<title>全部信息-管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link rel="stylesheet" type="text/css" href="../../admin/skin/system.css" >
<script type="text/javaScript" src="../../images/js/newjs/jquery.min.js"></script>
<script type="text/javaScript" src="../../images/js/newjs/jquery.easyui.min.js"></script>


<script type="text/javascript" src="../../images/js/newjs/datagrid_adapter.js"></script>
<script type="text/javascript" src="../../images/js/newjs/typeSelecter.js"></script>
<script type="text/javascript" src="../../images/js/newjs/FormEngine.js"></script>
<!--<script type="text/javaScript" src="images/js/css.js"></script>
<script type="text/javaScript" src="data/config.js"></script>
<script type="text/javaScript" src="images/js/common.js"></script>
<script type="text/javaScript" src="images/js/admin.js"></script>
<script type="text/javascript" src="images/js/validator.js"></script>
<script type="text/javascript" src="images/js/form.js"></script>-->
<link rel="stylesheet" type="text/css" href="../../images/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="../../images/js/newjs/easyui-balthasar.css">
</head>
<body>

<!--Form1-->
<form action="" method="post" name="form1" id="form1">
<input name="count" id="count" type="hidden" value="{num}">
<table cellpadding="0" cellspacing="1" class="table_form" style="border-bottom:none;">
  <caption>
	填写设备基本信息
  </caption>
<table>
<div id="Area1">
    <table cellpadding="0" cellspacing="1" class="table_form" style="border-top:none;border-bottom:none;">
   <tr>
    <td width="">模板ID:</td>
    <td><input id="Model_ID" name="Model_ID" value="" style="width:300px"/></td>
   </tr>
   <tr>
    <td width="">型号:</td>
    <td><input id="Model" name="Model" value="" style="width:300px"/></td>
   </tr>
   <tr>
    <td width="">模板名称:</td>
    <td><input id="Name" name="Name" value="" style="width:300px"/></td>
   </tr>
    <tr>
    <td width="">模板类型:</td>
    <td><input id="Type" name="Type" value="" style="width:300px"/></td>
   </tr>
    <tr>
    <td width="">模板图片路径:</td>
    <td><input id="Pic_url" name="Pic_url" value="" style="width:300px"/></td>
   </tr>
     <tr>
    <td width="">模板描述:</td>
    <td><input id="Description" name="Description" value="" style="width:300px"/></td>
   </tr>
   </table>
</div>

<table cellpadding="0" cellspacing="1" class="table_form" style="border-top:none;">
  <tr>
     <td width="" style="text-align:left;">
      <a class="buttonlink" onclick="add();return false;">增加接口</a>
     </td>
     <td style="text-align:left;"></td>
     <td width="443" style="text-align:left;"></td>
  </tr>
  
</table>


<table cellpadding="0" cellspacing="1" class="table_form" style="border-top:none;">
 <tr>
        
            <td width="28%" style="text-align:left;">
                <a class="buttonlink" onclick="searchgener();return false;">提交</a>
            </td>
          </tr>
</table>
<!-- <table cellpadding="0" cellspacing="1" class="table_form" style="border-top:none;">
  <tr>
     <td width="" style="text-align:left;">
      <a class="buttonlink" onclick="add1();return false;">添加元素</a>
     </td>
     <td style="text-align:left;"></td>
     <td width="443" style="text-align:left;"></td>
  </tr>
  
</table> -->
<!--Form1End-->

</form>
 <script>
               var num=0;
               var m=1;
               var Area1Temp="";
               Area1Temp=Area1Temp+"<div id=\"interface_{ERX}\">";
			   
			   
			   Area1Temp=Area1Temp+"<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;border-bottom:none;\">";
               Area1Temp=Area1Temp+"<tr>";
               Area1Temp=Area1Temp+" <td width=\"\">Interface_id_{ERX}:<input  name=\"Interface_id_{ERX}\"+num  value=\"\" style=\"width:300px\"/></td>";
        /*        Area1Temp=Area1Temp+" <td width=\"443\" style=\"text-align:left;\"></td>"; */
               Area1Temp=Area1Temp+"</tr>";
             /*   Area1Temp=Area1Temp+"<tr>"; */
               
               Area1Temp=Area1Temp+"<tr>";
               Area1Temp=Area1Temp+"<td width=\"\">is_control_{ERX}:<select name=\"is_control_{ERX}\";><option   >请选择</option ><option value=\"0\"  >true</option > <option value=\"1\"  >false</option></select></td>";
               Area1Temp=Area1Temp+"</tr>";
               
               Area1Temp=Area1Temp+"<tr>";
               Area1Temp=Area1Temp+"<td width=\"\">is_transparent_{ERX}:<select name=\"is_transparent_{ERX}\";><option   >请选择</option ><option value=\"0\"  >true</option > <option value=\"1\"  >false</option></select></td>";
               Area1Temp=Area1Temp+"</tr>";
               
               Area1Temp=Area1Temp+"<tr>";
               Area1Temp=Area1Temp+"<td width=\"\">need_response_{ERX}:<select name=\"need_response_{ERX}\";><option   >请选择</option ><option value=\"0\"  >true</option > <option value=\"1\"  >false</option></select></td>";
               Area1Temp=Area1Temp+"</tr>";
               
               Area1Temp=Area1Temp+"<tr>";
               Area1Temp=Area1Temp+"<td width=\"\">is_dir_down_{ERX}:<select name=\"is_dir_down_{ERX}\";><option   >请选择</option ><option value=\"0\"  >true</option > <option value=\"1\"  >false</option></select></td>";
               Area1Temp=Area1Temp+"</tr>";
               
               
               Area1Temp=Area1Temp+"<input name=\"paramm_{ERX}\" id=\"paramm_{ERX}\" type=\"hidden\" value=\"\">";
               Area1Temp=Area1Temp+"</table>"; 
              
               Area1Temp=Area1Temp+"</div>";
               
               
               Area1Temp=Area1Temp+"<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;\">";
               Area1Temp=Area1Temp+"<tr>";
               Area1Temp=Area1Temp+"<td width=\"\" style=\"text-align:left;\">";
               Area1Temp=Area1Temp+" <a class=\"buttonlink\" onclick=\"param('interface_{ERX}')\">添加参数</a>";
               Area1Temp=Area1Temp+" </td>";
               Area1Temp=Area1Temp+"<td style=\"text-align:left;\"></td>";
               Area1Temp=Area1Temp+"<td width=\"443\" style=\"text-align:left;\"></td>";
               Area1Temp=Area1Temp+"</tr>";
               Area1Temp=Area1Temp+"</table> ";
   
              
               /* Area1Temp=Area1Temp+"</div>"; */

			   function add(){
			     
			      $("#Area1").html($("#Area1").html()+Area1Temp.replace(/{ERX}/g,num));
			      num++;
			      $("#count").val(num);
			      m=0;
		       }
		       function param(name){
		          var index = name.substring(10);
		          var temp="";
                      temp=temp+"<div id=\"paramlist_{w}\">"
                      temp=temp+"<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;\">";
                      
                      temp=temp+"<tr>";
                      temp=temp+" <td width=\"\">encode_{a}_{w}:<input  name=\"encode_{a}_{w}\"+num  value=\"\" style=\"width:300px\"/></td>";
                      temp=temp+" <td width=\"443\" style=\"text-align:left;\"></td>";
                      temp=temp+"</tr>";
               
                      temp=temp+"<tr>";
                      temp=temp+" <td width=\"\">interface_description_{a}_{w}:<input  name=\"interface_description_{a}_{w}\"+num  value=\"\" style=\"width:300px\"/></td>";
                      temp=temp+" <td width=\"443\" style=\"text-align:left;\"></td>";
                      temp=temp+"</tr>";
                      
                      temp=temp+"<tr>";
                      temp=temp+" <td width=\"\">key_{a}_{w}:<input  name=\"key_{a}_{w}\"+num  value=\"\" style=\"width:300px\"/></td>";
                      temp=temp+" <td width=\"443\" style=\"text-align:left;\"></td>"; 
                      temp=temp+"</tr>";
                      temp=temp+"</table> ";
                      temp=temp+"</div>";
                         
                      temp=temp+"<table> ";
                      temp=temp+"<tr>";
                      temp=temp+"<td>参数类型:<select name=param_type_{a}_{w}  onchange=\"match(this.options[this.options.selectedIndex].value,this.name)\";><option>请选择</option ><option value=\"0\"  >enum</option > <option value=\"1\"  >Int</option><option value=\"2\"  >Float</option ><option value=\"3\"  >String</option ><option value=\"4\"  >Boolean</option ></select></td>";
                      temp=temp+"<td width=\"443\" style=\"text-align:left;\"></td>";
                      temp=temp+"</tr>";
                      temp=temp+"</table> ";
                   
                      
                      $("#interface_"+index).html($("#interface_"+index).html()+temp.replace(/{w}/g,m).replace(/{a}/g,index));
                      //alert("before:"+m);
                      m++;
                      //alert("after:"+m);
                      $("#paramm_"+index).val(m);
                      // alert("m="+m);
            
		       }
		       function match(sequence,name){
		       // alert(name);
		        var index = name.substring(11);
		       // alert(index);
		        var tempindex = index.substring(2);
		       // alert(tempindex);
		        if(sequence==0){
		         //  alert("0");
		           var temp="";
			        temp=temp+"<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;border-bottom:none;\">";
                    temp=temp+"<tr>";
                     temp=temp+" <td width=\"100\">enum_{w}:</td>";
                    temp=temp+" <td width=\"100\">option_{w}:<input  name=\"option_{w}\"+num  value=\"\" style=\"width:300px\"/></td>";
                    temp=temp+" <td width=\"443\" style=\"text-align:left;\"></td>";
                    temp=temp+"</tr>";
                    temp=temp+"</table>"
                    
		            $("#paramlist_"+tempindex).html($("#paramlist_"+tempindex).html()+temp.replace(/{w}/g,index));
		        }
		         if(sequence==1){
		            var temp="";
			        temp=temp+"<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;border-bottom:none;\">";
                    temp=temp+"<tr>";
                    temp=temp+" <td width=\"100\">Int_{w}:</td>";
                    temp=temp+" <td width=\"100\">unit_{w}:<input  name=\"unit_{w}\"+num  value=\"\" style=\"width:100px\"/></td>";
                    temp=temp+" <td width=\"100\">min_val_{w}<input name=\"min_val_{w}\"+num  value=\"\" style=\"width:100px\"/></td>";
                    temp=temp+" <td width=\"100\">max_val_{w}<input name=\"max_val_{w}\"+num  value=\"\" style=\"width:100px\"/></td>";
                    temp=temp+" <td width=\"443\" style=\"text-align:left;\"></td>";
                    temp=temp+"</tr>";
                    temp=temp+"</table>";
		        
		            $("#paramlist_"+tempindex).html($("#paramlist_"+tempindex).html()+temp.replace(/{w}/g,index));
		        }
		           if(sequence==2){
		           var temp="";
			        temp=temp+"<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;border-bottom:none;\">";
                    temp=temp+"<tr>";
                    temp=temp+" <td width=\"100\">Float_{w}</td>";
                    temp=temp+" <td width=\"100\">unit_{w}:<input  name=\"unit_{w}\"+num  value=\"\" style=\"width:100px\"/></td>";
                    temp=temp+" <td width=\"100\">min_val_{w}<input name=\"min_val_{w}\"+num  value=\"\" style=\"width:100px\"/></td>";
                    temp=temp+" <td width=\"100\">max_val_{w}<input name=\"max_val_{w}\"+num  value=\"\" style=\"width:100px\"/></td>";
                    temp=temp+" <td width=\"443\" style=\"text-align:left;\"></td>";
                    temp=temp+"</tr>";
                    temp=temp+"</table>";
		       
		             
		            $("#paramlist_"+tempindex).html($("#paramlist_"+tempindex).html()+temp.replace(/{w}/g,index));
		        }
		        if(sequence==3){
		            var temp="";
			        temp=temp+"<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;border-bottom:none;\">";
                    temp=temp+"<tr>"
                    temp=temp+" <td width=\"100\">String_{w}:<input  name=\"String_{w}\"+num  value=\"\" style=\"width:300px\"/></td>";
                    temp=temp+" <td width=\"443\" style=\"text-align:left;\"></td>";
                    temp=temp+"</tr>";
		            temp=temp+"</table>";
		             
		            $("#paramlist_"+tempindex).html($("#paramlist_"+tempindex).html()+temp.replace(/{w}/g,index));
		        }
		        if(sequence==4){
		            var temp="";
			        temp=temp+"<table cellpadding=\"0\" cellspacing=\"1\" class=\"table_form\" style=\"border-top:none;border-bottom:none;\">";
                    temp=temp+"<tr>"
                    temp=temp+" <td width=\"100\">Boolean_{w}:<input  name=\"Boolean_{w}\"+num  value=\"\" style=\"width:300px\"/></td>";
                    temp=temp+" <td width=\"443\" style=\"text-align:left;\"></td>";
                    temp=temp+"</tr>";
                    temp=temp+"</table>";
		       
		             
		            $("#paramlist_"+tempindex).html($("#paramlist_"+tempindex).html()+temp.replace(/{w}/g,index));
		        }
		       }
		       function searchgener()
			   {
			   
				  var jStr=Form2QueryString("#form1");
				 alert(jStr);
			   }
		 
</script>
<!--Form1-->
<table cellpadding="0" cellspacing="0" style="width:100%">
 
       
</table>
<!--Form1End-->

</body>
</html>