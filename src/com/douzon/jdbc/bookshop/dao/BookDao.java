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
import com.douzon.jdbc.bookshop.vo.BookVo;

public class BookDao {
	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement pstmt;

	public List<BookVo> getList(){
		List<BookVo> list = new ArrayList<BookVo>();
		
		try {
			conn = getConnection();
			
			// 3. Statement 객체를 생성
			stmt = conn.createStatement();

			// 4. SQL문 실행
			String sql = "select book.no, book.title, book.status, author.name from book join author on book.author_no = author.no order by book.no";
			rs = stmt.executeQuery(sql);
			
			// 5. 결과 가져오기
			while(rs.next()) {
				// String name = rs.getString(1); // db는 1부터 시작함
				// String owner = rs.getString(2);
				// String birth = rs.getString(3);
				
				long no = rs.getLong("book.no");
				String title = rs.getString("book.title");
				String status = rs.getString("book.status");
				String authorName = rs.getString("author.name");
				
				BookVo vo = new BookVo();
				
				vo.setNo(no);
				vo.setTitle(title);
				vo.setStatus(status);
				vo.setAuthorName(authorName);
				
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
	
	public boolean insert(BookVo bookVo) {
		boolean result = false;

		try {
			conn = getConnection();
			
			String sql = "insert into book values(null, ?, '대여가능', ?);";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bookVo.getTitle());
			pstmt.setLong(2, bookVo.getAuthorNo());
			
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
