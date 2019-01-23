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
	
	public int delete(int no, String password) {
		int result = 0;
		
		try {
			conn = getConnection();
			
			String sql = "delete from guestbook where no = ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			pstmt.setString(2, password);
			
			System.out.println(pstmt.toString());
			
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
	
	// 비밀번호 비교
	public String comparePW(String password) {
		String result = null;
		
		try {
			conn = getConnection();
			
			String sql = "select password from guestbook where password = password(?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, password);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				result = rs.getString("password");
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
		
		return result;
	}
	
	// 댓글 목록 가져오기
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
	
	// 댓글 등록
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
