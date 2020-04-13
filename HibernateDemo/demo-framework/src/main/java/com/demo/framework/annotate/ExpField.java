package com.demo.framework.annotate;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExpField {

	String name() default "";

	String field() default "";

	String subField() default "";

	int exportSort() default 0;
}