package com.demo.framework.dao.entity;

import com.demo.framework.dao.enums.CriteriaEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CriteriaCondition implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String AND = "AND";

	public static final String OR = "OR";
	
	private String relationType;

	private CriteriaEnum criteriaType;

	private String fieldName;

	private String paramName;

	private Object fieldValue1;

	private Object fieldValue2;

	private String childRelationType;

	private List<CriteriaCondition> childCriterias = new ArrayList<CriteriaCondition>();

	public CriteriaCondition() {
	}

	public CriteriaCondition(CriteriaEnum criteriaType, String fieldName, String paramName,
			Object fieldValue) {
		super();
		this.criteriaType = criteriaType;
		this.fieldName = fieldName;
		this.fieldValue1 = fieldValue;
		this.paramName = paramName;
	}

	public CriteriaCondition(CriteriaEnum criteriaType, String fieldName, String paramName,
			Object fieldValue, List<CriteriaCondition> childCriterias) {
		super();
		this.criteriaType = criteriaType;
		this.fieldName = fieldName;
		this.fieldValue1 = fieldValue;
		this.paramName = paramName;
		this.childCriterias = childCriterias;
	}

	public CriteriaCondition(CriteriaEnum criteriaType, String fieldName, String paramName,
			Object fieldValue1, Object fieldValue2) {
		super();
		this.criteriaType = criteriaType;
		this.fieldName = fieldName;
		this.fieldValue1 = fieldValue1;
		this.fieldValue2 = fieldValue2;
		this.paramName = paramName;
	}

	public CriteriaCondition(String relationType, CriteriaEnum criteriaType, String fieldName,
			String paramName, Object fieldValue1, Object fieldValue2) {
		super();
		this.relationType = relationType;
		this.criteriaType = criteriaType;
		this.fieldName = fieldName;
		this.fieldValue1 = fieldValue1;
		this.fieldValue2 = fieldValue2;
		this.paramName = paramName;
	}

	public CriteriaCondition(String relationType, CriteriaEnum criteriaType, String fieldName,
			String paramName, Object fieldValue1, List<CriteriaCondition> childCriterias) {
		super();
		this.relationType = relationType;
		this.criteriaType = criteriaType;
		this.fieldName = fieldName;
		this.fieldValue1 = fieldValue1;
		this.paramName = paramName;
		this.childCriterias = childCriterias;
	}

	public CriteriaCondition(String relationType, CriteriaEnum criteriaType, String fieldName,
			String paramName, Object fieldValue1) {
		super();
		this.relationType = relationType;
		this.criteriaType = criteriaType;
		this.fieldName = fieldName;
		this.fieldValue1 = fieldValue1;
		this.paramName = paramName;
	}

	public static CriteriaCondition getCriteria(CriteriaEnum criteriaType, String fieldName,
			String paramName, Object fieldValue) {
		return new CriteriaCondition(criteriaType, fieldName, paramName, fieldValue);
	}

	public static CriteriaCondition getCriteria(CriteriaEnum criteriaType, String fieldName,
			String paramName, Object fieldValue, List<CriteriaCondition> childCriterias) {
		return new CriteriaCondition(criteriaType, fieldName, paramName, fieldValue, childCriterias);
	}

	public static CriteriaCondition getCriteria(CriteriaEnum criteriaType, String fieldName,
			String paramName, Object fieldValue1, Object fieldValue2) {
		return new CriteriaCondition(criteriaType, fieldName, paramName, fieldValue1, fieldValue2);
	}

	public static CriteriaCondition getCriteria(String relationType, CriteriaEnum criteriaType,
			String fieldName, String paramName, Object fieldValue1) {
		return new CriteriaCondition(relationType, criteriaType, fieldName, paramName, fieldValue1);
	}

	public static CriteriaCondition getCriteria(String relationType, CriteriaEnum criteriaType,
			String fieldName, String paramName, Object fieldValue1, Object fieldValue2) {
		return new CriteriaCondition(relationType, criteriaType, fieldName, paramName, fieldValue1,
				fieldValue2);
	}

	public static CriteriaCondition getCriteria(String relationType, CriteriaEnum criteriaType,
			String fieldName, String paramName, Object fieldValue1,
			List<CriteriaCondition> childCriterias) {
		return new CriteriaCondition(relationType, criteriaType, fieldName, paramName, fieldValue1,
				childCriterias);
	}

	public CriteriaEnum getCriteriaType() {
		return criteriaType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object getFieldValue1() {
		return fieldValue1;
	}

	public String getParamName() {
		return paramName;
	}

	public Object getFieldValue2() {
		return fieldValue2;
	}

	public void setFieldValue2(Object fieldValue2) {
		this.fieldValue2 = fieldValue2;
	}

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	public void setCriteriaType(CriteriaEnum criteriaType) {
		this.criteriaType = criteriaType;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public void setFieldValue1(Object fieldValue1) {
		this.fieldValue1 = fieldValue1;
	}

	public String getChildRelationType() {
		return childRelationType;
	}

	public void setChildRelationType(String childRelationType) {
		this.childRelationType = childRelationType;
	}

	public List<CriteriaCondition> getChildCriterias() {
		return childCriterias;
	}

	public void setChildCriterias(List<CriteriaCondition> childCriterias) {
		this.childCriterias = childCriterias;
	}

}
