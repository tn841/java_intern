package com.test.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.post.Post;
import com.test.post.PostDaoImpl;

@Controller
public class PostController {
	
	@Autowired 
	private PostDaoImpl postDao;
	
	@RequestMapping(value="/post")
	public ModelAndView post_list(
			@RequestParam(value="per_page", required=false) String per_page,
			@RequestParam(value="cur_page", required=false) String cur_page
			){
		ModelAndView mv = new ModelAndView();
		HashMap<String, Integer> paramMap = new HashMap<String, Integer>();
		
		if(per_page == null){
			per_page = "8";
		}
		if(cur_page == null){
			cur_page = "1";
		}
		int total_page_cnt = 0;
		int total_post_cnt = postDao.select_total_post_count();
		
		total_page_cnt = total_post_cnt/Integer.parseInt(per_page);
		if(total_post_cnt % Integer.parseInt(per_page) != 0){
			total_page_cnt ++;
		}
		
		int s_page = Integer.parseInt(per_page) * (Integer.parseInt(cur_page)-1);
		int e_page = Integer.parseInt(per_page);
		paramMap.put("s_page", s_page);
		paramMap.put("e_page", e_page);
		
		ArrayList<Post> post_list = postDao.select_post_by_per_page(paramMap);
	
		mv.addObject("per_page", per_page);
		mv.addObject("cur_page", cur_page);
		mv.addObject("total_page_cnt", total_page_cnt);
		mv.addObject("total_post_cnt", total_post_cnt);
		mv.addObject("post_list", post_list);
		System.out.println("per_page : "+per_page);
		System.out.println("cur_page : "+cur_page);
		System.out.println("total_page_cnt : "+total_page_cnt);
		System.out.println("total_post_cnt : "+total_post_cnt);
		
		
		mv.setViewName("post");
		return mv;
	}
	
	@RequestMapping(value="/post_write_form")
	public String post_write_form(){
		return "post_write_form";
	}
	
	@RequestMapping(value="/post_insert_action", method=RequestMethod.POST)
	public String post_insert(@RequestParam String title, String content, String writer){
		String path = "";
	
		content = content.replaceAll("<","&lt;").replaceAll(">", "&gt;");
		title = title.replaceAll("<","&lt;").replaceAll(">", "&gt;");
		
		Post newpost = new Post(title, content, writer, new Date());
		
		if(postDao.insert_post(newpost) == 1){
			System.out.println("insert 성공.");
			path = "redirect:post"; 
		}else{
			path = "error";
		}
		return path;
	}
	
	
	@RequestMapping(value="post_view")
	public ModelAndView post_view(@RequestParam int no){
		ModelAndView mv = new ModelAndView();
		
		Post findp = postDao.select_by_no(no);
		System.out.println(no);
		mv.addObject("find_post", findp);
		mv.setViewName("post_view");
		
		return mv;
	}//post_view
	
	@RequestMapping(value="post_delete", method=RequestMethod.POST)
	@ResponseBody
	public String post_delete(@RequestParam int no){
		int row = postDao.delete_post(no);
		if(row == 1){
			return "del_ok";
		}
		else{
			return "del_fail";
		}
	}//post_delete
	
	@RequestMapping(value="post_modify", method=RequestMethod.POST)
	public ModelAndView post_modify(@RequestParam int no){
		ModelAndView mv = new ModelAndView();
		mv.addObject("modify_post", postDao.select_by_no(no));
		mv.setViewName("post_modify_form");
		return mv;
	}//post_modify
	
	
	@RequestMapping(value="post_modify_action", method=RequestMethod.POST)
	public String post_modify(@RequestParam String title, String content, int no){
		

		//System.out.println("before content:"+content);
		content = content.replaceAll("<","&lt;").replaceAll(">", "&gt;");
		title = title.replaceAll("<","&lt;").replaceAll(">", "&gt;");
		//System.out.println("after content:"+content);
		
		Post find_post = postDao.select_by_no(no);
		find_post.setP_content(content);
		find_post.setP_title(title);
		int result = postDao.update_post(find_post);
		
		if(result == 1){
			return "redirect:post";
		}
		
		return "error";
	}
	
}
