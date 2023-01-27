package com.Social11.Dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Social11.models.UserFriends;

@Repository
public interface FriendRepository extends JpaRepository<UserFriends,Integer>{
//	@Transactional
//	@Modifying
//	@Query("UPDATE UserFriends u SET u.request_report=:k WHERE u.from=:m and u.to=:n")
//	public void updateFriendRequest(@Param("m") int from, @Param("n") int to, @Param("k") int report)
}
