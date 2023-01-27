package com.Social11.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_fri_req")
public class Userfriend_request {

	@Id
	private int id;
	private int user_id;
	private int friend_id;
	private String date1;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(int friend_id) {
		this.friend_id = friend_id;
	}
	public String getDate() {
		return date1;
	}
	public void setDate(String date) {
		this.date1 = date;
	}
	@Override
	public String toString() {
		return "Userfriend_request [user_id=" + user_id + ", friend_id=" + friend_id + ", date=" + date1 + "]";
	}
	public Userfriend_request(int user_id, int friend_id, String date) {
		super();
		this.user_id = user_id;
		this.friend_id = friend_id;
		this.date1 = date;
	}
	public Userfriend_request() {
		System.out.println("userfriend_request called");
	}
	
	
	
}
