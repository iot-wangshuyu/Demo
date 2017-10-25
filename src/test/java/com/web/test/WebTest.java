package com.web.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shuyu.activemq.service.ActiveMqService;
import com.shuyu.controller.TestController;
import com.shuyu.entity.User;
//配置spring和Junit整合，Junit启动是加载springIOC容器
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml","classpath:activemq.xml"})
public class WebTest {
	@Autowired
	private ActiveMqService service;

	@Test
	public void testUserPush() {
		User user=new User();
		user.setUsername("test");
		service.push("user",user);
	}

}
