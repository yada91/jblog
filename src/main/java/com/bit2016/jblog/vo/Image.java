package com.bit2016.jblog.vo;

public class Image {

	private Long no;
	private Long PostNo;
	private Long userNo;
	private String saveName;
	private byte[] data;
	private int fileSize;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Long getPostNo() {
		return PostNo;
	}

	public void setPostNo(Long postNo) {
		PostNo = postNo;
	}

	public Long getUserNo() {
		return userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getMimeType() {
		int index = saveName.lastIndexOf('.');
		if (index > 0) {
			String extension = saveName.substring(index + 1).toLowerCase();
			switch (extension) {
			case "png":
			case "bmp":
			case "gif":
				return "image/" + extension;
			}
		}
		return "image/jpeg";
	}
}
