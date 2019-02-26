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
import com.mysql.jdbc.SQLError;

@Repository
public class GuestBookDao {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public int getLastID() {
		return sqlSession.selectOne("guestbook.lastID");
	}
	
	// Ajax insert
	public GuestBookVo get(int no) {
		return sqlSession.selectOne("guestbook.ajaxInsert", no);
	}
	
	// Ajax 방명록 가져오기
	public List<GuestBookVo> getList(int page){
		return sqlSession.selectList("guestbook.ajaxGetList", page);
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
