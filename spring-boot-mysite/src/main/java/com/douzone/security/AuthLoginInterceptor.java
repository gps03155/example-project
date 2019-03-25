package com.douzone.security;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("-----------> AuthLoginInterceptor");
		
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
			// response.sendRedirect(request.getContextPath() + "/user/login");
			request.setAttribute("result", "fail");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp");
			dispatcher.forward(request, response);

			return false;
		}
		
		// 로그인 처리
		HttpSession session = request.getSession(true);
		session.setAttribute("authuser", userVo);
		
		if(userVo.getRole().equals("user")) {
			response.sendRedirect(request.getContextPath() + "/"); // Redirect : @RequestMapping URL
		}
		else {
			response.sendRedirect(request.getContextPath() + "/admin");
		}
		
		return false;
	}

}
