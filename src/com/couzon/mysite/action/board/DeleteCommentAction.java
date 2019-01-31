package com.couzon.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.repository.BoardDao;

public class DeleteCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int page = Integer.parseInt(request.getParameter("page"));
		long no = Long.parseLong(request.getParameter("no"));
		long commentNo = Long.parseLong(request.getParameter("commentno"));
		
		new BoardDao().deleteComment(commentNo);
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board?action=viewform&no=" + no + "&page=" + page);
	}

}
