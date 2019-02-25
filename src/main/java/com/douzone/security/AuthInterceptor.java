package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.vo.UserVo;
import com.douzone.security.Auth.Role;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	// 로그인, 로그아웃 제외
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {		
		// 1. Handler 종류 확인
		// HandlerMethod가 아니면 default HandlerMethod
		if(!(handler instanceof HandlerMethod)) {
			return true;
		}
		
		// 2. Casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		// 3. Method에 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class); // 없으면 null 리턴
		
		// 3-1. Method에 @Auth가 안붙어 있으면 Class Type의 @Auth 받아오기
		if(auth == null) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}
		
		// 4. Method에 @Auth가 안붙어 있으면 (인증 불필요) : Method && Class에 붙어있지않음
		if(auth == null) {
			return true;
		}
		
		// Auth.Role role = auth.value(); // role 값이 나옴
		// 클래스에 있는거 빼오기
		// handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		
		// 5. @Auth 붙어 있으면 (인증 필요 - 로그인 여부 확인)
		HttpSession session = request.getSession();
		UserVo authUser = null;
		
		if(session != null) {
			authUser = (UserVo) session.getAttribute("authuser");
			System.out.println(authUser.getRole());
		}
		
		// 인증이 안되어 있는 경우
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			System.out.println("AuthInterceptor : preHandle() authUser == null");
			
			return false;
		}
		
		// 5-1. Role 비교 작업
		String role = auth.value().toString();
		
		if(!role.equals(authUser.getRole().toUpperCase())) { // user가 URL로 접근하는 것을 막음 : http:localhost:8080/mysite3/admin
			response.sendRedirect(request.getContextPath() + "/user/login");
			System.out.println("관리자만 접근 가능");
			
			return false;
		}
		
		// 6. 접근허용
		return true;
	}

}
