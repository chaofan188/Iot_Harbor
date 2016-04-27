package com.cetc.iot.database.dao;

import java.sql.*;
import java.util.Iterator;
import java.util.Map;

public class ConDB {
	 Connection conn=null;
	 ResultSet rs=null;
	 Statement statement = null;
	 /**
	  * this function is used to insert the data from device into your database
	  *  
	  * @param url   database url
	  * @param username  database username
	  * @param password  database password
	  * @param tableName  the table name you want to insert
	  * @param map  the map contains your the structure and data in your database       
	  * 
	  * @return
	  */
	 public boolean insert(String url,String username,String password,String tabName,Map<String,Object> map)
	 {      conn = getConnection(url, username, password);
		    int rowNum = 0;
		    try {
				statement = conn.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    Iterator<String> keyIter = map.keySet().iterator();
		   
		    String sqlStr = "";
			String sqlStr1 = "";
			String sqlStr2 = "";
			sqlStr1 = "insert into "+tabName+"(";
			while(keyIter.hasNext()){
				String key  = keyIter.next();
				Object value = map.get(key);

					sqlStr1 += key+",";
					sqlStr2 += "'" + value + "',";
				
			}
			if(sqlStr1.charAt(sqlStr1.length()-1)==','){
				sqlStr1 = sqlStr1.substring(0,sqlStr1.length()-1);
			}
			if(sqlStr2.charAt(sqlStr2.length()-1)==','){
				sqlStr2 = sqlStr2.substring(0,sqlStr2.length()-1);
			}
			
			sqlStr1 += ") values (";
			sqlStr2 += ")";
			sqlStr = sqlStr1 + sqlStr2;
		    System.out.println("SQL in vetemplate:"+sqlStr);
		    
			try {
				rowNum = statement.executeUpdate(sqlStr);
				close();
				if(rowNum==1){
					return true;
				}else{
					return false;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
    }
	 /**
	  * this function is used to delete the data
	  * @param url
	  * @param username
	  * @param password
	  * @param tabName
	  * @param map
	  * @return
	  */
	public boolean delete(String url,String username,String password,String tableName,Map<String,Object> map){
		conn = getConnection(url, username, password);
		int rowNum = 0;
		try {
			statement = conn.createStatement();

			Iterator<String> keyIter = map.keySet().iterator();

			StringBuffer sb = new StringBuffer();
			sb.append("delete from ");
			sb.append(tableName);
			sb.append(" where 1=1");

			while(keyIter.hasNext()){
				String key = keyIter.next();
				Object value = map.get(key);
				sb.append(" and "+key+"= '"+value+"'");
			}
			String sql = sb.toString();
			System.out.println(sql);
			
			rowNum = statement.executeUpdate(sql);
			close();
			if(rowNum>0){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * this function is used to update
	 * @param url
	 * @param username
	 * @param password
	 * @param tabName
	 * @param map
	 * @param condition
	 * @return
	 */
	public boolean update(String url,String username,String password,String tableName,Map<String,Object> map,Map<String,Object> condition){
		conn = getConnection(url, username, password);
		int rowNum = 0;
		try {
			statement = conn.createStatement();
			Iterator<String> keyIter1 = map.keySet().iterator();

			StringBuffer sb = new StringBuffer();
			sb.append("update ").append(tableName).append(" set ");
			while(keyIter1.hasNext()){
				String key = keyIter1.next();
				Object value = map.get(key);
				if(keyIter1.hasNext()){
					sb.append(key).append(" = '").append(value).append("',");
				}else{
					sb.append(key).append(" = '").append(value).append("'");
				}
			}
			
			if(condition.size() != 0){
				sb.append(" where ");
				Iterator<String> keyIter2 = condition.keySet().iterator();
				while(keyIter2.hasNext()){
					String key  = keyIter2.next();
					Object value = condition.get(key);
					if(keyIter2.hasNext()){
						sb.append(key).append(" = ");
						sb.append("'").append(value).append("'");
						sb.append(" and ");
					}else{
						sb.append(key).append(" = ");
						sb.append("'").append(value).append("'");
					}
				}
			}

			String sql = sb.toString();
			System.out.println(sql);
			rowNum = statement.executeUpdate(sql);
			close();
			if(rowNum>0){
				return true;
			}else{
				return false;
			}


		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * this function is used to select info from your database
	 * @param url   database url
	 * @param username  database username
	 * @param password  database password
	 * @param tableName  the table name you want to select
	 * @return  the ResultSet you select
	 */
	 public ResultSet queryAll(String url,String username,String password,String tableName) {
		    try {
		    	 conn = getConnection(url, username, password);
		    	
				 statement = conn.createStatement();
		         String sqls="select * from "+ tableName +" where 1=1";
				 rs = statement.executeQuery(sqls);
				 if(rs!=null){
		    		 System.out.println("rs is not null");
		    	 }
				 //close();
				 return rs;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rs;
			
		}
	public ResultSet queryWithCondition(String url,String username,String password,String tableName,Map<String,Object> map) {
	    try {
	    	 conn = getConnection(url, username, password);
	    	
			 statement = conn.createStatement();
	        // String sqls="select * from "+ tableName +" where 1=1";
			 StringBuffer sb = new StringBuffer();
			 sb.append("select * from "+ tableName +" where 1=1");
			 Iterator<String> keyInter = map.keySet().iterator();
			 while(keyInter.hasNext()){
				 String key = keyInter.next();
				 Object value = map.get(key);
				 sb.append(" and "+key+"= '"+value+"'");
			 }
			 String sql = sb.toString();
			 System.out.println(sql);
			 rs = statement.executeQuery(sql);
			 if(rs!=null){
	    		 System.out.println("rs is not null");
	    	 }
			 //close();
			 return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
		
	}
	@SuppressWarnings("finally")
	public  Connection getConnection(String url,String username,String password)
	{
		try
       {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			return conn ;
		}
	}    
	public boolean close(){  
        boolean isClose = false;  
        if(rs!=null){  
            try {  
            	rs.close();  
            	rs=null;  
            	isClose=true;  
            } catch (SQLException e) {  
            	isClose=false;  
                e.printStackTrace();  
                System.out.println("关闭结果集发生错误");  
            }  
        }  
        if(statement!=null){  
            try {  
            	statement.close();  
            	statement=null;  
                isClose=true;  
            } catch (SQLException e) {  
            	isClose=false;  
                e.printStackTrace();  
                System.out.println("关闭statement发生异常");  
            }  
        }  
        if(conn!=null){  
            try{  
                conn.close();  
                conn=null;  
                isClose=true;  
            }catch (Exception e) {  
            	isClose=false;  
                e.printStackTrace();  
                System.out.println("关闭conn发生异常");  
            }  
        }  
        return isClose;  
    }  
}
