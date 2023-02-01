package com.Social11.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usertemp123")
public class UserEntityTemp {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    @Column(unique=true)
	private String email_address;
    @Column(unique=true)
    private String username;
	private String password;
	private String dateandtime;
	private String firstname;
	private long otp;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDateandtime() {
		return dateandtime;
	}
	public void setDateandtime(String dateandtime) {
		this.dateandtime = dateandtime;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public long getOtp() {
		return otp;
	}
	public void setOtp(long otp) {
		this.otp = otp;
	}
	@Override
	public String toString() {
		return "UserEntityTemp [id=" + id + ", email_address=" + email_address + ", username=" + username
				+ ", password=" + password + ", dateandtime=" + dateandtime + ", firstname=" + firstname + ", otp="
				+ otp + "]";
	}
	public UserEntityTemp(int id, String email_address, String username, String password, String dateandtime,
			String firstname, long otp) {
		super();
		this.id = id;
		this.email_address = email_address;
		this.username = username;
		this.password = password;
		this.dateandtime = dateandtime;
		this.firstname = firstname;
		this.otp = otp;
	}
	
	public UserEntityTemp() {
		System.out.println("User entity temp called");
	}
	
	
}
