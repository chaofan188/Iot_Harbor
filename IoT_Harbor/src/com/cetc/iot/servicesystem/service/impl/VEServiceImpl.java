package com.cetc.iot.servicesystem.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cetc.iot.database.dao.TemplateDao;
import com.cetc.iot.database.dao.VEDao;
import com.cetc.iot.database.dao.VeGeoLocationDao;
import com.cetc.iot.database.dao.VeKeyConfigDao;
import com.cetc.iot.database.dao.VePeBindDao;
import com.cetc.iot.database.dao.VeWebsocketServiceDao;
import com.cetc.iot.database.model.VE;
import com.cetc.iot.database.model.VEAndTplInfo;
import com.cetc.iot.database.model.VETemplate;
import com.cetc.iot.database.model.VeGeoLocation;
import com.cetc.iot.database.model.VeKeyConfig;
import com.cetc.iot.database.model.VePeBind;
import com.cetc.iot.database.model.VeWebsocketService;
import com.cetc.iot.servicesystem.service.VETemplateService;
import com.cetc.iot.servicesystem.service.VePeBindService;
import com.cetc.iot.servicesystem.service.VEService;
import com.cetc.iot.servicesystem.util.BuildIDUtil;
import com.cetc.iot.servicesystem.util.CreateThread;
import com.cetc.iot.servicesystem.util.VeServiceKey;
import com.cetc.iot.servicesystem.util.VeStateChangedManagement;


@Service("veService")
@Transactional
public class VEServiceImpl implements VEService{
	@Autowired
	private VEDao veDao;
	@Autowired
	private VeGeoLocationDao veGeoLocationDao;
	@Autowired
	private TemplateDao tplDao;
	@Autowired
	private VePeBindService bs;
	@Autowired
	private VETemplateService vETemplateService;
	@Autowired
	private VeWebsocketServiceDao veWebsocketServiceDao;
	@Autowired
	private VeKeyConfigDao veKeyConfigDao;
	@Autowired
	private VePeBindDao bindDao;
	
	@Override
	public List<VE> query(VE ve,int page,int size) {
		//String veid = ve.getVe_id();
		
		return veDao.query(ve,page,size);
	}
	/**
	 * 根据VeId删除
	 */
	@Override
	@Transactional(readOnly = false)
	public String delete(String veId) {
		
		VE ve = new VE();
		ve.setVe_id(veId);
		int i = queryCOUNTNUM(ve);
		if(i!=1){
			System.out.println("VE is not find!");
			return "success";
		}
		VeGeoLocation veGeoLocation = new 	VeGeoLocation();
		VeKeyConfig veKeyConfig = new VeKeyConfig();
		VePeBind vePeBind = new VePeBind();
		
		ve.setVe_id(veId);
		veGeoLocation.setVe_id(veId);
		veKeyConfig.setVe_id(veId);
		vePeBind.setVe_id(veId);
		
		//对Ve相关的表进行删除
		if("success".equals(veDao.delete(ve))&&
				"success".equals(veGeoLocationDao.delete(veGeoLocation))&&
				"success".equals(veKeyConfigDao.delete(veKeyConfig))&&
				"success".equals(bindDao.delete(vePeBind))){
			return "success";
		}else {
			return "fail";
		}
		
	}

