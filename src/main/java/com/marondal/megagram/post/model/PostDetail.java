package com.marondal.megagram.post.model;

public class PostDetail {//한 카드에 필요한 데이터들
	
	private int id;
	private int userId;
	private String loginId;
	private int likeCount;//좋아요 누적갯수
	private boolean	isLike;	//좋아요 했는지 안했는지 여부 저장
	private String content;
	private String imagePath;
	
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
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public boolean isLike() {
		return isLike;
	}
	public void setLike(boolean isLike) {// isLike가 아닌 like is로 시작하는건 게터세터규칙이 다르다 함
		this.isLike = isLike;
	}
	
	//dto(데이터를 저장하기 위해 만든클래스)
	
}
