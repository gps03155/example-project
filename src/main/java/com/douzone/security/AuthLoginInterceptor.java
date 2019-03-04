package com.douzone.security;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		UserVo userVo = new UserVo();
		
		userVo.setId(id);
		userVo.setPassword(password);
		
		userVo = userService.login(userVo);
		
		if(userVo == null) {
			request.setAttribute("result", "fail");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/login.jsp");
			dispatcher.forward(request, response);
			
			System.out.println("로그인 실패");
			
			return false;
		}
		
		HttpSession session = request.getSession();
		
		session.setAttribute("authuser", userVo);
		System.out.println("로그인 성공");
		
		response.sendRedirect(request.getContextPath() + "/");
		
		return false;
	}
}
