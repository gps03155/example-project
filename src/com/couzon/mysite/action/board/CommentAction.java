package com.couzon.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;

public class CommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int page = Integer.parseInt(request.getParameter("page"));
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board?action=boardform&page=" + page);
	}

}
