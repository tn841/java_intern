package com.test.post;

import java.util.ArrayList;
import java.util.HashMap;

public interface PostDao {
	public int insert_post(Post post);
	public int delete_post(int p_no);
	public int update_post(Post post);
	public Post select_by_no(int p_no);
	public ArrayList<Post> select_all();
	public int select_total_post_count();
	public ArrayList<Post> select_post_by_per_page(HashMap<String, Integer> paramMap);
}
