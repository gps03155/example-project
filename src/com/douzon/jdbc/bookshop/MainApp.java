package com.douzon.jdbc.bookshop;

import java.util.List;
import java.util.Scanner;

import com.douzon.jdbc.bookshop.dao.BookDao;
import com.douzon.jdbc.bookshop.vo.BookVo;

public class MainApp {

	public static void main(String[] args) {
		displayBookInfo();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("대여하고 싶은 책 번호를 입력하세요 : ");
		long num = sc.nextLong();
		
		sc.close();
		
		rent(num);
		displayBookInfo();
		
	}

	private static void displayBookInfo() {
		System.out.println("***** 도서 정보 출력하기 *****");
		
		// DB에서 출력
		List<BookVo> bookVo = new BookDao().getList();
		
		for(BookVo vo : bookVo) {
			System.out.println(vo.getNo() + " " + vo.getTitle() + " " + vo.getStatus() + " " + vo.getAuthorName());
		}
	}
	
	private static void rent(long no) {
		// 책번호 입력 후 내용 갱신
		BookDao bookDao = new BookDao();
		boolean result = bookDao.updateStatus(no, "대여중");
		
		if(result) {
			System.out.println("정보가 갱신되었습니다.");
		}
	}
}
