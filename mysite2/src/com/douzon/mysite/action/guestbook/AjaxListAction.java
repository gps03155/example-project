package com.douzon.mysite.action.guestbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mysite.repository.GuestBookDao;
import com.douzon.mysite.vo.GuestBookVo;

import net.sf.json.JSONObject;

public class AjaxListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("application/json; charset=UTF-8");
		
		String sPage = request.getParameter("page");
		
		if("".equals(sPage)) {
			sPage = "1";
		}
		
		if(!sPage.matches("\\d*")) {
			sPage = "1";
		}
		
		int page = Integer.parseInt(sPage);
		
		GuestBookDao dao = new GuestBookDao();
		List<GuestBookVo> list = dao.getList(page);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", "success");
		map.put("data", list);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		
		response.getWriter().println(jsonObject.toString());
	}

}
