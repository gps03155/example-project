package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.douzon.bookmall.vo.BookVo;

public class BookDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int insert(String title, int price, int category_no) {
		int result = 0;

		try {
			conn = getConnection();

			String sql = "insert into book values (null, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setInt(2, price);
			pstmt.setInt(3, category_no);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
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
	
	public List<BookVo> selectList(){
		List<BookVo> list = new ArrayList<BookVo>();
		
		try {
			conn = getConnection();
			
			String sql = "select c.no, b.title, b.price, c.category from book b join category c on b.category_no = c.no";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("c.no");
				String title = rs.getString("b.title");
				int price = rs.getInt("b.price");
				String category = rs.getString("c.category");
				
				BookVo vo = new  BookVo();
				
				vo.setNo(no);
				vo.setTitle(title);
				vo.setPrice(price);
				vo.setCategory(category);
				
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
