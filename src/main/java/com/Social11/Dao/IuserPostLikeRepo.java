package com.Social11.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Social11.models.UserPostLike;

public interface IuserPostLikeRepo extends JpaRepository<UserPostLike,Integer>{


	@Query(value="select * from user_post_like1 where POST_ID =:postid AND USER_ID =:userid", nativeQuery = true)
	public UserPostLike isUserLikePost(@Param("postid") int postid , @Param("userid") int userid);
	
	@Transactional
	@Modifying
	@Query(value="update user_post_like1 u set report =:report where u.POST_ID =:postid AND u.USER_ID =:userid", nativeQuery = true)
	public int updateRecordfromtable(@Param("postid") int postid , @Param("userid") int userid , @Param("report") int report);
	
}
