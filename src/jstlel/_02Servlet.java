package jstlel;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class _02Servlet
 */
@WebServlet("/02")
public class _02Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVo vo2 = new UserVo();
		vo2.setNo(2);
		
		UserVo vo3 = new UserVo();
		vo3.setNo(3);
		
		UserVo vo4 = new UserVo();
		vo4.setNo(4);
		
		// request scope
		request.setAttribute("vo2", vo2);
		
		// session scope
		request.getSession(true).setAttribute("vo3", vo3);
		
		// application scope
		request.getServletContext().setAttribute("vo4", vo4);
		
		request.getRequestDispatcher("/WEB-INF/views/02.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
