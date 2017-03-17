package com.present.common.dto;


public class PageResultDto {

    /**
	 * 返回状态
	 */
    private int totalSize;

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
