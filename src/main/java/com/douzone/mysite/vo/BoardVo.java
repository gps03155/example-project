package com.douzone.mysite.vo;

public class BoardVo {
	private long no;
	private String title;
	private String content;
	private String writeDate;
	private int hit;
	private int groupNo;
	private int orderNo;
	private int depth;
	private long userNo;
	private String name;
	private int rowNum;
	private int boardNo;
	
	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNo() {
		return no;
	}
	
	public void setNo(long no) {
		this.no = no;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getWriteDate() {
		return writeDate;
	}
	
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	
	public int getHit() {
		return hit;
	}
	
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	public int getGroupNo() {
		return groupNo;
	}
	
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	
	public int getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public long getUserNo() {
		return userNo;
	}
	
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
}
