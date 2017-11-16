package com.zhenjinzi.util;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @描述：分页处理类
 * @author Bin
 */
public class Page implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_PAGE_SIZE = 20;

	private int pageSize = DEFAULT_PAGE_SIZE;
	private long start; // start from 0
	private long total;
	private Object data;

	public Page(int pageSize, long start, long total, Object data) {
		super();
		this.pageSize = pageSize;
		this.start = start;
		this.total = total;
		this.data = data;
	}

	@SuppressWarnings("rawtypes")
	public Page() {
		this(DEFAULT_PAGE_SIZE, 0, 0, new ArrayList());
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public long getTotalPageCount() {
		long temp = total / pageSize;
		if (total % pageSize == 0)
			return temp;
		else
			return temp + 1;
	}

	public long getCurrentPageNum() {
		return start / pageSize + 1;
	}

	public boolean hasNextPage() {
		return getCurrentPageNum() < getTotalPageCount();
	}

	public boolean hasPrevPage() {
		return getCurrentPageNum() > 1;
	}

	public static long getStartOfPage(long pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	public static long getStartOfPage(long pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}
}
