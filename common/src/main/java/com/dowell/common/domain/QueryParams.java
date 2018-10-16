package com.dowell.common.domain;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * @author nanbo
 * @description 查询参数
 * @create 2018-10-04
 **/
public class QueryParams implements Serializable {
	private static final long serialVersionUID = -4869594085374385813L;

	private int pageSize;
	private int pageNum;

	public QueryParams() {
		this.pageNum = 1;
		this.pageSize = 15;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("pageSize", pageSize)
				.add("pageNum", pageNum)
				.toString();
	}
}
