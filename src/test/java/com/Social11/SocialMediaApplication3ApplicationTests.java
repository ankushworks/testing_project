package com.Social11;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Social11.Dao.IuserRepository;
import com.Social11.models.UserEntity;

@SpringBootTest
class SocialMediaApplication3ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	IuserRepository respository;
	
//	@Test
//	public void testGetuserDetails() {
//		System.out.println("givee");
//		String email = "harmeetsinghghai567@gmail.com";
//		List<UserEntity> entity = this.respository.findByemail_address();
//		entity.forEach(e->{
//			System.out.println(e);
//		});
//	}
	
	@Test
	public void testoneuserDetails() {
		System.out.println("hii user");
		String email ="harmeetsinghghai567@gmail.com";
		UserEntity user=this.respository.findByemail(email);
		System.out.println(user);
	}
	
}
