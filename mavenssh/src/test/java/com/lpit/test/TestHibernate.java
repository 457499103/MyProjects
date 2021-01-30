package com.lpit.test;

import java.util.Date;
import java.util.UUID;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.lpit.myssh.entity.User;
import com.lpit.myssh.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring.xml","classpath:spring-hibernate.xml"})
public class TestHibernate {
	
	
/*	@Autowired
	private SessionFactory sessionfactory;

	

	public SessionFactory getSessionfactory() {
		return sessionfactory;
	}

	public void setSessionfactory(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}*/
	
	
	
	
	@Autowired
	IUserService userService;
	
	@Test
	public void test1() {
		
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setName("王翘楚");
		user.setPwd("123456");
		user.setCreaetime(new Date());
		
		
		userService.save(user);
		
	}
	
/*	@Test
	public void testSf() {
		
	   System.out.println(this.sessionfactory.getCurrentSession());
		
		
	}*/
	

}
