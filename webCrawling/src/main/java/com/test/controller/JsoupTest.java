package com.test.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.realtime.Realtime;
import com.test.realtime.RealtimeDaoImpl;

@Controller
public class JsoupTest {
	
	@Autowired
	public RealtimeDaoImpl realtimeDao;
	
	@RequestMapping("/jsoup")
	public void j(){
		try {
			Document document = Jsoup.connect("http://naver.com").get();
			
			Elements rank_list = document.select(".ah_l ");
			//System.out.println(rank_list);
			
			Elements list1 = rank_list.eq(1).select("ul li");
			Elements list2 = rank_list.eq(2).select("ul li");

			
			Date date = new Date();
			for(int i=0; i<list1.size(); i++){
				Element ele = list1.get(i);
	
				String url = ele.select("a").attr("href");
				String keyword = ele.select(".ah_k").text();
				System.out.println((i+1)+", "+url+", "+keyword);
				Realtime rt = new Realtime(keyword, url,i+1, date);
				realtimeDao.insert_naver(rt);
			}
			
			for(int j=0; j<list2.size(); j++){
				Element ele = list2.get(j);

				String url = ele.select("a").attr("href");
				String keyword = ele.select(".ah_k").text();
				System.out.println((j+11)+", "+url+", "+keyword);
				Realtime rt = new Realtime(keyword, url, j+11, date);
				realtimeDao.insert_naver(rt);
			}
			
			
			
			System.out.println(" : naver insert 완료.");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//naver test
	
	@RequestMapping("/daum")
	public void daumCrawling(){
		Document document = null;
		Date date = new Date();  
		try {
			document = Jsoup.connect("http://www.daum.net/").get();
			Elements realtimeword = document.select(".rank_dummy");
			for(int i=0; i<realtimeword.size(); i++){
				Element ele = realtimeword.get(i);
			
				String str = ele.select(".rank_dummy").text();
				String rank = ele.select(".rank_issue").text();
				String url = ele.select(".rank_dummy a").attr("href");
				String keyword = ele.select(".txt_issue").text();
				String increaseStr = ele.select(".num_issue").text();
				int degree = 0;
				
//				if(!increaseStr.equals("신규진입")){
//					String[] strArr = increaseStr.split(" ");
//					increaseStr = strArr[1].substring(0,2);
//					degree = Integer.parseInt(strArr[1].substring(2));
//				}
//				Realtime rt = new Realtime(keywoard, url, Integer.parseInt(rank.replace("위", "")), new Date().toLocaleString(), "다음", increaseStr, degree);
//				realTimeDao.create(rt);
				int row = realtimeDao.insert_daum(new Realtime(keyword, url, (i+1), date));
				//System.out.println("insert : "+row+"    "+str+", "+(i+1)+", "+keyword+", "+url);
				
				
			}//for
			
			System.out.println(new Timestamp(date.getTime())+" : daum insert 완료.");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//daumCrawling
	
	
	@RequestMapping("/zum")
	public void zum(){
		try {
			Document doc = Jsoup.connect("http://zum.com").get();
			//System.out.println(doc);
			Elements elements = doc.select(".d_rank_keyword ul");
			Date date = new Date();
			
			for(int i=0; i<elements.size(); i++){	
				Element ele = elements.get(i);
				
				Elements childrenEles = ele.children();
				for(int j=0; j<childrenEles.size(); j++){
					
					System.out.println("ele : "+childrenEles.get(j)+", "+j);
					
					String rank = childrenEles.get(j).select(".r_num").text();
					String keyword = childrenEles.get(j).child(1).text();
					String url = childrenEles.get(j).child(1).attr("href");
//					System.out.println("rank : "+rank);
//					System.out.println("keyword : "+keyword);
//					System.out.println("url : "+url);
					int row = realtimeDao.insert_zum(new Realtime(keyword, url, Integer.parseInt(rank), date));
					System.out.println("insert 확인 : "+row);
				}
			}//for
			
			System.out.println(new Timestamp(date.getTime())+" : zum insert 완료.");			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
