package com.javaex.vo;

public class TBoardVo {

	private int no;
	private String title;
	private int hit;
	private String regDate;
	private int userNo;
	private String name;
	private String content;
	
	public TBoardVo() {
		
	}

	public TBoardVo(int no, String title, int hit, String regDate, int userNo, String name, String content) {
		this.no = no;
		this.title = title;
		this.hit = hit;
		this.regDate = regDate;
		this.userNo = userNo;
		this.name = name;
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

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

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
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

	@Override
	public String toString() {
		return "TBoardVo [no=" + no + ", title=" + title + ", hit=" + hit + ", regDate=" + regDate + ", userNo="
				+ userNo + ", name=" + name + ", content=" + content + "]";
	}

	
	
}