// JavaScript Document

function GetMsg(){
		$.ajax({
			url : "getModelInfo.action",
    		type : "post",   		
    		dataType : "json",
    		data:{
    			bigType:$("#TypeLevel1").val(),
    			middleType:$("#TypeLevel2").val(),
    			smallType:$("#TypeLevel3").val(),
    			model:$("#ObjectType").val()
    		},
    		beforeSend : function(){
    		},
    		success : function(result){
    			var pe=eval("("+result+")"); 
    			var attrcontent="<table><tr><td>序号</td><td>属性名</td><td>属性值</td><td>单位</td></tr>";
    			var statuscontent="<table><tr><td>序号</td><td>属性名</td><td>默认值</td><td>单位</td><td>关联函数</td></tr>";   			
    			var domainlist=pe.domainAttributes;
    			var statuslist=pe.states;
    			for(i in domainlist){ 	  					
	  					var domaininfo=domainlist[i];
	  					var j=i+1;
	  					attrcontent+="<tr><td>"+j+"</td><td>"+domaininfo.attrName+"</td><td>"+domaininfo.attrValue+"</td><td>"+domaininfo.unit+"</td></tr>";
	  					$("#property").html(attrcontent);
	  			} 
	  			for(i in statuslist){ 	  					
	  					var statusinfo=statuslist[i];	  					
	  					statuscontent+="<tr><td>"+statusinfo.stateID+"</td><td>"+statusinfo.stateName+"</td><td>"+statusinfo.defaultValue+"</td><td>"+statusinfo.unit+"</td><td>"+statusinfo.m2mName+"</td></tr>";
	  					$("#status").html(statuscontent);
	  			} 
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
	  				var interfaceinfo="<table><tr><td>"+函数名称+"</td><td>"+函数说明+"</td><td>"+返回值类型+"</td><td>"+返回值说明+"</td></tr>";
	  				for(i in m2mlist){
	  					var m2minfo=m2mlist[i];
	  					interfaceinfo+="<tr><td>"+m2minfo.functionName+"</td><td>"+m2minfo.effect+"</td><td>"+returnType+"</td><td>"+returnComment+"</td><td>";
	  				}
	  				interfaceinfo+="</table>";
	  				$("#interface").html(interfaceinfo);
	  			}
    		}
		});
	}