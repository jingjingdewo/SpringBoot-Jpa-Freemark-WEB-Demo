package com.demo.framework.enums;

public enum StatusCodeEnum implements ValueEnum {

	error(-1), success(1), timeout(0), warning(2), confirm(3), exception(4);

	private final Integer value;

	private StatusCodeEnum(Integer value) {
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return value;
	}

}
