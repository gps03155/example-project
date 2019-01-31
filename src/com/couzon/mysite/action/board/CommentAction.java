package com.couzon.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.repository.BoardDao;

public class CommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int page = Integer.parseInt(request.getParameter("page"));
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String content = request.getParameter("content");
		long userNo = Long.parseLong(request.getParameter("authUser"));
		
		new BoardDao().insertComment(content, userNo, boardNo);
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board?action=viewform&no=" + boardNo + "&page=" + page);
	}

}
