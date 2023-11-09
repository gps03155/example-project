package com.douzon.mysite.action.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// cookie 읽기
		int count = 0;
		Cookie[] cookies = request.getCookies(); // cookie는 여러개라서 배열로 들어옴
		
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				System.out.println(cookie.getName() + " " + cookie.getValue()); // cookie는 계속 브라우저에 남아있음
				if("visitCount".equals(cookie.getName())) {
					count = Integer.parseInt(cookie.getValue()); // cookie 값은 스트링으로 넘어옴
					break;
				}
			}
		}
		
		// cookie 생성
		count++;
		Cookie cookie = new Cookie("visitCount", "" + count);
		
		cookie.setMaxAge(24 * 60 * 60); // 시 분 초 - 지정한 시간동안 쿠키가 존재
		cookie.setPath(request.getContextPath());
		
		response.addCookie(cookie); // 쿠키를 남기기 위해 response.add(cookie)
		
		WebUtils.forward(request, response, "/WEB-INF/views/main/index.jsp");
	}

}
