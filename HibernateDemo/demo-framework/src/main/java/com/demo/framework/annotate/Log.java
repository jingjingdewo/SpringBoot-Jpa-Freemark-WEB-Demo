package com.demo.framework.annotate;


import com.demo.framework.enums.ActionEnums;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

	String description() default "";

	ActionEnums action() default ActionEnums.Visit;

	String userIdSessionKey() default "curUserId";

	String userNameSessionKey() default "curUserName";
}
