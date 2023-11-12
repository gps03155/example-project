package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.douzon.bookmall.vo.CartVo;

public class CartDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public List<CartVo> select(int member_no){
		List<CartVo> list = new ArrayList<CartVo>();
		
		try {
			conn = getConnection();
			
			String sql = "select c.no, b.title, c.num, b.price from cart c join book b on c.book_no = b.no where member_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, member_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("c.no");
				String title = rs.getString("b.title");
				int num = rs.getInt("c.num");
				int price = rs.getInt("b.price");
				
				CartVo vo = new CartVo();
				
				vo.setNo(no);
				vo.setTitle(title);
				vo.setNum(num);
				vo.setPrice(price);
				
				list.add(vo);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return list;
	}
	
	public List<CartVo> selectList(){
		List<CartVo> list = new ArrayList<CartVo>();
		
		try {
			conn = getConnection();
			
			String sql = "select c.no, b.title, c.num, b.price from cart c join book b on c.book_no = b.no";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("c.no");
				String title = rs.getString("b.title");
				int num = rs.getInt("c.num");
				int price = rs.getInt("b.price");
				
				CartVo vo = new CartVo();
				
				vo.setNo(no);
				vo.setTitle(title);
				vo.setNum(num);
				vo.setPrice(price);
				
				list.add(vo);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return list;
	}
	
	public int insert(int num, int member_no, int book_no) {
		int result = 0;
		
		try {
			conn = getConnection();
			
			String sql = "insert into cart values (null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setInt(2, member_no);
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
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return conn;
	}
}
