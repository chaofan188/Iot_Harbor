$(function(){
	//��ѯ������Ϣ
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
	/*��ѯ������Ϣ*/
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
				          alert("����ʧ�ܣ������ԣ�");	                 
				      }
				});
			}
		});
	/*��ѯС����Ϣ*/
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
			          alert("����ʧ�ܣ������ԣ�");	                 
			      }
			});
		}
	});
	/*��ѯ�����е�����ģ��*/
	$.ajax({
		url:"queryAllPEModel",
		type:"post",
		success:function(result){
			var info=eval("("+result+")");
			/*�����table����ѭ���������г������ʹ��easyUI�Ͳ������ַ�ʽ*/
		}
	});
	
	/*�����ͺ��ͺż�������ģ��*/
	$("#queryPEModelByTypeAndModel").click(function(){
		
	});
	/*��ʱ���������ģ��*/
	$("#queryPEModelByTime").click(function(){
		
	});
	/*��ע���߼�������ģ��*/
	$("#queryPEModelByRegister").click(function(){
		
	});
	/*ɾ��ĳһ������ģ��*/
	$("#deletePEModelByTypeAndModel").click(function(){
		
	});
});