package com.demo.framework.annotate;

import com.demo.framework.annotate.enums.FieldType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Target({ FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldAnnotate {

	/**
	 * 字段名
	 * 
	 * @return
	 */
	String name() default "";

	/**
	 * 国际化KEY
	 * 
	 * @return
	 */
	String messageKey() default "";

	/**
	 * 字段类型
	 * 
	 * @return
	 */
	FieldType fieldType() default FieldType.NOMAL;

	/**
	 * 外键对象Class
	 * 
	 * @return
	 */
	String modelClass() default "";

	/**
	 * 外键对象KEY字段
	 * 
	 * @return
	 */
	String modelKeyField() default "";

	/**
	 * 外键对象名称字段
	 * 
	 * @return
	 */
	String modelNameField() default "";

	/**
	 * 字段日期格式
	 * 
	 * @return
	 */
	String dataFormat() default "";

	/**
	 * 允许转JSON
	 * 
	 * OneToMany,ManyToMany,ManyToOne请设置为false
	 * 
	 * @return
	 */
	boolean autoJson() default true;

	/**
	 * 允许对比
	 * 
	 * @return
	 */
	boolean canCompile() default true;

	/**
	 * 是否允许使用公用导出功能
	 * 
	 * @return
	 */
	boolean canExport() default true;

	/**
	 * fieldType = MODEL 允许导出多个字段
	 * 
	 * @return
	 */
	ExpField[] modelExpFields() default {};

	/**
	 * 导出序号
	 * 
	 * @return
	 */
	int exportSort() default 0;

	/**
	 * service查询方法
	 * 
	 * @return
	 */
	boolean queryField() default false;

	/**
	 * service查询方法返回值
	 * 
	 * @return
	 */
	boolean queryListResult() default true;

	boolean jspTableField() default false;

	String jspTableFieldFixed() default "";

	int jspTableFieldSort() default 0;

	/**
	 * form.jsp表单页面字段
	 * 
	 * @return
	 */
	boolean formField() default false;

	boolean hiddenEl() default false;

	boolean inputEl() default false;

	boolean selectEl() default false;

	/**
	 * 表单 查询按钮
	 * 
	 * @return
	 */
	boolean searchEl() default false;

	/**
	 * 表单textarea
	 * 
	 * @return
	 */
	boolean textareaEl() default false;

	/**
	 * 表单必填
	 * 
	 * @return
	 */
	boolean required() default false;

	/**
	 * 关联数据字典
	 * 
	 * @return
	 */
	boolean dataDic() default false;

	/**
	 * 验证正则表达式
	 * 
	 * @return
	 */
	String verifyRegx() default "";

	/**
	 * 关联表单CODE
	 * 
	 * @return
	 */
	String dataDictionaryCode() default "";

}
