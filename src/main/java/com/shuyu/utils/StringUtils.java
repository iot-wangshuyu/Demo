/*
 * Delta CONFIDENTIAL
 *
 * (C) Copyright Delta Electronics, Inc. 2015 All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the
 * property of Delta Electronics. The intellectual and technical
 * concepts contained herein are proprietary to Delta Electronics
 * and are protected by trade secret, patent law or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Delta Electronics.
 */
package com.shuyu.utils;

import java.text.SimpleDateFormat;

/**
 * @author Wenming.Huang
 * @date 2017-1-6
 */
public class StringUtils {
	public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * prepare Sql
	 * @param sql
	 * @param params
	 * @return
	 */
	public static String prepareSql(String sql, Object... params) {
		String[] sqlArray = sql.split("[?]");
		StringBuffer after = new StringBuffer();
		if(sqlArray.length == 0){
			return sql;
		}
		for (int i = 0; i < sqlArray.length; i++) {
			if(params.length > i){
				after.append(sqlArray[i] + "'" + params[i] + "'");
			} else {
				after.append(sqlArray[i]);
			}
		}
		return after.toString();
	}
	
	public static boolean isEmpty(String str) {
//		return org.apache.commons.lang3.StringUtils.isEmpty(str);
		return true;
	}
	
	public static String concatNormalization(String[] part) {
		if (part == null || part.length == 0) {
			return null;
		}
		String result = part[0];
		if (part.length > 1) {
			for (int i = 1; i < part.length; i++) {
				result = result + "," + part[i]; 
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		String str = "1245";
		String[] parts = str.split(",");
		for (String part : parts) {
			System.out.println(part);
		}
	}

}
