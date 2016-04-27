package com.cetc.iot.accesssystem.downinterface.protocol.base.service;

import com.cetc.iot.database.model.PeProtocolConfig;

public interface PeProtocolConfigService {
	public PeProtocolConfig query(String peID);
	public String add(PeProtocolConfig peProtocolConfig);
	public String update(PeProtocolConfig peProtocolConfig);
	public String delete(PeProtocolConfig peProtocolConfig);
	public String delete(String peID);
}
