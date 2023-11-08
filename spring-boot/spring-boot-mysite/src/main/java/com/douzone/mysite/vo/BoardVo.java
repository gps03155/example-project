package com.douzone.mysite.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
}
