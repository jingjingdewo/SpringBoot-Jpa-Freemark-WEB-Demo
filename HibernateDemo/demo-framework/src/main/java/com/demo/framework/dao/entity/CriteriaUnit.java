package com.demo.framework.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huangyh
 * 
 */
public class CriteriaUnit implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<CriteriaCondition> criterias = new ArrayList<CriteriaCondition>();

	private List<OrderCondition> orderConditions = new ArrayList<OrderCondition>();

	private List<JoinCondition> joinConditions = new ArrayList<JoinCondition>();

	public List<CriteriaCondition> getCriterias() {
		return criterias;
	}

	public void setCriterias(List<CriteriaCondition> criterias) {
		this.criterias = criterias;
	}

	public List<OrderCondition> getOrderConditions() {
		return orderConditions;
	}

	public void setOrderConditions(List<OrderCondition> orderConditions) {
		this.orderConditions = orderConditions;
	}

	public List<JoinCondition> getJoinConditions() {
		return joinConditions;
	}

	public void setJoinConditions(List<JoinCondition> joinConditions) {
		this.joinConditions = joinConditions;
	}
}
