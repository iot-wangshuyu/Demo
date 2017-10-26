package com.shuyu.utils;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName: JedisClusterFactory 
 * @Description: JedisFactory类,传入host 、port 和JedisPoolConfig 类
 * @author shuyu.wang
 * @date 2017年10月26日 下午5:02:50 
 * @version V1.0
 */
public class JedisClusterFactory {
	
	private JedisCluster jedisCluster;

	public JedisCluster getJedisCluster() {
	    return jedisCluster;
	}

	public JedisClusterFactory(JedisPoolConfig jedisPoolConfig,String host,int port){    
	    Set<HostAndPort> jedisClusterNodes= new HashSet<HostAndPort>();
	    //Jedis Cluster will attempt to discover cluster nodes automatically
	    jedisClusterNodes.add(new HostAndPort(host,port));
	    jedisCluster=new JedisCluster(jedisClusterNodes,jedisPoolConfig);
	}

	

}
