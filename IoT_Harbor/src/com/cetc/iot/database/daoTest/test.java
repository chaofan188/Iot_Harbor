package com.cetc.iot.database.daoTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cetc.iot.database.dao.CityDao;
import com.cetc.iot.database.dao.ClassInformationDao;
import com.cetc.iot.database.dao.CountryDao;
import com.cetc.iot.database.dao.DatailDao;
import com.cetc.iot.database.dao.EzvizCameraDao;
import com.cetc.iot.database.dao.GatewayDao;
import com.cetc.iot.database.dao.OnvifCameraDao;
import com.cetc.iot.database.dao.PeDao;
import com.cetc.iot.database.dao.PeDataDao;
import com.cetc.iot.database.dao.PeGeolocationDao;
import com.cetc.iot.database.dao.PeInterfaceDao;
import com.cetc.iot.database.dao.PeInterfaceKeyDao;
import com.cetc.iot.database.dao.PeParamDao;
import com.cetc.iot.database.dao.PeTemplateAttributeDao;
import com.cetc.iot.database.dao.PeTemplateDao;
import com.cetc.iot.database.dao.PeTemplateParamDao;
import com.cetc.iot.database.dao.PeTemplateProtocolHttpDao;
import com.cetc.iot.database.dao.ProvinceDao;
import com.cetc.iot.database.dao.TemplateBindDao;
import com.cetc.iot.database.dao.VEDao;
import com.cetc.iot.database.dao.VeGeoLocationDao;
import com.cetc.iot.database.dao.VeKeyConfigDao;
import com.cetc.iot.database.dao.VePeBindDao;
import com.cetc.iot.database.dao.VeWebsocketServiceDao;
import com.cetc.iot.database.dao.VeWebsocketServiceParamDao;
import com.cetc.iot.database.model.City;
import com.cetc.iot.database.model.ClassInformation;
import com.cetc.iot.database.model.Country;
import com.cetc.iot.database.model.Datail;
import com.cetc.iot.database.model.EzvizCamera;
import com.cetc.iot.database.model.Gateway;
import com.cetc.iot.database.model.OnvifCamera;
import com.cetc.iot.database.model.Pe;
import com.cetc.iot.database.model.PeData;
import com.cetc.iot.database.model.PeGeolocation;
import com.cetc.iot.database.model.PeInterface;
import com.cetc.iot.database.model.PeInterfaceKey;
import com.cetc.iot.database.model.PeParam;
import com.cetc.iot.database.model.PeTemplate;
import com.cetc.iot.database.model.PeTemplateAttribute;
import com.cetc.iot.database.model.PeTemplateParam;
import com.cetc.iot.database.model.PeTemplateProtocolHttp;
import com.cetc.iot.database.model.Province;
import com.cetc.iot.database.model.TemplateBind;
import com.cetc.iot.database.model.VE;
import com.cetc.iot.database.model.VeGeoLocation;
import com.cetc.iot.database.model.VeKeyConfig;
import com.cetc.iot.database.model.VePeBind;
import com.cetc.iot.database.model.VeWebsocketService;
import com.cetc.iot.database.model.VeWebsocketServiceParam;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class test {

	@Autowired
	private CityDao cityDao;
	//
	@Autowired
	private ClassInformationDao classInformationDao;

	@Autowired
	private CountryDao countryDao;

	@Autowired
	private GatewayDao gatewayDao;

	@Autowired
	private PeDao peDao;

	@Autowired
	private PeDataDao peDataDao;

	@Autowired
	private PeGeolocationDao peGeolocationDao;

	@Autowired
	private PeInterfaceDao peInterfaceDao;

	@Autowired
	private PeInterfaceKeyDao peInterfaceKeyDao;

	@Autowired
	private PeParamDao peParamDao;

	@Autowired
	private PeTemplateAttributeDao peTemplateAttributeDao;

	@Autowired
	private PeTemplateDao peTemplateDao;

	@Autowired
	private PeTemplateParamDao peTemplateParamDao;

	@Autowired
	private PeTemplateProtocolHttpDao peTemplateProtocolHttpDao;

	@Autowired
	private ProvinceDao provinceDao;

	@Autowired
	private TemplateBindDao templateBindDao;

	@Autowired
	private VEDao veDao;

	@Autowired
	private VeGeoLocationDao veGeoLocationDao;

	@Autowired
	private VeKeyConfigDao veKeyConfigDao;

	@Autowired
	private VePeBindDao vePeBindDao;

	@Autowired
	private VeWebsocketServiceDao veWebsocketServiceDao;

	@Autowired
	private VeWebsocketServiceParamDao veWebsocketServiceParamDao;

	@Autowired
	private DatailDao datailDao;

	@Autowired
	private OnvifCameraDao onvifCameraDao;

	@Autowired
	private EzvizCameraDao ezvizCameraDao;

	String state;
	List<Map<String, Object>> message = new ArrayList<Map<String, Object>>();

	@Test
	public void testOnvifCamera() {

		OnvifCamera onvifCamera = new OnvifCamera();
		// onvifCamera.setInterface_id("lhy");
		// onvifCamera.setIpv4("192.167.111.111");
		// onvifCamera.setPassword("aaaaa");
		// onvifCamera.setPe_id("aaaaaaa");
		onvifCamera.setUsername("admin");
		// onvifCameraDao.add(onvifCamera);
		onvifCameraDao.query(onvifCamera, 1, 10);
	}

	@Test
	public void testCityDao() {
		City city = new City();
		city.setCityId(8);
		// city.setCityName("lhy");
		city.setProvinceId(2);
		// cityDao.add(city);
		// cityDao.delete(city);
		cityDao.query(city, 0, 10);

	}

	@Test
	public void testClassInformation() {

		ClassInformation classInformation = new ClassInformation();
		classInformation.setClassId("100000000001");
		classInformation.setClassName("bb");
		classInformation.setClassType("bb");
		classInformationDao.add(classInformation);
		classInformationDao.query(classInformation, 0, 10);
		classInformationDao.delete(classInformation);
		// classInformationDao.update(classInformation);
	}

	@Test
	public void testCountry() {

		Country country = new Country();
		country.setCountryName("asdfghjkl");
		country.setCountryId(6);
		countryDao.add(country);
		countryDao.query(country, 0, 10);
		countryDao.delete(country);
		// countryDao.update(country);
	}

	@Test
	public void testDatail() {

		Datail datail = new Datail();
		datail.setDatailId(7);
		datail.setDataId("1");
		datail.setDatailKey("3333");
		datail.setDatailValue("2");
		datailDao.add(datail);
		datailDao.query(datail, 0, 10);
		datailDao.delete(datail);
		// datailDao.update(datail);
	}

	@Test
	public void testEzvizCamera() {

		EzvizCamera ezvizCamera = new EzvizCamera();
		ezvizCamera.setCameraname("lhy");
		ezvizCamera.setInterface_id("1111");
		ezvizCamera.setKey("1111");
		ezvizCamera.setPe_id("1111111");
		ezvizCamera.setSecret("111111");
		ezvizCamera.setM3u8Url("1111111");
		// ezvizCameraDao.add(ezvizCamera);
		// ezvizCameraDao.query(ezvizCamera, 0, 10);
		ezvizCameraDao.delete(ezvizCamera);
	}

	@Test
	public void testGateway() {

		Gateway gateway = new Gateway();
		gateway.setGatewayId("1");
		gateway.setGatewayName("v");
		gateway.setGatewayUri("v");
		gateway.setGatewayState("v");
		gatewayDao.add(gateway);
		gatewayDao.query(gateway, 0, 10);
		gatewayDao.delete(gateway);
		// gatewayDao.update(gateway);
	}

	@Test
	public void testPe() {
		Pe pe = new Pe();
		Date date = new Date();
		pe.setPeId("a");
		pe.setPeName("b");
		pe.setPeOwner("a");
		pe.setPePictureUrl("a");
		pe.setPeLastTime(date);
		pe.setPeState(0);
		pe.setPeLifecycle(1);
		pe.setTemplateId("1");
		peDao.add(pe);
		peDao.query(pe, 0, 10);
		peDao.delete(pe);
		// peDao.update(pe);

	}

	@Test
	public void testPeData() {

		PeData peData = new PeData();
		peData.setDataContent("b");

		peData.setDataId("b");
		peData.setDatail(1);
		peData.setDataTime(new Date());
		peData.setPeId("1");
		peData.setPeInterfaceId("1");
		peDataDao.add(peData);
		peDataDao.query(peData, 0, 10);
		peDataDao.delete(peData);
		// peDataDao.update(peData);
	}

	@Test
	public void testPeGeolocation() {
		PeGeolocation geGeolocation = new PeGeolocation();
		geGeolocation.setCityId(0);
		geGeolocation.setCountryId(0);
		geGeolocation.setGeolocationId("1");
		geGeolocation.setPeId("2");
		geGeolocation.setProvinceId(0);
		peGeolocationDao.add(geGeolocation);
		peGeolocationDao.query(geGeolocation, 0, 10);
		peGeolocationDao.delete(geGeolocation);
		// peGeolocationDao.update(geGeolocation);
	}

	@Test
	public void testPeInterface() {

		PeInterface peInterface = new PeInterface();
		peInterface.setInterfaceChannel(0);
		peInterface.setInterfaceContent("1");
		peInterface.setInterfaceFashion(0);
		peInterface.setInterfaceId("1");
		peInterface.setInterfaceName("2");
		peInterface.setInterfaceRemark("1");
		peInterface.setInterfaceResponse(0);
		peInterface.setInterfaceType("asdsfdd");
		peInterface.setParameterType("1");
		peInterface.setResult("1");
		peInterface.setResultType("1");
		peInterface.setTemplateId("1");
		peInterfaceDao.add(peInterface);
		peInterfaceDao.query(peInterface, 0, 10);
		peInterfaceDao.delete(peInterface);
		peInterfaceDao.update(peInterface);

	}

	@Test
	public void testPeInterfaceKey() {

		PeInterfaceKey peInterfaceKey = new PeInterfaceKey();
		peInterfaceKey.setInterfaceID("111");
		peInterfaceKey.setInterfaceIDKey("111");
		peInterfaceKey.setPeID("111");
		peInterfaceKeyDao.add(peInterfaceKey);
		peInterfaceKeyDao.query("111", "111");
		peInterfaceKeyDao.delete("111");
	}

	@Test
	public void testPeParam() {

		PeParam peparam = new PeParam();
		peparam.setContent("111");
		peparam.setKey("111");
		peparam.setPe_id("111");
		peparam.setPe_param_id(30);
		peparam.setTemplate_param_id("1111");
		peParamDao.add(peparam);
		peParamDao.query(peparam, 0, 10);

	}

	@Test
	public void testPeTemplateAttribute() {

		PeTemplateAttribute peTemplateAttribute = new PeTemplateAttribute();
		peTemplateAttribute.setAttributeId("a");
		peTemplateAttribute.setAttributeValue("b");
		peTemplateAttribute.setAttributeKey("a");
		peTemplateAttribute.setAttributeUnit("a");
		peTemplateAttribute.setTemplateId("a"); //
		peTemplateAttributeDao.add(peTemplateAttribute);
		peTemplateAttributeDao.query(peTemplateAttribute, 0, 10);
		peTemplateAttributeDao.delete(peTemplateAttribute);
		// peTemplateAttributeDao.update(peTemplateAttribute);
	}

	@Test
	public void testPeTemplate() {

		PeTemplate peTemplate = new PeTemplate();
		peTemplate.setTemplateId("1");
		peTemplate.setTemplatePicUrl("aa");
		peTemplate.setTemplateModel("a");
		peTemplate.setTemplateName("2");
		peTemplate.setTemplateRemark("1");
		peTemplate.setTemplateTime(new Date());
		peTemplate.setTemplateType("1");
		peTemplateDao.add(peTemplate);
		peTemplateDao.query(peTemplate, 0, 10);
		peTemplateDao.delete(peTemplate);
		// peTemplateDao.update(peTemplate);
	}

	@Test
	public void testPeTemplateParam() {

		PeTemplateParam peTemplateParam = new PeTemplateParam();
		peTemplateParam.setDescription("aaaa");
		peTemplateParam.setKey("111");
		peTemplateParam.setParam_id(111);
		peTemplateParam.setTemplate_id("1111");
		peTemplateParam.setType(1111);
		peTemplateParam.setOption("111");
		peTemplateParam.setUnit("111");
		peTemplateParam.setValue_max("5");
		peTemplateParam.setValue_min("1");
		peTemplateParamDao.add(peTemplateParam);
		peTemplateParamDao.query(peTemplateParam, 0, 10);

	}

	@Test
	public void testPeTemplateProtocolHttp() {
		PeTemplateProtocolHttp peTemplateProtocolHttp = new PeTemplateProtocolHttp();
		peTemplateProtocolHttp.setId(11);
		peTemplateProtocolHttp.setInterface_id("111");
		peTemplateProtocolHttp.setRule("11");
		peTemplateProtocolHttp.setTemplate_id("11");
		peTemplateProtocolHttpDao.add(peTemplateProtocolHttp);
		peTemplateProtocolHttpDao.query(peTemplateProtocolHttp, 0, 10);
	}

	@Test
	public void testProvince() {

		Province province = new Province();
		province.setCountryId(35);
		province.setProvinceId(223);
		province.setProvinceName("n");
		provinceDao.add(province);
		provinceDao.query(province, 0, 10);
		provinceDao.delete(province);
		// provinceDao.update(province);

	}

	@Test
	public void testTemplateBind() {

		TemplateBind templateBind = new TemplateBind();
		templateBind.setBind_max(5);
		templateBind.setBind_min(1);
		templateBind.setBind_type("11");
		templateBind.setIdentify_id("22");
		templateBind.setPe_template_id("33");
		templateBind.setService_id("44");
		templateBind.setTemplate_bind_id("55");
		templateBind.setVe_template_id("66");
		// templateBindDao.add(templateBind);
		templateBindDao.query(templateBind, 0, 10);
		// templateBindDao.delete(templateBind);

	}

	@Test
	public void testVe() {
		VE ve = new VE();
		ve.setTemplate_id("11");
		ve.setVe_atom("22");
		ve.setVe_creator_id("33");
		ve.setVe_description("44");
		ve.setVe_id("55");
		ve.setVe_name("77");
		ve.setVe_privacy("88");
		ve.setVe_state("99");
		veDao.add(ve);
		veDao.query(ve, 0, 10);
		veDao.delete(ve);

	}

	@Test
	public void VeGeoLocationtest() {

		VeGeoLocation veGeoLocation = new VeGeoLocation();
		veGeoLocation.setAltitude(111);
		veGeoLocation.setCity_id(11);
		veGeoLocation.setCountry_id(22);
		veGeoLocation.setGeolocation_id("aa");
		veGeoLocation.setLatitude(44);
		veGeoLocation.setLongitude(66);
		veGeoLocation.setVe_id("11111");
		veGeoLocationDao.add(veGeoLocation);
		veGeoLocationDao.query(veGeoLocation, 0, 10);
		// veGeoLocationDao.delete(veGeoLocation);
	}

	@Test
	public void testVeKeyConfig() {

		VeKeyConfig veKeyConfig = new VeKeyConfig();
		veKeyConfig.setBase_key("111");
		// veKeyConfig.setChange_time(new Date());
		veKeyConfig.setKey_state("aaaaaaa");
		veKeyConfig.setService_id("88888888");
		veKeyConfig.setVe_id("99999999");
		veKeyConfigDao.add(veKeyConfig);
		veKeyConfigDao.query(veKeyConfig, 0, 10);
	}

	@Test
	public void testVePeBind() {
		VePeBind vePeBind = new VePeBind();
		vePeBind.setBind_id("aaaaaa");
		vePeBind.setBind_type("bbbbb");
		vePeBind.setIdentify_id("CCCCC");
		vePeBind.setPe_id("dddddd");
		vePeBind.setService_id("eeeee");
		vePeBind.setState("ggggg");
		vePeBind.setVe_id("jjjjj");
		vePeBindDao.add(vePeBind);
		vePeBindDao.query(vePeBind, 0, 10);
	}

	@Test
	public void testVeWebsocketService() {
		VeWebsocketService veWebsocketService = new VeWebsocketService();
		veWebsocketService.setService_description("GGGGGGGGG");
		veWebsocketService.setService_id("RRRRRRRR");
		veWebsocketService.setService_name("LLLLLLLL");
		veWebsocketService.setTemplate_id("OOOOOOO");
		veWebsocketServiceDao.add(veWebsocketService);
		veWebsocketServiceDao.query(veWebsocketService, 0, 10);
	}

	@Test
	public void testVeWebsocketServiceParam() {
		VeWebsocketServiceParam veWebsocketServiceParam = new VeWebsocketServiceParam();
		veWebsocketServiceParam.setParam_id("FFFFFFF");
		veWebsocketServiceParam.setParam_name("FFFFFFFF");
		veWebsocketServiceParam.setParam_type("FFFFFFF");
		veWebsocketServiceParam.setParam_value("FFFFFFF");
		veWebsocketServiceParam.setService_id("FFFFFFFF");
		veWebsocketServiceParamDao.add(veWebsocketServiceParam);
		veWebsocketServiceParamDao.query(veWebsocketServiceParam, 0, 10);

	}

	/*
	 * public static void main(String[] agrs) { test t = new test(); try { //
	 * t.testCity(); t.testOnvifCamera(); } catch (Exception e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 */
	/*
	 */
}
