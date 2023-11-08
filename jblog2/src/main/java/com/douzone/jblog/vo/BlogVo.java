package com.douzone.jblog.vo;

public class BlogVo {
	private long userNo;
	private String title;
	private String logo;
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getUserNo() {
		return userNo;
	}
	
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getLogo() {
		return logo;
	}
	
	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Override
	public String toString() {
		return "BlogVo [userNo=" + userNo + ", title=" + title + ", logo=" + logo + "]";
	}
}
