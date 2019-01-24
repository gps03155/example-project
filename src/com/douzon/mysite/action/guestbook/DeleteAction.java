package com.douzon.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.repository.GuestBookDao;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		
		String no = request.getParameter("no");
		String password = request.getParameter("password");
		
		GuestBookDao dao = new GuestBookDao();
		
		String resultPW = dao.comparePW(password);
		int resultNo = Integer.parseInt(no);
		
		dao.delete(resultNo, resultPW);
		
		WebUtils.redirect(request, response, request.getContextPath() + "/guestbook?action=guestbookform");
	}

}
