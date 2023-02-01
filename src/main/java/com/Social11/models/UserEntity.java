package com.Social11.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user123")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
    @Column(unique=true)
	private String email_address;
    @Column(unique=true)
    private String username;
	private String password;
	private String country;
	private String dateandtime;
	private String firstname;
	private String lastname;
	private String userUrl;
	private boolean enabled;
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUserUrl() {
		return userUrl;
	}
	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public UserEntity(String email_address, String username, String password,
			String dateandtime,int otp) {
		super();
		this.email_address = email_address;
		this.username = username;
		this.password = password;
		this.dateandtime = dateandtime;
	}
	
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", email_address=" + email_address + ", username=" + username + ", password="
				+ password + ", country=" + country + ", dateandtime=" + dateandtime + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", userUrl=" + userUrl + ", enabled=" + enabled + ", role=" + role + "]";
	}
	public UserEntity() {
		System.out.println("User Entity class called");
	}
	
}
