package com.douzone.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// @Target - 어디에 쓸 수 있는 어노테이션인가
@Target(ElementType.METHOD) //ElementType.TYPE) // 메서드에만 붙일 수 있음
@Retention(RetentionPolicy.RUNTIME) // 어느 시기에 붙일 것인가
public @interface Auth {
	public enum Role {ADMIN, USER};
	
	Role value() default Role.USER;
	
	/* test */
	// String value() default "USER";
	// String[] value() default {}; // Array 사용가능
	// int method() default 1;
}
