package com.bit2016.jblog.vo;

public class Blog {

	private Long no;
	private String title;
	private String logo;

	public Long getNo() {
		return no;
	}

	public String getTitle() {
		return title;
	}

	public String getLogo() {
		return logo;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Override
	public String toString() {
		return "Blog [no=" + no + ", title=" + title + ", logo=" + logo + "]";
	}

}
