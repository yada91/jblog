package com.bit2016.jblog.vo;

public class Post {

	private Long no;
	private Long categoryNo;
	private String title;
	private String content;
	private String regDate;

	public Long getNo() {
		return no;
	}

	public Long getCategoryNo() {
		return categoryNo;
	}

	public String getTitle() {
		return title;
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

	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Post [no=" + no + ", categoryNo=" + categoryNo + ", title=" + title + ", content=" + content
				+ ", regDate=" + regDate + "]";
	}

}
