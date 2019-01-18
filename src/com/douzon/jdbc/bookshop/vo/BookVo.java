package com.douzon.jdbc.bookshop.vo;

public class BookVo {
	private long no;
	private String title;
	private String status;
	private long authorNo;
	private String authorName;

	// private AuthorVo author;

	public long getNo() {
		return no;
	}
	
	@Override
	public String toString() {
		return super.toString();
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
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public long getAuthorNo() {
		return authorNo;
	}
	
	public void setAuthorNo(long authorNo) {
		this.authorNo = authorNo;
	}
	
	public String getAuthorName() {
		return authorName;
	}
	
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}	
}
