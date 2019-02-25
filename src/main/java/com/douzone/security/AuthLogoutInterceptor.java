package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthLogoutInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 접근 제한
		HttpSession session = request.getSession();
		
		if(session != null) {
			session.removeAttribute("authuser");
			session.invalidate();
		}
		
		response.sendRedirect(request.getContextPath() + "/");
		
					  // 뒤에 Handler가 없다면 true 했을 때 에러
		return false; // postHandler(), afterComplete() 없으므로 로그아웃 처리 후 리턴
	}

}
