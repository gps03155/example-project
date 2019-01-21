package com.douzon.bookmall.main;

import java.util.List;

import com.douzon.bookmall.dao.BookDao;
import com.douzon.bookmall.dao.CartDao;
import com.douzon.bookmall.dao.CategoryDao;
import com.douzon.bookmall.dao.MemberDao;
import com.douzon.bookmall.dao.OrderDao;
import com.douzon.bookmall.vo.BookVo;
import com.douzon.bookmall.vo.CartVo;
import com.douzon.bookmall.vo.CategoryVo;
import com.douzon.bookmall.vo.MemberVo;
import com.douzon.bookmall.vo.OrderVo;

public class BookMall {

	public static void main(String[] args) {
		// test 후 insert하는 코드들 넣기
		// primary key 가능한 1번부터
		// 개별젹으로 테스트 후 db 리셋 후 지정된 결과가 나오게끔 출력
		// 회원 : 이름, 전화번호, 이메일, 비밀번호
		// 카테고리 : 번호, 이름
		// 상품리스트 : 제목, 가격, 카테고리  
	    // 카트리스트 : 회원 1명꺼만 보여주면 됨
		// 주문 : 카트에 있는 물건 주문하기
		// 주문 도서 리스트 출력
		// ========== 타이틀 =========== 적어서 알아보기 쉽게 출력하기
		
		// ========== 회원 리스트 ==========
		insertMember("마이콜", "010-4444-4444", "cccc@gmail.com", "5678");
		insertMember("라이언", "010-5555-5555", "dddd@naver.com", "1234");
		insertMember("콘", "010-6666-6666", "ffff@hanmail.net", "3456");
		
		getMemberList();

		// ========== 카테고리 리스트 ==========
		insertCategory("경제");
		insertCategory("수필");
		insertCategory("예술");
		
		getCategorytList();
		
		// ========== 상품 리스트 ==========
		insertBook("자바의 정석", 27000, 1);
		insertBook("경제학콘서트", 25000, 5);
		insertBook("눈먼자들의 도시", 30000, 2);
		
		getBookList();
		
		// ========== 카트(장바구니) 리스트 ==========
		insertCart(5, 2, 2);
		insertCart(10, 1,1);
		
		getCart(2);
		
		// ========== 주문 리스트 ==========
		insertOrderList(30000, "울산광역시", 3);
		insertOrderList(25000, "대전광역시", 4);
		
		getOrderList();
		
		// ========== 주문 도서 리스트 ==========
		insertOrderBook(4, 3, 3);
		insertOrderBook(5, 4, 4);
		
		getOrderBook();
	}
	
	// 회원 리스트 출력
	public static void getMemberList() {
		System.out.println("========== 회원 리스트 ==========");
		
		List<MemberVo> list = new MemberDao().selectList();
		
		for(MemberVo vo : list) {
			System.out.println(vo.getNo() + " " + vo.getName() + " " + vo.getPhone() + " " + vo.getMail());
		}
	}
	
	// 회원 추가
	public static void insertMember(String name, String phone, String mail, String password) {
		int result = new MemberDao().insert(name, phone, mail, password);
		
		if(result == 1) {
			System.out.println("고객이 등록되었습니다.");
		}
	}
	
	// 카테고리 추가
	public static void insertCategory(String category) {
		int result = new CategoryDao().insert(category);
		
		if(result == 1) {
			System.out.println("카테고리가 등록되었습니다.");
		}
	}
	
	// 카테고리 리스트 출력
	public static void getCategorytList() {
		System.out.println("========== 카테고리 리스트 ==========");
		
		List<CategoryVo> list = new CategoryDao().selectList();
		
		for(CategoryVo vo : list) {
			System.out.println(vo.getNo() + " " + vo.getCategory());
		}
	}
	
	// 상품 추가
	public static void insertBook(String title, int price, int category_no) {
		int result = new BookDao().insert(title, price, category_no);
		
		if(result == 1) {
			System.out.println("도서가 추가되었습니다.");
		}
	}
	
	// 상품 리스트 출력
	public static void getBookList() {
		System.out.println("========== 상품 리스트 ==========");
		
		List<BookVo> list = new BookDao().selectList();
		
		for(BookVo vo : list) {
			System.out.println(vo.getNo() + " " + vo.getCategory() + " " + vo.getTitle() + " " + vo.getPrice());
		}
	}

	// 카트(장바구니) 전체 리스트 출력
	public static void getCartList() {
		System.out.println("========== 카트 리스트 ==========");
		
		List<CartVo> list = new CartDao().selectList();
		
		for(CartVo vo : list) {
			System.out.println(vo.getNo() + " " + vo.getTitle() + " " + vo.getNum() + " " + vo.getPrice());
		}
	}
	
	// 카트(장바구니) 회원별 출력
	public static void getCart(int member_no) {
		System.out.println("========== 카트 리스트 ==========");
		
		List<CartVo> list = new CartDao().select(member_no);
		
		for(CartVo vo : list) {
			System.out.println(vo.getNo() + " " + vo.getTitle() + " " + vo.getNum() + " " + vo.getPrice());		
		}
	}
	
	// 카트(장바구니) 추가
	public static void insertCart(int num, int member_no, int book_no) {
		int result = new CartDao().insert(num, member_no, book_no);
		
		if(result == 1) {
			System.out.println("카트(장바구니)에 등록되었습니다.");
		}
	}
	
	// 주문 리스트 출력
	public static void getOrderList() {
		System.out.println("========== 주문 리스트 ==========");
		
		List<OrderVo> list = new OrderDao().selectOrderList();
		
		for(OrderVo vo : list) {
			System.out.println(vo.getNo() + " " + vo.getName() + " " + vo.getMail() + " " + vo.getPrice() + " " + vo.getDestination());
		}
	}
	
	// 주문 도서 리스트 출력
	public static void getOrderBook() {
		System.out.println("========== 주문 도서 리스트 ==========");
		
		List<OrderVo> list = new OrderDao().selectOrderBook();
		
		for(OrderVo vo : list) {
			System.out.println(vo.getNo() + " " + vo.getTitle() + " " + vo.getNum());
		}
	}
	
	// 주문 추가
	public static void insertOrderList(int price, String dest, int member_no) {
		int result = new OrderDao().insertOrderList(price, dest, member_no);
		
		if(result == 1) {
			System.out.println("주문이 등록되었습니다.");
		}
	}
	
	// 주문 도서 추가
	public static void insertOrderBook(int num, int order_no, int book_no) {
		int result = new OrderDao().insertOrderBook(num, order_no, book_no);
		
		if(result == 1) {
			System.out.println("주문 도서가 등록되었습니다.");
		}
	}

}
