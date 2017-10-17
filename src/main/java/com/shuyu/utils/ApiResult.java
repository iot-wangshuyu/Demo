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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ApiResult {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	/** Result status */
	private Integer code;
	/** Result message */
	private String message;
	/** Total item size */
	private Long total;
	/** Size of per page */
	private Integer pageSize;
	/** Current page number */
	private Integer pageCurrent;
	/** Total page size */
	private Integer pageTotal;
	/** Result list size */
	private Integer returnTotal;
	/** Result data */
	private Object rows;

	public ApiResult() {
		this(ResultCode.DEF_ERROR.getCode(), ResultMessage.DEF_ERROR.getMessage(), null, null);
	}

	public ApiResult(Integer code, String message, Object data, PageEntity pageEntity) {
		this.code = code;
		this.message = message;
		this.rows = data;
		if (pageEntity != null) {
			this.pageCurrent = pageEntity.getCurrentPage();
			this.pageSize = pageEntity.getPageSize();
			this.total = pageEntity.getTotalcount();
			this.pageTotal = pageEntity.getTotalPage();
		}
	}
	
	public ApiResult(ResultCode code, String message, Object data, PageEntity pageEntity) {
		this(code.getCode(), message, data, pageEntity);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(ResultCode code) {
		this.code = code.getCode();
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(ResultMessage message) {
		this.message = message.getMessage();
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(Integer pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public Integer getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}

	public Integer getReturnTotal() {
		return returnTotal;
	}

	public void setReturnTotal(Integer returnTotal) {
		this.returnTotal = returnTotal;
	}

	public void setPageEntity(PageEntity pageEntity) {
		if (pageEntity != null) {
			this.pageCurrent = pageEntity.getCurrentPage();
			this.pageSize = pageEntity.getPageSize();
			this.total = pageEntity.getTotalcount();
			this.pageTotal = pageEntity.getTotalPage();
		}
	}

	public void setSuccess() {
		this.setCode(ResultCode.SUCCESS);
		this.setMessage(ResultMessage.DEF_SUCCESS.getMessage());
	}

	public String toJSON() {
		return GSON.toJson(this);
	}
}
