package com.marondal.megagram.post.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondal.megagram.post.like.dao.LikeDAO;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	public int addLike(int userId, int postId) {
		
		return likeDAO.insertLike(userId, postId);
	}
	//게시글하나에 대응되는 좋아요 갯수?	
	public int getLikeCount(int postId) {//갯수리턴이니 마찬가지로 int
		return likeDAO.selectCountLike(postId);
	}
	//좋아요가 어떤거인지 구현??? 빈하트냐 풀하트냐??
	public boolean isLike(int userId, int postId) {
		
		int count = likeDAO.selectCountLikeByUserId(userId, postId);
		
		// 조회된 개수가 0개면 좋아요 안함
		if(count == 0) {
			return false;
		} else {		// 조회된 개수가 1이상이면 좋아요 
		
			return true;
		}
		
	}
	
	public int unLike(int userId, int postId) {
		return likeDAO.deleteLike(userId, postId);
		//실제수행은 다오가
	}
}
