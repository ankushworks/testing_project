package com.Social11.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Social11.models.UserEntity;

public interface IuserRepository extends JpaRepository<UserEntity,Integer>{

	public UserEntity findByusername(String username);
	
//	@Query(value = "SELECT * FROM user123 u WHERE u.email_address :harmeetsinghghai567@gmail.com", nativeQuery = true)
	
//	@Query("SELECT u from UserEntity u")
//	public List<UserEntity> findByemail_address();
	
	
	@Query("SELECT u from UserEntity u WHERE u.email_address= :m")
	public UserEntity findByemail(@Param("m") String email);
	
}
