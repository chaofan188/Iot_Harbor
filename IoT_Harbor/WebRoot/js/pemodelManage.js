$(function(){
	//查询大类信息
	$.ajax({
		url : "bigType",
			type : "post",
			success : function(result){		
				var info = eval("("+result+")");
				$("#bigType").combobox({
				data : info,
				valueField : 'id',
				textField : 'bigTypeName'
				});
			}
	});
	/*查询中类信息*/
	$("#bigType").combobox({
			onSelect:function(){	  		  		
				$.ajax({
					url:"middleType",
					type:"post",
					data:{
					bigTypeName: $("#bigType").combobox("getText"),
					},
					dataType : "json",
				      beforeSend : function(){
				      },
				      success : function(result){				                      		                	
				      		 var info = eval("("+result+")");
				      		 $("#middleType").combobox({
				      		 	data : info,
				      		 	valueField : 'id',
				      		 	textField : 'middleTypeName'
				      		 });	                				                    
				      },
				      error : function(){
				          alert("加载失败，请重试！");	                 
				      }
				});
			}
		});
	/*查询小类信息*/
	$("#middleType").combobox({
		onSelect:function(){
			$.ajax({
				url:"smallType",
				type:"post",
				data:{
				middleTypeName: $("#middleType").combobox("getText"),
				},
				dataType : "json",
			      beforeSend : function(){
			      },
			      success : function(result){				                      		                	
			      		 var info = eval("("+result+")");
			      		 $("#smallType").combobox({
			      		 	data : info,
			      		 	valueField : 'id',
			      		 	textField : 'smallTypeName'
			      		 });	                				                    
			      },
			      error : function(){
			          alert("加载失败，请重试！");	                 
			      }
			});
		}
	});
	/*查询出所有的物体模板*/
	$.ajax({
		url:"queryAllPEModel",
		type:"post",
		success:function(result){
			var info=eval("("+result+")");
			/*如果是table就做循环，依次列出，如果使用easyUI就不用这种方式*/
		}
	});
	
	/*按类型和型号检索物体模板*/
	$("#queryPEModelByTypeAndModel").click(function(){
		
	});
	/*按时间检索物体模板*/
	$("#queryPEModelByTime").click(function(){
		
	});
	/*按注册者检索物体模板*/
	$("#queryPEModelByRegister").click(function(){
		
	});
	/*删除某一个物体模板*/
	$("#deletePEModelByTypeAndModel").click(function(){
		
	});
});