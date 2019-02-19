package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

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
	
	@RequestMapping(value="/write/{page}", method=RequestMethod.GET)
	public String write(@PathVariable("page") int page, Model model) {
		model.addAttribute("page", page);
		
		return "board/write";
	}
	
	@RequestMapping(value="/write/{page}", method=RequestMethod.POST)
	public String write(@PathVariable("page") int page, @ModelAttribute BoardVo vo, HttpSession session) {
		UserVo uservo = (UserVo) session.getAttribute("authuser");
		vo.setUserNo(uservo.getNo());
		
		boardService.insert(vo);
		
		return "redirect:/board/list/" + page;
	}
}
