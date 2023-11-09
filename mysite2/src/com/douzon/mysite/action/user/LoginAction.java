package com.douzon.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.repository.UserDao;
import com.douzon.mysite.vo.UserVo;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo authUser = new UserDao().get(email, password);
		
		if(authUser == null) {
			// 인증 실패 - 로그인 실패
			request.setAttribute("result", "fail");
			WebUtils.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			
			return;
		}
		
		// 인증 성공 - 로그인 성공
		// request.getSession(false); // 없으면 null
		HttpSession session = request.getSession(true); // 없으면 만들어 달라
		
		session.setAttribute("authuser", authUser);
		
		// main redirect
		WebUtils.redirect(request, response, request.getContextPath());
	}

}
