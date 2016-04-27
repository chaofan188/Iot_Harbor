// JavaScript Document
if(UserId==undefined)
{
	var UserId=4;
}
function BindIndexRedirect()
{
	$("#titlebar").bind('click',function(){window.location.href=getpagebyrole(100);});
}
function ResizeWindow()
{
			var GenFlag=true;
			if($.browser.msie)
			{
				if(parseFloat($.browser.version)<8)
				{
					window.document.title=window.document.title+"  [页面非正常状态，请升级IE或切换至其他浏览器！]";
				}
			}
			if(GenFlag)
			{
				$(window).resize(function(){
											 var WinH=$(window).height();
											 if(WinH<1024){WinH=1024;};
											 var bodyheight=WinH-120-3-60-15; //TITLE HEIGHT 120,BAR HEIGHT 3,FOOTER 60,PADDINT TOP 27
											 $("#rightbar").css("height",(bodyheight-5)+"px");
											 
											 
											try
											{
												$("#insertBodyWindow").css("height",bodyheight-5-100);
											}catch(e){;};
											 
										  });
				$(window).resize();
			}
			else
			{
				$("#rightbar").css("height","800px");
			}
			try
			{
				$(".easyui-dialog").dialog("close");
			}catch(e){;}
}
function GenViewFormBox()
{
	try
	{
		$(".displaybox").attr("disabled","true");
	}catch(e)
	{
	}
}
//Public Event
function LoadCommand(cmd){try{eval(cmd);}catch(e){}}
function InitEvent()
{			
			LoadCommand("InitPage()");
			LoadCommand("bindEndEditEvent()");
			LoadCommand("LoadUserSession()");
			LoadUser(function()
							  {
									LoadCommand("LoadPage(1)");
									LoadCommand("LoadAjax(null)");
									LoadCommand("LoadData()");
							  });
}

//Loader
$(document).ready(function(){		
			InitEvent();
			BindIndexRedirect();
			ChangeManaM();
			ResizeWindow();
			GenViewFormBox();
	});
	
function ChangeManaM()
{
	try
	{
		var p=Request.QueryString("CheckMana");
		if(p!=null)
		{
			$("#K0").removeClass("selected");
			$("#K1").removeClass("selected");
			$("#K2").removeClass("selected");
			$("#K"+p).addClass("selected");
		}
	}catch(e){};
}