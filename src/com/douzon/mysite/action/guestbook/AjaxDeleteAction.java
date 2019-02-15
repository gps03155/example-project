package com.douzon.mysite.action.guestbook;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mysite.repository.GuestBookDao;

import net.sf.json.JSONObject;

public class AjaxDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("application/json; charset=UTF-8");
		
		int page = Integer.parseInt(request.getParameter("page"));
		String password = request.getParameter("password");
		int no = Integer.parseInt(request.getParameter("no"));
		
		System.out.println(page);
		System.out.println(password);
		System.out.println(no);
		
		GuestBookDao dao = new GuestBookDao();
		
		String resultPW = dao.comparePW(password);
		int result = dao.delete(no, resultPW);
		
		JSONObject obj = new JSONObject();
		
		System.out.println(result);
		
		if(result == 1) {
			obj.put("result", "success");
		}
		
		PrintWriter out = response.getWriter();
		
		out.println(obj);
	}

}
