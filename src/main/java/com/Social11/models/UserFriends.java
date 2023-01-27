package com.Social11.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_fri_map")
public class UserFriends {

	@Id
   	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="user_id")
	private int userid;
	@Column(name="friend_id")
	private int friendid;
	@Column(name="friendship_date")
	private String date1;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getFriendid() {
		return friendid;
	}
	public void setFriendid(int friendid) {
		this.friendid = friendid;
	}
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	@Override
	public String toString() {
		return "UserFriends [id=" + id + ", userid=" + userid + ", friendid=" + friendid + ", date1=" + date1 + "]";
	}
	public UserFriends(int userid, int friendid, String date1) {
		this.userid = userid;
		this.friendid = friendid;
		this.date1 = date1;
	}
	
}	