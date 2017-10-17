package com.shuyu.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuyu.service.TestService;

@Controller
@RequestMapping("test")
public class TestController {
	
	

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private TestService service;

	/**
	 * @Title: getHeader @Description: TODO @param @return @return String @throws
	 */
	@RequestMapping("header")
	@ResponseBody
	public Map<String, Object> getHeader() {
		Map<String, Object> map=new HashMap<>();
		map.put("header", getHeadersInfo());
		map.put("user-agent", getUserAgent());
		return map;
	}
	
	@RequestMapping("sql")
	@ResponseBody
	public List<Map<String, String>> getSql(@RequestParam(value="name",required=true)String name) {
		System.out.println(getToken());
		return service.query(name);
	}

	// get user agent
	private String getUserAgent() {
		return request.getHeader("user-agent");
	}

	// get request headers
	private Map<String, String> getHeadersInfo() {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		return map;
	}
	
	
	// get request headers
		private String getToken() {
			Enumeration headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String key = (String) headerNames.nextElement();
				if("token".equals(key)) {
					String value = request.getHeader(key);
					return value;

				}
			}
			return null;
		}
}
