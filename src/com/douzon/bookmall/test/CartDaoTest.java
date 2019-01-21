package com.douzon.bookmall.test;

import java.util.List;

import com.douzon.bookmall.dao.CartDao;
import com.douzon.bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
		// 카트 리스트 2개
		insertCart(10, 3, 2);
		getList();
	}

	public static void getList() {
		List<CartVo> list = new CartDao().selectList();
		
		for(CartVo vo : list) {
			System.out.println(vo.getNo() + " " + vo.getTitle() + " " + vo.getNum() + " " + vo.getPrice());
		}
	}
	
	public static void insertCart(int num, int member_no, int book_no) {
		int result = new CartDao().insert(num, member_no, book_no);
		
		if(result == 1) {
			System.out.println("카트(장바구니)에 등록되었습니다.");
		}
	}
}
