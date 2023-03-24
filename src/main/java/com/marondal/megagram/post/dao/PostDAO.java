package com.marondal.megagram.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.marondal.megagram.post.model.Post;

@Repository
public interface PostDAO {

	public int insertPost(
			@Param("userId") int userId
			, @Param("content") String content
			, @Param("imagePath") String imagePath
			);
	
	public List<Post> selectPostList(@Param("userId") int userId);	

	
	public int insertComment(
			@Param("userId") int userId
			, @Param("postId") int postID
			, @Param("content") String content	
			);
	
	public List<Post> selectCommentList(@Param("userId") int userId);
	
	
}
