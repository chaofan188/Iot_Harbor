// JavaScript Document

					function FillChange(Value,Level)
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
							BigLevelURL='/IoT_Harbor/type1.html';
							BigParam='';//'TypeLevel=1';
							MidLevelURL='/IoT_Harbor/type2.html';
							MidParam='TypeLevel1='+newValue;
							SmallLevelURL='/IoT_Harbor/type3.html';
							SmallParam='TypeLevel2='+newValue;
							OtherLevelURL='/IoT_Harbor/type.html';
							OtherParam='TypeLevel='+newValue;
							TypeLevelURL='/IoT_Harbor/type.html';
							TypeParam='TypeLevel='+newValue;
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
										fillcombobox(MidLevelURL,MidParam,'TypeLevel2',function(){
																														   FillDefault(Level);
																														});
									}catch(e){};
									break;//ON BIG Type Change
								case 2:
									try
									{
										fillcombobox(SmallLevelURL,SmallParam,'TypeLevel3',function(){
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
					
					
					
					
					function FillCountryChange(Value,Level)
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
						
							CountryURL='/appPlatform/getCountryList.html';
							CountryParam='';
							ProvideURL='/appPlatform/getProvinceList.html';
							ProvideParam='countryId='+newValue;
							CityURL='/appPlatform/getCityList.html';
							CityParam='provinceId='+newValue;
						
							switch(Level)
							{
								case 0:fillcombobox(CountryURL,CountryParam,'Country',function(){FillCountryDefault(Level);});
									break;//INIT
								case 1:fillcombobox(ProvideURL,ProvideParam,'Prov',function(){FillCountryDefault(Level);});
									break;//ON BIG Type Change
								case 2:fillcombobox(CityURL,CityParam,'City',function(){FillCountryDefault(Level);});
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