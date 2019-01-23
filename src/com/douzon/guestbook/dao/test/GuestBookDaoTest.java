package com.douzon.guestbook.dao.test;

import java.util.List;

import com.douzon.guestbook.dao.GuestBookDao;
import com.douzon.guestbook.vo.GuestBookVo;

public class GuestBookDaoTest {

	public static void main(String[] args) {
		GuestBookVo vo = new GuestBookVo();
		
		vo.setName("또치");
		vo.setPassword("5678");
		vo.setMessage("나는 나다");
		
		// insert(vo);
		
		getList();
	}
	
	public static void getList() {
		List<GuestBookVo> list = new GuestBookDao().getList();
		
		for(GuestBookVo vo : list) {
			System.out.println(vo.toString());
		}
	}

	public static void insert(GuestBookVo vo) {
		int result = new GuestBookDao().insert(vo);
		
		if(result == 1) {
			System.out.println("댓글이 등록되었습니다.");
		}
	}
}
