// JavaScript Document

				$(".cbrbutton[default]").css("color","#999");
				$(".cbrbutton[default]").css("background","none");
				$(".cbrbutton").css("text-shadow","#00a0e8 2 2 2");
				$("#selecttype").attr("value",$(".cbrbutton[default]").attr("selecttype"));

				$(".cbrbutton").click(
									  function(){
										  var typevalue=$(this).attr("selecttype");
										  $(".cbrbutton").css("color","#011b3c");
										  $(".cbrbutton").css("background","none");
										  $(this).css("color","#999");
										  $(this).css("background","none");
										  $("#selecttype").attr("value",typevalue);
									  }
									  );
				function SearchEvent()
				{
										var SearchServer="http://192.168.12.152/index/";
										
										var Type=$("#selecttype").attr("value");
										var type=parseInt(Type);
										switch(type)
										{
											case 1:$("#f1").attr("action",SearchServer+"vivision.aspx");
											        $("#keyword").attr("name","veid");break;
											case 2:$("#f1").attr("action",SearchServer+"areaResult.aspx");
											        $("#keyword").attr("name","ci");break;
											case 3:$("#f1").attr("action",SearchServer+"mapresult.aspx");
											        $("#keyword").attr("name","peid");break;
											case 4:$("#f1").attr("action",SearchServer+"keywordResult.aspx");
											        $("#keyword").attr("name","kw");break;
											default:$("#f1").attr("action",SearchServer+"keywordResult.aspx");
											        $("#keyword").attr("name","kw");break;
										}
										$("#keyword").attr("value",$("#search_content").attr("value"));
									    //$("#f1").submit();
										var CLKURL=$("#f1").attr("action")+"?"+$("#keyword").attr("name")+"="+escape($("#keyword").attr("value"));
										window.open(CLKURL);
				}