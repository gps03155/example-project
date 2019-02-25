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
import com.douzone.security.Auth;

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
		
		boardService.updateViews(no);
		
		model.addAttribute("vo", boardService.getView(no));
		model.addAttribute("list", boardService.getCommentList(no));
		
		return "board/view";
	}
	
	@Auth
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
		
		return "redirect:/board/list/1";
	}
	
	@RequestMapping("/delete/{no}/{page}")
	public String delete(@PathVariable("no") long no, @PathVariable("page") int page, Model model) {	
		boardService.delete(no);
		
		return "redirect:/board/list/1";
	}
	
	@RequestMapping(value="/modify/{no}/{page}", method=RequestMethod.GET)
	public String modify(@PathVariable("no") long no, @PathVariable("page") int page, Model model) {
		model.addAttribute("no", no);
		model.addAttribute("page", page);
		model.addAttribute("vo", boardService.getView(no));
		
		return "board/modify";
	}
	
	@RequestMapping(value="/modify/{no}/{page}", method=RequestMethod.POST)
	public String modify(@PathVariable("no") long no, @PathVariable("page") int page, @ModelAttribute BoardVo vo) {
		boardService.update(vo);
		
		return "redirect:/board/list/1";
	}
	
	@RequestMapping(value="/reply/{no}/{page}", method=RequestMethod.GET)
	public String reply(@PathVariable("no") long no, @PathVariable("page") int page, Model model) {
		model.addAttribute("no", no);
		model.addAttribute("page", page);
		model.addAttribute("vo", boardService.getParentInfo(no));
		
		return "board/reply";
	}
	
	@RequestMapping(value="/reply/{no}/{page}", method=RequestMethod.POST)
	public String reply(@PathVariable("no") long no, @PathVariable("page") int page, Model model, @ModelAttribute BoardVo vo, HttpSession session) {
		//model.addAttribute("no", no);
		//model.addAttribute("page", page);
		UserVo userVo = (UserVo) session.getAttribute("authuser");
		vo.setUserNo(userVo.getNo());
		
		boardService.updateReply(vo);
		boardService.insertReply(vo);
		
		return "redirect:/board/list/1";
	}
	
	@RequestMapping(value="/comment/{no}/{page}", method=RequestMethod.POST)
	public String comment(@PathVariable("no") long no, @PathVariable("page") int page, @ModelAttribute BoardVo vo, HttpSession session) {
		UserVo uservo = (UserVo) session.getAttribute("authuser");
		vo.setUserNo(uservo.getNo());
		
		boardService.insertComment(vo);
		
		return "redirect:/board/view/" + no + "/" + page;
	}
	
	@RequestMapping(value="/deletecomment/{no}/{commentno}/{page}", method=RequestMethod.GET)
	public String comment(@PathVariable("no") long no, @PathVariable("commentno") int commentNo, @PathVariable("page") int page) {
		boardService.deleteComment(commentNo);
		
		return "redirect:/board/view/" + no + "/" + page;
	}
	
	@RequestMapping(value="/search/{page}", method=RequestMethod.POST)
	public String search(@PathVariable("page") int page, Model model, @ModelAttribute BoardVo vo) {
		System.out.println(vo.getSearch());
		System.out.println(vo.getKwd());
		
		model.addAttribute("list", boardService.getSearch(vo.getSearch(), vo.getKwd(), page));
		model.addAttribute("kwd", vo.getKwd());
		model.addAttribute("search", vo.getSearch());
		
		return "board/list";
	}
	
	@RequestMapping("/getsearch/{page}/{kwd}/{search}")
	public String getSearch(@PathVariable("page") int page, @PathVariable("kwd") String kwd, @PathVariable("search") String search, Model model) {
		model.addAttribute("list", boardService.getSearch(search, kwd, page));
		model.addAttribute("kwd", kwd);
		model.addAttribute("search", search);
		
		return "board/list";
	}
}
