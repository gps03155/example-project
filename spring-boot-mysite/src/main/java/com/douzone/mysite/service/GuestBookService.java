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
	
	public int delete(GuestBookVo vo) {
		return guestbookDao.delete(vo.getNo(), guestbookDao.comparePW(vo.getPassword(), vo.getNo()));
	}
	
	public List<GuestBookVo> ajaxGetList(int page){
		List<GuestBookVo> list = guestbookDao.getList(page);
		
		return list;
	}
	
	public GuestBookVo ajaxInsert(int no) {
		return guestbookDao.get(no);
	}
	
	public int getLastID() {
		return guestbookDao.getLastID();
	}
}
