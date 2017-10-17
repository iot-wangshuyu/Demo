package com.shuyu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface TestMapper {
	
	List<Map<String,String>> query(@Param("name")String name);

}
