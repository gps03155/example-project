package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// Ajax
	public GuestBookVo get(int no) {
		GuestBookVo vo = null;
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "select no, name, message, meg_date from guestbook where no = ? order by meg_date";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new GuestBookVo();
				
				vo.setNo(rs.getInt("no"));
				vo.setName(rs.getString("name"));
				vo.setMessage(rs.getString("message"));
				vo.setMsgDate(rs.getString("meg_date"));
			}
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
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
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return vo;
	}
	
	// Ajax 방명록 가져오기
	public List<GuestBookVo> getList(int page){
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "select no, name, message, meg_date from guestbook order by meg_date desc limit ?, 5";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, (page-1) * 5);
			
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
	
	// 댓글 삭제
	public int delete(int no, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("no", no);
		map.put("password", password);
		
		return sqlSession.delete("guestbook.delete", map);
	}
	
	// 비밀번호 비교
	public String comparePW(String password, int no) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("password", password);
		map.put("no", no);
		
		return sqlSession.selectOne("guestbook.comparePW", map);
	}
	
	// 댓글 목록 가져오기
	public List<GuestBookVo> getList(){
		List<GuestBookVo> list = sqlSession.selectList("guestbook.getList");
		
		return list;
	}
	
	// 댓글 등록
	public int insert(String name, String password, String content) {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("name", name);
		map.put("password", password);
		map.put("content", content);
		
		return sqlSession.insert("guestbook.insert", map);
		
		// long no = vo.getNo();
		// return no;
	}
}
