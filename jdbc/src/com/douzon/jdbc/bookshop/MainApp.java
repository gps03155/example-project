package com.douzon.jdbc.bookshop;

import java.util.List;
import java.util.Scanner;

import com.douzon.jdbc.bookshop.dao.BookDao;
import com.douzon.jdbc.bookshop.vo.BookVo;

public class MainApp {

	public static void main(String[] args) {
		displayBookInfo();

		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.print("대여하고 싶은 책 번호를 입력하세요 : ");
		long num = sc.nextLong();

		sc.close();

		rent(num);
		
		System.out.println();
		displayBookInfo();

	}

	private static void displayBookInfo() {
		System.out.println("***** 도서 정보 출력하기 *****");

		// DB에서 출력
		List<BookVo> bookVo = new BookDao().getList();

		for (BookVo vo : bookVo) {
			System.out.println(vo.getNo() + " " + vo.getTitle() + " " + vo.getStatus() + " " + vo.getAuthorName());
		}
	}

	private static void rent(long no) {
		// 책번호 입력 후 내용 갱신
		boolean statusChk = new BookDao().getStatus(no);

		if (!statusChk) {
			boolean result = new BookDao().updateStatus(no, "대여중");

			if (result) {
				String title = new BookDao().getTitle(no);
				System.out.println(title + "(이)가 대여되었습니다.");
			}
		} else {
			System.out.println("이미 대여중인 책입니다.");
		}
	}
}
