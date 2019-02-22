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

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// test
	public int insert(BoardVo vo) { // 새글, 댓글 - 같이 합쳐서 쓰기
		int result = 0;
		
		return result;
	}
	
	// 댓글 삭제
	public int deleteComment(long no) {
		return sqlSession.delete("board.deleteComment", no);
	}
	
	// 댓글 보여주기
	public List<BoardVo> getCommentList(long boardNo){
		List<BoardVo> list = sqlSession.selectList("board.getCommentList", boardNo);
		
		return list;
	}
	
	// 댓글 등록
	public int insertComment(String content, long userNo, int boardNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("content", content);
		map.put("userNo", userNo);
		map.put("boardNo", boardNo);
		
		return sqlSession.insert("board.insertComment", map);
	}
	
	// 검색한 게시글 수
	public int getSearchCount(String search, String kwd) {
		String kwd2 = "%" + kwd + "%";
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("search", search);
		map.put("kwd", kwd);
		map.put("kwd2", kwd2);
		
		return sqlSession.selectOne("board.getSearchCount", map);
	}
	
	// 게시글 전체 수
	public int getTotalCount() {
		return sqlSession.selectOne("board.getTotalCount");
	}

	// 조회수
	public int updateViews(long no) {
		return sqlSession.update("board.updateViews", no);
	}
	
	// 게시글 검색
	public List<BoardVo> getSearch(String search, String kwd, int page) {
		String kwd2 = "%" + kwd + "%";
		int page1 = page * 10;
		int page2 = (page * 10) - 9;
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("search", search);
		map.put("kwd", kwd);
		map.put("kwd2", kwd2);
		map.put("page1", page1);
		map.put("page2", page2);
		
		List<BoardVo> list = sqlSession.selectList("board.getSearch", map);
	
		return list;
	}

	// 답글 - order_no
	public int updateReply(int orderNo, int groupNo) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("orderNo", orderNo);
		map.put("groupNo", groupNo);
		
		return sqlSession.update("board.updateReply", map);
	}

	// 답글 등록
	public int insertReply(String title, String content, int groupNo, int orderNo, int depth, long userNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("title", title);
		map.put("content", content);
		map.put("groupNo", groupNo);
		map.put("orderNo", orderNo + 1);
		map.put("depth", depth + 1);
		map.put("userNo", userNo);
		
		return sqlSession.insert("board.insertReply", map);
	}

	// 답글 - 부모글 정보 (group_no, order_no, depth)
	public BoardVo getInfo(long no) {
		return sqlSession.selectOne("board.parentInfo", no);
	}

	// 게시글 삭제하기
	public int delete(long no) {
		return sqlSession.delete("board.delete", no);
	}

	// 게시글 수정하기
	public int update(String title, String content, long no) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("title", title);
		map.put("content", content);
		map.put("no", no);
		
		// System.out.println(sqlSession.getConfiguration().getMappedStatement("board.update").getSqlSource().getBoundSql("board.update").getSql());
		
		return sqlSession.update("board.update", map);
	}

	// 글 내용 가져오기
	public BoardVo get(long no) {
		return sqlSession.selectOne("board.getByNo", no);
	}

	// 페이지별 게시글 목록 가져오기
	public List<BoardVo> getPageList(int page) {
			int page1 = page * 10;
			int page2 = (page * 10) - 9;
			
			Map<String, Integer> map = new HashMap<String, Integer>();
			
			map.put("page1", page1);
			map.put("page2", page2);
				
			List<BoardVo> list = sqlSession.selectList("board.selectPage", map);	
			
			// System.out.println(sqlSession.getConfiguration().getMappedStatement("board.selectPage").getSqlSource().getBoundSql("board.selectPage").getSql());
			
			return list;
		}
	
	// 게시글 목록 가져오기
	public List<BoardVo> getList() {
		List<BoardVo> list = sqlSession.selectList("board.select");
		
		return list;
	}

	// 게시글 등록
	public int insert(String title, String content, long userNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("title", title);
		map.put("content", content);
		map.put("userNo", userNo);
		
		return sqlSession.insert("board.insert", map);
	}
}
