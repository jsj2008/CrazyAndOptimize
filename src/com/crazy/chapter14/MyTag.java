package com.crazy.chapter14;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyTag {

	String name() default "Oliver";
	int age() default 32;
	
}
