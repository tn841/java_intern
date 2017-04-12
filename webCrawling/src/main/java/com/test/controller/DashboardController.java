package com.test.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.plaf.synth.SynthSplitPaneUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.realtime.Realtime;
import com.test.realtime.RealtimeDaoImpl;

@Controller
public class DashboardController {
	
	@Autowired
	public RealtimeDaoImpl realtimeDao;
	
	@RequestMapping(value="/dash_board_1")
	public ModelAndView dashboard_main1(){
		ModelAndView mv = new ModelAndView();
		Date date = new Date();
		String curDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
		
		
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("portal", "naver");
		
		ArrayList<Realtime> r_list = realtimeDao.select_r_keyword(paramMap);
		
		mv.addObject("r_list", r_list);
		mv.addObject("cur_date", new Timestamp(new Date().getTime()).toString().substring(0, 16));
		mv.setViewName("dashboard_1");
		return mv;
	}
	
	@RequestMapping(value="/dash_board_2")
	public ModelAndView dashboard_main2(){
		ModelAndView mv = new ModelAndView();
		Date date = new Date();
		String curDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
		mv.addObject("cur_date", new Timestamp(new Date().getTime()).toString().substring(0, 16));
		mv.setViewName("dashboard_2");
		return mv;
	}
	
	@RequestMapping(value="/chart_json")
	@ResponseBody
	public ArrayList<HashMap<String, String>> dashboard_main_json(
				@RequestParam String period, String e_date, String portal) throws ParseException{
		HashMap<String, String> paramMap = new HashMap<String, String>();
			
		//문자열로 부터 Date 객체 생성
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = format.parse(e_date);
		
		//Date객체로부터 calendar객체 생성
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
	
		calendar.add(Calendar.MINUTE, -Integer.parseInt(period));
		String pervious_date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(calendar.getTime());
		System.out.println(" ** 포털 : "+portal);
		
		paramMap.put("portal", portal);
		paramMap.put("period", pervious_date);
		paramMap.put("e_date", e_date);
		System.out.println("조회 기간 : "+pervious_date+", "+e_date);
		ArrayList<HashMap<String, String>> result = realtimeDao.select_keyword_count(paramMap);
		return result;
	}
	
	
	//select_line_chart_data
	@RequestMapping(value="/list_chart")
	@ResponseBody
	public ArrayList<Realtime> select_line_chart_data(
							@RequestParam 	String table_name,
											int rank,
											int interval,
											int limit){
		System.out.println("받은 데이터 확인 : "+table_name+", "+rank+", "+interval+", "+limit);
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("table_name", table_name);
		paramMap.put("r_rank", rank);
		paramMap.put("interval", interval);
		paramMap.put("limit", limit);
		
		return realtimeDao.select_line_chart_data(paramMap);
	}
	
		
	@RequestMapping(value="/portal_select")
	@ResponseBody
	public ArrayList<Realtime> select_portal(
				@RequestParam String portal
			){
		System.out.println("portal:"+portal);
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("portal", portal);
		return realtimeDao.select_r_keyword(paramMap);
	}//portal_select
	
	@RequestMapping(value="/keyword_select")
	@ResponseBody
	public ArrayList<Realtime> select_keyword(
				@RequestParam(value="portal", required=false) String portal,
				@RequestParam(value="keyword", required=false) String keyword
			){
		System.out.println("portal:"+portal+", keyword:"+keyword);
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("portal", portal);
		paramMap.put("keyword", keyword);
		paramMap.put("interval", 15);
		return realtimeDao.select_by_keyword(paramMap);
	}
	
}
