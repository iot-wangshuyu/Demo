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

/**
 * @date 2017-5-16
 */
public enum ResultCode {
	SUCCESS(0), 
	
	DEF_ERROR(-1);
	
	private Integer code;

	private ResultCode(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}
}
