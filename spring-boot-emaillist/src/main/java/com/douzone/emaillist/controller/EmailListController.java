package com.douzone.emaillist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.emaillist.dao.EmailListDao;
import com.douzone.emaillist.vo.EmailListVo;

@Controller
public class EmailListController {
	@Autowired
	private EmailListDao dao;
	
	/*
	@RequestMapping("")
	public ModelAndView list() {		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("list", dao.getList());
		mav.setViewName("/WEB-INF/views/list.jsp");
		
		return mav;
	}
	*/
	
	@RequestMapping({"", "/list"})
	public String list(Model model) {		
		model.addAttribute("list", dao.getList());
		
		return "list";
	}
	
	@RequestMapping("/form")
	public String form() {
		return "form";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(EmailListVo vo) {
		dao.insert(vo);
		
		return "redirect:/";
	}
}

