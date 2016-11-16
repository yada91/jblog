package com.bit2016.jblog.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Jusers {

	private Long no;
	@NotEmpty
	private String id;
	@NotEmpty
	@Length(min = 2, max = 8)
	private String name;
	@NotEmpty
	private String password;
	private String regDate;

	public Long getNo() {
		return no;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
