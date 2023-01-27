package com.Social11.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Social11.Dao.FriendRepository;
import com.Social11.Dao.IUserFriendReqRepo;
import com.Social11.Dao.IuserPostLikeRepo;
import com.Social11.Dao.IuserPostRepos;
import com.Social11.Dao.IuserPostdata;
import com.Social11.Dao.IuserRepository;
import com.Social11.Dao.UserRepository;
import com.Social11.helper.JwtUserDetailService;
import com.Social11.models.Post_data;
import com.Social11.models.UserEntity;
import com.Social11.models.UserFriends;
import com.Social11.models.UserPost;
import com.Social11.models.UserPostLike;
import com.Social11.models.Userfriend_request;
import com.Social11.service.IjavaMailService;

@RestController
public class UserController {
	
	@Autowired
	private IuserPostRepos userpost;
	
	@Autowired
	private IuserPostdata userpostrelation;
	
	@Autowired
	private IuserRepository userdata;
	
	@Autowired
	private FriendRepository friendaccept;
	
	@Autowired
	private JwtUserDetailService userdetailservice;
	
	@Autowired
	private IUserFriendReqRepo userfriendrequest;
	
	@Autowired
	private IuserPostLikeRepo postlike;
	
	
	@GetMapping("/user")
	public UserEntity homepage(Principal principal,HttpSession session) {
		String name = userdetailservice.currentLoggedInUser();
		UserEntity user = userdata.findByusername(name);
		session.setAttribute("user_id", user.getId());
		return user;
	}		
	
	@PostMapping("user/post")
	public Map<String,String> userpost(@RequestBody Post_data user_post,HttpSession session) {
		try {
			int userid=(Integer)session.getAttribute("user_id");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			user_post.setDate(dtf.format(now));
			userpost.save(user_post);
			System.out.println(user_post.getId());
			System.out.println(userid);
			
//			Post created in Post_data model class
			int user_post_id = user_post.getId();
			UserPost post = new UserPost();
			post.setUser_id(userid);
			post.setPost_id(user_post_id);
			System.out.println("wrking or not");
			System.out.println(post);
			this.userpostrelation.save(post);
			System.out.print("User post Uploaded");
			Map<String,String> mp = new HashMap<>();
			mp.put("text", "Post Succesfully Created");
			mp.put("response", "200 OK");
			return mp;
		}
		catch(Exception e) {
			System.out.println(e+" Invalid post");
			Map<String,String> mp = new HashMap<>();
			mp.put("text", "Oops something went wrong");
			return mp;
		}
	}
	
//	fetch the friends post
	@GetMapping("user/home")
	public List<Post_data> getallpost(HttpSession session) {
		int userid=(Integer)session.getAttribute("user_id");
		List<Post_data> lg= this.userpost.fetchAllPost2(userid);
		return lg;
	}
	
//	User can send Friend request to many users
	@PostMapping("user/{id}/request")
	public String friendRequest(@PathVariable("id") int to,HttpSession session) {
		System.out.println("requese is going");
		int user_id = (Integer)session.getAttribute("user_id");
		int friend_id = to;
		try {
	//  Dateandtime  		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		Userfriend_request friend_request = new Userfriend_request(user_id,friend_id,dtf.format(now));
	//  end dateandtime
		userfriendrequest.save(friend_request);
		}
		catch(Exception e) {
			System.out.println("Error while sending request"+e);
		}
		Map<String,String> mp = new HashMap<>();
		mp.put("text", "Friend Request send succesfully");
		mp.put("Response", "200 OK");
		return "success";
	}
	
//	Accept or reject a users friend request
	@PostMapping("user/{id}/accept")
	@ResponseBody
	public Map<String,String> friendAccept(@PathVariable("id") int from,HttpSession session) {
		int user_id=(Integer)session.getAttribute("user_id");
		System.out.println(user_id);
		try {
//		first entry from user to friend id
	//  Dateandtime  		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		UserFriends userfriends = new UserFriends(user_id,from,dtf.format(now));
		friendaccept.save(userfriends);
		
//		second entry from friends id to user
		UserFriends userfriends1 = new UserFriends(from,user_id,dtf.format(now));
		friendaccept.save(userfriends1);
		
//      third entry for deleting record from userfrien_request table
		userfriendrequest.deletefriendrequest(from, user_id);
		}
		catch(Exception e) {
			System.out.println(e+"Error on saving data");
		}
		Map<String,String> mp = new HashMap<>();
		mp.put("text", "Friendship is successfull");
		return mp;
	}
	
//	Accept or reject a users friend request
	@PostMapping("user/{id}/reject")
	public Map<String,String> friendDenied(@PathVariable("id") int friendId,HttpSession session) {
		int user_id = (Integer)session.getAttribute("user_id");
		Map<String,String> mp = new HashMap<>();
		mp.put("text", "Friendship is Denied");
		try {
			userfriendrequest.deletefriendrequest(friendId, user_id);
		}
		catch(Exception e) {
			System.out.println("Friend Request data not deleted "+e);
		}
		return mp;
	}
//	
	
	@PostMapping("user/like/101")
	public Map<String,String> userpostlike(@RequestBody UserPostLike post_like,HttpSession session){
		int user_id = (Integer)session.getAttribute("user_id");
		try {
			Map<String,String> mp = new HashMap<>();
			post_like.setUser_id(user_id);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			post_like.setDate1(dtf.format(now));
			UserPostLike postlike = this.postlike.isUserLikePost(post_like.getPost_id(), user_id);
//			if(postlike.isUserLikePost(post_like.getPost_id(), user_id)==null) {
//				this.postlike.removeRecordfromtable(post_like.getPost_id(), user_id);
//			}
//			else {
//				this.postlike.save(post_like);
//			}
			System.out.println(postlike);
			if(postlike==null) {
				this.postlike.save(post_like);
			}
			else {
				this.postlike.updateRecordfromtable(post_like.getPost_id(), user_id,post_like.getReport());
			}
			mp.put("Response", "Success");
			return mp;
		}
		catch(Exception e) {			
			Map<String,String> mp = new HashMap<>();
			mp.put("Response", "Failure");
			System.out.println(e);
			return mp;
		}
	}
}
