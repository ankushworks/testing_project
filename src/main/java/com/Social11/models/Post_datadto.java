package com.Social11.models;

public class Post_datadto {

	private int id;
	private String post_title;
	private String post_data;
	private String post_img_url;
	private String post_video_url;
	private String date1;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_data() {
		return post_data;
	}
	public void setPost_data(String post_data) {
		this.post_data = post_data;
	}
	public String getPost_img_url() {
		return post_img_url;
	}
	public void setPost_img_url(String post_img_url) {
		this.post_img_url = post_img_url;
	}
	public String getPost_video_url() {
		return post_video_url;
	}
	public void setPost_video_url(String post_video_url) {
		this.post_video_url = post_video_url;
	}
	public String getDate() {
		return date1;
	}
	public void setDate(String date) {
		this.date1 = date;
	}
	
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	
	
	@Override
	public String toString() {
		return "Post_data [id=" + id + ", post_title=" + post_title + ", post_data=" + post_data + ", post_img_url="
				+ post_img_url + ", post_video_url=" + post_video_url + ", date1=" + date1 + "]";
	}
		
	public Post_datadto(int id, String post_title, String post_data, String post_img_url, String post_video_url,
			String date1, UserPost userpost) {
		super();
		this.id = id;
		this.post_title = post_title;
		this.post_data = post_data;
		this.post_img_url = post_img_url;
		this.post_video_url = post_video_url;
		this.date1 = date1;
	}
	public Post_datadto() {
		System.out.println("Post data dto called");
	}
	
}
