package com.douzon.helloweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TableServlet
 */
@WebServlet("/table")
public class TableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String row = request.getParameter("row"); // 숫자라도 String
		String col = request.getParameter("col");
		
		int nRow = 1;
		
		if(row != null){
			nRow = Integer.parseInt(row);
		}
			
		int nCol = 1;
		
		if(col != null){
			nCol = Integer.parseInt(col);
		}
		
		// 응답
		PrintWriter out = response.getWriter();
		
		out.println("<table border='1px' cellspacing='0' cellpadding='5px'>");
		
		for(int i=0;i<nRow;i++) {
			out.println("<tr>");
			
			for(int j=0;j<nCol;j++) {
				out.println("<td> cell(" + i + ", " + j + ") </td>");
			}
			
			out.println("</tr>");
		}
		
		out.print("</table>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
