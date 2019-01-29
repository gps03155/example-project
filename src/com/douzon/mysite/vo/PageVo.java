package com.douzon.mysite.vo;

public class PageVo {
	private int page;
	private int countList = 10;
	private int countPage = 10;
	private int totalCount;
	private int totalPage;
	private int startPage;
	private int endPage;
	
	// 총 페이지 수 구하기
	public int getTotalPage(int totalCount) {
		totalPage = totalCount / countList;
		
		if(totalCount % countList > 0) {
			totalPage++;
		}
		
		if(page > totalPage) {
			page = totalPage;
		}
		
		return totalPage;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		if(page < 1) {
			page = 1;
		}
		
		if(page > totalPage) {
			page = totalPage;
		}
		
		this.page = page;
	}
	
	public int getCountList() {
		return countList;
	}
	
	public void setCountList(int countList) {
		this.countList = countList;
	}
	
	public int getCountPage() {
		return countPage;
	}
	
	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public void setStartPage(int page) {
		if(page < 1) {
			page = 1;
		}
		
		this.startPage = ((page -1 ) / 10) * 10 + 1;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public void setEndPage(int countPage) {
		if(countPage > totalPage) {
			countPage = totalPage;
		}
		
		this.endPage = startPage + countPage - 1;
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
	}
	
	
}
