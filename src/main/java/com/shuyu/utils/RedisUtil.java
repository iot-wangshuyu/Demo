package com.shuyu.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	/** 
	 * ����Ƭ���ӳ�
	 * 
	 */
	private JedisPool jedisPool;

	private String ip = "127.0.0.1";

	/**
	 * ����Ƭ���ӳس�ʼ��
	 */
	private JedisPool initialPool() {

		// �ػ�������

		JedisPoolConfig config = new JedisPoolConfig();

		config.setMaxTotal(100);

		config.setMaxIdle(5);

		config.setMaxWaitMillis(1000);

		config.setTestOnBorrow(true);

		jedisPool = new JedisPool(config, ip, 6379);
		return jedisPool;

	}

	/**
	 * �ڶ��̻߳���ͬ����ʼ��
	 * 
	 */
	private synchronized void poolInit() {

		if (jedisPool == null) {

			initialPool();

		}

	}

	/**
	 * ����Ƭ�ͻ������� ͬ����ȡ����ƬJedisʵ��
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
				// jedis.auth(redisCacheConfig.getAuth());
			}

		} catch (Exception e) {
			e.printStackTrace();
			// �ͷ�jedis����
			jedisPool.returnBrokenResource(jedis);
		} finally {
			// �������ӳ�
			if (jedis != null && jedisPool != null) {
				jedisPool.returnResource(jedis);

			}

		}

		return jedis;

	}

	/**
	 * �ͷ�jedis��Դ
	 * @param jedis
	 * 
	 */
	@SuppressWarnings("deprecation")

	public void returnResource(JedisPool pool, Jedis redis) {

		if (redis != null && pool != null) {
			pool.returnResource(redis);
			// pool.returnResourceObject(redis);

		}

	}

}
