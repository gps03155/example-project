package com.douzone.hellospring.controller;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@ResponseBody
	@RequestMapping({"/main", ""}) // @RequestMapping : method 단독 매핑
	public String main() {
		return "MainController:main()";
	}
	
	@ResponseBody
	@RequestMapping("/main2") // @RequestMapping : method 단독 매핑
	public String main2() {
		return "MainController:main2()";
	}
	
	// 비추천
	// 기술이 침투되어 있다.
	@ResponseBody
	@RequestMapping("/main3")
	public String main3(HttpServletRequest request, Writer out) {
		String name = request.getParameter("n");
		
		return "MainController:main3()";
	}
}
