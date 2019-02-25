package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Container가 Servlet Context 전역 범위에 담겨있음
		// ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		// UserService userService = ac.getBean(UserService.class); // 클래스 객체를 받아옴 - new Userservice()로 사용하면 NullPointException 발생 (객체를 가져오지 못해서)
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo userVo = new UserVo();
		
		userVo.setEmail(email);
		userVo.setPassword(password);
		
		userVo = userService.login(userVo);
		
		if(userVo == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			
			return false;
		}
		
		// 로그인 처리
		HttpSession session = request.getSession(true);
		session.setAttribute("authuser", userVo);
		
		response.sendRedirect(request.getContextPath() + "/");
		
		return false;
	}

}
