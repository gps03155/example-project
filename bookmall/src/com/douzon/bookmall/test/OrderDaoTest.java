package com.douzon.bookmall.test;

import java.util.List;

import com.douzon.bookmall.dao.OrderDao;
import com.douzon.bookmall.vo.OrderBookVo;
import com.douzon.bookmall.vo.OrderVo;

public class OrderDaoTest {
	public static void main(String[] args) {
		// 주문 리스트, 주문도서 리스트 : 1개, 2개
		// insertOrderList(30000, "부산광역시", 2);
		// insertOrderBook(1, 2, 3);
		
		getOrderList();
		getOrderBook();
	}
	
	public static void getOrderList() {
		List<OrderVo> list = new OrderDao().selectOrderList();
		
		for(OrderVo vo : list) {
			System.out.println(vo.getNo() + " " + vo.getName() + " " + vo.getMail() + " " + vo.getPrice() + " " + vo.getDestination());
		}
	}
	
	public static void getOrderBook() {
		List<OrderBookVo> list = new OrderDao().selectOrderBook();
		
		for(OrderBookVo vo : list) {
			System.out.println(vo.getNo() + " " + vo.getTitle() + " " + vo.getNum());
		}
	}
	
	public static void insertOrderList(int price, String dest, int member_no) {
		int result = new OrderDao().insertOrderList(price, dest, member_no);
		
		if(result == 1) {
			System.out.println("주문이 등록되었습니다.");
		}
	}
	
	public static void insertOrderBook(int num, int order_no, int book_no) {
		int result = new OrderDao().insertOrderBook(num, order_no, book_no);
		
		if(result == 1) {
			System.out.println("주문 도서가 등록되었습니다.");
		}
	}
}
