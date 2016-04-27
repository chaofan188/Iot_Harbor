<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8>">
<title>全部信息-管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="admin/skin/system.css" rel="stylesheet" type="text/css">
<script type="text/javaScript" src="images/newjs/jquery.min.js"></script>
<!--<script type="text/javaScript" src="images/js/css.js"></script>
<script type="text/javaScript" src="data/config.js"></script>
<script type="text/javaScript" src="images/js/common.js"></script>
<script type="text/javaScript" src="images/js/admin.js"></script>
<script type="text/javascript" src="images/js/validator.js"></script>
<script type="text/javascript" src="images/js/form.js"></script>-->
</head>
<body>
<!--Form1-->
<table cellpadding="0" cellspacing="1" class="table_form">
  <caption>测试窗体</caption>
  <tr>
    <td>窗体条目1-1</td>
    <td>窗体条目1-2</td>
  </tr>
  <tr>
    <td>窗体条目2-1</td>
    <td>窗体条目2-2</td>
  </tr>
</table>
<!--Form1End-->

<!--Form1-->
<table cellpadding="0" cellspacing="1" class="table_form">
  <caption>管理信息</caption>
  <tr>
    <td><a href='?mod=phpcms&file=content_all&action=manage' >所有通过信息</a> | <a href='?mod=phpcms&file=content_all&action=recycle&status=0' >回收站</a> | <a href='?mod=phpcms&file=content_all&action=my' >我发布的信息</a> | <a href='?mod=phpcms&file=content_all&action=search' >搜索</a></td>
  </tr>
</table>
<!--Form1End-->
<form name="search" method="get" action="">
<input type="hidden" name="mod" value="phpcms"> 
<input type="hidden" name="file" value="content_all"> 
<input type="hidden" name="action" value=""> 
<input type="hidden" name="catid" value="0">
<table cellpadding="0" cellspacing="1" class="table_form">
  <caption>信息查询</caption>
<tr>
<td class="align_c">
<select name='catid' id='catid' >
<option value='0'>不限栏目</option>
<option value='33' >业界新闻</option>
<option value='34' >资讯通知</option>
<option value='36' >重点推介</option>
<option value='37' >主页介绍-面向政府</option>
<option value='38' >主页介绍-面向企业</option>
<option value='39' >主页介绍-应用开发</option>
</select>  
<select name='typeid' id='typeid' >
<option value='0'>类别</option>
</select> 
<select name='field'>
<option value='title'  >标题</option>
<option value='username'  >用户名</option>
<option value='userid'  >用户ID</option>
<option value='contentid'  >ID</option>
</select>
<input type="text" name="q" value="" size="15" />&nbsp;
发布时间：          <!--时间日期框-->
					<link rel="stylesheet" type="text/css" href="images/js/calendar/calendar-blue.css"/> <!--时间日期框初始化-->
			        <script type="text/javascript" src="images/js/calendar/calendar.js"></script><!--时间日期框初始化-->
					<input type="text" name="inputdate_start" id="inputdate_start" value="" size="10" readonly>
					<script language="javascript" type="text/javascript">
					date = new Date();document.getElementById ("inputdate_start").value="";
					Calendar.setup({
						inputField     :    "inputdate_start",
						ifFormat       :    "%Y-%m-%d",
						showsTime      :    false,
						timeFormat     :    "24"
					});
				    </script>
					<!--/时间日期框-->
					- <input type="text" name="inputdate_end" id="inputdate_end" value="" size="10" readonly>&nbsp;<script language="javascript" type="text/javascript">
					date = new Date();document.getElementById ("inputdate_end").value="";
					Calendar.setup({
						inputField     :    "inputdate_end",
						ifFormat       :    "%Y-%m-%d",
						showsTime      :    false,
						timeFormat     :    "24"
					});
				 </script>&nbsp;
<input type="submit" name="dosubmit" value=" 查询 " />
</td>
</tr>
</table>
</form>








<form name="myform" method="post" action="">
<table cellpadding="0" cellspacing="1" class="table_list">
  <caption>
信息管理</caption>
<tr>
<th width="30">选中</th>
<th width="40">排序</th>
<th width="40">ID</th>
<th>标题</th>
<th width="80">状态</th>
<th width="100">栏目</th>
<th width="70">录入者</th>
<th width="100">更新时间</th>
<th width="170">管理操作</th>
</tr>
<!--ContentItem-->
<tr>
<td><input type="checkbox" name="contentid[]" value="11" id="content_11" /></td>
<td><input type="text" name="listorders[11]" value="0" size="3" /></td>
<td>11</td>
<td><a href="2014/0909/11.html" target="_blank">2222</a> &nbsp;&nbsp;</td>
<td class="align_c">终审通过</td>
<td class="align_c"><a href="/iot_index_c1/" target="_blank">主页介绍-面向政府</a></td>
<td class="align_c"><a href="?mod=member&file=member&action=view&userid=1">phpcms</td>
<td class="align_c">2014-09-09 17:11</td>
<td class="align_c">
<a href="?mod=phpcms&file=content&action=view&catid=0&contentid=11">查看</a> | 
<a href="?mod=phpcms&file=content&action=edit&catid=0&contentid=11">修改</a> | 
<a href="?mod=phpcms&file=content&action=log_list&catid=0&contentid=11">日志</a> 
 | <a href="?mod=comment&file=comment&keyid=phpcms-content-title-11">评论</a> </td>
</tr>
<!--/ContentItem-->
</table>




<!--分页区间1-->
<div class="button_box">
<span style="width:60px"><a href="###" onclick="javascript:$('input[type=checkbox]').attr('checked', true)">全选</a>/<a href="###" onclick="javascript:$('input[type=checkbox]').attr('checked', false)">取消</a></span>
		<input type="button" name="listorder" value=" 排序 " onclick="myform.action='?mod=phpcms&file=content_all&action=listorder&catid=0&processid=&forward=http%3A%2F%2Fweb.iot.com%2Fadmin.php%3Fmod%3Dphpcms%26file%3Dcontent_all';myform.submit();"> 
		<input type="button" name="delete" value=" 删除 " onclick="myform.action='?mod=phpcms&file=content_all&action=cancel&catid=0&processid=&forward=http%3A%2F%2Fweb.iot.com%2Fadmin.php%3Fmod%3Dphpcms%26file%3Dcontent_all';myform.submit();"> 
		<input type="button" name="pass" value="终审通过"  onclick="myform.action='?mod=phpcms&file=content_all&action=pass&catid=0&forward=http%3A%2F%2Fweb.iot.com%2Fadmin.php%3Fmod%3Dphpcms%26file%3Dcontent_all';myform.submit();"> 
</div>
<!--/分页区间1-->
<!--分页区间2-->
<div id="pages">总数：<b>11</b>
<a href="page.jsp&page=1">首页</a><a href="page.jsp&page=1">上一页</a><a href="page.jsp&page=1">下一页</a><a href="page.jsp&page=1">尾页</a>
页次：<b><font color="red">1</font>/1</b>
<input type="text" name="page" id="page" size="2" onKeyDown="if(event.keyCode==13) {redirect('page.jsp&page='+this.value); return false;}"> 
<input type="button" value="GO" class="gotopage" onclick="redirect('page.jsp&page='+$('#page').val())"></div>
</form>
<!--/分页区间2-->
</body>
</html>
