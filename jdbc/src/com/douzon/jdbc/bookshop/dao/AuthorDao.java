package com.douzon.jdbc.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.douzon.jdbc.bookshop.vo.AuthorVo;

public class AuthorDao {
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	public List<AuthorVo> getList() {
		List<AuthorVo> list = new ArrayList<AuthorVo>();
		
		try {
			conn = getConnection();
			
			// 3. Statement 객체를 생성
			stmt = conn.createStatement();

			// 4. SQL문 실행
			String sql = "select no, name, bio from author";
			rs = stmt.executeQuery(sql);
			
			// 5. 결과 가져오기
			while(rs.next()) {
				// String name = rs.getString(1); // db는 1부터 시작함
				// String owner = rs.getString(2);
				// String birth = rs.getString(3);
				
				Long no = rs.getLong("no");
				String name = rs.getString("name");
				String bio = rs.getString("bio");
				
				AuthorVo vo = new AuthorVo();
				
				vo.setNo(no);
				vo.setName(name);
				vo.setBio(bio);
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(stmt != null) {
					rs.close();
				}
				
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public boolean insert(AuthorVo authorVo) {
		boolean result = false;

		try {
			conn = getConnection();
			
			String sql = "insert into author values(null, ?, ?);";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, authorVo.getName());
			pstmt.setString(2, authorVo.getBio());

			int count = pstmt.executeUpdate();

			return count == 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
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

	private Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/webdb?useSSL=false";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		
			System.out.println("DB 연결 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
