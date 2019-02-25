package com.douzone.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.security.Auth;
import com.douzone.security.Auth.Role;

@Controller
// @Auth(Role.ADMIN)
@RequestMapping("/admin")
public class AdminController {
	
	@Auth(Role.ADMIN)
	@RequestMapping("")
	public String main() {
		return "admin/main";
	}
	
	@Auth(Role.ADMIN)
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
}
