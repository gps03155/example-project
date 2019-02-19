package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.exception.UserDaoException;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET) // 넘어오는 데이터 없을 때 : GET
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST) // form에서 데이터 넘어올 때 : POST
	public String join(@ModelAttribute UserVo userVo) {
		userService.join(userVo);
	
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	/*
	@ExceptionHandler(UserDaoException.class)
	public String handleUserDaoException() {
		// 1. logging
		// 2. 페이지 전환 (Error 페이지 - 사용자에게 에러 발생을 알림)
		return "error/exception";
	}
	*/
}
