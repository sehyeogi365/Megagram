package com.marondal.megagram.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.marondal.megagram.post.model.Comment;
import com.marondal.megagram.post.model.Post;
import com.marondal.megagram.post.model.PostDetail;

@Repository
public interface PostDAO {

	public int insertPost(
			@Param("userId") int userId
			, @Param("content") String content
			, @Param("imagePath") String imagePath
			);
	
	public List<Post> selectPostList();//유저와 관련된 클래스에서 얻어내온다??	포스트비오에서 다오 얻어오는건 자연스럽다. 하지만..

	public int insertLike(@Param("userId") int userId);
	
	public int selectLike(@Param("userId") int userId);
	
	
	public int insertComment(
			@Param("userId") int userId
			, @Param("content") String content	
			);


	public List<Comment> selectCommentList();


	
	
}
