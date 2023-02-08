package com.Social11.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.Social11.models.Post_data;

@Service
public interface PostService {
	
//	@Autowired
//	private IuserPostRepos postrepos;

	public List<Post_data> findfriendspost(int id, int pagenumber);
	
	
}
