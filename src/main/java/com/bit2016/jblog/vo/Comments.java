package com.bit2016.jblog.vo;

public class Comments {

	private Long no;
	private Long postNo;
	private String content;
	private String regDate;

	public Long getNo() {
		return no;
	}

	public Long getPostNo() {
		return postNo;
	}

	public String getContent() {
		return content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public void setPostNo(Long postNo) {
		this.postNo = postNo;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
