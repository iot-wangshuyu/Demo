package com.shuyu.utils;

import com.shuyu.utils.StringUtils;

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

public class PageEntity {

	/**
	 * default each page size
	 */
	private static final int DEFAULT_PAGE_Size = 10;
	
	/**
	 * default each page No
	 */
	private static final int DEFAULT_PAGE_NO = 1;

	/**
	 * current page
	 */
	private int currentPage;

	/**
	 * page size
	 */
	private int pageSize;
	
	/**
	 * total count
	 */
	private Long totalcount;

	/**
	 * total page
	 */
	private int totalPage;

	/**
	 * 
	 */
	public PageEntity() {
	}

	/**
	 * @param pageSize
	 * @param currentPage
	 * @param totalcount
	 * @param totalPage
	 * @param result
	 */
	public PageEntity(int pageSize, int currentPage, Long totalcount, int totalPage) {
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalcount = totalcount;
		this.totalPage = totalPage;
	}

	public static PageEntity buildPageEntity(Integer pageNo, Integer pageSize) {
		PageEntity pageEntity = new PageEntity();
		
		if (pageNo ==  null && pageSize == null) {
			return null;
		}
		
		if (pageNo != null) {
			pageEntity.currentPage = pageNo > 0 ? pageNo : DEFAULT_PAGE_NO;
		} else {
			pageEntity.currentPage = DEFAULT_PAGE_NO;
		}
		
		if (pageSize != null) {
			pageEntity.pageSize = pageSize > 0 ? pageSize : DEFAULT_PAGE_Size;
		} else {
			pageEntity.pageSize = DEFAULT_PAGE_Size;
		}
		
		return pageEntity;
	}
	
	public static PageEntity buildPageEntity(String pageNo, String pageSize) {
		
		if (StringUtils.isEmpty(pageNo) && StringUtils.isEmpty(pageSize)) {
			return null;
		}
		
		Integer nPageNo = null;
		Integer nPageSize = null;
		
		if (!StringUtils.isEmpty(pageNo)) {
			try {
				nPageNo = Integer.valueOf(pageNo);
			} catch (NumberFormatException e) {
			}
		}
		
		if (!StringUtils.isEmpty(pageSize)) {
			try {
				nPageSize = Integer.valueOf(pageSize);
			} catch (NumberFormatException e) {
			}
		}
		
		return buildPageEntity(nPageNo, nPageSize);
	}
	
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the totalcount
	 */
	public Long getTotalcount() {
		return totalcount;
	}

	/**
	 * @param totalcount the totalcount to set
	 */
	public void setTotalcount(Long totalcount) {
		this.totalcount = totalcount;
	}

	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return this.totalPage = (totalcount % pageSize == 0) ? (int)(totalcount / pageSize) : (int)(totalcount / pageSize + 1);
	}

	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PageEntity [pageSize=" + pageSize + ", currentPage=" + currentPage + ", totalcount=" + totalcount + ", totalPage=" + totalPage + "]";
	}

}
