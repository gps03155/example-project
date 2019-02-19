package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.repository.UserDao;
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
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/loginform";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpSession session, @ModelAttribute UserVo userVo, Model model) {
		// 인증 처리
		UserVo authUser = new UserDao().get(userVo.getEmail(), userVo.getPassword());
		
		if(authUser == null) {
			model.addAttribute("result", "fail");
			
			return "user/loginform";
		}
		
		session.setAttribute("authuser", authUser);
		
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// 접근 제한
		if(session != null && session.getAttribute("authuser") != null) {
			session.removeAttribute("authuser");
			session.invalidate();
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(HttpSession session, Model model) {
		UserVo authUser = null;
		
		if(session != null) {
			authUser = (UserVo) session.getAttribute("authuser");
		}
		
		if(authUser == null) {
			return "redirect:/";
		}
		
		UserVo vo = userService.getUserInfo(authUser);
		model.addAttribute("vo", vo);
		
		return "user/modifyform";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(HttpSession session, @ModelAttribute UserVo userVo) {
		userService.modify(userVo);
		
		if(!"".equals(userVo.getName())) {
			session.setAttribute("authuser", userVo);
		}
		return "redirect:/";
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
