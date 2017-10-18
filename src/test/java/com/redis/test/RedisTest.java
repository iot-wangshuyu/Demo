package com.redis.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.dao.support.DaoSupport;

import com.shuyu.redis.dao.RedisDaoImpl;


public class RedisTest {
	
	private RedisDaoImpl dao=new RedisDaoImpl();

	@Test
	public void testSetStringStringIntString() {
		dao.setString("a1", 10, "a1");
	}

	@Test
	public void testExist() {
		boolean exist = dao.exist("a1");
		System.out.println("testExist   "+exist);
	}

	@Test
	public void testSetStringStringString() {
		dao.setString("a2", "a2");
		
	}

	@Test
	public void testGetString() {
		System.out.println("testGetString  "+dao.getString("a2"));
	}

	@Test
	public void testSetBean() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBean() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetList() {
		ArrayList<String> list=new ArrayList<String>();
		list.add("a3");
		dao.setList("a3", list);
	}

	@Test
	public void testGetList() {
		System.out.println("testGetList   "+dao.getList("a3"));
	}

	@Test
	public void testSetMap() {
		Map<String, String> map=new HashMap<>();
		map.put("a4", "a4");
		System.out.println("testSetMap  "+dao.setMap("a4", map));
	}

	@Test
	public void testGetMap() {
		System.out.println("testGetMap"+dao.getMap("a4"));
	}

	@Test
	public void testDel() {
//		System.out.println("testDel  "+dao.del("a4"));
	}

	@Test
	public void testSetInteger() {
		dao.SetInteger("int", 10);
	}

	@Test
	public void testGetSetInteger() {
		System.out.println("testGetSetInteger   "+dao.getSetInteger("int", 5));
	}

	@Test
	public void testGetInteger() {
		System.out.println("testGetInteger   "+dao.getInteger("int"));
	}

	@Test
	public void testSetHash() {
		Map<String, String> map=new HashMap<>();
		map.put("a4", "a4");
		map.put("a5", "a5");
		dao.setHash("a5", map);
	}

	@Test
	public void testGetAllHash() {
		System.out.println("testGetAllHash   "+dao.getAllHash("a5"));
	}

	@Test
	public void testGetHashm() {
		System.out.println("testGetHashm   "+dao.getHashm("a5", "a5"));
	}

	@Test
	public void testLogin() {
//		System.out.println("testLogin   "+dao.login("123456", 20));
	}

	@Test
	public void testValidate() {
		System.out.println("testValidate   "+dao.validate("lc_vc_123456"));
	}

	@Test
	public void testLogout() {
//		dao.logout(dao.login("123456  ", 10));
	}

	@Test
	public void testGetUserId() {
		System.out.println("testGetUserId   "+dao.getUserId("lc_vc_123456"));
	}

}
