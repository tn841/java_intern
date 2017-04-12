package com.test.post;

import java.util.ArrayList;
import java.util.HashMap;

import com.test.mapper.PostMapper;

public class PostDaoImpl implements PostDao {
	private PostMapper postMapper;

	public void setPostMapper(PostMapper postMapper) {
		this.postMapper = postMapper;
	}

	@Override
	public int insert_post(Post post) {
		// TODO Auto-generated method stub
		return postMapper.insert_post(post);
	}

	@Override
	public int delete_post(int p_no) {
		// TODO Auto-generated method stub
		return postMapper.delete_post(p_no);
	}

	@Override
	public int update_post(Post post) {
		// TODO Auto-generated method stub
		return postMapper.update_post(post);
	}

	@Override
	public Post select_by_no(int p_no) {
		// TODO Auto-generated method stub
		return postMapper.select_by_no(p_no);
	}

	@Override
	public ArrayList<Post> select_all() {
		// TODO Auto-generated method stub
		return postMapper.select_all();
	}

	@Override
	public int select_total_post_count() {
		// TODO Auto-generated method stub
		return postMapper.select_total_post_count();
	}

	@Override
	public ArrayList<Post> select_post_by_per_page(HashMap<String, Integer> paramMap) {
		// TODO Auto-generated method stub
		return postMapper.select_post_by_per_page(paramMap);
	}
	
	

	
	
}
