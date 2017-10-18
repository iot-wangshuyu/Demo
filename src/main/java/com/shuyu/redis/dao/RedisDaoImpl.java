package com.shuyu.redis.dao;

import java.util.List;
import java.util.Map;

import com.shuyu.utils.RedisUtil;
import com.shuyu.utils.SerializeUtil;

import redis.clients.jedis.Jedis;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class RedisDaoImpl implements RedisDao {
	private static final String VIRTUAL_COURSE_PREX = "lc_vc_";

	private RedisUtil redisUtil = new RedisUtil();

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String buildKey(String key) {
		return VIRTUAL_COURSE_PREX + key;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shuyu.cache.RedisDao#setString(java.lang.String, int,
	 * java.lang.String)
	 */
	public void setString(String key, int seconds, String value) {
		Jedis jedis = redisUtil.getJedis();
		jedis.setex(buildKey(key), seconds, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shuyu.cache.RedisDao#exist(java.lang.String)
	 */
	public boolean exist(String key) {
		Jedis jedis = redisUtil.getJedis();
		String bKey = buildKey(key);
		if (jedis == null || !jedis.exists(bKey)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param key
	 * @param param
	 */
	public <T> boolean setString(String key, String param) {

		Jedis jedis = redisUtil.getJedis();
		String bKey = buildKey(key);
		String set = null;
		set = jedis.set(bKey.getBytes(), SerializeUtil.serialize(param));
		if (!set.isEmpty() && ("OK").equals(set)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * ��ȡStringֵ
	 * 
	 * @param key
	 * @return String
	 */
	public String getString(String key) {
		Jedis jedis = redisUtil.getJedis();
		String bKey = buildKey(key);
		String retru = null;
		if (jedis == null || !jedis.exists(bKey)) {
			return null;
		}
		byte[] in = jedis.get(bKey.getBytes());
		retru = SerializeUtil.unserialize(in).toString();
		return retru;

	}

	/**
	 * 
	 * @param key
	 * @param bean
	 */
	public <T> boolean setBean(String key, Object bean) {
		String bKey = buildKey(key);
		Jedis jedis = redisUtil.getJedis();
		String set = null;
		set = jedis.set(bKey.getBytes(), SerializeUtil.serialize(bean));
		if (!set.isEmpty() && ("OK").equals(set)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 * @param key
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public <T> T getBean(String key) {
		Jedis jedis = redisUtil.getJedis();
		String bKey = buildKey(key);
		T bean = null;
		if (jedis == null || !jedis.exists(bKey.getBytes())) {
			return null;
		}
		byte[] in = jedis.get(bKey.getBytes());
		bean = (T) SerializeUtil.unserialize(in);
		return bean;

	}

	/**
	 * @param key
	 * @param list
	 */

	public <T> boolean setList(String key, List<T> list) {
		Jedis jedis = redisUtil.getJedis();
		String bKey = buildKey(key);
		String set = null;
		set = jedis.set(bKey.getBytes(), SerializeUtil.serialize(list));
		if (!set.isEmpty() && ("OK").equals(set)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 * @param key
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getList(String key) {
		Jedis jedis = redisUtil.getJedis();
		String bKey = buildKey(key);
		List<T> list = null;
		if (jedis == null || !jedis.exists(bKey.getBytes())) {
			return null;
		}
		byte[] in = jedis.get(bKey.getBytes());
		list = (List<T>) SerializeUtil.unserialize(in);
		return list;

	}

	/**
	 * 
	 * @param <T>
	 * @param key
	 */
	public <T> boolean setMap(String key, Map<String, T> map) {
		String bKey = buildKey(key);
		Jedis jedis = redisUtil.getJedis();
		String set = null;
		set = jedis.set(bKey.getBytes(), SerializeUtil.serialize(map));
		if (!set.isEmpty() && ("OK").equals(set)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 * @param key
	 * @return Map
	 */
	@SuppressWarnings("unchecked")
	public <T> Map<String, T> getMap(String key) {
		String bKey = buildKey(key);
		Jedis jedis = redisUtil.getJedis();
		Map<String, T> map = null;
		if (jedis == null || !jedis.exists(bKey.getBytes())) {
			return null;
		}
		byte[] in = jedis.get(bKey.getBytes());
		map = (Map<String, T>) SerializeUtil.unserialize(in);
		return map;
	}

	@Override
	public boolean del(String key) {
		String bKey = buildKey(key);
		Jedis jedis = redisUtil.getJedis();
		Long del = (long) 0;
		if (jedis == null || !jedis.exists(bKey.getBytes())) {
			return false;
		}
		del = jedis.del(bKey.getBytes());
		if (del > 0) {
			return true;
		}
		return false;
	}

	@Override
	public <T> boolean SetInteger(String key, Integer num) {
		Jedis jedis = redisUtil.getJedis();
		String bKey = buildKey(key);
		String set = null;
		set = jedis.set(bKey, String.valueOf(num));
		if (!set.isEmpty() && ("OK").equals(set)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Integer getSetInteger(String key, Integer num) {
		String bKey = buildKey(key);
		Jedis jedis = redisUtil.getJedis();
		String set = null;
		set = jedis.getSet(bKey, String.valueOf(num));
		return Integer.valueOf(set);
	}

	@Override
	public Integer getInteger(String key) {
		String bKey = buildKey(key);
		Jedis jedis = redisUtil.getJedis();
		String set = null;
		if (jedis == null || !jedis.exists(bKey)) {
			return null;
		}
		set = jedis.get(bKey);
		return Integer.valueOf(set);
	}

	@Override
	public <T> boolean setHash(String key, Map<String, String> map) {
		String bKey = buildKey(key);
		Jedis jedis = redisUtil.getJedis();
		String hmset = null;
		hmset = jedis.hmset(bKey, map);
		if (!hmset.isEmpty() && ("OK").equals(hmset)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Map<String, String> getAllHash(String key) {
		String bKey = buildKey(key);
		Jedis jedis = redisUtil.getJedis();
		Map<String, String> map = null;
		if (jedis == null || !jedis.exists(bKey)) {
			return null;
		}
		map = jedis.hgetAll(bKey);
		return map;
	}

	@Override
	public List<String> getHashm(String key, String... fields) {
		String bKey = buildKey(key);
		Jedis jedis = redisUtil.getJedis();
		List<String> list = null;
		if (jedis == null || !jedis.exists(bKey)) {
			return null;
		}
		list = jedis.hmget(bKey, fields);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hc.redis.dao.RedisDao#login(java.lang.String, int)
	 */
	@Override
	public String login(String userId, int second) {
		Jedis jedis = redisUtil.getJedis();
		String token = buildKey(userId);
		if (jedis == null || !jedis.exists(token)) {
			// token生产规则自定义ֵ
			jedis.setex(token, second, userId);
		} else {
			jedis.expire(token, second);
		}

		return token;
	}

	@Override
	public Boolean validate(String token) {
		Jedis jedis = redisUtil.getJedis();
		boolean flag = false;
		if (jedis == null || !jedis.exists(token)) {
			flag = false;
		} else {
			jedis.expire(token, 30 * 24 * 60 * 60);
			flag = true;
		}
		return flag;

	}

	@Override
	public void logout(String token) {
		Jedis jedis = redisUtil.getJedis();
		if (jedis != null && jedis.exists(token)) {
			jedis.del(token);
		}

	}

	@Override
	public String getUserId(String token) {
		Jedis jedis = redisUtil.getJedis();
		String userId = null;
		if (jedis != null && jedis.exists(token)) {
			userId = jedis.get(token);
		}
		return userId;
	}

}
