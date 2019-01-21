package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.douzon.bookmall.vo.OrderVo;

public class OrderDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public List<OrderVo> selectOrderList(){
		List<OrderVo> list = new ArrayList<OrderVo>();
		
		try {
			conn = getConnection();
			
			String sql = "select o.no, m.name, m.mail, o.price, o.destination from orderlist o join member m on o.member_no = m.no";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("o.no");
				String name = rs.getString("m.name");
				String mail = rs.getString("m.mail");
				int price = rs.getInt("o.price");
				String dest = rs.getString("o.destination");
				
				OrderVo vo = new OrderVo();
				
				vo.setNo(no);
				vo.setName(name);
				vo.setMail(mail);
				vo.setPrice(price);
				vo.setDestination(dest);
				
				list.add(vo);
			}
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				System.out.println(e);
			}
		}
		
		return list;
	}
	
	public List<OrderVo> selectOrderBook(){
		List<OrderVo> list = new ArrayList<OrderVo>();
		
		try {
			conn = getConnection();
			
			String sql = "select o1.no, b.title, o1.num from (order_book o1 join orderlist o2 on o1.order_no = o2.no) join book b on o1.book_no = b.no";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("o1.no");
				String title = rs.getString("b.title");
				int num = rs.getInt("o1.num");
				
				OrderVo vo = new OrderVo();
				
				vo.setNo(no);
				vo.setTitle(title);
				vo.setNum(num);
				
				list.add(vo);
			}
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				System.out.println(e);
			}
		}
		
		return list;
	}
	
	public int insertOrderList(int price, String dest, int member_no) {
		int result = 0;
		
		try {
			conn = getConnection();
			
			String sql = "insert into orderlist values (null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, price);
			pstmt.setString(2, dest);
			pstmt.setInt(3, member_no);
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				System.out.println(e);
			}
		}
		
		return result;
	}
	
	public int insertOrderBook(int num, int order_no, int book_no) {
		int result = 0;
		
		try {
			conn = getConnection();
			
			String sql = "insert into order_book values(null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setInt(2, order_no);
			pstmt.setInt(3, book_no);
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				System.out.println(e);
			}
		}
		
		return result;
	}
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/bookmall?useSSL=false";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
			
			// System.out.println("DB 연결 성공");
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return conn;
	}
}
