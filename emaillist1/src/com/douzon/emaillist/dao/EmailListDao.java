package com.douzon.emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.douzon.emaillist.vo.EmailListVo;

public class EmailListDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public int insert(EmailListVo vo) {
		int result = 0;
		
		try {
			conn = getConnection();
			
			String sql = "insert into emaillist values (null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getFirstName());
			pstmt.setString(2, vo.getLastName());
			pstmt.setString(3, vo.getEmail());
			
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
	
	public List<EmailListVo> getList(){
		List<EmailListVo> list = new ArrayList<EmailListVo>();
		
		try {
			conn = getConnection();
			
			String sql = "select no, first_name, last_name, email from emaillist order by no desc";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				long no = rs.getLong("no");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String email = rs.getString("email");
				
				EmailListVo vo = new EmailListVo();
				
				vo.setNo(no);
				vo.setFirstName(first_name);
				vo.setLastName(last_name);
				vo.setEmail(email);
				
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
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // WEB-INF/lib 파일에 .jar 파일이 있어야함 : 프로젝트의 deployment build path 수정하기
			
			String url = "jdbc:mysql://localhost:3306/webdb?useSSL=false";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			System.out.println("DB 연결 성공");
		}catch(Exception e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
		
		return conn;
	}
}
