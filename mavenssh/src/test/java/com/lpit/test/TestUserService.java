package com.lpit.test;



import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lpit.myssh.service.IUserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring.xml"})
public class TestUserService {
	
	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	IUserService userService;
	
	@Test
	public void test1() {
		
		logger.info("执行测试方法！");
		userService.test();
	}
	

}
