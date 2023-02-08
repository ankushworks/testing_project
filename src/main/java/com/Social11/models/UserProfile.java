package com.Social11.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserProfile {

	@Id
	private int profile_id;
	private int totalPost;
	private int totalFriends;
	private String username;
	private String profile_url;
	@OneToMany
	private List<Post_data> Allpost;
	public int getProfile_id() {
		return profile_id;
	}
	public void setProfile_id(int profile_id) {
		this.profile_id = profile_id;
	}
	public int getTotalPost() {
		return totalPost;
	}
	public void setTotalPost(int totalPost) {
		this.totalPost = totalPost;
	}
	public int getTotalFriends() {
		return totalFriends;
	}
	public void setTotalFriends(int totalFriends) {
		this.totalFriends = totalFriends;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProfile_url() {
		return profile_url;
	}
	public void setProfile_url(String profile_url) {
		this.profile_url = profile_url;
	}
	
	public List<Post_data> getAllpost() {
		return Allpost;
	}
	public void setAllpost(List<Post_data> allpost) {
		Allpost = allpost;
	}
	@Override
	public String toString() {
		return "UserProfile [profile_id=" + profile_id + ", totalPost=" + totalPost + ", totalFriends=" + totalFriends
				+ ", username=" + username + ", profile_url=" + profile_url + ", Allpost=" + Allpost + "]";
	}

	public UserProfile(int profile_id, int totalPost, int totalFriends, String username, String profile_url,
			List<Post_data> allpost) {
		super();
		this.profile_id = profile_id;
		this.totalPost = totalPost;
		this.totalFriends = totalFriends;
		this.username = username;
		this.profile_url = profile_url;
		Allpost = allpost;
	}
	
	public UserProfile() {
		System.out.println("User profile class called");
	}
	
}
