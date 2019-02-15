package com.douzon.mysite.action.guestbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mysite.repository.GuestBookDao;
import com.douzon.mysite.vo.GuestBookVo;

import net.sf.json.JSONObject;

public class AjaxInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("application/json; charset=UTF-8");
		
		int page = Integer.parseInt(request.getParameter("page"));
		String name = request.getParameter("name");
		String message = request.getParameter("message");
		String password = request.getParameter("password");
		
		GuestBookVo vo = new GuestBookVo();
		
		vo.setName(name);
		vo.setMessage(message);
		vo.setPassword(password);
		
		GuestBookDao dao = new GuestBookDao();	
		
		int no = dao.insert(vo);
		GuestBookVo newVo = dao.get(no);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", "success");
		map.put("data", newVo);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		
		response.getWriter().println(jsonObject.toString());
	}

}
