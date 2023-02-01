package com.Social11.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Social11.models.Post_data;
import com.Social11.models.UserPost;

public interface IuserPostRepos extends JpaRepository<Post_data,Integer> {

	@Query("SELECT u from UserPost u WHERE u.user_id= :id")
	public List<Post_data> findByid(@Param("id") int user_id);
	
//	@Query("UPDATE Post_data u SET u.userpost.user_id= :uid, u.userpost.post_id= :pid where u.id= :uid")
//	public void updatebyid(@Param("uid") int userid, @Param("pid") int postid);
	
//	datetime default_timespam
	
//	@Query("SELECT u from Post_data u inner join UserPost v on u.id = v.post_id where v.user_id in (SELECT friendid from UserFriends w where w.userid =:user_id)")
//	public List<Post_data> fetchAllPost(@Param("user_id") int user_id); 
//	
	@Query(value = "SELECT * from post_da1 inner join user_post on post_da1.post_id = user_post.post_id where user_post.user_id in (SELECT friend_id from user_fri_map where user_fri_map.user_id =:user_id) OR (SELECT user_id from user_fri_map where user_fri_map.user_id =:user_id)", nativeQuery = true)
	public List<Post_data> fetchAllPost2(@Param("user_id") int user_id); 
//	
	@Query(value="select * from user_post", nativeQuery = true)
	public List<UserPost> fetchpost();
}