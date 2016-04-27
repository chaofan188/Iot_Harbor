// JavaScript Document
var PageRole=2;
function InitPage()
{
	window.document.title="物联网开发者(应用)";
	try
	{
		if(isVE){
			PageRole=1;
			window.document.title="物联网开发者(VE)";
		}
	}catch(e)
	{
	}
}