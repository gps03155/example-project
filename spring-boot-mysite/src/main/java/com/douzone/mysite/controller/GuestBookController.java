package com.douzone.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	@Autowired
	private GuestBookService guestbookService;

	@RequestMapping("/list")
	public String list(Model model, @ModelAttribute GuestBookVo guestBookVo) {
		model.addAttribute("list", guestbookService.getList());
		
		return "guestbook/list";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute @Valid GuestBookVo guestBookVo, BindingResult result, Model model) {
		if(result.hasErrors()) {
			System.out.println(result);
			
			model.addAllAttributes(result.getModel());
			model.addAttribute("list", guestbookService.getList());
			
			return "guestbook/list";
		}
		
		guestbookService.insert(guestBookVo.getName(), guestBookVo.getPassword(), guestBookVo.getMessage());
		
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(Model model, @PathVariable("no") int no) {
		model.addAttribute("no", no);		
		
		return "guestbook/deleteform";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@ModelAttribute GuestBookVo vo) {
		guestbookService.delete(vo);
		
		return "redirect:/guestbook/list";
	}
}
