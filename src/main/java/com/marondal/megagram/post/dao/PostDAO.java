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

	public int insertLike(@Param("userId") int userId
						  , @Param("postId") int postId);
	
	public int selectCountLike(@Param("postId") int postId);
	
	//좋아요 빈하트냐 아니냐 조회?
	public int selectCountLikeByUserId(//둘다 일치하는것의 갯수 리턴
			@Param("userId") int userId
			,@Param("postId") int postId);//두개의 정보
	
	public int deleteLike(@Param("postId") int postId);
	
	
	
	
	public int insertComment(
			@Param("userId") int userId
			, @Param("content") String content	
			);


	public List<Comment> selectCommentList();


	
	
}
