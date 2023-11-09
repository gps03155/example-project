package com.douzone.aoptest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect // 로딩이 되면서 자동으로 proxy 생성
@Component
public class MyAspect {

	@Before("execution(public ProductVo com.douzone.aoptest.ProductService.find(..))") // point cut 지정
	public void beforeAdvice() {
		System.out.println("MyAspect : beforeAdvice()");
	}
	
	@After("execution(* *..*.ProductService.*(..))") // 모든 패키지의 ProductService의 모든 메서드
	public void afterAdvice() {
		System.out.println("MyAspect : afterAdvice()");
	}
	
	@AfterReturning("execution(* *..*.ProductService.*(..))") // @after 후에 실행
	public void afterReturningAdvice() {
		System.out.println("MyAspect : afterReturningAdvice()");
	}
	
	@AfterThrowing(value="execution(* *..*.*.*(..))", throwing="ex") // Global Exception 처리
	public void afterThrowAdvice(Throwable ex) {
		System.out.println("MyAspect : afterThrowAdvice() " + ex);
	}
	
	@Around("execution(* *..*.ProductService.*(..))") // @before + @after
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable { // Point Cut이 되는 메서드를 사용할 수 있도록 객체를 넘겨줘야함
		System.out.println("MyAspect : aroundAdvice()");

		// before
		System.out.println("aroundAdvice() : Before");
		
		// Point Cut 되는 메서드 호출
		Object[] parameters = {"camera"};
		Object result = pjp.proceed(parameters);
		
		// after
		System.out.println("aroundAdvice() : After");
		
		return result;
	}
}
