package com.bit2016.jblog.vo;

public class Category {

	private Long no;
	private Long userNo;
	private String name;
	private String description;
	private String regDate;
	private int num;

	public Long getNo() {
		return no;
	}

	public Long getUserNo() {
		return userNo;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
