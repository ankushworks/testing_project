package com.Social11.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {

	public CurrentUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
			super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
	}

//	public CurrentUser(String username, String password, boolean enabled, boolean accountNonExpired,
//			boolean credentialsNonExpired, boolean accountNonLocked,
//			Collection<? extends GrantedAuthority> authorities,String firstName, String email_address, String usernames) {
//		
//	    	super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//	    	
//	    	this.firstName=firstName;
//	    	this.email_address=email_address;
//	    	this.username=usernames;
//	}
//	
//	private String firstName;
//	private String email_address;
//	private String username;
//    
//	public String getFirstName() {
//		return firstName;
//	}
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	public String getEmail_address() {
//		return email_address;
//	}
//	public void setEmail_address(String email_address) {
//		this.email_address = email_address;
//	}
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
    
}
