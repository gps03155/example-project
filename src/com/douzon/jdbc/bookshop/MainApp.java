package com.douzon.jdbc.bookshop;

import java.util.Scanner;

import com.douzon.jdbc.bookshop.dao.BookDao;

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
	}
	
	private static void rent(long no) {
		// 책번호 입력 후 내용 갱신
		new BookDao.updateStatus(no, "대여중");
		
	}
}
