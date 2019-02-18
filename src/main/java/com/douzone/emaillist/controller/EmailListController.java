package com.douzone.emaillist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.emaillist.dao.EmailListDao;
import com.douzone.emaillist.vo.EmailListVo;

@Controller
public class EmailListController {
	@Autowired
	private EmailListDao dao;
	
	@ResponseBody
	@RequestMapping("")
	public String list() {		
		List<EmailListVo> list = dao.getList();
		System.out.println(list);
		
		return "/WEB-INF/views/list.jsp";
	}
}

