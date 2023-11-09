package com.douzon.guestbook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.guestbook.dao.GuestBookDao;
import com.douzon.guestbook.vo.GuestBookVo;
import com.douzon.web.WebUtils;

/**
 * Servlet implementation class GuestBookServlet
 */
@WebServlet("/guestbook")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 요청 분리 (식별)
		String action = request.getParameter("action");
		
		System.out.println(action);
		
		// action이 NULL 값일 수 있기 때문에 "문자열".equals(변수명)으로 사용해야함
		if ("deleteform".equals(action)) {
			WebUtils.forward(request, response, "/WEB-INF/views/deleteform.jsp");
		} else if ("add".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String message = request.getParameter("message");

			GuestBookVo vo = new GuestBookVo();

			vo.setName(name);
			vo.setPassword(password);
			vo.setMessage(message);

			new GuestBookDao().insert(vo);

			// response.sendRedirect(request.getContextPath() + "/guestbook");
			WebUtils.redirect(request, response, request.getContextPath() + "/guestbook");
		} else if ("delete".equals(action)) {
			request.setCharacterEncoding("UTF-8");

			String no = request.getParameter("no");
			String password = request.getParameter("password");
			
			GuestBookDao dao = new GuestBookDao();
			
			String resultPW = dao.comparePW(password);
			int resultNo = Integer.parseInt(no);
			
			System.out.println(resultNo + " " + resultPW);
			
			dao.delete(resultNo, resultPW);
			
			// response.sendRedirect("index.jsp");
			WebUtils.redirect(request, response, request.getContextPath() + "/guestbook");
		} else {
			// default action : 디폴트 요청 처리 (index.jsp) - 사용자가 url을 마음대로 바꿀 수 있기 때문

			List<GuestBookVo> list = new GuestBookDao().getList();

			// 데이터를 request 범위에 저장
			request.setAttribute("list", list);

			// forwarding
			// this.getServletContext();
			WebUtils.forward(request, response, "/WEB-INF/views/index.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
