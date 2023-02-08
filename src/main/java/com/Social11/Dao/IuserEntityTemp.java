package com.Social11.Dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Social11.models.UserEntityTemp;

public interface IuserEntityTemp extends JpaRepository<UserEntityTemp,Integer> {

	@Query("SELECT u from UserEntityTemp u WHERE u.email_address= :m")
	public UserEntityTemp findByemail(@Param("m") String email);
	
	@Transactional
	@Modifying
	@Query("Delete from UserEntityTemp u WHERE u.email_address= :email")
	public void deletebyemail(@Param("email") String email);
	
//	@Query("insert into usertemp123(id,email_address,otp) values(109,'paramjeetsingh123@gmail.com',12919)", nativeQuery = true)
//	public int updateRecordfromtable(@Param("postid") int postid , @Param("userid") int userid , @Param("report") int report);
}