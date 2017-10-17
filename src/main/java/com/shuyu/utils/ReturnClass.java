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

import java.io.Serializable;


/**
 * 
 * @author Wenming.Huang
 * @date 2017-1-6
 */
public class ReturnClass<T> implements Serializable {
	private static final long serialVersionUID = 1098033339357933985L;
	/** fun is run success */
	private Boolean success;
	/** fun error message */
	private String message;
	/** fun return data */
	private T data;

	public ReturnClass() {
		this.success = false;
		this.message ="unknow";
	}
	
	public Boolean isSuccess() {
		return success;
	}

	public void setSuccess(Boolean isSuccess) {
		this.success = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ReturnClass [isSuccess=" + success + ", message=" + message + ", data=" + data
				+ "]";
	}
}
