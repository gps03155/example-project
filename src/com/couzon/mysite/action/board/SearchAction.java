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
import com.douzon.mysite.vo.PageVo;

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String search = request.getParameter("search");
		String kwd = request.getParameter("kwd");
		int page = Integer.parseInt(request.getParameter("page"));

		System.out.println(kwd);
		System.out.println(search);

		PageVo vo = new PageVo();
		List<BoardVo> list = new BoardDao().getSearch(search, kwd, 1);
		
		vo.setTotalCount(new BoardDao().getSearchCount(search, kwd));
		System.out.println("aa" + new BoardDao().getSearchCount(search, kwd));
		int totalPage = vo.getTotalPage(vo.getTotalCount());
		vo.setStartPage(1);
		vo.setEndPage(10);
		vo.setPage(page);
		
		if(vo.getTotalCount() > 10) {
			vo.setTotalPage(totalPage);
			vo.setPage(page);
			list = new BoardDao().getSearch(search, kwd, vo.getPage());
		}

		System.out.println(list.size());
		System.out.println(vo.getTotalCount() + " " + vo.getTotalPage() + " " + vo.getPage());
		System.out.println(vo.getStartPage() + " " + vo.getEndPage());

		request.setAttribute("list", list);
		request.setAttribute("startPage", vo.getStartPage());
		request.setAttribute("endPage", vo.getEndPage());
		request.setAttribute("page", vo.getPage());
		request.setAttribute("totalCount", vo.getTotalCount());
		request.setAttribute("kwd", kwd);
		request.setAttribute("search", search);

		WebUtils.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}

}
