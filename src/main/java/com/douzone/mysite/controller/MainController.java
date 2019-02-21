package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.repository.GuestBookDao;
import com.douzone.mysite.vo.GuestBookVo;

@Controller
public class MainController {
	@Autowired
	private GuestBookDao dao;

	@RequestMapping({"", "/main"})
	public String main() {
		/*
		GuestBookVo vo = new GuestBookVo();
		vo.setName("안대혁");
		vo.setPassword("1234");
		vo.setMessage("test");
		long no = dao.insert(vo.getName(), vo.getPassword(), vo.getMessage());
		System.out.println(no);
		*/
		
		return "main/index"; // ViewResolver - prefix : /WEB-INF/views , suffix : .jsp
							 // ViewResolver - /WEB-INF/views/main/index.jsp로 경로가 설정됨
	}
}
