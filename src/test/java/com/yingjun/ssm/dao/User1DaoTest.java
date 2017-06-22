package com.yingjun.ssm.dao;


import java.util.List;


import com.yingjun.ssm.user.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yingjun.ssm.entity.User;

/**
 * 
 * @author yingjun
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-dao.xml")
public class User1DaoTest {

    @Autowired
    private UserDao userDao;
	
	@Test
	public void testQueryById() {
		User user=userDao.queryByPhone("22222222222");
		System.out.println(user.toString());
		System.out.println("--------------------------");
	}

	@Test
	public void testQueryAll() {
		List<User> list=userDao.queryAll(0, 100);
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testAddScore() {
		userDao.addScore(10);
		List<User> list=userDao.queryAll(0, 100);
		for (User user : list) {
			System.out.println(user);
		}
	}

}
