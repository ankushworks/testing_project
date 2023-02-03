package com.Social11.models;

import org.springframework.security.core.userdetails.UserDetails;

public class UserLogin {
    private String access_token;
    private UserDetails userDetails;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}

