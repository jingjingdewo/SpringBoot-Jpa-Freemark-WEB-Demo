package com.demo.framework.dao.enums;

public enum CriteriaEnum {
	Like("Like", "Like"), NotLike("NotLike", "NotLike"), LeftLike("LeftLike", "LeftLike"), LeftNotLike("LeftNotLike",
			"LeftNotLike"), RightLike("RightLike", "RightLike"), RightNotLike("RightNotLike", "RightNotLike"), Equal(
					"Equal", "="), NotEqual("NotEqual", "<>"), Between("Between", "Between"), IsNotNull("IsNotNull",
							"IsNotNull"), IsNull("IsNull", "IsNull"), LessEqual("LessEqual", "<="), MoreEqual(
									"MoreEqual", ">="), Less("Less", "<"), More("More", ">"), IN("in", "in"), NOTIN(
											"not in", "not in"), DateMore("DateMore", ">"), DateLess("DateLess", "<");

	private final String value;

	private final String name;

	private CriteriaEnum(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}
