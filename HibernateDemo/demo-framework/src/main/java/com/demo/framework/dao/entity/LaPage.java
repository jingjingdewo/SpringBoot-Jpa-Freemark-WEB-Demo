package com.demo.framework.dao.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LaPage<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 包含实际数据的数组
	 */
	private List<T> data = new ArrayList<T>();

	/**
	 * 查询出的记录数
	 */
	private Integer count = 0;

	/**
	 * 当前页
	 */
	private Integer page = 0;

	/**
	 * 每页记录数
	 */
	private Integer pageSize = 0;

	private String msg = "";

	private Integer code = 0;

	private Object otherData;

}
