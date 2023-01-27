package com.Social11.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Social11.models.Post_data;
import com.Social11.models.Userfriend_request;


@Repository
public interface IUserFriendReqRepo  extends JpaRepository<Userfriend_request,Integer>{

	
	@Transactional
	@Modifying
	@Query("DELETE from Userfriend_request u WHERE u.friend_id =:friendid AND u.user_id =:id")
	public void deletefriendrequest(@Param("id") int user_id, @Param("friendid") int friend_id);

}
