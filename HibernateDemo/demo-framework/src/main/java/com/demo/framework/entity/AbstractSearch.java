package com.demo.framework.entity;

import com.demo.framework.dao.enums.OrderEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author 静畏人心
 * @Date 2020/4/11 1:39
 * @Description 搜索内容
 */
@Setter
@Getter
public abstract class AbstractSearch implements Serializable {

	private static final long serialVersionUID = -8570525332869914990L;

	private String keyValue;

	private String uuid;

	private Long searchId;

	private String keywords;

	private String keyword;

	private String searchCategory;

	private String beginDate;

	private String endDate;

	private String searchField;

	private String searchSid;

	private String searchType;

	private Integer searchYear;

	private String selectedVal;

	private boolean searchSelf = false;

	private String ztreePids;

	private String ztreePid;

	private String ztreeId;

	private Integer ztreeLevel;

	private String ztreeKey;

	private Boolean backQuery;

	private String delIds;

	private Integer page;

	private Integer rows;

	private OrderEnum sort;

	private Integer limit;

	private String childType;

	private Boolean selected;

}
