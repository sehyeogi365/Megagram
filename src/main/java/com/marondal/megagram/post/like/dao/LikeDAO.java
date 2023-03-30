package com.marondal.megagram.post.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	public int insertLike(
			@Param("userId") int userId
			, @Param("postId") int postId);
	
	public int selectCountLike(@Param("postId") int postId);
	
	public int selectCountLikeByUserId(//둘다 일치하는것의 갯수 리턴
			@Param("userId") int userId
			,@Param("postId") int postId);//두개의 정보
	
	public int deleteLike(
			@Param("userId") int userId
			, @Param("postId") int postId);//비오가 원하는것만큼 수행못할수도 있음 다오하나로 처리 못하는것도 있어서 ㄱ

	public int deleteLikeByPostId(@Param("postId") int postId);//좋아요까지삭제

}
