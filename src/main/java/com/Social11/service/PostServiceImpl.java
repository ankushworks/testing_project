package com.Social11.service;

import com.Social11.Dao.IuserPostRepos;
import com.Social11.models.Post_data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private IuserPostRepos postrepo;
	
	@Override
	public List<Post_data> findfriendspost(int id, int pagenumber) {
		int pageSize = 3;
		Pageable p = PageRequest.of(pagenumber, pageSize);
		Page<Post_data> posts=postrepo.fetchAllPost2(id,p);
		if(posts != null) {
			List<Post_data> Allposts=posts.getContent();
			return Allposts;
		} else {
			new ArrayList<Post_data>();
		}
		return null;
	}

}
