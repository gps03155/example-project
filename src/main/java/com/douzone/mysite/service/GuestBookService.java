package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestBookDao;
import com.douzone.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {
	@Autowired
	private GuestBookDao guestbookDao;
	
	public void insert(String name, String pass, String content) {
		guestbookDao.insert(name, pass, content);
	}
	
	public List<GuestBookVo> getList(){
		List<GuestBookVo> list = guestbookDao.getList();
		
		return list;
	}
	
	public void delete(GuestBookVo vo) {
		guestbookDao.delete(vo.getNo(), guestbookDao.comparePW(vo.getPassword()));
	}
}
