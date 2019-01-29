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

public class BoardFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String page = request.getParameter("page");
		System.out.println(page);
		
		PageVo vo = new PageVo();
		
		vo.setTotalCount(new BoardDao().getTotalCount());
		int totalPage = vo.getTotalPage(vo.getTotalCount());
		vo.setStartPage(Integer.parseInt(page));
		vo.setEndPage(10);
		vo.setPage(Integer.parseInt(page));
		System.out.println(vo.getStartPage() + " " + vo.getEndPage());
		
		List<BoardVo> list = new BoardDao().getList();
		
		request.setAttribute("list", list);
		request.setAttribute("startPage", vo.getStartPage());
		request.setAttribute("endPage", vo.getEndPage());
		request.setAttribute("page", page);
		
		WebUtils.forward(request, response, "/WEB-INF/views/board/list.jsp");	
	}

}
