package com.douzon.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {
	public static void forward(HttpServletRequest request, HttpServletResponse response, String location) {
		try {
			RequestDispatcher rd = request.getRequestDispatcher(location);

			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void redirect(HttpServletRequest request, HttpServletResponse response, String url) {
		try {
			response.sendRedirect(url);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