	@Override
	//veGeoLocation无所谓空不空
	public String add( VE ve ,  VeGeoLocation veGeoLocation) {
		
		String veid="";
		if(ve.getVe_id()!=null){
			veid = ve.getVe_id();
		}else{
			 veid = BuildIDUtil.build("ve");
		}
		String veGeLocation =BuildIDUtil.build("veGeoLocation"); 
		ve.setVe_id(veid);
		if(veGeoLocation != null){
			veGeoLocation.setVe_id(veid);
			veGeoLocation.setGeolocation_id(veGeLocation);
			//这处以后要改！！！！！向TNS注册VE，此处应该写在VE管理（调veservice）的代码里，
			//2015-2-10还没有VE管理代码，所以先写在这里
			//从这开始！！！！！！！！！
			
			//Send2TNS.send2TNS(ve, veGeoLocation);
			
			Thread thread = new Thread(new CreateThread(ve,veGeoLocation));
			thread.start();
			//从这结束！！！！！！！！
			return (veDao.add(ve).equals("success") && veGeoLocationDao.add(veGeoLocation).equals("success"))==true?"success":"fail" ;
		}
		//这处和上个场景一样，当有VE管理代码时，此处要删除
		//从这开始！！！！！！！！！
		veGeoLocation=null;
		//Send2TNS.send2TNS(ve, veGeoLocation);
		/*SearchClient sc = new SearchClient();
		try {
			sc.registerVE(ve, veGeoLocation);
		} catch (ElasticsearchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		//从这结束！！！！！！！！
		
		//生成VE的服务的密钥
		String baseKey = null;
		String templateId = ve.getTemplate_id();
		
		VeWebsocketService vews = new VeWebsocketService();
		vews.setTemplate_id(templateId);
		VeKeyConfig veKeyConfig = new VeKeyConfig();
		
		List<VeWebsocketService> list= veWebsocketServiceDao.query(vews, 1, 1000);
		
		for(int i=0;i<list.size();i++){
			veKeyConfig.setService_id(list.get(i).getService_id());
			veKeyConfig.setVe_id(veid);
			baseKey = VeServiceKey.getKey();
			veKeyConfig.setBase_key(baseKey);
			String result = veKeyConfigDao.add(veKeyConfig);
			if(!result.equals("success")){
				break;
			}
		}
		
		
		return veDao.add(ve);
	}

	@Override
	public String update(VE ve,VeGeoLocation veGeoLocation) {
		
		// TODO Auto-generated method stub
		if(veGeoLocation!=null){
			return (veDao.update(ve).equals("success") && veGeoLocationDao.update(veGeoLocation).equals("success"))==true?"success":"fail" ;
		}
		return veDao.update(ve);
	}
	
	@Override
	public int queryCOUNTNUM(VE ve) {
		return veDao.queryCOUNTNUM(ve);
	}
	@Override
	public VE getVeByVeId(String veid){
		VE ve = new VE();
		ve.setVe_id(veid);
		List<VE> list = query(ve,0,1);
	 return	list.size()==0?null:list.get(0);
	}
	@Override
	public List<VE> getVeByTemplateId(String templateId){
		VE ve = new VE();
		ve.setTemplate_id(templateId);
	 return	query(ve,0,queryCOUNTNUM(ve));
	}
	@Override
	public String updateState(String veid,String newState){
		if(VeStateChangedManagement.CheckVeStateChangedIsReasonable(veid, newState)){
			//拼接ve对象
			VE ve = new VE();
			ve.setVe_id(veid);
			ve.setVe_state(newState);
			if( update(ve, null).equals("success")){
				if(VeStateChangedManagement.checkVeIsInUse(veid)){
					VeStateChangedManagement.shutDownVeService(veid);
				}
				return "success";
			}
			else
				return "fail";
		}else 
			 return "fail";
	}

	@Override
	public List<VEAndTplInfo> getVEAndTplInfoByCreatorID(String userId) {
		List<VEAndTplInfo> list = new ArrayList<VEAndTplInfo>();
		VE ve = new VE();
		ve.setVe_creator_id(userId);
		int lineNum = queryCOUNTNUM(ve);
		List<VE> vePart = query(ve,0,lineNum);
		for(int i=0;i<vePart.size();i++){
			VEAndTplInfo veAndTplInfo = new VEAndTplInfo();
			veAndTplInfo.setVe(vePart.get(i));
			VETemplate vetemplate = new VETemplate();  
			vetemplate.setTemplate_id(vePart.get(i).getTemplate_id());
			VETemplate vetemplatePart = vETemplateService.query(vetemplate, 0, 1).get(0);
			veAndTplInfo.setVetemplate(vetemplatePart);
			list.add(veAndTplInfo);
		}
		return list;
	}
	@Override
	public List<Map<String, Object>> queryALLVeForMap() {
		return	veDao.queryALLVeForMap();
	}
	
}
