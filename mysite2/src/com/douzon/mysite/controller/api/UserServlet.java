package com.douzon.mysite.controller.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.AbstractActionFactory;
import com.douzon.mvc.action.Action;
import com.douzon.mysite.action.user.UserActionFactory;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(name = "APIUserServlet", urlPatterns = { "/api/user" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionName = request.getParameter("action");
		
		AbstractActionFactory af = new UserActionFactory();
		Action action = af.getAction(actionName);
		
		action.execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
