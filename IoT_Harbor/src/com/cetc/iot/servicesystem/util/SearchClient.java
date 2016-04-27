package com.cetc.iot.servicesystem.util;

import java.io.IOException;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;

import com.cetc.iot.database.model.VE;
import com.cetc.iot.database.model.VeGeoLocation;


public class SearchClient {

	@Test
	public void registerVE(VE ve,VeGeoLocation vegeo) throws ElasticsearchException, IOException  {
		String TNSIP = GetProperty.getValue("TNSIP");
		int TNSPORT = Integer.parseInt(GetProperty.getValue("TNSPORT"));
		
		String harborName = GetProperty.getValue("harborName");
		String veid = harborName + "/" +ve.getVe_id();
		String deviceID = ve.getVe_id();
		Settings settings = ImmutableSettings.settingsBuilder()  
				.put("client.transport.sniff", true)  
				.put("cluster.name","vsc")  
				.build();  
		Client client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress(TNSIP, TNSPORT)); 
	
		IndexResponse response = client.prepareIndex("ve", "typev")  
				.setSource(
						XContentFactory.jsonBuilder().startObject()
						.startObject("Coordinate").field("lat",vegeo.getLatitude()).field("lon",vegeo.getLongitude()).endObject()
						.field("VEID", veid)
						.field("deviceID", deviceID)
						.field("VEName",ve.getVe_name())
						.field("TypeID","18")
						.field("Geology",vegeo.getOtherInfo())
						.field("Description",ve.getVe_description())
						.field("VEState",ve.getVe_state())
						.field("ServiceType","1")
						.field("Owner",ve.getVe_creator_id())
						.field("Producer","18")
						.field("VEPrivacy",ve.getVe_privacy())
						.endObject()
						)  
				.execute()
				.actionGet();  
		System.out.println(response.getId());  
	}
}

