package com.douzon.helloweb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	// post로 넘어와도 호출이 되기 때문에 괜찮음
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Post 방식으로 넘어오는 데이터 인코딩 (필수)
		// request 앞에 항상 넣어줘야함
		request.setCharacterEncoding("UTF-8");
		
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String birthYear = request.getParameter("birth-year");
		String[] hobbies = request.getParameterValues("hobby"); // 하나의 이름으로 여러 개의 값이 올 때
		String selfInfo = request.getParameter("self-info");

		System.out.println(mail);
		System.out.println(password);
		System.out.println(gender);
		System.out.println(birthYear);
		System.out.println(selfInfo);
		
		for(String hobby:hobbies) {
			System.out.println(hobby);
		}
		
		response.getWriter().println("ok");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost() called");
		doGet(request, response);
	}

}
