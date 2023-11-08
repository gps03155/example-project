package com.douzone.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// @RequestMapping : type에만 단독으로 사용

@Controller
@RequestMapping("/guestbook/*")
public class GuestBookController {
	
	public void commonFunction() {
		
	}
	
	@ResponseBody
	@RequestMapping
	public String list() {
		return "GuestBookController:list()";
	}
	
	@ResponseBody
	@RequestMapping
	public String add() {
		return "GuestBookController:add()";
	}
	
	@ResponseBody
	@RequestMapping
	public String delete() {
		return "GuestBookController:delete()";
	}
}
