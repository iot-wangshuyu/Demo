package com.shuyu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuyu.activemq.service.ActiveMqService;
import com.shuyu.entity.User;
import com.shuyu.service.TestService;
import com.shuyu.utils.GsonUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * @ClassName: TestController 
 * @Description: TODO
 * @author shuyu.wang
 * @date 2017年10月19日 下午2:53:49 
 * @version V1.0
 */
@Controller
@RequestMapping(value="/test")
@Api(value="test",description="测试接口描述")
public class TestController {
	@Autowired
	private TestService service;
	
	@Autowired
	@Qualifier(value="queueServiceImpl")
	private ActiveMqService queue;
	
	@Autowired
	@Qualifier(value="topicServiceImpl")
	private ActiveMqService topic;

	/**
	 * @Title: getHeader @Description: TODO @param @return @return String @throws
	 */
	@RequestMapping(value="/header")
	@ResponseBody
	@ApiOperation(value="根据header户信息",httpMethod="GET",notes="get user by id") 
	public Map<String, Object> getHeader() {
		Map<String, Object> map=new HashMap<>(16);
		return map;
	}
	
	@RequestMapping(value="/sql")
	@ResponseBody
	@ApiOperation(value="数据库查询",httpMethod="GET",notes="mysql数据库查询操作") 
	public List<Map<String, String>> getSql(@RequestParam(value="name",required=true)String name) {
		return service.query(name);
	}
	@RequestMapping(value="/queue/user",method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String queue(String message) {
//		System.out.println("入参"+GsonUtil.GsonString(user));
		queue.push("queue.user",message);
		return message;
	}
	
	@RequestMapping(value="/topic/user",method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String topic(String message) {
//		System.out.println("入参"+GsonUtil.GsonString(user));
		topic.push("topic.user",message);
		return GsonUtil.GsonString(message);
	}


}
