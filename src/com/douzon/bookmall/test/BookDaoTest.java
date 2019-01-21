package com.douzon.bookmall.test;

import java.util.List;

import com.douzon.bookmall.dao.BookDao;
import com.douzon.bookmall.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		// 상품리스트 3개
		// insertBook("Head First Java", 30000, 1);
		getList();
	}
	
	public static void insertBook(String title, int price, int category_no) {
		int result = new BookDao().insert(title, price, category_no);
		
		if(result == 1) {
			System.out.println("도서가 추가되었습니다.");
		}
	}
	
	public static void getList() {
		List<BookVo> list = new BookDao().selectList();
		
		for(BookVo vo : list) {
			System.out.println(vo.getNo() + " " + vo.getCategory() + " " + vo.getTitle() + " " + vo.getPrice());
		}
	}

}
