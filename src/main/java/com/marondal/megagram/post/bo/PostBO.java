package com.marondal.megagram.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marondal.megagram.common.FileManagerService;
import com.marondal.megagram.post.dao.PostDAO;
import com.marondal.megagram.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	
	public int addPost(int userId, String content,  MultipartFile file) {
		
		String imagePath = FileManagerService.saveFile(userId, file);
		return postDAO.insertPost(userId, content, imagePath);

	
	}
	
	
		public List<Post>getPostList(int userId) {//여러개이므로 리스트 게시글들 영러개 저장 <>안에 객체 여기도 파라미터
			
			
			return postDAO.selectPostList(userId);
		
		
		}
		
		

		
		
		
	}

	
	

