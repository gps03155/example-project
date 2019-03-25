package com.douzone.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.hellospring.vo.UserVo;


// @RequestMapping : type + method - 추천하는 방식

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinform() {
		return "/WEB-INF/views/join.jsp";
	}
	
	@ResponseBody
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo vo) { // form에 흩어져 있는 객체를 하나로 모아줌 
		System.out.println(vo.toString());
		
		return "UserController:join()";
	}
	
	@ResponseBody
	@RequestMapping("/loginform")
	public String loginform() {
		return "UserController:loginform()";
	}
	
	@ResponseBody
	@RequestMapping("/login")
	public String login() {
		return "UserController:login()";
	}
}
