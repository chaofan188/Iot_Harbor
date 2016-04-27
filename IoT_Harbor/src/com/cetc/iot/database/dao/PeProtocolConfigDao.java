package com.cetc.iot.database.dao;

import com.cetc.iot.database.model.PeProtocolConfig;

public interface PeProtocolConfigDao {
	public PeProtocolConfig query(String peID);
	public String add(PeProtocolConfig peProtocolConfig);
	public String update(PeProtocolConfig peProtocolConfig);
	public String delete(PeProtocolConfig peProtocolConfig);
	public String delete(String peID);
}
