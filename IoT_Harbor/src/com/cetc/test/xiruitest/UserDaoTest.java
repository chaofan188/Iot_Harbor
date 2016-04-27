package com.cetc.test.xiruitest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cetc.iot.database.dao.UserDao;
import com.cetc.iot.database.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserDaoTest {
	@Autowired
	private UserDao userDao;
	@Test
	public void test(){
		User user = new User();
		user.setUserName("haha");
		user.setUserPassword("123");
		String result = userDao.add(user);
		System.out.println("result: "+result);
	}
//	public void main(String[] args){
//		User user = new User();
//		user.setUserName("haha");
//		user.setUserPassword("123");
//		String result = userDao.add(user);
//		System.out.println("result: "+result);
//	}
}
