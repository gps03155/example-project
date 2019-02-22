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
		
		/*
		int searchCount = 0;
		String sql = null;
		
		try {
			conn = dataSource.getConnection();
			
			if ("title".equals(search)) {
				System.out.println("title Count");
				
				sql = "select count(*) " +
					  "from (select b.no as b_no, b.title, u.name, b.hit, b.write_date, b.depth, u.no " + 
					         "from board b join user u on b.user_no = u.no " +
					         "where b.title Like ?) tmp";
				
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, "%" + kwd + "%");
			} 
			else if ("content".equals(search)) {
				sql = "select count(*) " +
						  "from (select b.no as b_no, b.title, u.name, b.hit, b.write_date, b.depth, u.no " + 
						         "from board b join user u on b.user_no = u.no " + 
						         "where b.contents Like ?) tmp";
				
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, "%" + kwd + "%");
			} 
			else if ("name".equals(search)) {
				sql =  "select count(*) " +
						  "from (select b.no as b_no, b.title, u.name, b.hit, b.write_date, b.depth, u.no " + 
				                 "from board b join user u on b.user_no = u.no " + 
				                 "where u.name Like ?) tmp";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, "%" + kwd + "%");
			} 
			else if ("full".equals(search)) {
				if ("".equals(kwd)) {
					sql = "select count(*) " +
							  "from (select b.no as b_no, b.title, u.name, b.hit, b.write_date, b.depth, u.no " + 
					                 "from board b join user u on b.user_no = u.no) tmp";
					pstmt = conn.prepareStatement(sql);
				} 
				else {
					sql = "select count(*) " +
							  "from (select b.no as b_no, b.title, u.name, b.hit, b.write_date, b.depth, u.no " + 
				                     "from board b join user u on b.user_no = u.no " + 
					                 "where b.title Like ? or b.contents Like ? or u.name Like ?) tmp";

					pstmt = conn.prepareStatement(sql);

					pstmt.setString(1, "%" + kwd + "%");
					pstmt.setString(2, "%" + kwd + "%");
					pstmt.setString(3, "%" + kwd + "%");
				}
			}
			
			rs = pstmt.executeQuery();
			System.out.println(pstmt.toString());
			
			if(rs.next()) {
				searchCount = rs.getInt("count(*)");
				System.out.println(searchCount);
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
		
		return searchCount;
		*/
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
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("search", search);
		map.put("kwd", kwd);
		
		String kwd2 = "%" + kwd + "%";
		int page1 = page * 10;
		int page2 = (page * 10) - 9;
		
		map.put("kwd2", kwd2);
		map.put("page1", page1);
		map.put("page2", page2);
		
		System.out.println("aa");
		System.out.println(map.get("search"));
		List<BoardVo> list = sqlSession.selectList("board.getSearch", map);
		System.out.println(list);
		// System.out.println(sqlSession.getConfiguration().getMappedStatement("board.getSearch").getSqlSource().getBoundSql("board.getSearch").getSql());
		System.out.println("bb");
		
		return list;
		
		/*
		List<BoardVo> list = new ArrayList<BoardVo>();
		String sql = null;

		try {
			conn = dataSource.getConnection();

			if ("title".equals(search)) {
				sql = "select * " + 
					  "from (select * " + 
						     "from (select @rownum:=@rownum + 1 as row_num, b_no, title, name, hit, write_date, depth, no " + 
						            "from ((select b.no as b_no, b.title, u.name, b.hit, b.write_date, b.depth, u.no " + 
						                    "from board b join user u on b.user_no = u.no " + 
						                    "where b.title Like ? " + 
						                    "order by b.group_no DESC, b.order_no ASC) pagetable, (SELECT @rownum:=0) tmp)) pagetable " + 
						     "where row_num <= ?) pagetable " + 
						"where row_num >= ?";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, "%" + kwd + "%");
				pstmt.setInt(2, page * 10);
				pstmt.setInt(3, (page * 10) -9 ); 
			} 
			else if ("content".equals(search)) {
				sql = "select * " + 
					  "from (select * " + 
						     "from (select @rownum:=@rownum + 1 as row_num, b_no, title, name, hit, write_date, depth, no " + 
						            "from ((select b.no as b_no, b.title, u.name, b.hit, b.write_date, b.depth, u.no " + 
						                    "from board b join user u on b.user_no = u.no " + 
						                    "where b.contents Like ? " + 
						                    "order by b.group_no DESC, b.order_no ASC) pagetable, (SELECT @rownum:=0) tmp)) pagetable " + 
						     "where row_num <= ?) pagetable " + 
						"where row_num >= ?";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, "%" + kwd + "%");
				pstmt.setInt(2, page * 10);
				pstmt.setInt(3, (page * 10) - 9);
			} 
			else if ("name".equals(search)) {
				sql = "select * " + 
						"from(select * " + 
						      "from( select @rownum:=@rownum + 1 as row_num, b_no, title, name, hit, write_date, depth, no " + 
						             "from ((select b.no as b_no, b.title, u.name, b.hit, b.write_date, b.depth, u.no " + 
						                     "from board b join user u on b.user_no = u.no " + 
						                     "where u.name Like ? " + 
						                     "order by b.group_no DESC, b.order_no ASC) pagetable, (SELECT @rownum:=0) tmp)) pagetable " + 
						      "where row_num <= ?) pagetable " + 
						"where row_num >= ?";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, "%" + kwd + "%");
				pstmt.setInt(2, page * 10);
				pstmt.setInt(3, (page * 10) - 9); 
			} 
			else if ("full".equals(search)) {
				if ("".equals(kwd)) {
					sql = "select * " + 
						  "from (select * " + 
							     "from (select @rownum:=@rownum + 1 as row_num, b_no, title, name, hit, write_date, depth, no " + 
							            "from ((select b.no as b_no, b.title, u.name, b.hit, b.write_date, b.depth, u.no " + 
							                    "from board b join user u on b.user_no = u.no " + 
							                    "order by b.group_no DESC, b.order_no ASC) pagetable, (SELECT @rownum:=0) tmp)) pagetable " + 
							     "where row_num <= ?) pagetable " + 
							"where row_num >= ?";
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, page * 10);
					pstmt.setInt(2, (page * 10) - 9);
				} 
				else {
					sql = "select * " + 
							"from (select * " + 
							       "from (select @rownum:=@rownum + 1 as row_num, b_no, title, name, hit, write_date, depth, no " + 
							              "from ((select b.no as b_no, b.title, u.name, b.hit, b.write_date, b.depth, u.no " + 
							                      "from board b join user u on b.user_no = u.no " + 
							                      "where b.title Like ? or b.contents Like ? or u.name Like ? " + 
							                      "order by b.group_no DESC, b.order_no ASC) pagetable, (SELECT @rownum:=0) tmp)) pagetable " + 
							       "where row_num <= ?) pagetable " + 
							"where row_num >= ?";

					pstmt = conn.prepareStatement(sql);

					pstmt.setString(1, "%" + kwd + "%");
					pstmt.setString(2, "%" + kwd + "%");
					pstmt.setString(3, "%" + kwd + "%");
					pstmt.setInt(4, page * 10);
					pstmt.setInt(5, (page * 10) - 9);
				}
			}

			rs = pstmt.executeQuery();
			System.out.println(pstmt.toString());

			while (rs.next()) {
				long no = rs.getLong("b_no");
				String title = rs.getString("title");
				String name = rs.getString("name");
				int hit = rs.getInt("hit");
				String writeDate = rs.getString("write_date");
				int depth = rs.getInt("depth");
				long userNo = rs.getLong("no");
				int rowNum = rs.getInt("row_num");

				BoardVo vo = new BoardVo();

				vo.setNo(no);
				vo.setTitle(title);
				vo.setName(name);
				vo.setHit(hit);
				vo.setWriteDate(writeDate);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setRowNum(rowNum);

				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("검색 조건을 입력해주세요.");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
		*/
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
		System.out.println(no);
		System.out.println("!!!!" + sqlSession);
		System.out.println(sqlSession.selectOne("board.getByNo", no).toString());
		BoardVo vo = sqlSession.selectOne("board.getByNo", no);
		System.out.println(vo.getTitle());
		System.out.println(vo.getContent());
		System.out.println(vo.getUserNo());
		System.out.println(vo.getNo());
		
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
