package com.shuyu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuyu.mapper.TestMapper;
import com.shuyu.service.TestService;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
	private TestMapper mapper;

	@Override
	public List<Map<String, String>> query(String name) {
		return mapper.query(name);
	}
    
    
}
