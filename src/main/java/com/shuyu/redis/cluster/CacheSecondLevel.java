package com.shuyu.redis.cluster;

public interface CacheSecondLevel {
	public Object get(String key);

	public Object[] gets(String[] keys);

	public void set(String key, Object obj);

	public void delete(String key);

}
