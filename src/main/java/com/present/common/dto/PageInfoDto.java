package com.present.common.dto;


public class PageInfoDto {

	/**
	 * 当前页
	 */
    private int pageNo;
    
    /**
	 * 返回状态
	 */
    private int pageSize;

    /**
	 * 返回状态
	 */
    private int totalSize;

	/**
	 * getter method
	 * @return Returns the pageNo.
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * setter method
	 * @param pageNo The pageNo to set.
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * getter method
	 * @return Returns the pageSize.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * setter method
	 * @param pageSize The pageSize to set.
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * getter method
	 * @return Returns the totalSize.
	 */
	public int getTotalSize() {
		return totalSize;
	}

	/**
	 * setter method
	 * @param totalSize The totalSize to set.
	 */
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	
}
