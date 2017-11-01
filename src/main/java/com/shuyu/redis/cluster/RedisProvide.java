package com.shuyu.redis.cluster;


import org.springframework.stereotype.Service;

import com.shuyu.utils.JedisClusterFactory;

import redis.clients.jedis.JedisCluster;

public class RedisProvide implements CacheSecondLevel{
	
	private JedisCluster jedisCluster;

	public RedisProvide(JedisClusterFactory jedisClusterFactory){
	       jedisCluster=jedisClusterFactory.getJedisCluster();
	}

	@Override
	public Object get(String key){
	    return jedisCluster.get(key);        
	}

	@Override
	public Object[] gets(String[] keys) {
	    return null;
	}

	@Override
	public void set(String key, Object obj) {
	    jedisCluster.set(key, obj.toString());
	}

	@Override
	public void delete(String key) {
	    // TODO Auto-generated method stub
	    jedisCluster.del(key);
	}

}
