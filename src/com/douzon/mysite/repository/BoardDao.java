package com.douzon.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.douzon.mysite.vo.BoardVo;

public class BoardDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 게시글 목록 가져오기
	public List<BoardVo> getList(){
		List<BoardVo> list = new ArrayList<BoardVo>();
		
		try {
			conn = getConnection();
			
			String sql = "select b.no, b.title, u.name, b.hit, b.write_date from board b join user u on b.user_no = u.no order by b.group_no DESC, b.order_no ASC";
			pstmt = conn.prepareStatement(sql);			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				long no = rs.getLong("b.no");
				String title = rs.getString("b.title");
				String name = rs.getString("u.name");
				int hit = rs.getInt("b.hit");
				String writeDate = rs.getString("b.write_date");
				
				BoardVo vo = new BoardVo();
				
				vo.setNo(no);
				vo.setTitle(title);
				vo.setName(name);
				vo.setHit(hit);
				vo.setWriteDate(writeDate);
				
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
	
	// 게시글 등록
	public int insert(String title, String content, long userNo) {
		int result = 0;
		
		try {
			conn = getConnection();
			
			String sql = "insert into board values (null, ?, ?, CURRENT_TIMESTAMP(), 0, (select IFNULL(max(group_no), 0) + 1 as group_no from board a),  1, 0, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setLong(3, userNo);
			
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
