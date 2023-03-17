package com.marondal.megagram.user.dao;

import org.apache.ibatis.annotations.Param;

public interface UserDAO {

	
	public int insertUser( 
		@Param("loginId") String loginId
		, @Param("password") String password
		, @Param("name") String name
		, @Param("email") String email
		);
		
	
}
