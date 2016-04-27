package com.cetc.iot.database.model;

import java.io.Serializable;

public class VEAndTplInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VE ve;
	private VETemplate vetemplate;
	public VE getVe() {
		return ve;
	}
	public void setVe(VE ve) {
		this.ve = ve;
	}
	public VETemplate getVetemplate() {
		return vetemplate;
	}
	public void setVetemplate(VETemplate vetemplate) {
		this.vetemplate = vetemplate;
	}
	
}
