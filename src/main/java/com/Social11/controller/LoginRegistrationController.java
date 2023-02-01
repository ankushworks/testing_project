package com.Social11.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Social11.Dao.IuserEntityTemp;
import com.Social11.Dao.IuserRepository;
import com.Social11.helper.JwtTokenUtil;
import com.Social11.models.UserEntity;
import com.Social11.models.UserEntityTemp;
import com.Social11.service.IjavaMailService;

@RestController
public class LoginRegistrationController {

	@Autowired
	IjavaMailService mailService;
	
//	@Autowired
//	UserProfileEntity userProfile;

	@Autowired
	IuserRepository Repository;
	
	@Autowired
	private PasswordEncoder bcyrtp;
	
	
	@Autowired
	private IuserEntityTemp tempentity;
	
	@GetMapping("/createUser")
	public String createUser() {
		return "User Account page";
	}
	
	@PostMapping("/CreateProfile")
	public Map<String,String> createUser(@RequestBody UserEntityTemp userprofile,HttpSession session) {
		Map<String,String> mp = new HashMap<>();
		String message;
		// check email is already present in database or not
		try {
			UserEntity existinguser=Repository.findByusername(userprofile.getUsername());
			String email = userprofile.getEmail_address();
			if(existinguser!=null) {
				// user already exist
				mp.put("text", "User already exist");
				return mp;
			}
			Long OTPcode  = (long)((Math.random()*9*Math.pow(5,6))+Math.pow(5,6));
			System.out.println(email);
			System.out.println(OTPcode);
			message =  mailService.SendMailToEmail(email,OTPcode);
//			session.setAttribute("generatedOtp", OTPcode);
			userprofile.setOtp(OTPcode);
			
			if(message!="") {
			// Saving user data into database
			// userprofile.setPassword(encoder.encode(userprofile.getPassword()));
				
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDateTime now = LocalDateTime.now();
			
			userprofile.setDateandtime(dtf.format(now));
			
			
//			userprofile.setRole("user");
//			session.setAttribute("email", userprofile.getEmail_address());
//			session.setAttribute("date", userprofile.getDateandtime());
//			session.setAttribute("role", userprofile.getRole());
//			session.setAttribute("country", userprofile.getCountry());
//			session.setAttribute("firstname", userprofile.getFirstname());
//			session.setAttribute("id", userprofile.getId());
//			session.setAttribute("lastname", userprofile.getLastname());
//			session.setAttribute("username", userprofile.getUsername());
//			session.setAttribute("password", userprofile.getPassword());
			tempentity.save(userprofile);
			mp.put("text", "Otp Send Succesfully check your email for otp");
			mp.put("response", "200 OK");
			mp.put("email", userprofile.getEmail_address());
			return mp;
			}
			else {
				mp.put("text", "Opps something went wrong opt not send");
				mp.put("response", "400 Bad Request");
				return mp;
			}
		}
		catch(Exception e) {
			System.out.println("Exception while creating account");
			mp.put("text", "Exception occur");
			return mp;
		}
	}
	
	@GetMapping("otpform")
	public String otpform() {
		return "otp";
	}
	
	@PostMapping("/otpVerification")
	public Map<String,String> verifyOtp(@RequestParam("otp") long otp,@RequestParam("email") String email) {
		Map<String,String> mp = new HashMap<>();
		System.out.println("hello");
		System.out.println("my otp is :"+otp+" email is :"+email);
		try {
		if(email!=""){
			System.out.println("working succesfully");
			UserEntityTemp userTemp=this.tempentity.findByemail(email);
			Long otp1 = Long.valueOf(otp);
			System.out.println(userTemp);
			Long OTPcode= userTemp.getOtp();
			System.out.println(OTPcode);
			if(OTPcode.equals(otp1)) {
				UserEntity entity = new UserEntity();
				entity.setEmail_address(userTemp.getEmail_address());
				entity.setUsername(userTemp.getUsername());
				entity.setPassword(bcyrtp.encode(userTemp.getPassword()));
				entity.setDateandtime(userTemp.getDateandtime());
				entity.setFirstname(userTemp.getFirstname());
				Repository.save(entity);
				System.out.println("working or not");
				this.tempentity.deletebyemail(email);
				System.out.println("noot workign");
				mp.put("text", "User Saved Succesfully");
				mp.put("response", "200 OK");
				return mp;
			}
			else {
				mp.put("text", "Incorrect otp");
				mp.put("response", "400 Bad Request");
				return mp;
			}
		}
		else {
			Map<String,String> mp1 = new HashMap<>();
			mp1.put("Error", "Email cannot be empty");
			return mp1;
		}
		}
		catch(Exception e) {
			System.out.println("Error occured "+e);
			Map<String,String> mp1 = new HashMap<>();
			mp1.put("error", "Incorrect email");
			return mp1;
		}
	}
	
	@GetMapping("/forgot")
	public String otpform(Principal principal) {
		System.out.println("hello");
		return "forgotform";
	}
	
	@PostMapping("/verifyOtp")
	public Map<String,String> otpverify(@RequestParam("email") String email) {
		Map<String,String> mp  = new HashMap<>();
		System.out.println("Otp form page "+email);
		Long OTPcode  = (long)((Math.random()*9*Math.pow(5,6))+Math.pow(5,6));
		UserEntity entity=Repository.findByemail(email);
		// In order to confirm the user is registered user or not we send otp in registered email
		if(entity!=null) {
			UserEntityTemp tentity = new UserEntityTemp();
			tentity.setEmail_address(entity.getEmail_address());
			tentity.setOtp(OTPcode);
			this.tempentity.save(tentity);
			mp.put("text", "email send succesfully");
			mp.put("email",entity.getEmail_address());
			mp.put("response", "200 OK");
			String message = mailService.SendMailToEmail(email,OTPcode);
			return mp;
		}
		else {
			System.out.println("User does not exist with this email");
			mp.put("text", "user does not exist with this email");
			mp.put("response", "400 Bad Request");
			return mp;
		}
	}

	@PostMapping("/checkotp")
	public Map<String,String> checkOtp(@RequestParam("otp") long otp,@RequestParam("email") String email) {
		Map<String,String> mp  = new HashMap<>();
		UserEntityTemp entity =this.tempentity.findByemail(email);
		Long Otp = entity.getOtp();
		Long otp1 = Long.valueOf(otp);
		if(otp1.equals(Otp)) {
			mp.put("text", "Otp is Correct");
			mp.put("email",entity.getEmail_address());
			mp.put("response", "Success");
			return mp;	
		}
		else {
			System.out.println("Incorrect otp");
			mp.put("text", "incorrect otp");
			return mp;
		}
	}
	
	@PostMapping("/changePassword")
	public Map<String,String> changepassword(@RequestParam
			("password") String password,@RequestParam("cpassword") String cpassword,
			@RequestParam("email") String email) {
		Map<String,String> mp  = new HashMap<>();
		if(password.equals(cpassword)) {
			try {
				UserEntity user=Repository.findByemail(email);
				user.setPassword(bcyrtp.encode(password));
				this.Repository.save(user);
				this.tempentity.deletebyemail(email);
				System.out.println("password changed");
				mp.put("text", "Password Changed Succesfully");
				mp.put("response", "200");
				return mp;
			}
			catch(Exception e) {
				mp.put("text", "User not found");
				return mp;
			}
		}
		else {
			mp.put("text", "password is not matched");
			return mp;
		}
	}
}