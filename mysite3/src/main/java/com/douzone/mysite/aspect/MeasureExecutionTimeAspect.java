package com.douzone.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect {
	
	@Around("execution(* *..repository.*.*(..)) || execution(* *..service.*.*(..)) || execution(* *..controller.*.*(..))") // com.douzone.repository의 모든 클래스의 모든 메서드	
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		// before
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		// method 실행
		Object result = pjp.proceed();
		
		// after
		stopWatch.stop();
		Long totalTime = stopWatch.getTotalTimeMillis();
		
		// System.out.println("TotalTime : " + totalTime); // 한번 로그인 이후 캐시 때문에 시간 단축
		
		String className = pjp.getTarget().getClass().getName(); // target 객체의 getClass - class 정보
		String methodName = pjp.getSignature().getName(); // method 이름, 파라미터 정보
		String taskName = className + "." + methodName;
		
		System.out.println("[ExecutionTime][" + taskName + "] " + totalTime + "millis");
		
		return result;
	}
}
