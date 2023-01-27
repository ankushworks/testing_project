package com.Social11.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Social11.models.JwtBlackList;

@Repository
public interface JwtBlackListRepository extends JpaRepository<JwtBlackList,Integer>{

	JwtBlackList findByTokenEquals(String token);
	
}
