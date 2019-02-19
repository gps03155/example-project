package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PageVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	
	public Map<String, Object> list(int page){
		// pager 알고리즘
		PageVo vo = new PageVo();
		
		vo.setTotalCount(boardDao.getTotalCount());
		int totalPage = vo.getTotalPage(vo.getTotalCount());
		vo.setStartPage(vo.getPage());
		vo.setEndPage(10);
		vo.setPage(page);
		System.out.println(vo.getStartPage() + " " + vo.getEndPage());
		
		List<BoardVo> list = boardDao.getPageList(vo.getPage());
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list);
		map.put("startPage", vo.getStartPage());
		map.put("endPage", vo.getEndPage());
		map.put("page", vo.getPage());
		map.put("totalCount", vo.getTotalCount());
	
		return map;
	}
	
	public BoardVo getView(long no) {
		BoardVo vo = boardDao.get(no);
		
		return vo;
	}
	
	public void insert(BoardVo boardVo) {
		boardDao.insert(boardVo.getTitle(), boardVo.getContent(), boardVo.getUserNo());
	}
	
	public void delete(long no) {
		boardDao.delete(no);
	}
	
	public void update(BoardVo vo) {
		boardDao.update(vo.getTitle(), vo.getContent(), vo.getNo());
	}
	
	public BoardVo getParentInfo(long no) {
		return boardDao.getInfo(no);
	}
	
	public void insertReply(BoardVo boardVo) {
		boardDao.insertReply(boardVo.getTitle(), boardVo.getContent(), boardVo.getGroupNo(), boardVo.getOrderNo(), boardVo.getDepth(), boardVo.getUserNo());
	}
	
	public void updateReply(BoardVo boardVo) {
		boardDao.updateReply(boardVo.getOrderNo(), boardVo.getGroupNo());
	}
	
	public void updateViews(long no) {
		boardDao.updateViews(no);
	}
	
	public void insertComment(BoardVo boarVo) {
		boardDao.insertComment(boarVo.getContent(), boarVo.getUserNo(), boarVo.getBoardNo());
	}
	
	public List<BoardVo> getCommentList(long boardNo) {
		return boardDao.getCommentList(boardNo);
	}
	
	public void deleteComment(long no) {
		boardDao.deleteComment(no);
	}
}
