package com.javaex.vo;

public class AttachVo {

	private int no ;
	private String orgName;
	private String saveName;
	private String filePath;
	private long fileSize;
	private int userNo;
	private String name;

	public AttachVo() {

	}
	
	public AttachVo(String orgName, String saveName, String filePath, long fileSize, int userNo) {
		this.orgName = orgName;
		this.saveName = saveName;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.userNo = userNo;
	}


	public AttachVo(int no, String orgName, String saveName, String filePath, long fileSize, int userNo, String name) {
		this.no = no;
		this.orgName = orgName;
		this.saveName = saveName;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.userNo = userNo;
		this.name = name;
	}
	
	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "AttachVo [no=" + no + ", orgName=" + orgName + ", saveName=" + saveName + ", filePath=" + filePath
				+ ", fileSize=" + fileSize + ", userNo=" + userNo + ", name=" + name + "]";
	}
	
	
	
	
	
}
