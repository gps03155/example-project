package com.douzon.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.repository.UserDao;
import com.douzon.mysite.vo.UserVo;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password =request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserVo vo = new UserVo();
		
		vo.setName(name);
		vo.setEmail(email);
		vo.setPassword(password);
		vo.setGender(gender);
		
		new UserDao().insert(vo);
		
		WebUtils.redirect(request, response, request.getContextPath() + "/user?action=joinsuccess");
	}

}
