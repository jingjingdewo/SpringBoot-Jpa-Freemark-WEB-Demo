package com.demo.framework.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JqPage<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 包含实际数据的数组
	 */
	private List<T> rows = new ArrayList<T>();

	/**
	 * 查询出的记录数
	 */
	private Integer records = 0;

	/**
	 * 当前页
	 */
	private Integer page = 0;

	/**
	 * 总页数
	 */
	private Integer total = 0;

	/**
	 * 每页记录数
	 */
	private Integer pageSize = 0;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Integer getRecords() {
		return records;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

}
