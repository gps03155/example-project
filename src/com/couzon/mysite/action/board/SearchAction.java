package com.couzon.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.repository.BoardDao;
import com.douzon.mysite.vo.BoardVo;

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String search = request.getParameter("search");
		String kwd = request.getParameter("kwd");
		int page = Integer.parseInt(request.getParameter("page"));
		
		List<BoardVo> list = new BoardDao().getSearch(search, kwd, page);
		
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		
		WebUtils.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}

}
