package com.cetc.iot.servicesystem.util;

import java.io.File;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
//import com.ve.bean.phy;

public class jerseyClient {

	public static void main(String[] args) {
		
		jerseyClient jc = new jerseyClient();
		//jc.queryCoordinate();
		//jc.queryID();
		//jc.queryProducer();
		//jc.queryRegion();
		//jc.queryType();
		jc.registerVE();
		//jc.registerVE();
		//jc.updateVE();
		//jc.queryDescription();
		//jc.updatePE();
		//jc.deletePE();
		//jc.deletePE();
	//	jc.deleteVE();
	//  jc.queryPeid();
		
	}
	
	public void queryType(){
		Client c = Client.create();
		WebResource r = c.resource("http://192.168.12.253:8080/Jersey/services/search");
		MultivaluedMap queryParams = new MultivaluedMapImpl();
		queryParams.add("type", "灯具");
		queryParams.add("size", "1");
		queryParams.add("page", "1");
		String s = r.path("type").queryParams(queryParams).get(String.class);
	    System.out.println(s);
	}

	public void queryRegion(){
		Client c = Client.create();
		WebResource r = c.resource("http://192.168.12.253:8080/Jersey/services/search");
		MultivaluedMap queryParams = new MultivaluedMapImpl();
		queryParams.add("co", "china");
	    queryParams.add("pr", "beijing");
		queryParams.add("ci", "beijing");
	    queryParams.add("ot", "nci");
		 
		String s = r.path("region").queryParams(queryParams).get(String.class);
	    System.out.println(s);
	}

	public void queryProducer(){
		Client c = Client.create();
		WebResource r = c.resource("http://192.168.12.253:8080/Jersey/services/search");
		MultivaluedMap queryParams = new MultivaluedMapImpl();
		queryParams.add("producer", "郎驰欣创科技有限公司");
		String s = r.path("producer").queryParams(queryParams).get(String.class);
	    System.out.println(s);
	}
	public void queryID(){
		Client c = Client.create();
		WebResource r = c.resource("http://192.168.12.253:8080/Jersey/services/search");
		MultivaluedMap queryParams = new MultivaluedMapImpl();
		queryParams.add("peid", "bit");
		String s = r.path("ID").queryParams(queryParams).get(String.class);
	    System.out.println(s);
	}
	public void queryCoordinate(){
		Client c = Client.create();
		WebResource r = c.resource("http://192.168.12.253:8080/Jersey/services/search");
		MultivaluedMap queryParams = new MultivaluedMapImpl();
		queryParams.add("la", "50");
		queryParams.add("lo", "50");
		queryParams.add("ro", "50000");
		String s = r.path("coordinate").queryParams(queryParams).get(String.class);
	    System.out.println(s);   
	    
	    
	}
	public void registerVE(){
		Client c = Client.create();
		WebResource r = c.resource("http://192.168.12.253:8080/Jersey/services/ve");
		System.out.println("sdfasdf");
		String s = r.type(MediaType.APPLICATION_XML).entity( new File("E:/6.xml")).post(String.class);
		System.out.println(s);
	}
	public void updateVE(){
		Client c = Client.create();
		WebResource r = c.resource("http://192.168.12.253:8080/Jersey/services/ve");
		String s = r.type(MediaType.APPLICATION_XML).entity( new File("E:/1.xml")).put(String.class);

	}
	public void updatePE(){
		Client c = Client.create();
		WebResource r = c.resource("http://192.168.12.253:8080/Jersey/services/pe");
		String s = r.type(MediaType.APPLICATION_XML).entity( new File("E:/2.xml")).put(String.class);

	}
	public void queryDescription(){
		Client c = Client.create();
		WebResource r = c.resource("http://192.168.12.253:8080/Jersey/services/search");
		MultivaluedMap queryParams = new MultivaluedMapImpl();
		
		queryParams.add("param1","类型");
		
		queryParams.add("size", "1");
		queryParams.add("page", "1");
		
		String s = r.path("description").queryParams(queryParams).get(String.class);
	    System.out.println(s);
	}
	public void deletePE(){
		Client c = Client.create();
		WebResource r = c.resource("http://192.168.12.253:8080/Jersey/services/pe");
		ClientResponse response = r.path("133333").delete(ClientResponse.class);
	}
	public void deleteVE(){
		Client c = Client.create();
		WebResource r = c.resource("http://192.168.12.253:8080/Jersey/services/ve");
      
		String id = "iot_bj_cetc15:camera1";
		String s = r.path("delete").type(MediaType.TEXT_PLAIN).put(String.class,id);
		
	}
	public void queryPeid(){
		Client c = Client.create();
		WebResource r = c.resource("http://192.168.12.253:8080/Jersey/services/pe");
  
		String id = "10013030303030333535353535353535";
		String s = r.path("query").type(MediaType.TEXT_PLAIN).put(String.class,id);
		System.out.println(s);
	}
}
