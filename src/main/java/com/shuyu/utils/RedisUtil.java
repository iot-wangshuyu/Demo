package com.shuyu.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	/**
	 * 非切片链接池
	 * 
	 */
	private JedisPool jedisPool;

	private String ip = "127.0.0.1";

	/**
	 * 非切片连接池初始化
	 */
	private JedisPool initialPool() {

		// 池基本配置

		JedisPoolConfig config = new JedisPoolConfig();

		config.setMaxTotal(100);

		config.setMaxIdle(5);

		config.setMaxWaitMillis(1000);

		config.setTestOnBorrow(true);

		jedisPool = new JedisPool(config, ip, 6379);
		return jedisPool;

	}

	/**
	 * 在多线程环境同步初始化
	 * 
	 */
	private synchronized void poolInit() {

		if (jedisPool == null) {

			initialPool();

		}

	}

	/**
	 * 非切片客户端链接 同步获取非切片Jedis实例
	 * 
	 * @return Jedis
	 * 
	 */
	@SuppressWarnings("deprecation")
	public synchronized Jedis getJedis() {
		if (jedisPool == null) {
			poolInit();
		}
		Jedis jedis = null;
		try {
			if (jedisPool != null) {

				jedis = jedisPool.getResource();
				// jedis.auth(password);
			}

		} catch (Exception e) {
			System.out.println("抛错");
			e.printStackTrace();
			// 释放jedis对象
			jedisPool.returnBrokenResource(jedis);
		} finally {
			// 返还连接池
			if (jedis != null && jedisPool != null) {
				jedisPool.returnResource(jedis);

			}

		}

		return jedis;

	}

}
