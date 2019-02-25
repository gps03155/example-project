package com.douzone.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor01 implements HandlerInterceptor{

	// handler 호출 전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Handler 호출 전 (요청 전)
		// Handler 호출 여부를 결정 (boolean 반환값에 따라)
		System.out.println("MyInterceptor01 : preHandle()");
	
		// false : 뒤에꺼 호출 안됨
		// true : 뒤에꺼 호출 됨
		return false;
	}

	// handler 보낸 후
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// Handler 호출이 된 후 (응답 후)- Handler의 작업이 다 끝난 후
		System.out.println("MyInterceptor01 : postHandle()");
	}

	// handler 호출 종료
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// View에 Rendering 작업까지 완전히 완료 (응답 후)
		System.out.println("MyInterceptor01 : afterCompletion()");
	}

}
