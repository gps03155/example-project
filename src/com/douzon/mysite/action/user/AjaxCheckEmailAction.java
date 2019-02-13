package com.douzon.mysite.action.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mysite.repository.UserDao;
import com.douzon.mysite.vo.UserVo;

import net.sf.json.JSONObject;

public class AjaxCheckEmailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("application/json; charset=UTF-8");
		
		String email = request.getParameter("email");
		
		UserDao dao = new UserDao();
		UserVo vo = dao.get(email);
		
		boolean exist = vo != null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("exist", exist);
		
		// java object -> json string
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		String jsonString = jsonObject.toString();
		
		out.println(jsonString);
	}

}
