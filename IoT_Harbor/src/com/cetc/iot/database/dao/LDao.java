package com.cetc.iot.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.cetc.iot.database.dao.ConDB;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")

public class LDao {
	
    @Test
   	public void testcondb() throws SQLException {
   
    	//Class.forName("com.mysql.jdbc.Driver");
    	//Statement statement = null;
    	ConDB db = new ConDB();
		String url="jdbc:mysql://192.168.12.11:3306/iot_tss_db";
		String username="root";
		String password="111111";
		//Connection conn = db.getConnection(url, username, password);
		//插入接口封装，参数为连接名、表名、veid和value值，返回值是改变的行数。
		//cona.insert(url,username,password,"iot_db","666666","66666666666");		
		//查询接口的封装，参数是连接名和表名，查询结果是表内所有数据的集合
		ResultSet rs= db.queryAll(url,username,password,"iot_db");
		if(rs!=null){
			System.out.println("111111111111");
			while(rs.next()){  //  遍历文件
				System.out.println("22222222222222");
				String rsString=rs.getString(1);
				System.out.println(rsString);
				String rsString1=rs.getString(2);
				System.out.println(rsString1);
			}
			db.close();
		}else{
			System.out.println("rs is null");
		}
		
   	}
}





