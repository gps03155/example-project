package com.douzone.guestbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.guestbook.dao.GuestBookDao;
import com.douzone.guestbook.vo.GuestBookVo;

@Controller
public class GuestBookController {
	@Autowired
	private GuestBookDao dao;
	
	@RequestMapping("")
	public String index(Model model) {
		model.addAttribute("list", dao.getList());
		
		return "/WEB-INF/views/index.jsp";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(GuestBookVo vo) {
		dao.insert(vo);
		
		return "redirect:/";
	}
	
	@RequestMapping("/deleteform/{no}")
	public String delete(@PathVariable(value="no") Long no, Model model) {
		model.addAttribute("no", no);
		
		return "/WEB-INF/views/deleteform.jsp";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(String no, String password) {
		dao.delete(Integer.parseInt(no), dao.comparePW(password));

		return "redirect:/";
	}
}
