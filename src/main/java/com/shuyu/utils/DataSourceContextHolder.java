package com.shuyu.utils;

/**
 * @ClassName: DataSourceContextHolder
 * @Description: 用来切换数据库
 * @author shuyu.wang
 * @date 2017年11月2日 下午6:17:02
 * @version V1.0
 */
public class DataSourceContextHolder {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
	public static void setDbType(String dbType) {
		contextHolder.set(dbType);
	}

	public static String getDbType() {
		return ((String) contextHolder.get());
	}

	public static void clearDbType() {
		contextHolder.remove();
	}

}
