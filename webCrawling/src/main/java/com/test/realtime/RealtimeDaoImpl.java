package com.test.realtime;

import java.util.ArrayList;
import java.util.HashMap;

import com.test.mapper.RealtimeMapper;

public class RealtimeDaoImpl implements RealtimeDao{

	RealtimeMapper realtimeMapper;

	public void setRealtimeMapper(RealtimeMapper realtimeMapper) {
		this.realtimeMapper = realtimeMapper;
	}

	@Override
	public int insert_naver(Realtime realtime) {
		// TODO Auto-generated method stub
		return realtimeMapper.insert_naver(realtime);
	}

	@Override
	public ArrayList<Realtime> select_recent_data() {
		// TODO Auto-generated method stub
		return realtimeMapper.select_recent();
	}

	@Override
	public int insert_daum(Realtime realtime) {
		// TODO Auto-generated method stub
		return realtimeMapper.insert_daum(realtime);
	}

	@Override
	public int insert_zum(Realtime realtime) {
		// TODO Auto-generated method stub
		return realtimeMapper.insert_zum(realtime);
	}

	@Override
	public ArrayList<Realtime> select_naver_last() {
		// TODO Auto-generated method stub
		return realtimeMapper.select_naver_last();
	}

	@Override
	public ArrayList<Realtime> select_daum_last() {
		// TODO Auto-generated method stub
		return realtimeMapper.select_daum_last();
	}

	@Override
	public ArrayList<Realtime> select_zum_last() {
		// TODO Auto-generated method stub
		return realtimeMapper.select_zum_last();
	}

	@Override
	public ArrayList<HashMap<String, String>> select_keyword_count(HashMap<String, String> hashmap) {
		// TODO Auto-generated method stub
		return realtimeMapper.select_keyword_count(hashmap);
	}

	@Override
	public ArrayList<Realtime> select_line_chart_data(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return realtimeMapper.select_line_chart_data(paramMap);
	}

	@Override
	public ArrayList<Realtime> select_by_keyword(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return realtimeMapper.select_by_keyword(paramMap);
	}

	@Override
	public ArrayList<Realtime> select_r_keyword(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return realtimeMapper.select_r_keyword(paramMap);
	}



}
