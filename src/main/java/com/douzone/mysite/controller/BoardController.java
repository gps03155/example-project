package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/list/{page}")	
	public String list(@PathVariable("page") int page, Model model) {
		model.addAttribute("list", boardService.list(page));
		
		return "board/list";
	}
	
	@RequestMapping("/view/{no}/{page}")
	public String view(@PathVariable("no") long no, @PathVariable("page") int page, Model model) {
		model.addAttribute("no", no);
		model.addAttribute("page", page);
		model.addAttribute("vo", boardService.getView(no));
		
		return "board/view";
	}
}
