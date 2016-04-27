package com.cetc.iot.servicesystem.util;

import java.io.IOException;

import org.elasticsearch.ElasticsearchException;

import com.cetc.iot.database.model.VE;
import com.cetc.iot.database.model.VeGeoLocation;

public class CreateThread implements Runnable {
	private VE ve;
	private VeGeoLocation veGeoLocation;
    public CreateThread(VE ve, VeGeoLocation veGeoLocation){
    	this.ve = ve;
    	this.veGeoLocation = veGeoLocation;
    }
   public void run(){
	   SearchClient sc = new SearchClient();
		try {
			sc.registerVE(ve, veGeoLocation);
		} catch (ElasticsearchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
   }
}
