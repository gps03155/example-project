package com.douzon.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.douzon.guestbook.vo.GuestBookVo;

public class GuestBookDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public List<GuestBookVo> getList(){
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		
		try {
			conn = getConnection();
			
			String sql = "select no, name, message, meg_date from guestbook";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String message = rs.getString("message");
				String msgDate = rs.getString("meg_date");
				
				GuestBookVo vo = new GuestBookVo();
				
				vo.setNo(no);
				vo.setName(name);
				vo.setMessage(message);
				vo.setMsgDate(msgDate);
				
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
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
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public int insert(GuestBookVo vo) {
		int result = 0;
		
		try {
			conn = getConnection();
			
			String sql = "insert into guestbook values(null, ?, password(?), ?, current_timestamp())";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/webdb?useSSL=false";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			System.out.println("DB 연결 성공");
		} catch(Exception e) {
			System.out.println("DB 드라이버 로딩 실패");
			e.printStackTrace();
		}
		
		return conn;
	}
}
