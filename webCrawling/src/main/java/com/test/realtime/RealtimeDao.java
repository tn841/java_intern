package com.test.realtime;

import java.util.ArrayList;
import java.util.HashMap;

public interface RealtimeDao {
	public int insert_naver(Realtime realtime);
	public int insert_daum(Realtime realtime);
	public int insert_zum(Realtime realtime);
	public ArrayList<Realtime> select_recent_data();
	public ArrayList<Realtime> select_naver_last();
	public ArrayList<Realtime> select_daum_last();
	public ArrayList<Realtime> select_zum_last();
	
	public ArrayList<HashMap<String, String>> select_keyword_count(HashMap<String, String> hashmap);
	
	public ArrayList<Realtime> select_line_chart_data(HashMap<String, Object> paramMap);
	
	public ArrayList<Realtime> select_by_keyword(HashMap<String, Object> paramMap);
	public ArrayList<Realtime> select_r_keyword(HashMap<String, Object> paramMap);
}
