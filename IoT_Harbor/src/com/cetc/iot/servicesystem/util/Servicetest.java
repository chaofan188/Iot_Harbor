package com.cetc.iot.servicesystem.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cetc.iot.database.model.VETemplate;
import com.cetc.iot.servicesystem.service.VEService;
import com.cetc.iot.servicesystem.service.VETemplateService;
@Component
public class Servicetest {

	@Autowired
	private VEService veService;
	@Autowired
	private static VETemplateService vts;
	
	/*public List<Map<String, Object>> query(VETemplate veTemplate, int page,
			int size) {
		
		return vts.query(veTemplate, page, size);
	}*/
	public static void main(String [] args){
		Servicetest test = new Servicetest();
		
		VETemplate vet = new VETemplate();
		/*vet.setTemplate_id(null);
		vet.setTemplate_openness(null);
		vet.setTpl_classpath(null);
		vet.setTpl_description(null);
		vet.setTpl_developer(null);
		vet.setTpl_display(null);
		vet.setTpl_enroll_time(null);
		vet.setTpl_icon(null);
		vet.setTpl_id(null);
		vet.setTpl_name(null);
		vet.setTpl_owner(null);
		vet.setTpl_state(null);
		vet.setTpl_type_id(null);
		vet.setTpl_version(null);*/
		
		//int a = test.query(vet, 0, 100).size();
		//System.out.println("a:"+a);
	}
}
