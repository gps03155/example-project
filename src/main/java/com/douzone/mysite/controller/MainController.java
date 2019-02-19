package com.douzone.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping({"", "/main"})
	public String main() {
		return "main/index"; // ViewResolver - prefix : /WEB-INF/views , suffix : .jsp
							 // ViewResolver - /WEB-INF/views/main/index.jsp로 경로가 설정됨
	}
}
