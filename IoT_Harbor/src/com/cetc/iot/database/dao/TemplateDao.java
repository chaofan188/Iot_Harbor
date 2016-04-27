package com.cetc.iot.database.dao;

import java.util.*;


import com.cetc.iot.database.model.VETemplate;


public interface  TemplateDao {

	public List<VETemplate> query(VETemplate vetemplate, int page, int size);

	public String delete(VETemplate vetemplate);

	public String add(VETemplate vetemplate);

	public String update(VETemplate vetemplate);
}
