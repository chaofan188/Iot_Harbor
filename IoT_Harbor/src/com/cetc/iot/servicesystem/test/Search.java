package com.cetc.iot.servicesystem.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.cetc.iot.database.dao.ConDB;
import com.cetc.iot.servicesystem.service.ServiceRequest;
import com.cetc.iot.servicesystem.service.ServiceResponse;
import com.cetc.iot.servicesystem.service.Topic;
import com.cetc.iot.servicesystem.service.VeObject;
import com.cetc.iot.servicesystem.service.impl.CallCenterSession;
import com.cetc.iot.servicesystem.util.DataLoopReceiverNew;

public class Search extends VeObject
{
	
	private String clientId;
	private String veId;
	private String peId;
	private String search_key;

	@Override
	public void service(ServiceRequest arg0, ServiceResponse arg1) 
	{
		
		clientId = arg0.getParameter("clientId").toString();
		veId = arg0.getParameter("veId").toString();
		String serviceName = arg0.getCtrl();
		JSONObject param = (JSONObject) arg0.getParameter("param");
		
		peId = (String)param.get("count_act");////XML中的identifyID
		search_key = (String)param.get("search_key");
		String from_time_str_temp = (String)param.get("from_time");
		String to_time_str_temp = (String)param.get("to_time");
		String from_time_str = from_time_str_temp  +" "+"00:00:00";
		String to_time_str = to_time_str_temp  +" "+"00:00:00";
		JSONArray array = new JSONArray();
		
		System.out.println(from_time_str);
		System.out.println(to_time_str);
		
		Timestamp from_time = Timestamp.valueOf(from_time_str);
		Timestamp to_time = Timestamp.valueOf(to_time_str);

		if("search_count".equals(serviceName))
		{
			
			System.out.println("===============================");
		
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://127.0.0.1:3306/supplies?characterEncoding=UTF-8";
			String username = "root";
			String password = "root";
			
			
			System.out.println("正在加载MySQL 驱动程序...");
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException ex) {
				System.out.println("加载MySQL 驱动程序错误...");
			}
			System.out.println("成功加载MySQL 驱动程序");
			Connection con = null;
			try {
				System.out.println("正在连接MySQL 数据库...");
				con = DriverManager.getConnection(url, username, password);
				System.out.println("已连接MySQL 数据库");
			} catch (Exception e) {
				System.out.println("连接MySQL 数据库错误");
			}
			
			if("enter".equals(search_key)){
				String sqlStr = "select * from enter_warehouse";
				try 
				{
				  PreparedStatement pstat = con.prepareStatement(sqlStr);
				  ResultSet rs=pstat.executeQuery();
				  
				  while(rs.next()){
					  if(from_time.compareTo(rs.getTimestamp("enter_time"))<=0 && to_time.compareTo(rs.getTimestamp("enter_time"))>=0){		  
						  resultSetToJson(rs,array);
					  }
				  }
				  System.out.println(array);
				  rs.close();       //关闭结果集
				  pstat.close();   //关闭sql语句
				 }catch(SQLException e)
		    	   {
		    		  e.printStackTrace(); 
		    	   }
		    	      finally {
		    			   if(con!=null)
		    			   {
		    				   try{
		    						con.close();
		    					}catch (SQLException e){
		    						System.out.println("关闭MySQL 数据库错误");
		    					}
		    			   }
		    		}//end(finally)
		        }//endif(enter)
			
			if("leave".equals(search_key)){
				String sqlStr = "select * from leave_warehouse";
				try 
				{
				  PreparedStatement pstat = con.prepareStatement(sqlStr);
				  ResultSet rs=pstat.executeQuery();
				  
				  while(rs.next()){
					  if(from_time.compareTo(rs.getTimestamp("leave_time"))<=0 && to_time.compareTo(rs.getTimestamp("leave_time"))>=0){		  
						  resultSetToJson(rs,array);
					  }
				  }
				  System.out.println(array);
				  rs.close();       //关闭结果集
				  pstat.close();   //关闭sql语句
				 }catch(SQLException e)
		    	   {
		    		  e.printStackTrace(); 
		    	   }
		    	      finally {
		    			   if(con!=null)
		    			   {
		    				   try{
		    						con.close();
		    					}catch (SQLException e){
		    						System.out.println("关闭MySQL 数据库错误");
		    					}
		    			   }
		    		}//end(finally)
		        }//endif(enter)
			
			if("storage".equals(search_key)){
				String sqlStr = "select * from storage_warehouse";
				try 
				{
				  PreparedStatement pstat = con.prepareStatement(sqlStr);
				  ResultSet rs=pstat.executeQuery();
				  
				  while(rs.next()){
						  resultSetToJson(rs,array);
				  }
				  System.out.println(array);
				  rs.close();       //关闭结果集
				  pstat.close();   //关闭sql语句
				 }catch(SQLException e)
		    	   {
		    		  e.printStackTrace(); 
		    	   }
		    	      finally {
		    			   if(con!=null)
		    			   {
		    				   try{
		    						con.close();
		    					}catch (SQLException e){
		    						System.out.println("关闭MySQL 数据库错误");
		    					}
		    			   }
		    		}//end(finally)
		        }//endif(enter)
			
			
			}//endif(servicename)
			
			
			
			
			String up_data = array.toString();
			
			System.out.println("++++++++++++++updata++++++++++++++");
			System.out.println(up_data);
			System.out.println("++++++++++++++updata++++++++++++++");
			
			CallCenterSession.send(clientId, up_data);
	}
		
	
	
	
	public  void resultSetToJson(ResultSet rs,JSONArray array) throws SQLException,JSONException
	{
		
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		
		JSONObject jsonObj = new JSONObject();
		for( int i = 1;i <= columnCount;i ++)
		{
			String columnName = metaData.getColumnLabel(i);
			String value = rs.getString(columnName);
			jsonObj.put(columnName, value);
		}
		array.add(jsonObj);
	}
	
	@Override
	public void receive(String json) {
		
	}
}


