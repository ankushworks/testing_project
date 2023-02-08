package com.Social11.controller;

import com.Social11.Dao.*;
import com.Social11.helper.JwtTokenUtil;
import com.Social11.helper.JwtUserDetailService;
import com.Social11.models.*;
import com.Social11.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private IuserRepository repository;
	
	@GetMapping("/user")
	public UserProfile homepage(Principal principal,HttpSession session,@RequestHeader("Authorization") String bearerToken) {
		System.out.println(bearerToken);
		bearerToken = bearerToken.substring(7);
		Map<String,Object> claims=this.jwtTokenUtil.getAllClaimsFromToken(bearerToken);
		
		Map<String,Object> mp = new HashMap<>();
		String username= (String)claims.get("Username");
		int id = (int)claims.get("Id");
		System.out.println(id);
		UserProfile profile = new UserProfile();
		if(this.userpost.fetchMyPost(id) != null) {
			List<Post_data> posts = this.userpost.fetchMyPost(id);
			profile.setTotalPost(this.userpost.totalpost(id));
			System.out.println(posts.toString());
			profile.setAllpost(this.userpost.fetchMyPost(id));
		}
		profile.setProfile_id(id);
		profile.setUsername(username);
		return profile;
	}		
		
	@PostMapping("/user/UpdateProfile")
	public Map<String,String> profileUpdate(@RequestHeader("Authorization") String bearerToken,@RequestBody UserEntity entity){
		bearerToken = bearerToken.substring(7);
		Map<String,Object> claims=this.jwtTokenUtil.getAllClaimsFromToken(bearerToken);
		int id=(int)claims.get("Id");
		try {
			UserEntity entity1=this.repository.findById(id).get();
			System.out.println(entity1);
			entity1.setFirstname(entity.getFirstname());
			entity1.setLastname(entity.getLastname());
			entity1.setCountry(entity.getCountry());
			entity1.setUserUrl(entity.getUserUrl());
			this.repository.save(entity1);
		}
		catch(Exception e) {
			Map<String,String> mp1 = new HashMap<>();
			mp1.put("Response", "Error updating Userdata");
			return mp1;
		}
		Map<String,String> mp = new HashMap<>();
		mp.put("Message","User Updated Succesfully");
		return mp;
	}
	
	@PostMapping("user/home/post")
	public Map<String,String> userpost(@RequestBody Post_data user_post,@RequestHeader("Authorization") String bearerToken) {
		bearerToken = bearerToken.substring(7);
		Map<String,Object> claims=this.jwtTokenUtil.getAllClaimsFromToken(bearerToken);
		int id=(int)claims.get("Id");
		try {
			int userid=id;
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
	@GetMapping("user/home/{page}")
	public List<Post_data> getallpost(@RequestHeader("Authorization") String bearerToken , @PathVariable("page") Integer page, HttpSession session) {
		bearerToken = bearerToken.substring(7);
		Map<String,Object> claims=this.jwtTokenUtil.getAllClaimsFromToken(bearerToken);
		int id = (int)claims.get("Id");
		System.out.println(id);
		List<Post_data> posts = this.postService.findfriendspost(id,page);
		return posts;
	}
	
//	User can send Friend request to many users
	@PostMapping("user/{id}/request")
	public String friendRequest(@PathVariable("id") int to,@RequestHeader("Authorization") String bearerToken) {
		bearerToken = bearerToken.substring(7);
		Map<String,Object> claims=this.jwtTokenUtil.getAllClaimsFromToken(bearerToken);
		int id = (int)claims.get("Id");
		int user_id = id;
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
	public Map<String,String> friendAccept(@PathVariable("id") int from,@RequestHeader("Authorization") String bearerToken) {
		bearerToken = bearerToken.substring(7);
		Map<String,Object> claims=this.jwtTokenUtil.getAllClaimsFromToken(bearerToken);
		int id = (int)claims.get("Id");
		int user_id=id;
		System.out.println(user_id);
		try {
//		first entry from user to friend id
//      Dateandtime  		
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
	public Map<String,String> friendDenied(@PathVariable("id") int friendId,@RequestHeader("Authorization") String bearerToken) {
		bearerToken = bearerToken.substring(7);
		Map<String,Object> claims=this.jwtTokenUtil.getAllClaimsFromToken(bearerToken);
		int id = (int)claims.get("Id");
		int user_id = id;
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
	
	@PostMapping("user/comment/102")
	public Map<String,String> postComment(@RequestBody UserPostComment post_comment, HttpSession session){
		return null;
	}
	
}
