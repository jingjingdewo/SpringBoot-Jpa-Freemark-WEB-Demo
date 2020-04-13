package com.demo.framework.dao.entity;

import com.demo.framework.dao.enums.OrderEnum;

import java.io.Serializable;

public class OrderCondition implements Serializable {

	private static final long serialVersionUID = 1L;

	private OrderEnum orderType;

	private String fieldName;

	public OrderCondition() {
	}

	public static OrderCondition addOrder(OrderEnum orderType, String fieldName) {
		return new OrderCondition(orderType, fieldName);
	}

	public OrderCondition(OrderEnum orderType, String fieldName) {
		super();
		this.orderType = orderType;
		this.fieldName = fieldName;
	}

	public OrderEnum getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderEnum orderType) {
		this.orderType = orderType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
