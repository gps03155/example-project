package com.douzone.mysite.vo;

public class BoardVo {
	private Long no;
	private String title;
	private String content;
	private String writeDate;
	private Integer hit;
	private Integer groupNo;
	private Integer orderNo;
	private Integer depth;
	private Long userNo;
	private String name;
	private Integer rowNum;
	private Integer boardNo;
	private String kwd;
	private String search;
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getKwd() {
		return kwd;
	}

	public void setKwd(String kwd) {
		this.kwd = kwd;
	}

	public Integer getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(Integer boardNo) {
		this.boardNo = boardNo;
	}

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNo() {
		return no;
	}
	
	public void setNo(Long no) {
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
	
	public Integer getHit() {
		return hit;
	}
	
	public void setHit(Integer hit) {
		this.hit = hit;
	}
	
	public Integer getGroupNo() {
		return groupNo;
	}
	
	public void setGroupNo(Integer groupNo) {
		this.groupNo = groupNo;
	}
	
	public Integer getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	public Integer getDepth() {
		return depth;
	}
	
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	
	public Long getUserNo() {
		return userNo;
	}
	
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
}
