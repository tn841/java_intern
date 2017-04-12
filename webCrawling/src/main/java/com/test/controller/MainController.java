package com.test.controller;

import java.text.SimpleDateFormat;
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

import com.test.realtime.Realtime;
import com.test.realtime.RealtimeDaoImpl;

@Controller
public class MainController {
	
	@Autowired
	public RealtimeDaoImpl realtimeDao;
	
	@RequestMapping("/main")
	public ModelAndView mainPage(
				@RequestParam(value="logout", required=false) String logout
			){
		
		ModelAndView mv = new ModelAndView();
		Date date = new Date();
		String curDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
		if(logout != null){
			mv.addObject("msg", "성공적으로 로그아웃 되었습니다.");
		}
		//System.out.println("realtimeDao 확인 : "+realtimeDao);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
		mv.addObject("curDate", curDate);
		mv.addObject("naver_last", realtimeDao.select_naver_last());
		mv.addObject("daum_last", realtimeDao.select_daum_last());
		mv.addObject("zum_last", realtimeDao.select_zum_last());
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping(value="data_reload", method=RequestMethod.POST)
	@ResponseBody
	public ArrayList<Realtime> realtime_json(){
		System.out.println("realtime_json test");
		ArrayList<Realtime> naver_rt_list = realtimeDao.select_naver_last();
		return naver_rt_list;
	}
	
	@RequestMapping(value="data_reload_map", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, ArrayList<Realtime>> realtime_json_map(){
		System.out.println("realtime_json_map test");
		Date date = new Date();

		HashMap<String, ArrayList<Realtime>> rt_map = new HashMap<>();
		ArrayList<Realtime> naver_rt_list = realtimeDao.select_naver_last();
		ArrayList<Realtime> daum_rt_list = realtimeDao.select_daum_last();
		ArrayList<Realtime> zum_rt_list = realtimeDao.select_zum_last();
		rt_map.put("naver_list", naver_rt_list);
		rt_map.put("daum_list", daum_rt_list);
		rt_map.put("zum_list", zum_rt_list);
		return rt_map;
	}
}
