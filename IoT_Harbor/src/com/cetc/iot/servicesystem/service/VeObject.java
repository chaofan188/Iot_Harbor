package com.cetc.iot.servicesystem.service;

import java.util.ArrayList;
import java.util.List;
import com.cetc.iot.database.model.Pe;
import com.cetc.iot.database.model.VE;
import com.cetc.iot.database.model.VETemplate;
import com.cetc.iot.harbormanage.service.PEService;
import com.cetc.iot.servicesystem.util.GetBean;

/**
 * @author nci_my
 * VE模板的模型类，开发的VE模板都需要继承这个类，目前只需要重写其中的service方法。
 */
public abstract class VeObject{
	
	private ArrayList<Topic> topics;
	//this is the pe entity
	private Pe pe;
	
	private List<Pe> peList;
	
	private VE ve;
	
	private VETemplate veTemplate;
	
	private VEService vs = (VEService) GetBean.getBean("veService");
	private PEService ps = (PEService) GetBean.getBean("peService");
	private VePeBindService bs = (VePeBindService) GetBean.getBean("bindService");
	private VETemplateService vts = (VETemplateService) GetBean.getBean("veTplService");

	public VeObject(){
	
	}
	
	public void init(String veId){
	/*	
		Map<String,Object> veMap= vs.getVeByVeId(veId);
		ve.set
		List<Map<String, Object>> pl = bs.getBindInfoByVEID(veId);
		//List<Map>תList<E>
*/	}
	
	/**
	 * ��ø�VE��󶨵�PE��peid
	 * @return peid
	 */
	public List<String> getPeid(){
		List<String> list = new ArrayList<String>();
		for(int i =0;i<peList.size();i++){
			list.add(peList.get(i).getPeId());
		}
		return list;
	}
	
	/**
	 * 根据peid获取pe
	 * @param peid
	 * @return
	 */
	public Pe getPe(String peId){
		for(int i =0;i<peList.size();i++){
			if(peId == peList.get(i).getPeId()){
				return peList.get(i);
			}
		}
		return null;
	}
	
	/**
	 * ����peid����pe��ve��������
	 * @param peid
	 */
	public void setPeid(String peid){
		
	}
	/**
	 * ���VE��������Ϣ
	 * @return
	 */
	public VE getVE(){
		return ve;
	}
	/**
	 * ���VE��Ӧ��ģ���������Ϣ
	 * @return
	 */
	public VETemplate getVETemplate(){
		//��ø�ve��Ӧ��VEģ��ģ��
		return null;
	}
	/**
	 * ����VE����
	 * 
	 */
	public void destroy(){
		
	}
	/**
	 * ��ø�VE��PSDL����
	 * @return
	 */
	public String getPsdl(){
		return null;
	}

	/**
	 * ���VE����������б�
	 * @return
	 */
	public ArrayList<Topic> getTopics(){
		return this.topics;
	}
	
	/**
	 * ���VE�����һ������
	 * @param i
	 * @return
	 */
	public Topic getTopic(int i){
		return this.topics.get(i);
	}
	
	/**
	 * ΪVE���һ������
	 * @param t
	 */
	public void addTopic(Topic t){
		topics.add(t);
	}
	
	public void setVeState(String state){
		vs.updateState(ve.getVe_id(), state);
		ve.setVe_state(state);
	}
	
	/**
	 * �ɷ�����������ã����ڴ���һ��VE���󣬲���Ӧ
	 * @param req:用于VE模板的入参获取
	 * @param resp:用于VE模板的出参传递
	 * @return 
	 */
	public abstract void service(ServiceRequest req, ServiceResponse resp);
	public abstract void receive(String a);
	private void genEvent() {
		// TODO Auto-generated method stub
		
	}
	
	
}
