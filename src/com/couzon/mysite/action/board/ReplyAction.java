package com.couzon.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.repository.BoardDao;
import com.douzon.mysite.vo.UserVo;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		UserVo vo = (UserVo) session.getAttribute("authuser");
		long userNo = vo.getNo();
		
		String groupNo = request.getParameter("groupNo");
		String orderNo = request.getParameter("orderNo");
		String depth = request.getParameter("depth");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		new BoardDao().updateReply(Integer.parseInt(orderNo), Integer.parseInt(groupNo));
		new BoardDao().insertReply(title, content, Integer.parseInt(groupNo), Integer.parseInt(orderNo), Integer.parseInt(depth), userNo);
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board?action=boardform");
	}

}
