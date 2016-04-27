// JavaScript Document

					function FillChange(Value,Level,InterfaceType)
					{
						var newValue=Value;
						if(typeof(Value)=="string")
						{
							if(Value.substring(0,1)=="-")
							{
								return;
							}
						}
						var BigLevelURL='';
						var OtherLevelURL='';
						var TypeLevelURL='';
						var BigParam='';
						var OtherParam='';
						var TypeParam='';
						if(InterfaceType!="LPY")
						{
							BigLevelURL='/appPlatform/classByTypeList.html';
							BigParam='classType=1';
							OtherLevelURL='/appPlatform/subClassList.html';
							OtherParam='classId='+newValue;
							TypeLevelURL='/appPlatform/subClassList.html';
							TypeParam='classId='+newValue;
						}else
						{
							BigLevelURL='/PSDLProject/bigType.action';
							BigParam='classType=1';
							OtherLevelURL='/PSDLProject/otherType.action';
							OtherParam='classId='+newValue;
							TypeLevelURL='/PSDLProject/otherType.action';
							TypeParam='classId='+newValue;
						}
							switch(Level)
							{
								case 0:
									try
									{
										fillcombobox(BigLevelURL,BigParam,'TypeLevel1',function(){
																														   FillDefault(Level);
																														});
									}catch(e){};
									break;//INIT
								case 1:								
									try
									{
										fillcombobox(OtherLevelURL,OtherParam,'TypeLevel2',function(){
																														   FillDefault(Level);
																														});
									}catch(e){};
									break;//ON BIG Type Change
								case 2:
									try
									{
										fillcombobox(OtherLevelURL,OtherParam,'TypeLevel3',function(){
																														   FillDefault(Level);
																														});
									}catch(e){};
									break;//ON MID Type Change
								case 3:
									try
									{
										fillcombobox(TypeLevelURL,TypeParam,'ObjectType',function(){
																														   FillDefault(Level);
																														});
									}catch(e){};
									break;//ON SMAILL TYPE Change
								case 4:try{
										callback(Level,Value,Interface);
									   }catch(e){};break;//ON KOGChange
							}
					}
					function FillDefault(Level)
					{
						switch(Level)
						{
							case 0: $("#TypeLevel1").combobox('setValue', '-大类-');
									$("#TypeLevel2").combobox('setValue', '-中类-');
									$("#TypeLevel3").combobox('setValue', '-小类-');
									try{$("#ObjectType").combobox('setValue', '-型号-');}catch(e){};
									break;
							case 1: $("#TypeLevel2").combobox('setValue', '-中类-');
									$("#TypeLevel3").combobox('setValue', '-小类-');
									try{$("#ObjectType").combobox('setValue', '-型号-');}catch(e){};
									break;
							case 2: $("#TypeLevel3").combobox('setValue', '-小类-');
									try{$("#ObjectType").combobox('setValue', '-型号-');}catch(e){};
									break;
							case 3: try{$("#ObjectType").combobox('setValue', '-型号-');}catch(e){};
									break;
							case 4:break;
						}
					}
					
					
					
					
					function FillCountryChange(Value,Level,InterfaceType)
					{
						var newValue=Value;
						if(typeof(Value)=="string")
						{
							if(Value.substring(0,1)=="-")
							{
								return;
							}
						}
						var CountryURL='';
						var ProvideURL='';
						var CityURL='';
						var CountryParam='';
						var ProvideParam='';
						var CityParam='';
						if(InterfaceType!="LPY")
						{
							CountryURL='/appPlatform/getCountryList.html';
							CountryParam='';
							ProvideURL='/appPlatform/getProvinceList.html';
							ProvideParam='countryId='+newValue;
							CityURL='/appPlatform/getCityList.html';
							CityParam='provinceId='+newValue;
						}else
						{
							CountryURL='/PSDLProject/country.action';
							CountryParam='';
							ProvideURL='/PSDLProject/province.action';
							ProvideParam='countryId='+newValue;
							CityURL='/PSDLProject/city.action';
							CityParam='provinceId='+newValue;
						}
							switch(Level)
							{
								case 0:fillcombobox(CountryURL,CountryParam,'Country',function(){FillDefault(Level);});
									break;//INIT
								case 1:fillcombobox(ProvideURL,ProvideParam,'Prov',function(){FillDefault(Level);});
									break;//ON BIG Type Change
								case 2:fillcombobox(CityURL,CityParam,'City',function(){FillDefault(Level);});
									break;//ON MID Type Change
								case 3://fillcombobox(BigLevelURL,BigParam,'TypeLevel1',function(){FillDefault(Level);});
									break;//ON SMAILL TYPE Change
							}
					}
					function FillCountryDefault(Level)
					{
						switch(Level)
						{
							case 0:$('#Country').combobox('setValue', '-国家-');
                                   $('#Prov').combobox('setValue', '-省份-');
                                   $('#City').combobox('setValue', '-县市-');
									break;//INIT;
							case 1:$('#Prov').combobox('setValue', '-省份-');
                                   $('#City').combobox('setValue', '-县市-');
									break;//Country
							case 2:$('#City').combobox('setValue', '-县市-');
									break;//Province
							case 3:break;//City
						}
					}