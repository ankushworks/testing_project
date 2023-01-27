package com.Social11.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Indexed;

@Entity
@Table(name="black_list")
public class JwtBlackList {

	@Id
   	@GeneratedValue(strategy=GenerationType.AUTO)
	private int  id;
    private String token;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "JwtBlackList [id=" + id + ", token=" + token + "]";
	}
	public JwtBlackList(int id, String token) {
		super();
		this.id = id;
		this.token = token;
	}
	
	public JwtBlackList() {
		System.out.println("Jwt Black list called");
	}
	
}