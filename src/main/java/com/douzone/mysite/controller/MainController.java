package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.repository.GuestBookDao;
import com.douzone.mysite.service.SiteService;

@Controller
public class MainController {
	@Autowired
	private GuestBookDao dao;
	
	@Autowired
	private SiteService siteService;

	@RequestMapping({"", "/main"})
	public String main(Model model) {
		/*
		GuestBookVo vo = new GuestBookVo();
		vo.setName("안대혁");
		vo.setPassword("1234");
		vo.setMessage("test");
		long no = dao.insert(vo.getName(), vo.getPassword(), vo.getMessage());
		System.out.println(no);
		*/
		
		// SiteVo siteVo = siteService.getSite();
		
		// model.addAttribute("site", siteVo);
		
		return "main/index"; // ViewResolver - prefix : /WEB-INF/views , suffix : .jsp
							 // ViewResolver - /WEB-INF/views/main/index.jsp로 경로가 설정됨
	}
}
