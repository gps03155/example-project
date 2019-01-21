package com.douzon.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.douzon.bookmall.vo.CategoryVo;

public class CategoryDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public List<CategoryVo> selectList(){
		List<CategoryVo> list = new ArrayList<CategoryVo>();
		
		try {
			conn = getConnection();
			
			String sql = "select no, category from category";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("no");
				String category = rs.getString("category");
				
				CategoryVo vo = new CategoryVo();
				
				vo.setNo(no);
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
	
	public int insert(String category) {
		int result = 0;
		
		try {
			conn = getConnection();
			
			String sql = "insert into category values (null, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, category);
			
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
