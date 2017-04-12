package com.test.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.test.member.Member;
import com.test.member.MemberDaoImpl;

@Controller
public class MemberController {
	
	@Autowired
	public MemberDaoImpl memberDao;
	
	@RequestMapping(value="login")
	public ModelAndView login(
				@RequestParam(value="error", required=false) String error,
				@RequestParam(value="logout", required=false) String logout 
			){
		ModelAndView mv = new ModelAndView();
		
		if(error != null){
			mv.addObject("error", "아이디 또는 비밀번호가 틀렸습니다.");
		}
		if(logout != null){
			mv.addObject("msg", "성공적으로 로그아웃 되었습니다.");
		}
		mv.setViewName("login");
		return mv;
	}
	
	@RequestMapping("/hello")
	public ModelAndView hello(){
		ModelAndView mv = new ModelAndView();
		
		ArrayList<Member> member_list = new ArrayList<Member>();
		
		member_list = memberDao.selectAll();
		System.out.println("memberdao : "+memberDao);
		System.out.println("member_list : "+member_list);
		mv.setViewName("hello");
		mv.addObject("title", "select_All");
		mv.addObject("member_list", member_list);
		
		return mv;
	}
	
	@RequestMapping("select_one")
	public ModelAndView select_one(@RequestParam int no){
		ModelAndView mv = new ModelAndView();
		
		Member findMember = memberDao.select(no);
		System.out.println("findMember : "+findMember);
		mv.addObject("title", "select_one");
	
		mv.setViewName("hello");
		
		return mv;
	}
	
	@RequestMapping("insert")
	public ModelAndView insert(){
		ModelAndView mv = new ModelAndView();
		
		int last_no = memberDao.select_last_no();
		System.out.println("last_no : "+last_no);
		
		int result = memberDao.insert_member(new Member("name"+(last_no+1), 0));
		System.out.println("insert result : "+result);
		mv.addObject("title", "insert");
	
		mv.setViewName("hello");
		
		return mv;
	}
	
	@RequestMapping("update")
	public ModelAndView update(){
		ModelAndView mv = new ModelAndView();
	
		int last_no = memberDao.select_last_no();
		Member targetMember = memberDao.select(last_no);
		
		targetMember.setMember_name(targetMember.getMember_name()+"_edit!");
		targetMember.setMember_age(targetMember.getMember_age()+1);
		
		memberDao.update(targetMember);
		
		//System.out.println("insert result : "+result);
		mv.addObject("title", "update");
	
		mv.setViewName("hello");
		
		return mv;
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam int no){
		ModelAndView mv = new ModelAndView();
		
		int rrow = memberDao.delete_member(no);
		System.out.println("삭제된 열 갯수 : "+rrow);
		
		mv.addObject("title", "delete");
		mv.setViewName("hello");
		return mv;
	}
	
	@RequestMapping("/timestamp")
	public ModelAndView timestamp(){
		ModelAndView mv = new ModelAndView();
		
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		System.out.println("ts 확인 : "+ts);
		
		mv.addObject("title", "timestamp");
		mv.setViewName("hello");
		return mv;
	}

}
