package com.douzone.mysite.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.bind.annotation.ControllerAdvice;

// @Aspect
// @Component
@ControllerAdvice
public class GlobalExceptionHander {

	@AfterThrowing(value="execution(* *..*.*.*(..))", throwing="ex") // Global Exception 처리 - 모든 패키지에서 모든 메서드가 실행할때 예외처리
	public void afterThrowAdvice(Throwable ex) {
		System.out.println("call [afterThrowAdvice()] " + ex);
	}
}
