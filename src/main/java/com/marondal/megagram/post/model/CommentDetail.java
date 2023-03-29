package com.marondal.megagram.post.model;

public class CommentDetail {//dto 타임라인 하나 표시해서 댓글들 표시하려면 두개의 테이블 조인 한다. ㅇㅇ
	
	private int id;
	private int userId;
	private String userLoginId;
	private String content;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserLoginId() {
		return userLoginId;
	}
	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
}
