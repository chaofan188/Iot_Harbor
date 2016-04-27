// JavaScript Document

function Map2Json(Map,KeyName,ValueName)
	{
		try
			{
			var newstr="{";
			for(i=0;i<Map.length;i++)
			{
				var ii=Map[i];
				if(newstr.length>2)
				{
					newstr=newstr+",";
				}
				if(typeof(ii[ValueName])=="string")
				{
					newstr=newstr+ii[KeyName]+":'"+ii[ValueName]+"'";
				}else
				{
					newstr=newstr+ii[KeyName]+":"+ii[ValueName]+"";
				}
			}
			newstr=newstr+"}";
			eval("var tmp="+newstr);
			return tmp;
		}catch(e)
		{
			return null;
		}
}

						
function Form2Json(form)
{
		var postdata=$(form).serializeArray();
		return Map2Json(postdata,"name","value");
}						
function Form2QueryString(form)
{
	return $(form).serialize();
}

function Json2Form(form,json)
{
	$(form).form('load',json);
}

function QueryString2Form(form,json)
{
	
}

function SubmitFormAJAX(form,success,error)
{
	var o=$(form);
	var action=o.attr("action");
	var method=o.attr("method");
	var postdata=Form2QueryString(form);
	$.ajax({type:method,
			url:action,
			data:postdata,
			success:success,
			error:error
			});
}
function SubmitFormAJAXEx(form,callbackType,success,error)
{
	var o=$(form);
	var action=o.attr("action");
	var method=o.attr("method");
	var postdata=Form2QueryString(form);
	$.ajax({type:method,
			url:action,
			data:postdata,
			dataType:callbackType,
			success:success,
			error:error
			});
}


function hiddenObjectBorder(parent)
{
	var P="";
	if(parent!="")
	{
		P=parent+" ";
	}
	$(P+"input").each(function(i){
				this.css("border","none");
				this.attr("disabled","true");
		});
}

