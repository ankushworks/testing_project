package com.Social11.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Social11.models.UserPost;

public interface IuserPostdata extends JpaRepository<UserPost,Integer>{
	
	
}
