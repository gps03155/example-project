package com.douzon.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTest {

	public static void main(String[] args) {
		boolean result = update("Fluffy", "안대혁", "m");
		
		System.out.println(result);
	}

	public static boolean update(String name, String owner, String gender) {
		boolean result = false;		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/webdb?useSSL=false";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("DB 연결 성공");
			
			// 3. SQL문 준비
			String sql = "update pet set owner = ?, gender = ? where name = ?";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 바인딩
			pstmt.setString(1, owner); // DB는 1부터 시작
			pstmt.setString(2, gender);
			pstmt.setString(3, name);
			
			// 5. SQL문 실행
			int count = pstmt.executeUpdate(); // sql문을 넣으면 안됨
			result = count == 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}
