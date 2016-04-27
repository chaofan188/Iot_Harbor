package com.cetc.iot.accesssystem.downinterface.socket.service;



import java.util.List;

import com.cetc.iot.database.model.Pe;

public interface SocketPeService {
	public List<Pe> getAllPe();
	public Pe getPeByPeID(String peID);
	public String updatePe(Pe pe);
	public String deletePe(String peID);
	public void closeAll(List<Pe> list);
}
