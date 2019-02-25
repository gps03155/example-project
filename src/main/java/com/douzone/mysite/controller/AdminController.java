package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.security.Auth;
import com.douzone.security.Auth.Role;

@Controller
@Auth(Role.ADMIN)
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private SiteService siteService;
	
	@RequestMapping("")
	public String main(Model model) {
		model.addAttribute("siteVo", siteService.select());
		
		return "admin/main";
	}
	
	@RequestMapping(value="/main/update", method=RequestMethod.POST)
	public String update(@ModelAttribute SiteVo siteVo) {
		siteVo.setProfile("/assets/images/profile.png");
		
		siteService.update(siteVo);
		
		return "redirect:/";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
}
