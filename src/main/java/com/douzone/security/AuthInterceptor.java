package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {		
		if(!(handler instanceof HandlerMethod)) {
			return true;
		}
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		if(auth == null) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}
		
		if(auth == null) {
			return true;
		}
		
		HttpSession session = request.getSession();
		UserVo authUser = null;
		
		if(session != null) {
			authUser = (UserVo) session.getAttribute("authuser");
		}
		
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			System.out.println("로그인 필요");
			
			return false;
		}
		
		String id = request.getParameter("id");
		
		System.out.println(id);
		System.out.println(authUser.getId());
		
		if(!authUser.getId().equals(id)) {
			response.sendRedirect(request.getContextPath() + "/");
			System.out.println("관리자만 접근 가능");
			
			return false;
		}
		
		request.setAttribute("id", id);
		
		return true;
	}
	
}
