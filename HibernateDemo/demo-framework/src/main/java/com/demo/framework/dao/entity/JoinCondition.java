package com.demo.framework.dao.entity;

public class JoinCondition {

	private String fieldName;

	private String alias;

	public JoinCondition() {
	}

	public static JoinCondition addJoin(String fieldName, String alias) {
		return new JoinCondition(fieldName, alias);
	}

	public JoinCondition(String fieldName, String alias) {
		super();
		this.alias = alias;
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
}
