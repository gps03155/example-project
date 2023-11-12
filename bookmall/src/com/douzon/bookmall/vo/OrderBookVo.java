package com.douzon.bookmall.vo;

public class OrderBookVo {
	private int no;
	private String title;
	private int num;
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return getNo() + " " + getTitle() + " " + getNum();
	}
	
}
